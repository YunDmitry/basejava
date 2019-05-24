package com.dyun.basejava.storage;

import com.dyun.basejava.exception.NotExistStorageException;
import com.dyun.basejava.model.*;
import com.dyun.basejava.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        this.sqlHelper = new SqlHelper(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume(uuid, full_name)  VALUES (?, ?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            insertContacts(resume, conn);
            insertSections(resume, conn);
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
                ps.setString(1, resume.getUuid());
                ps.execute();
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM section WHERE resume_uuid = ?")) {
                ps.setString(1, resume.getUuid());
                ps.execute();
            }
            insertContacts(resume, conn);
            insertSections(resume, conn);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        Resume resume_result = sqlHelper.execute("" +
                        "     SELECT * " +
                        "       FROM resume r" +
                        "      WHERE r.uuid = ?",
                ps -> {
                    ps.setString(1, uuid);
                    final ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    return new Resume(uuid, rs.getString("full_name"));
                });
        sqlHelper.execute("" +
                        "     SELECT * " +
                        "       FROM contact c" +
                        "      WHERE resume_uuid = ?",
                ps -> {
                    ps.setString(1, uuid);
                    final ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        readContact(rs, resume_result);
                    }
                    return null;
                });
        sqlHelper.execute("" +
                        "     SELECT * " +
                        "       FROM section s" +
                        "      WHERE resume_uuid = ?",
                ps -> {
                    ps.setString(1, uuid);
                    final ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        readSection(rs, resume_result);
                    }
                    return null;
                });
        return resume_result;
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }


    @Override
    public List<Resume> getAllSorted() {
        Map<String, Resume> map = new LinkedHashMap<>();
        sqlHelper.execute("" +
                        "     SELECT * " +
                        "       FROM resume r" +
                        "   ORDER BY r.full_name, r.uuid",
                ps -> {
                    final ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        String uuid = rs.getString("uuid");
                        String fullName = rs.getString("full_name");
                        map.put(uuid, new Resume(uuid, fullName));
                    }
                    return null;
                });
        sqlHelper.execute("" +
                        "     SELECT * " +
                        "       FROM contact c",
                ps -> {
                    final ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        String resume_uuid = rs.getString("resume_uuid");
                        readContact(rs, map.get(resume_uuid));
                    }
                    return null;
                });
        sqlHelper.execute("" +
                        "     SELECT * " +
                        "       FROM section s",
                ps -> {
                    final ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        String resume_uuid = rs.getString("resume_uuid");
                        readSection(rs, map.get(resume_uuid));
                    }
                    return null;
                });
        return new ArrayList<>(map.values());

    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) AS size FROM resume", ps -> {
            final ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("size");
        });
    }

    private void readContact(ResultSet rs, Resume resume) throws SQLException {
        String contactTypeStr = rs.getString("type");
        if (contactTypeStr != null) {
            String value = rs.getString("value");
            ContactType type = ContactType.valueOf(contactTypeStr);
            if (resume != null) {
                resume.setContact(type, value);
            }
        }
    }

    private void readSection(ResultSet rs, Resume resume) throws SQLException {
        String sectionTypeStr = rs.getString("type");
        if (sectionTypeStr != null) {
            String value = rs.getString("value");
            SectionType type = SectionType.valueOf(sectionTypeStr);
            if (resume != null) {
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.setSection(type, new TextSection(value));
                        break;
                    case ACHIVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = Arrays.asList(value.split("\\n"));
                        resume.setSection(type, new ListSection(list));
                        break;
                }
            }
        }
    }

    private void insertContacts(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact(type, value, resume_uuid)  VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                ps.setString(1, entry.getKey().name());
                ps.setString(2, entry.getValue());
                ps.setString(3, resume.getUuid());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertSections(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section(type, value, resume_uuid)  VALUES (?, ?, ?)")) {
            for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {
                final SectionType sectionType = entry.getKey();
                ps.setString(1, sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        ps.setString(2, ((TextSection) entry.getValue()).getDescription());
                        break;
                    case ACHIVEMENT:
                    case QUALIFICATIONS:
                        StringBuilder listValueResult = new StringBuilder();
                        for (String listValue : ((ListSection) entry.getValue()).getList()) {
                            listValueResult.append(listValue).append("\n");
                        }
                        ps.setString(2, listValueResult.toString());
                        break;
                }
                ps.setString(3, resume.getUuid());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}

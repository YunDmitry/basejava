package com.dyun.basejava.storage;

import com.dyun.basejava.exception.NotExistStorageException;
import com.dyun.basejava.model.*;
import com.dyun.basejava.sql.SqlHelper;
import com.dyun.basejava.util.JsonParser;

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
        return sqlHelper.transactionalExecute(conn -> {
            Resume resume;
            try (PreparedStatement ps = conn.prepareStatement("" +
                    "     SELECT * " +
                    "       FROM resume r" +
                    "      WHERE r.uuid = ?")) {
                ps.setString(1, uuid);
                final ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
            }
            try (PreparedStatement ps = conn.prepareStatement("" +
                    "     SELECT * " +
                    "       FROM contact c" +
                    "      WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                final ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    readContact(rs, resume);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("" +
                    "     SELECT * " +
                    "       FROM section s" +
                    "      WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                final ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    readSection(rs, resume);
                }
            }
            return resume;
        });
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
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> map = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("" +
                    "     SELECT * " +
                    "       FROM resume r" +
                    "   ORDER BY r.full_name, r.uuid")) {
                final ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    String fullName = rs.getString("full_name");
                    map.put(uuid, new Resume(uuid, fullName));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("" +
                    "     SELECT * " +
                    "       FROM contact c")) {
                final ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String resume_uuid = rs.getString("resume_uuid");
                    readContact(rs, map.get(resume_uuid));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("" +
                    "     SELECT * " +
                    "       FROM section s")) {
                final ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String resume_uuid = rs.getString("resume_uuid");
                    readSection(rs, map.get(resume_uuid));
                }
            }
            return new ArrayList<>(map.values());
        });
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
        String value = rs.getString("value");
        if (value != null) {
            SectionType type = SectionType.valueOf(rs.getString("type"));
            resume.setSection(type, JsonParser.read(value, Section.class));
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
                ps.setString(1, entry.getKey().name());
                final Section section = entry.getValue();
                ps.setString(2, JsonParser.write(section, Section.class));
                ps.setString(3, resume.getUuid());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}

package com.dyun.basejava.storage;

import com.dyun.basejava.exception.NotExistStorageException;
import com.dyun.basejava.model.ContactType;
import com.dyun.basejava.model.Resume;
import com.dyun.basejava.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
            insertContacts(resume, conn);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("" +
                        "     SELECT * " +
                        "       FROM resume r" +
                        "  LEFT JOIN contact c" +
                        "         ON r.uuid = c.resume_uuid" +
                        "      WHERE r.uuid = ?",
                ps -> {
                    ps.setString(1, uuid);
                    final ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        readContact(rs, resume);
                    } while (rs.next());
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
}

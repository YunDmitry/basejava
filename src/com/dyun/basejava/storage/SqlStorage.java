package com.dyun.basejava.storage;

import com.dyun.basejava.exception.NotExistStorageException;
import com.dyun.basejava.model.Resume;
import com.dyun.basejava.sql.ConnectionFactory;
import com.dyun.basejava.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SqlStorage implements Storage {
    private final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        this.connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("DELETE FROM resume")) {
//            ps.execute();
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
        SqlHelper.Execute(connectionFactory, "DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void save(Resume resume) {
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("INSERT INTO resume(uuid, full_name)  VALUES (?, ?)")) {
//            ps.setString(1, resume.getUuid());
//            ps.setString(2, resume.getFullName());
//            ps.execute();
//        } catch (SQLException e) {
//            if (e.getSQLState().equals("23505")) {
//                throw new ExistStorageException(resume.getUuid());
//            }
//            throw new StorageException(e);
//        }
        SqlHelper.Execute(connectionFactory, "INSERT INTO resume(uuid, full_name)  VALUES (?, ?)", resume.getUuid(), ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
        });
    }

    @Override
    public void update(Resume resume) {
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
//            ps.setString(1, resume.getFullName());
//            ps.setString(2, resume.getUuid());
//            if (ps.executeUpdate() == 0) {
//                throw new NotExistStorageException(resume.getUuid());
//            }
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
        SqlHelper.Execute(connectionFactory, "UPDATE resume SET full_name = ? WHERE uuid = ?", ps -> {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(resume.getUuid());
            }
        });
    }

    @Override
    public Resume get(String uuid) {
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r WHERE r.uuid = ?")) {
//            ps.setString(1, uuid);
//            final ResultSet rs = ps.executeQuery();
//            if (!rs.next()) {
//                throw new NotExistStorageException(uuid);
//            }
//            return new Resume(uuid, rs.getString("full_name"));
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
        return SqlHelper.ExecuteResult(connectionFactory, "SELECT * FROM resume r WHERE r.uuid = ?", ps -> {
            ps.setString(1, uuid);
            final ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            List<Resume> result = new ArrayList<>();
            result.add(new Resume(uuid, rs.getString("full_name")));
            return result;
        }).get(0);
    }

    @Override
    public void delete(String uuid) {
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("DELETE FROM resume WHERE uuid = ?")) {
//            ps.setString(1, uuid);
//            if (ps.executeUpdate() == 0) {
//                throw new NotExistStorageException(uuid);
//            }
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
        SqlHelper.Execute(connectionFactory, "DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
        });
    }

    @Override
    public List<Resume> getAllSorted() {
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid")) {
//            List<Resume> result = new ArrayList<>();
//            final ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                result.add(new Resume(rs.getString("uuid").trim(), rs.getString("full_name")));
//            }
//            return result;
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
        return SqlHelper.ExecuteResult(connectionFactory, "SELECT * FROM resume ORDER BY full_name, uuid", ps -> {
            List<Resume> result = new ArrayList<>();
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Resume(rs.getString("uuid").trim(), rs.getString("full_name")));
            }
            return result;
        });
    }

    @Override
    public int size() {
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) AS size FROM resume")) {
//            final ResultSet rs = ps.executeQuery();
//            rs.next();
//            return rs.getInt("size");
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
        AtomicInteger size = new AtomicInteger();
        SqlHelper.Execute(connectionFactory, "SELECT COUNT(*) AS size FROM resume", ps -> {
            final ResultSet rs = ps.executeQuery();
            rs.next();
            size.set(rs.getInt("size"));
        });
        return size.get();
    }
}

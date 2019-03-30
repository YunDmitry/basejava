package com.dyun.basejava.sql;

import com.dyun.basejava.exception.ExistStorageException;
import com.dyun.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(String dbUrl, String dbUser, String dbPassword) {
        this.connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public void execute(String sqlStatement, ConsumerSqlException action) {
        execute(sqlStatement, null, action);
    }

    public void execute(String sqlStatement, String uuid, ConsumerSqlException action) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlStatement)) {
            action.action(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException(uuid);
            }
            throw new StorageException(e);
        }
    }

    public <T> List<T> executeResult(String sqlStatement, ConsumerResultSqlException<T> action) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlStatement)) {
            return action.action(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public interface ConsumerSqlException {
        void action(PreparedStatement ps) throws SQLException;
    }

    public interface ConsumerResultSqlException<T> {
        List<T> action(PreparedStatement ps) throws SQLException;
    }
}

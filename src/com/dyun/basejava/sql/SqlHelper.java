package com.dyun.basejava.sql;

import com.dyun.basejava.exception.ExistStorageException;
import com.dyun.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(String dbUrl, String dbUser, String dbPassword) {
        this.connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public  <T> T execute(String sqlStatement, ConsumerSqlException<T> action) {
        return execute(sqlStatement, null, action);
    }

    public <T> T execute(String sqlStatement, String uuid, ConsumerSqlException<T> action) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlStatement)) {
            return action.action(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException(uuid);
            }
            throw new StorageException(e);
        }
    }

    public interface ConsumerSqlException<T> {
        T action(PreparedStatement ps) throws SQLException;
    }
}

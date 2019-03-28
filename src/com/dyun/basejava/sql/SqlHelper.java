package com.dyun.basejava.sql;

import com.dyun.basejava.exception.ExistStorageException;
import com.dyun.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SqlHelper {
    public static void Execute(ConnectionFactory connectionFactory, String sqlStatement, ConsumerSqlException<PreparedStatement> action) {
        Execute(connectionFactory, sqlStatement, null, action);
    }

    public static void Execute(ConnectionFactory connectionFactory, String sqlStatement, String uuid, ConsumerSqlException<PreparedStatement> action) {
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

    public static <T> List<T> ExecuteResult(ConnectionFactory connectionFactory, String sqlStatement, ConsumerResultSqlException<PreparedStatement, T> action) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlStatement)) {
            return action.action(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public interface ConsumerSqlException<T> {
        void action(T t) throws SQLException;
    }

    public interface ConsumerResultSqlException<PreparedStatement, T> {
        List<T> action(PreparedStatement ps) throws SQLException;
    }
}

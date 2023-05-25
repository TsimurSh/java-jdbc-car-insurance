package demo.config;

import demo.model.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DBConnector {
    String POSTGRES_USER = "postgres";
    String POSTGRES_PSWRD = "postgres";
    String POSTGRES_URL = "jdbc:postgresql://localhost:5432/" + POSTGRES_USER;


    static Connection getConnection() {
        try {
            return DriverManager.getConnection(POSTGRES_URL, POSTGRES_USER, POSTGRES_PSWRD);
        } catch (SQLException e) {
            throw new DatabaseException("Could not open a connection to " + POSTGRES_URL, e);
        }
    }
}

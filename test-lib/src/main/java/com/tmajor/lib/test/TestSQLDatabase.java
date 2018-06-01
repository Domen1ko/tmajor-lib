package com.tmajor.lib.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.junit.Assert.fail;

public class TestSQLDatabase {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;MODE=Oracle;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    private Connection connection;

    public TestSQLDatabase() {
        createConnection();
    }

    private void createConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            fail(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            connection = dbConnection;
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public Connection getConnection() {
        try {
            if (connection != null && connection.isClosed()) {
                createConnection();
            }
        } catch (SQLException e) {
            // silent
        }
        return connection;

    }

    public boolean loadTablesFromProperties(Properties props) {
        return loadTables(props.values().stream().map(String.class::cast).collect(Collectors.toList()));
    }

    public boolean loadTables(List<String> createTable) {
        try {
            for (String table : createTable) {
                try (PreparedStatement stm = connection.prepareStatement(table)) {
                    stm.execute();
                }
            }
            connection.commit();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean loadData(List<String> inserts) {
        return loadTables(inserts);
    }
}

package com.tmajor.lib.test;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Properties;

public class TestSQLDatabaseTest {

    @Test
    public void getConnection() throws SQLException {
        TestSQLDatabase db = new TestSQLDatabase();
        Connection connection = db.getConnection();
        Assert.assertNotNull(connection);
        Assert.assertFalse(connection.isClosed());
        connection.close();
        connection = db.getConnection();
        Assert.assertNotNull(connection);
        Assert.assertFalse(connection.isClosed());
    }

    @Test
    public void loadTablesFromProperties() {

        Properties properties = new Properties();

        try (InputStream is = getClass().getResourceAsStream("/dbtest.properties")) {
            if (is == null) {
                throw new IOException("File Not Found");
            }
            properties.load(is);
        } catch (IOException e) {
            Assert.fail("no properties found");
        }
        TestSQLDatabase db = new TestSQLDatabase();
        boolean loaded = db.loadTablesFromProperties(properties);
        Assert.assertTrue(loaded);
        testTableCreated(db);

    }

    @Test
    public void loadTables() {
        TestSQLDatabase db = new TestSQLDatabase();
        boolean loaded = db.loadTables(Collections.singletonList("CREATE TABLE CQS_POSITION_DECISION\n" +
                "  (\n" +
                "    POSITION_ID          VARCHAR2 (20 CHAR) ,\n" +
                "    CATEGORY_RISK        VARCHAR2 (1 CHAR) ,\n" +
                "    SIGNATURE_LEVEL      NUMBER (6) ,\n" +
                "    APPLICATION_FUNCTION VARCHAR2 (255 CHAR) ,\n" +
                "    DECISION_OUTCOME     VARCHAR2 (2 CHAR) ,\n" +
                "    DECISION_USER        VARCHAR2 (32 CHAR) ,\n" +
                "    DECISION_AT          TIMESTAMP ,\n" +
                "    DELETED_AT           TIMESTAMP \n" +
                "  ) ;\n"));
        Assert.assertTrue(loaded);
        testTableCreated(db);
    }

    private void testTableCreated(TestSQLDatabase db) {
        try (Connection connection = db.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM CQS_POSITION_DECISION"); ResultSet set = statement.executeQuery()) {
            Assert.assertNotNull(set);
            Assert.assertFalse(set.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
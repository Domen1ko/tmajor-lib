package com.tmajot.lib.properties;

import com.tmajor.lib.test.TestSQLDatabase;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;

public class TMajorPropertyTest {

    @Test
    public void getInstance() {
        // load from file
        String value = TMajorProperty.getInstance().get("test");
        Assert.assertTrue(value.equalsIgnoreCase("esito"));
    }

    @Test
    public void getInstancePropertyLoadSystem() {
        URL resource = TMajorPropertyTest.class.getResource("/application.properties");
        String file = resource.getFile();
        System.setProperty("tmajor.properties.location", file);
        // load from file
        String value = TMajorProperty.getInstance().get("test");
        Assert.assertTrue(value.equalsIgnoreCase("esito"));
    }

    @Test
    public void loadFromDbSQL() throws SQLException {
        TestSQLDatabase db = new TestSQLDatabase();
        boolean loaded = db.loadTables(Collections.singletonList(" create table PROPERTY\n" +
                "(\n" +
                "\tPROPERTY_COD VARCHAR2(64 char) not null,\n" +
                "\tPROPERTY_VALUE VARCHAR2(255) not null,\n" +
                "\tDESCRIPTION VARCHAR2(255),\n" +
                "\tCACHEABLE VARCHAR2(1) default 'N' not null,\n" +
                "\tENCRYPTED VARCHAR2(1) default 'N' not null,\n" +
                "\tDELETION_FLAG VARCHAR2(1)\n" +
                ")"));
        Assume.assumeTrue(loaded);
        loaded = db.loadData(Collections.singletonList("INSERT INTO PROPERTY (PROPERTY_COD, PROPERTY_VALUE, DESCRIPTION, CACHEABLE, ENCRYPTED, DELETION_FLAG) VALUES ('test', 'no-esito', 'prova', 'Y', 'N', 'N');"));
        Assume.assumeTrue(loaded);
        String testB4reload = TMajorProperty.getInstance().get("test");
        Assert.assertNotNull(testB4reload);
        TMajorProperty.getInstance().loadFromDbSQL(db.getConnection());
        String testAfterRelaod = TMajorProperty.getInstance().get("test");
        Assert.assertNotNull(testAfterRelaod);
        Assert.assertFalse(testB4reload.equalsIgnoreCase(testAfterRelaod));
        db.getConnection().close();
    }

    @Test
    public void get() {
        String not_found = TMajorProperty.getInstance().get("not_found");
        Assert.assertNull(not_found);
        String test = TMajorProperty.getInstance().get("test");
        Assert.assertNotNull(test);
    }

    @Test
    public void getWithParameters() {
        String nnt = TMajorProperty.getInstance().get("not_found", "found", false);
        Assert.assertNotNull(nnt);
        Assert.assertTrue("found".equalsIgnoreCase(nnt));
        nnt = TMajorProperty.getInstance().get("not_found", "found");
        Assert.assertNotNull(nnt);
        Assert.assertTrue("found".equalsIgnoreCase(nnt));
    }

    @Test
    public void loadAdditionalProperties() {
        String aNew = TMajorProperty.getInstance().get("new");
        Assert.assertNull(aNew);
        TMajorProperty.getInstance().loadAdditionalProperties(Collections.singletonMap("new","got it"));
        aNew = TMajorProperty.getInstance().get("new");
        Assert.assertNotNull(aNew);
        Assert.assertTrue("got it".equalsIgnoreCase(aNew));
    }
}
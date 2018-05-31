package com.tmajor.lib.log.logger;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;
import java.util.logging.Level;

public class TMajorLoggerTest {

    public static final String APP = "TEST";
    public static final TMajorLogger logger = TMajorLogger.getLogger(APP);

    @Test
    public void uuidTrace() {
        String uuidToCheck = UUID.randomUUID().toString();
        String single_message = logger.uuidTrace(uuidToCheck, APP, "t1", "b1", "Single Message");
        Assert.assertTrue(single_message.contains(uuidToCheck));
    }

    @Test
    public void uuidDebug() {
        String uuidToCheck = UUID.randomUUID().toString();
        String single_message = logger.uuidDebug(uuidToCheck, APP, "t1", "b1", "Single Message");
        Assert.assertTrue(single_message.contains(uuidToCheck));
    }

    @Test
    public void uuidInfo() {
        String uuidToCheck = UUID.randomUUID().toString();
        String single_message = logger.uuidInfo(uuidToCheck, APP, "t1", "b1", "Single Message");
        Assert.assertTrue(single_message.contains(uuidToCheck));
    }

    @Test
    public void uuidError() {
        String uuidToCheck = UUID.randomUUID().toString();
        String single_message = logger.uuidError(uuidToCheck, APP, "t1", "b1", "Single Message");
        Assert.assertTrue(single_message.contains(uuidToCheck));
    }

    @Test
    public void log() {
        String uuidToCheck = UUID.randomUUID().toString();
        String log = logger.log(Level.SEVERE, uuidToCheck, APP, "t1", "b1", "Single Message");
        Assert.assertTrue(log.contains(uuidToCheck));
    }

    @Test
    public void log1() {
        String uuidToCheck = UUID.randomUUID().toString();
        Exception e = new Exception("test");
        String log = logger.log(Level.SEVERE, uuidToCheck, APP, "t1", "b1", "Single Message", e);
        Assert.assertTrue(log.contains(uuidToCheck));
    }
}
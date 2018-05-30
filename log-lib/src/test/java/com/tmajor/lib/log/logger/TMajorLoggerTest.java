package com.tmajor.lib.log.logger;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class TMajorLoggerTest {

    public static final String APP = "TEST";
    public static final TMajorLogger logger = TMajorLogger.getLogger(APP);

    @Test
    public void uuidTrace() {
        String uuidToCheck = UUID.randomUUID().toString();
        String single_message = logger.uuidTrace(uuidToCheck,APP, "t1", "b1", "Single Message");
        Assert.assertTrue(single_message.contains(uuidToCheck));
    }

    @Test
    public void uuidDebug() {
    }

    @Test
    public void uuidInfo() {
    }

    @Test
    public void uuidError() {
    }

    @Test
    public void log() {
    }

    @Test
    public void log1() {
    }
}
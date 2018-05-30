package com.tmajor.lib.log.pattern;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class LogPatternTest {

    @Test
    public void applyPattern() {
        UUID uui = UUID.randomUUID();
        String result = LogPattern.applyPattern(uui.toString(), "TEST", "111", "be3", "Test message", "param 1", "param 2");
        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains(uui.toString()));
    }

    @Test
    public void applyPatternWithoutParams() {
        UUID uui = UUID.randomUUID();
        String result = LogPattern.applyPattern(uui.toString(), "TEST", "111", "be3", "Test message");
        Assert.assertNotNull(result);
        Assert.assertFalse(result.contains(LogTags.PARAMS));

    }
}
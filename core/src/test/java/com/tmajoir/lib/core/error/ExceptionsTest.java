package com.tmajoir.lib.core.error;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ExceptionsTest {

    @Test
    public void stackTrace() {

        Exception test = new Exception("test");
        String stck = Exceptions.stackTrace(test);
        Assert.assertNotNull(stck);
    }

}
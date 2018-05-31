package com.tmajor.lib.string;

import org.junit.Assert;
import org.junit.Test;

public class StringExtractorTest {

    @Test
    public void extract() {
        // found
        String phras = "this is simple \"phrase\"";
        String simple_ = StringExtractor.extract(phras, "simple", "\"", " ");
        Assert.assertNotNull(simple_);
        Assert.assertEquals("phrase", simple_);
    }

    @Test
    public void extractFail() {
        //not found
        String phras = "this is \"phrase\"";
        String simple_ = StringExtractor.extract(phras, "simple", "\"", " ");
        Assert.assertNull(simple_);
    }
}
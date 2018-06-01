package com.tmajor.lib.jaxrs.provider.exception;

import com.tmajoir.lib.core.model.MessageResponse;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;

public class RuntimeErrorMapperProviderTest {

    @Test
    public void toResponse() {
        RuntimeErrorMapperProvider provider = new RuntimeErrorMapperProvider();
        try {
            throw new NullPointerException("Test");
        } catch (RuntimeException e) {
            Response response = provider.toResponse(e);
            Assert.assertEquals(response.getStatus(), 500);
            Assert.assertTrue(response.hasEntity());
            Object entity = response.getEntity();
            Assert.assertTrue(entity instanceof MessageResponse);
        }
    }

}
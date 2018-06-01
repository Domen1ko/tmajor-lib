package com.tmajor.lib.jaxrs.provider.exception;

import com.tmajoir.lib.core.error.TMajorException;
import com.tmajoir.lib.core.model.MessageResponse;
import com.tmajoir.lib.core.registry.ErrorRegistry;
import com.tmajor.lib.log.logger.TMajorLogger;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class TMajorErrorMapperProviderTest {

    @Test
    public void toResponse() {
        TMajorLogger logger = TMajorLogger.getLogger("T");
        TMajorErrorMapperProvider provider = new TMajorErrorMapperProvider();
        Response response = provider.toResponse(new TMajorException(ErrorRegistry.GENERIC, logger.error("T", "t1", "b1", "Test Message")));
        Assert.assertEquals(response.getStatus(), 500);
        Assert.assertTrue(response.hasEntity());
        Object entity = response.getEntity();
        Assert.assertTrue(entity instanceof MessageResponse);
    }
}
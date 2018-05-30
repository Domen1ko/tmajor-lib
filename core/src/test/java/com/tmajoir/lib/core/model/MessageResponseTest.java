package com.tmajoir.lib.core.model;

import com.tmajoir.lib.core.registry.ErrorRegistry;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageResponseTest {

    @Test
    public void messageBuilder() {
        MessageResponse respose = new MessageResponse();
        respose.addMessage(new Message());
        assertFalse(respose.getMessages().isEmpty());
        assertTrue(respose.getError());
    }

    @Test
    public void messageBuilderWithError() {
        MessageResponse respose = new MessageResponse();
        respose.addMessage(new Message(" Error ", ErrorRegistry.GENERIC, Message.LEVEL.ERROR, "as"));
        assertFalse(respose.getMessages().isEmpty());
        assertTrue(respose.getError());
    }

    @Test
    public void messageBuilderWithWarning() {
        MessageResponse respose = new MessageResponse();
        respose.addMessage(new Message(" Warning ", ErrorRegistry.GENERIC, Message.LEVEL.WARNING, "as"));
        assertFalse(respose.getMessages().isEmpty());
        assertFalse(respose.getError());
    }

}
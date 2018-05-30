package com.tmajoir.lib.core.model;

import java.util.SortedSet;
import java.util.TreeSet;

public class MessageResponse {

    private SortedSet<Message> messages;
    private boolean isError;


    public SortedSet<Message> getMessages() {
        if (messages == null) {
            messages = new TreeSet<>();
        }
        return messages;
    }

    public void setMessages(SortedSet<Message> messages) {
        this.messages = messages;
    }

    public boolean isError() {
        Message first = getMessages().first();
        if (first == null) {
            return false;
        } else {
            return first.getLevel() == Message.LEVEL.ERROR.getLevelInteger();
        }
    }

    public void setError(boolean error) {
        isError = error;
    }

    public void addMessage(Message message) {
        getMessages().add(message);
    }
}

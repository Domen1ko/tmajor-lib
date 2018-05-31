package com.tmajoir.lib.core.model;

import java.util.SortedSet;
import java.util.TreeSet;

public class MessageResponse {

    private SortedSet<Message> messages;


    /**
     * @return all messages
     */
    public SortedSet<Message> getMessages() {
        if (messages == null) {
            messages = new TreeSet<>();
        }
        return messages;
    }

    public void setMessages(SortedSet<Message> messages) {
        this.messages = messages;
    }

    public boolean getError() {
        Message first = getMessages().first();
        if (first == null) {
            return false;
        } else {
            return first.getLevel() == Message.LEVEL.ERROR.getLevelInteger();
        }
    }

    public void addMessage(Message message) {
        getMessages().add(message);
    }
}

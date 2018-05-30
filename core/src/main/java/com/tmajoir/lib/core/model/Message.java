package com.tmajoir.lib.core.model;

import com.tmajoir.lib.core.registry.ErrorRegistry;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class Message implements Comparable<Message> {

    private String message, uuid;
    @NotNull
    private String code;
    private List<String> params;
    private int level;

    public Message() {
        new Message("Unexpected generic error occurred", ErrorRegistry.GENERIC, LEVEL.ERROR, null);
    }

    public Message(String message, String code, LEVEL level, String uuid) {
        this.message = message;
        this.code = code;
        this.level = level.getLevelInteger();
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return getLevel() == message1.getLevel() &&
                Objects.equals(getCode(), message1.getCode());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCode(), getLevel());
    }

    @Override
    public int compareTo(Message o) {
        if (o != null) {
            if (this.equals(o)) {
                return 0;
            } else {
                return Integer.compare(this.level, o.level);
            }
        }
        return 0;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public enum LEVEL {
        ERROR(0),
        WARNING(1),
        INFORMATION(2),
        SUCCESS(3);

        private final int levelInteger;

        LEVEL(int i) {
            levelInteger = i;
        }

        public int getLevelInteger() {
            return levelInteger;
        }
    }
}

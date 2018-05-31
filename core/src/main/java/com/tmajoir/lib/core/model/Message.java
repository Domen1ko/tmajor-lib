package com.tmajoir.lib.core.model;

import com.tmajoir.lib.core.registry.ErrorRegistry;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class Message implements Comparable<Message> {

    private String msg;
    private String uuid;
    @NotNull
    private String code;
    private List<String> params;
    private int level;

    public Message() {
        this.code = ErrorRegistry.GENERIC;
        this.msg = "Unexpected generic error occurred";
        this.level = LEVEL.ERROR.getLevelInteger();
    }

    public Message(String msg, String code, LEVEL level, String uuid) {
        this.msg = msg;
        this.code = code;
        this.level = level.getLevelInteger();
        this.uuid = uuid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

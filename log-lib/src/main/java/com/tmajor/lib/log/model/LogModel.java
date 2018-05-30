package com.tmajor.lib.log.model;

import com.tmajor.lib.log.pattern.LogPattern;

import java.util.List;
import java.util.Objects;

public class LogModel {

    private String uuid, app, technicalId, businessId, message;
    private List params;

    private LogModel(String uuid, String app, String technicalId, String businessId, String message, List params) {
        this.uuid = uuid;
        this.app = app;
        this.technicalId = technicalId;
        this.businessId = businessId;
        this.message = message;
        this.params = params;
    }

    public String getLogMessage() {
        return LogPattern.applyPattern(uuid, app, technicalId, businessId, message, params);
    }

    public String getUuid() {
        return uuid;
    }

    public String getApp() {
        return app;
    }

    public String getTechnicalId() {
        return technicalId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public String getMessage() {
        return message;
    }

    public List getParams() {
        return params;
    }

    public static class LogModelBuilder {
        String uuid, app, technicalId, businessId, message;
        List params;

        public LogModelBuilder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public LogModelBuilder setApp(String app) {
            this.app = app;
            return this;
        }

        public LogModelBuilder setTechnicalId(String technicalId) {
            this.technicalId = technicalId;
            return this;
        }

        public LogModelBuilder setBusinessId(String businessId) {
            this.businessId = businessId;
            return this;
        }

        public LogModelBuilder setMessage(String message) {
            this.message = message;
            return this;
        }


        public LogModelBuilder setParams(List params) {
            this.params = params;
            return this;
        }

        public LogModel buid() {
            return new LogModel(uuid, app, technicalId, businessId, message, params);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogModel logModel = (LogModel) o;
        return Objects.equals(getUuid(), logModel.getUuid()) &&
                Objects.equals(getApp(), logModel.getApp()) &&
                Objects.equals(getTechnicalId(), logModel.getTechnicalId()) &&
                Objects.equals(getBusinessId(), logModel.getBusinessId()) &&
                Objects.equals(getMessage(), logModel.getMessage());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUuid(), getApp(), getTechnicalId(), getBusinessId(), getMessage());
    }
}

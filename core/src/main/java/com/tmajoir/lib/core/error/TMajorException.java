package com.tmajoir.lib.core.error;

import com.tmajor.lib.log.model.LogModel;
import com.tmajor.lib.log.pattern.LogMessageExtractor;
import com.tmajor.lib.log.pattern.LogPattern;

import javax.validation.constraints.NotNull;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TMajorException extends Exception {


    @NotNull
    private String uuid, devMessage, code, app;
    private String technicalId, businessId;


    public TMajorException(@NotNull String code, @NotNull String logMessage) {
        super(logMessage);
        LogModel logModel = LogMessageExtractor.extractModel(logMessage);
        this.uuid = logModel.getUuid();
        this.devMessage = logModel.getMessage();
        this.code = code;
        this.app = logModel.getApp();
        this.technicalId = logModel.getTechnicalId();
        this.businessId = logModel.getBusinessId();
    }


    /**
     * Base Exception model
     *
     * @param technicalId - technical id useful for dev team
     * @param businessId  - business id useful for business team
     * @param app         - application
     * @param code        - code of error
     * @param devMessage  - custom dev log message of the problem
     * @param cause       - Exception
     */
    public TMajorException(String technicalId, String businessId, @NotNull String app, @NotNull String code, @NotNull String devMessage, Throwable cause) {
        this(UUID.randomUUID().toString(), technicalId, businessId, app, code, devMessage, cause);
        Logger.getLogger(this.app).log(Level.SEVERE, LogPattern.applyPattern(this.uuid, this.app, this.technicalId, this.businessId, this.devMessage, cause), cause);
    }

    /**
     * Base Exception model
     *
     * @param uuid        - unique id of exception/log
     * @param technicalId - technical id useful for dev team
     * @param businessId  - business id useful for business team
     * @param app         - application
     * @param code        - code of error
     * @param devMessage  - custom dev log message of the problem
     * @param cause       - Exception
     */
    public TMajorException(@NotNull String uuid, String technicalId, String businessId, @NotNull String app, @NotNull String code, @NotNull String devMessage, Throwable cause) {
        super(devMessage, cause);
        this.uuid = uuid;
        this.devMessage = devMessage;
        this.code = code;
        this.app = app;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getTechnicalId() {
        return technicalId;
    }

    public void setTechnicalId(String technicalId) {
        this.technicalId = technicalId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
}

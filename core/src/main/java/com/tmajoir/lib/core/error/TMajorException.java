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
    private final String uuid;
    @NotNull
    private final String devMessage;
    @NotNull
    private final String code;
    @NotNull
    private final String app;
    private final String businessId;
    private final String technicalId;

    /**
     * Create an exception strating from log message and code
     *
     * @param code       - error code
     * @param logMessage - log message produced by LogPattern
     */
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
     * Exception delegate log to exception
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
        this.technicalId = technicalId;
        this.businessId = businessId;
    }

    public String getUuid() {
        return uuid;
    }


    public String getDevMessage() {
        return devMessage;
    }


    public String getCode() {
        return code;
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

}

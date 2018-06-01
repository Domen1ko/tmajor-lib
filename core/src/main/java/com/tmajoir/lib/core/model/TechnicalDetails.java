package com.tmajoir.lib.core.model;

import com.tmajoir.lib.core.error.Exceptions;
import com.tmajoir.lib.core.error.TMajorException;
import com.tmajot.lib.date.DateUtility;

import java.io.Serializable;

public class TechnicalDetails implements Serializable {


    private String technicalId;
    private String businessId;
    private String uuid;
    private String eventAt;
    private String stackTrace;

    public static TechnicalDetails build(TMajorException e) {

        TechnicalDetails tech = new TechnicalDetails();
        tech.setBusinessId(e.getBusinessId());
        tech.setUuid(e.getUuid());
        tech.setTechnicalId(e.getUuid());
        tech.setEventAt(DateUtility.now());
        tech.setStackTrace(Exceptions.stackTrace(e));
        return tech;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEventAt() {
        return eventAt;
    }

    public void setEventAt(String eventAt) {
        this.eventAt = eventAt;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}

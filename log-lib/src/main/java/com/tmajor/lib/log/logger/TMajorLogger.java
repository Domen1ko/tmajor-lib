package com.tmajor.lib.log.logger;

import javax.validation.constraints.NotNull;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TMajorLogger {

    private static TMajorLogger instance = null;
    private final String app;

    private Logger log;

    private TMajorLogger(@NotNull String app) {
        log = Logger.getLogger(app);
        this.app = app;
    }

    /**
     * Retrieve standard logger. It's a singleton
     *
     * @param app - Application
     * @return Logger instance
     */
    public static TMajorLogger getLogger(@NotNull String app) {
        if (instance == null) {
            instance = new TMajorLogger(app);
        }
        return instance;
    }

    private String log(Level lvl, String uuid, String app, String technicalId, String businessId, String message, Object... params) {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }

        return uuid;
    }


    private String log(Level lvl, String uuid, String app, String technicalId, String businessId, String message, Throwable cause, Object... params) {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }

        return uuid;
    }
}

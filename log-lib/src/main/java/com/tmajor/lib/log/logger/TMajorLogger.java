package com.tmajor.lib.log.logger;

import com.tmajor.lib.log.model.LogModel;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
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

    private String log(Level lvl, LogModel model) {
        log.log(lvl, model.getLogMessage());
        return model.getLogMessage();
    }

    private String log(Level lvl, LogModel model, Throwable cause) {
        log.log(lvl, model.getLogMessage(), cause);
        return model.getLogMessage();
    }

    private String logUuid(Level lvl, String uuid, String app, String technicalId, String businessId, String message, Object... params) {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
        log(lvl, new LogModel.LogModelBuilder()
                .setMessage(message)
                .setTechnicalId(technicalId)
                .setBusinessId(businessId)
                .setApp(app)
                .setUuid(uuid)
                .setParams(Arrays.asList(params))
                .buid());
        return uuid;
    }


    private String logUuid(Level lvl, String uuid, String app, String technicalId, String businessId, String message, Throwable cause, Object... params) {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
        log(lvl, new LogModel.LogModelBuilder()
                        .setMessage(message)
                        .setTechnicalId(technicalId)
                        .setBusinessId(businessId)
                        .setApp(app)
                        .setUuid(uuid)
                        .setParams(Arrays.asList(params))
                        .buid(),
                cause);
        return uuid;
    }




    public String uuidTrace(String uuid, String app, String technicalId, String businessId, String message, Object... params) {
        return logUuid(Level.FINEST, uuid, app, technicalId, businessId, message, params);
    }

    public String uuidTrace(String uuid, String app, String technicalId, String businessId, String message, Throwable cause, Object... params) {
        return logUuid(Level.FINEST, uuid, app, technicalId, businessId, message, cause, params);
    }


    public String uuidDebug(String uuid, String app, String technicalId, String businessId, String message, Object... params) {
        return logUuid(Level.WARNING, uuid, app, technicalId, businessId, message, params);
    }

    public String uuidDebug(String uuid, String app, String technicalId, String businessId, String message, Throwable cause, Object... params) {
        return logUuid(Level.WARNING, uuid, app, technicalId, businessId, message, cause, params);
    }


    public String uuidInfo(String uuid, String app, String technicalId, String businessId, String message, Object... params) {
        return logUuid(Level.INFO, uuid, app, technicalId, businessId, message, params);
    }

    public String uuidInfo(String uuid, String app, String technicalId, String businessId, String message, Throwable cause, Object... params) {
        return logUuid(Level.INFO, uuid, app, technicalId, businessId, message, cause, params);
    }


    public String uuidError(String uuid, String app, String technicalId, String businessId, String message, Object... params) {
        return logUuid(Level.SEVERE, uuid, app, technicalId, businessId, message, params);
    }

    public String uuidError(String uuid, String app, String technicalId, String businessId, String message, Throwable cause, Object... params) {
        return logUuid(Level.SEVERE, uuid, app, technicalId, businessId, message, cause, params);
    }

    /**
     * Log trace level ( FINEST - 300 )
     *
     * @param app         - application
     * @param technicalId - technical id useful for dev team
     * @param businessId  - business id  useful for business team
     * @param message     - Message (dev)
     * @param params      - paramters
     * @return String builded for logging purpose
     */
    public String trace(String app, String technicalId, String businessId, String message, Object... params) {
        return trace(null, app, technicalId, businessId, message, params);
    }

    /**
     * Log trace level ( FINEST - 300 )
     *
     * @param uuid        - unique identifier of log
     * @param app         - application
     * @param technicalId - technical id useful for dev team
     * @param businessId  - business id  useful for business team
     * @param message     - Message (dev)
     * @param params      - paramters
     * @return String builded for logging purpose
     */
    public String trace(String uuid, String app, String technicalId, String businessId, String message, Object... params) {
        return log(Level.FINEST, uuid, app, technicalId, businessId, message, params);
    }

    /**
     * Log debug level ( FINE - 400 )
     *
     * @param app         - application
     * @param technicalId - technical id useful for dev team
     * @param businessId  - business id  useful for business team
     * @param message     - Message (dev)
     * @param params      - paramters
     * @return String builded for logging purpose
     */
    public String debug(String app, String technicalId, String businessId, String message, Object... params) {
        return debug(null, app, technicalId, businessId, message, params);
    }

    /**
     * Log debug level ( FINE - 400 )
     *
     * @param uuid        - unique identifier of log
     * @param app         - application
     * @param technicalId - technical id useful for dev team
     * @param businessId  - business id  useful for business team
     * @param message     - Message (dev)
     * @param params      - paramters
     * @return String builded for logging purpose
     */
    public String debug(String uuid, String app, String technicalId, String businessId, String message, Object... params) {
        return log(Level.FINE, uuid, app, technicalId, businessId, message, params);
    }

    /**
     * Log information level ( INFO - 800 )
     *
     * @param app         - application
     * @param technicalId - technical id useful for dev team
     * @param businessId  - business id  useful for business team
     * @param message     - Message (dev)
     * @param params      - paramters
     * @return String builded for logging purpose
     */
    public String info(String app, String technicalId, String businessId, String message, Object... params) {
        return info(null, app, technicalId, businessId, message, params);
    }

    /**
     * Log information level ( INFO - 800 )
     *
     * @param uuid        - unique identifier of log
     * @param app         - application
     * @param technicalId - technical id useful for dev team
     * @param businessId  - business id  useful for business team
     * @param message     - Message (dev)
     * @param params      - paramters
     * @return String builded for logging purpose
     */
    public String info(String uuid, String app, String technicalId, String businessId, String message, Object... params) {
        return log(Level.INFO, uuid, app, technicalId, businessId, message, params);
    }

    /**
     * Log error level ( SEVERE - 1000 )
     *
     * @param app         - application
     * @param technicalId - technical id useful for dev team
     * @param businessId  - business id  useful for business team
     * @param message     - Message (dev)
     * @param params      - paramters
     * @return String builded for logging purpose
     */
    public String error(String app, String technicalId, String businessId, String message, Object... params) {
        return error(null, app, technicalId, businessId, message, params);
    }

    /**
     * Log error level ( SEVERE - 1000 )
     *
     * @param uuid        - unique identifier of log
     * @param app         - application
     * @param technicalId - technical id useful for dev team
     * @param businessId  - business id  useful for business team
     * @param message     - Message (dev)
     * @param params      - paramters
     * @return String builded for logging purpose
     */
    public String error(String uuid, String app, String technicalId, String businessId, String message, Object... params) {
        return log(Level.SEVERE, uuid, app, technicalId, businessId, message, params);
    }

    /**
     * Log
     *
     * @param lvl         - Level
     * @param uuid        - unique identifier of log
     * @param app         - application
     * @param technicalId - technical id useful for dev team
     * @param businessId  - business id  useful for business team
     * @param message     - Message (dev)
     * @param params      - paramters
     * @return String builded for logging purpose
     */
    public String log(Level lvl, String uuid, String app, String technicalId, String businessId, String message, Object... params) {
        if (uuid != null) {
            uuid = UUID.randomUUID().toString();
        }
        return log(lvl, new LogModel.LogModelBuilder()
                .setMessage(message)
                .setTechnicalId(technicalId)
                .setBusinessId(businessId)
                .setApp(app)
                .setUuid(uuid)
                .setParams(Arrays.asList(params))
                .buid());
    }

    /**
     * Log warning level ( WARNING - 800 )
     *
     * @param app         - application
     * @param technicalId - technical id useful for dev team
     * @param businessId  - business id  useful for business team
     * @param message     - Message (dev)
     * @param cause       - Exception to log
     * @param params      - paramters
     * @return String builded for logging purpose
     */
    public String warning(String app, String technicalId, String businessId, String message, Throwable cause, Object... params) {
        return warning(null, app, technicalId, businessId, message, cause, params);
    }

    /**
     * Log warning level ( WARNING - 800 )
     *
     * @param uuid        - unique identifier of log
     * @param app         - application
     * @param technicalId - technical id useful for dev team
     * @param businessId  - business id  useful for business team
     * @param message     - Message (dev)
     * @param cause       - Exception to log
     * @param params      - paramters
     * @return String builded for logging purpose
     */
    public String warning(String uuid, String app, String technicalId, String businessId, String message, Throwable cause, Object... params) {
        return log(Level.WARNING, uuid, app, technicalId, businessId, message, cause, params);
    }

    /**
     * Log error level ( SEVERE - 1000 )
     *
     * @param app         - application
     * @param technicalId - technical id useful for dev team
     * @param businessId  - business id  useful for business team
     * @param message     - Message (dev)
     * @param cause       - Exception to log
     * @param params      - paramters
     * @return String builded for logging purpose
     */
    public String error(String app, String technicalId, String businessId, String message, Throwable cause, Object... params) {
        return error(null, app, technicalId, businessId, message, cause, params);
    }

    /**
     * Log error level ( SEVERE - 1000 )
     *
     * @param uuid        - unique identifier of log
     * @param app         - application
     * @param technicalId - technical id useful for dev team
     * @param businessId  - business id  useful for business team
     * @param message     - Message (dev)
     * @param cause       - Exception to log
     * @param params      - paramters
     * @return String builded for logging purpose
     */
    public String error(String uuid, String app, String technicalId, String businessId, String message, Throwable cause, Object... params) {
        return log(Level.SEVERE, uuid, app, technicalId, businessId, message, cause, params);
    }

    /**
     * Log
     *
     * @param lvl         - Level
     * @param uuid        - unique identifier of log
     * @param app         - application
     * @param technicalId - technical id useful for dev team
     * @param businessId  - business id  useful for business team
     * @param message     - Message (dev)
     * @param params      - paramters
     * @param cause       - Exception to log
     * @return String builded for logging purpose
     */
    public String log(Level lvl, String uuid, String app, String technicalId, String businessId, String message, Throwable cause, Object... params) {
        if (uuid != null) {
            uuid = UUID.randomUUID().toString();
        }
        return log(lvl, new LogModel.LogModelBuilder()
                .setMessage(message)
                .setTechnicalId(technicalId)
                .setBusinessId(businessId)
                .setApp(app)
                .setUuid(uuid)
                .setParams(Arrays.asList(params))
                .buid(), cause);
    }


}

package com.tmajot.lib.properties;

import com.tmajor.lib.log.logger.TMajorLogger;

import javax.validation.constraints.NotNull;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TMajorProperty {

    private static final String APP = "TM";
    private static final Properties properties = new Properties();
    private static final TMajorLogger logger = TMajorLogger.getLogger(APP);
    private static TMajorProperty instance;


    private TMajorProperty() {
        logger.debug(APP, null, null, "Loading properties");
        String location = System.getProperty("tmajor.properties.location");
        if (location != null) {
            logger.trace(APP, null, null, "Loading properties from: ".concat(location));
            try (FileInputStream in = new FileInputStream(location)) {
                properties.load(in);
            } catch (IOException e) {
                logger.warning(APP, null, null, "Cannot open file form location: ".concat(location), e);
            }
        }
        if (properties.isEmpty()) {
            try (InputStream is = TMajorProperty.class.getResourceAsStream("/application.properties")) {
                if (is == null) {
                    throw new IOException("File Not Found");
                }
                properties.load(is);
            } catch (IOException e) {
                logger.warning(APP, null, null, "Cannot open file form location application.properties ", e);
            }
        }
        logger.debug(APP, null, null, "Properties loaded");
    }

    public static TMajorProperty getInstance() {
        if (instance == null) {
            instance = new TMajorProperty();
        }
        return instance;
    }

    public boolean loadFromDbSQL(@NotNull Connection connection) {
        logger.debug(APP, null, null, "Reading and add properties from SQL-based DB");
        String query = properties.getProperty("query.properties");
        if (query == null || query.isEmpty()) {
            logger.info(APP, null, null, "Query not present into properties. For enable it add: 'query.properties' see documentation for more information");
            return false;
        }
        boolean gotCacheableColumn = query.contains("CACHEABLE");
        boolean gotDeletionColumn = query.contains("DELETION_FLAG");
        logger.trace(APP, null, null, "Property Statement: ", new Object[]{query});
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int idx = 1;
            // Cacheable
            if (gotCacheableColumn) {
                statement.setString(idx++, "Y");
            }
            // Deleted
            if (gotDeletionColumn) {
                statement.setString(idx, "N");
            }
            Map<String, String> newProps = new HashMap<>();
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    newProps.put(rs.getString(1), rs.getString(2));
                }
            }
            properties.putAll(newProps);
        } catch (SQLException e) {
            logger.warning(APP, null, null, "Some error occurred in sql", e);
            return false;
        }
        return true;
    }

    public void loadAdditionalProperties(@NotNull Map<String, String> additional) {
        properties.putAll(additional);
    }

    public String get(@NotNull String key) {
        return get(key, null, true);
    }

    @NotNull
    public String get(@NotNull String key, String def) {
        return get(key, def, false);
    }

    public String get(@NotNull String key, String def, boolean nullable) {
        Object val = properties.get(key);
        if (val != null) {
            return val.toString().trim();
        } else if (def != null) {
            return def;
        } else if (nullable) {
            return null;
        } else return "";
    }
}

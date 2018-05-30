package com.tmajor.lib.log.pattern;

import com.tmajor.lib.string.StringConverter;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

import static com.tmajor.lib.log.pattern.LogTags.DELIMETER;

/**
 * This class build pattern log
 */
public class LogPattern {

    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);

    /**
     * Full pattern log <b> _uiid: _msg: _params [ ] </b>
     *
     * @param uuid    - Unique identifier of log ( auto generated if missing )
     * @param message - Message to log
     * @param params  - params
     * @return full pattern
     */
    public static String applyPattern(String uuid, String message, Object... params) {
        return applyPattern(uuid, null, null, null, message, params);
    }

    /**
     * Full pattern log <b> _uiid: _app: _bid: _msg: _params [ ] </b>
     *
     * @param uuid       - Unique identifier of log ( auto generated if missing )
     * @param app        - Application want to log
     * @param businessId - Business id useful for business team
     * @param message    - Message to log
     * @param params     - params
     * @return full pattern
     */
    public static String applyPattern(String uuid, String app, String businessId, String message, Object... params) {
        return applyPattern(uuid, app, null, businessId, message, params);
    }

    /**
     * Full pattern log <b> _uiid: _app: _tid: _bid: _msg: _params [ ] </b>
     *
     * @param uuid        - Unique identifier of log ( auto generated if missing )
     * @param app         - Application want to log
     * @param technicalId - Technical id useful for dev team
     * @param businessId  - Business id useful for business team
     * @param message     - Message to log
     * @param params      - params
     * @return full pattern
     */
    public static String applyPattern(String uuid, String app, String technicalId, String businessId, String message, Object... params) {

        StringBuilder joiner = new StringBuilder(LogTags.UUID).append(DELIMETER);
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
        joiner.append(StringConverter.stringify(uuid)).append(" ");
        if (app != null) {
            joiner.append(LogTags.APP).append(DELIMETER).append(StringConverter.stringify(app.toUpperCase())).append(" ");
        }
        if (technicalId != null) {
            joiner.append(LogTags.TECH_ID).append(DELIMETER).append(StringConverter.stringify(technicalId)).append(" ");
        }
        if (businessId != null) {
            joiner.append(LogTags.BNESS_ID).append(DELIMETER).append(StringConverter.stringify(businessId)).append(" ");
        }
        joiner.append(LogTags.MSG).append(DELIMETER).append(StringConverter.stringify(message)).append(" ");
        if (params != null && params.length > 0 && Arrays.stream(params).allMatch(Objects::nonNull)) {
            joiner.append(LogTags.PARAMS).append(DELIMETER).append(" [ ");
            for (Object param : params) {
                joiner.append("\n ").append(Objects.toString(param)).append(";");
            }
            joiner.append("\n ]").append(" ");
        }
        return joiner.toString();

    }

}

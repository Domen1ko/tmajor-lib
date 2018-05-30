package com.tmajor.lib.log.pattern;

import com.tmajor.lib.log.model.LogModel;
import com.tmajor.lib.string.StringExtractor;

import javax.validation.constraints.NotNull;
import java.util.regex.Pattern;

public class LogMessageExtractor {

    private static final String groupContainer = "(.*?)";
    private static final Pattern uuidPattern = Pattern.compile(LogTags.UUID + LogTags.DELIMETER + "\"" + groupContainer + "\"");
    private static final Pattern appPattern = Pattern.compile(LogTags.APP + LogTags.DELIMETER + "\"" + groupContainer + "\"");
    private static final Pattern techPattern = Pattern.compile(LogTags.TECH_ID + LogTags.DELIMETER + "\"" + groupContainer + "\"");
    private static final Pattern bnessPattern = Pattern.compile(LogTags.BNESS_ID + LogTags.DELIMETER + "\"" + groupContainer + "\"");
    private static final Pattern msgPattern = Pattern.compile(LogTags.MSG + LogTags.DELIMETER + "\"" + groupContainer + "\"");
    private final StringExtractor extractor = new StringExtractor();

    /**
     * Extract UUID from an logMessage with standard pattern
     *
     * @param logMessage - logMessage
     * @return empty string if not found. uuid otherwise
     */
    public static String extractUuid(@NotNull String logMessage) {
        return extract(logMessage, LogTags.UUID, uuidPattern);
    }

    /**
     * Extract APP from an logMessage with standard pattern
     *
     * @param logMessage - logMessage
     * @return empty string if not found. uuid otherwise
     */
    public static String extractApp(@NotNull String logMessage) {
        return extract(logMessage, LogTags.APP, appPattern);
    }

    /**
     * Extract UUID from an logMessage with standard pattern
     *
     * @param logMessage - logMessage
     * @return empty string if not found. uuid otherwise
     */
    public static String extractTechnicalId(@NotNull String logMessage) {
        return extract(logMessage, LogTags.TECH_ID, techPattern);
    }

    /**
     * Extract UUID from an logMessage with standard pattern
     *
     * @param logMessage - logMessage
     * @return empty string if not found. uuid otherwise
     */
    public static String extractBusinessId(@NotNull String logMessage) {
        return extract(logMessage, LogTags.BNESS_ID, bnessPattern);
    }

    /**
     * Extract UUID from an logMessage with standard pattern
     *
     * @param logMessage - logMessage
     * @return empty string if not found. uuid otherwise
     */
    public static String extractMessage(@NotNull String logMessage) {
        return extract(logMessage, LogTags.MSG, msgPattern);
    }

    /**
     * Extract LogModel contains all necessary information
     *
     * @param logMessage - log message
     * @return  Log Bean
     */
    public static LogModel extractModel(@NotNull String logMessage) {
        return new LogModel.LogModelBuilder()
                .setUuid(extract(logMessage, LogTags.UUID, uuidPattern, true))
                .setApp(extract(logMessage, LogTags.APP, appPattern, true))
                .setBusinessId(extract(logMessage, LogTags.TECH_ID, bnessPattern, true))
                .setTechnicalId(extract(logMessage, LogTags.TECH_ID, techPattern, true))
                .setMessage(extract(logMessage, LogTags.MSG, msgPattern, true))
                .buid();
    }


    private static String extract(@NotNull String logMessage, @NotNull String tag, @NotNull Pattern pattern) {
        return extract(logMessage, tag, pattern, false);
    }

    private static String extract(@NotNull String logMessage, @NotNull String tag, @NotNull Pattern pattern, boolean nullable) {
        String _string = null;
        if (logMessage.contains(tag)) {
            _string = StringExtractor.extract(logMessage, pattern);
        }
        if (_string == null && !nullable) {
            _string = "";
        }
        return _string;
    }


}

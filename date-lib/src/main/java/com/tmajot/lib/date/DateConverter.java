package com.tmajot.lib.date;

import java.time.LocalDateTime;

public class DateConverter {

    private DateConverter() {
    }


    public static LocalDateTime convert(String date) {
        if (date != null) {
            return LocalDateTime.parse(date);
        } else return null;
    }

    public static String convert(LocalDateTime date) {
        if (date != null) {
            return date.toString();
        } else return null;
    }

}

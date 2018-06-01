package com.tmajot.lib.date;

import java.time.LocalDateTime;

public class DateUtility {


    private DateUtility() {
    }

    public static String now() {
        return LocalDateTime.now().toString();
    }
}

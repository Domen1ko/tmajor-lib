package com.tmajot.lib.date;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DateConverterTest {

    private String data = "2018-06-01T09:45:28.744";
    private LocalDateTime time = DateConverter.convert(data);

    @Test
    public void convert() {
        LocalDateTime convert = DateConverter.convert(data);
        assertEquals(convert.getYear(), 2018);
        assertEquals(convert.getDayOfMonth(), 1);
        assertEquals(convert.getMonth(), Month.JUNE);
        assertEquals(convert.getHour(), 9);
        assertEquals(convert.getMinute(), 45);
        assertEquals(convert.getSecond(), 28);
    }

    @Test
    public void convert1() {
        String convert = DateConverter.convert(time);
        assertTrue(convert.equalsIgnoreCase(data));

    }
}
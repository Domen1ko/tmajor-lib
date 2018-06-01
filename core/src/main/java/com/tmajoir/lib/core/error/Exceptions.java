package com.tmajoir.lib.core.error;

import com.tmajoir.lib.core.registry.ApplicationRegistry;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exceptions {


    public static String stackTrace(Throwable e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString(); // stack trace as a string

        } finally {
            if (pw != null) {
                pw.close();
            }
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    Logger.getLogger(ApplicationRegistry.APP_DEF).log(Level.SEVERE, "Cannot close String writer", e1);
                }
            }
        }
    }
}

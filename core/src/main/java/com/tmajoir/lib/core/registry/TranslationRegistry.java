package com.tmajoir.lib.core.registry;

import java.util.Locale;

public class TranslationRegistry {
    private static TranslationRegistry instance;

    private TranslationRegistry() {
    }

    public static TranslationRegistry getInstance(Locale locale) {
        if (instance == null) {
            instance = new TranslationRegistry();
        }
        return instance;
    }

    public String translateError(String code, String app) {
        return null;
    }
}

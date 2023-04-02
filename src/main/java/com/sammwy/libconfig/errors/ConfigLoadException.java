package com.sammwy.libconfig.errors;

public class ConfigLoadException extends Exception {
    public ConfigLoadException(String message) {
        super(message);
    }

    public ConfigLoadException(Exception child) {
        super(child);
    }
}

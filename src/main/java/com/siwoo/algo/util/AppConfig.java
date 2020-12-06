package com.siwoo.algo.util;

import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Properties;

public enum AppConfig {
    INSTANCE;
    private final Properties properties;
    private final String path = "./src/main/resources/app.properties";

    AppConfig() {
        try {
            properties = new Properties();
            properties.load(new FileReader(path));
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }
    
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
}

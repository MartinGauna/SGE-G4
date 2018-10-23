package ar.edu.utn.frba.dds;

import java.util.Properties;

public class Config {

    private Properties configFile;

    private static Config instance;
    public Config() {
        configFile = new java.util.Properties();
        try {
            configFile.load(this.getClass().getClassLoader().getResourceAsStream("config.cfg"));
        } catch (Exception eta) {
            eta.printStackTrace();
        }
    }

    private String getValue(String key) {
        return configFile.getProperty(key);
    }

    public String getProperty(String key) {
        if (instance == null) instance = new Config();
        return instance.getValue(key);
    }
}
package com.dyun.basejava;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
//    private static final File PROPS = new File("C:\\Users\\dyun\\IdeaProjects\\basejava\\config\\resumes.properties");
    private static final String PROPS = "/resumes.properties";
    private static final Config INSTANCE = new Config();
    private Properties props = new Properties();
    private String storageDir;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = /*new FileInputStream(PROPS)*/Config.class.getResourceAsStream(PROPS)) {
            props.load(is);
            storageDir = props.getProperty("storage.dir");
            dbUrl = props.getProperty("db.url");
            dbUser = props.getProperty("db.user");
            dbPassword = props.getProperty("db.password");
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS/*.getAbsolutePath()*/);
        }
    }

    public String getStorageDir() {
        return storageDir;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}

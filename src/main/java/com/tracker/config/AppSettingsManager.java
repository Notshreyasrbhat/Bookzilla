package com.tracker.config;

// Singleton Pattern Example (Explicit)
// Spring Beans (@Service, @Repository, etc.) are singletons by default,
// but this explicit class demonstrates the GoF pattern.
public class AppSettingsManager {
    private static volatile AppSettingsManager instance;
    private String appName = "Reading Tracker MVC";
    
    private AppSettingsManager() {}
    
    public static AppSettingsManager getInstance() {
        if (instance == null) {
            synchronized (AppSettingsManager.class) {
                if (instance == null) {
                    instance = new AppSettingsManager();
                }
            }
        }
        return instance;
    }

    public String getAppName() {
        return appName;
    }
}

package com.tracker.config;

// Singleton Pattern Example (Explicit)
// Spring Beans (@Service, @Repository, etc.) are singletons by default,
// but this explicit class demonstrates the GoF pattern.
// DESIGN PATTERN: Singleton.
// Ensures that only one instance of the app settings ever exists in memory at any given time.
public class AppSettingsManager {
    private static volatile AppSettingsManager instance;
    private String appName = "Bookzilla";
    
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

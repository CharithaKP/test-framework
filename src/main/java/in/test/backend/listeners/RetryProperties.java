package in.test.backend.listeners;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility class for managing retry properties from multiple sources
 * Priority: Suite parameter > System property > Default value
 */
@Slf4j
public class RetryProperties {
    
    // Property keys
    private static final String MAX_RETRIES_KEY = "maxRetriesFromSuite";
    private static final String DELAY_KEY = "delayBetweenRetryInSeconds";
    private static final String RETRY_ENABLED_KEY = "retryEnabled";
    private static final String CONFIG_NAME_KEY = "retryConfigName";
    private static final String CONFIG_FILE_KEY = "retryConfigFile";
    
    // System property keys
    private static final String SYS_MAX_RETRIES_KEY = "retry.maxRetries";
    private static final String SYS_DELAY_KEY = "retry.delay";
    private static final String SYS_RETRY_ENABLED_KEY = "retry.enabled";
    private static final String SYS_CONFIG_NAME_KEY = "retry.config.name";
    private static final String SYS_CONFIG_FILE_KEY = "retry.config.file";
    
    // Default values
    private static final int DEFAULT_MAX_RETRIES = 3;
    private static final int DEFAULT_DELAY = 2;
    private static final boolean DEFAULT_RETRY_ENABLED = true;
    private static final String DEFAULT_CONFIG_NAME = "default";
    private static final String DEFAULT_CONFIG_FILE = "src/main/resources/retry-config.xml";
    
    /**
     * Gets the maximum number of retries from suite parameter, system property, or default
     * @param suiteParameter Suite parameter value (can be null)
     * @return Maximum number of retries
     */
    public static int getMaxRetries(String suiteParameter) {
        // Priority 1: Suite parameter
        if (suiteParameter != null && !suiteParameter.trim().isEmpty()) {
            try {
                int value = Integer.parseInt(suiteParameter.trim());
                log.debug("Using max retries from suite parameter: {}", value);
                return value;
            } catch (NumberFormatException e) {
                log.warn("Invalid suite parameter for max retries: '{}', checking system property", suiteParameter);
            }
        }
        
        // Priority 2: System property
        String systemProperty = System.getProperty(SYS_MAX_RETRIES_KEY);
        if (systemProperty != null && !systemProperty.trim().isEmpty()) {
            try {
                int value = Integer.parseInt(systemProperty.trim());
                log.debug("Using max retries from system property: {}", value);
                return value;
            } catch (NumberFormatException e) {
                log.warn("Invalid system property for max retries: '{}', using default", systemProperty);
            }
        }
        
        // Priority 3: Default value
        log.debug("Using default max retries: {}", DEFAULT_MAX_RETRIES);
        return DEFAULT_MAX_RETRIES;
    }
    
    /**
     * Gets the delay between retries from suite parameter, system property, or default
     * @param suiteParameter Suite parameter value (can be null)
     * @return Delay in seconds
     */
    public static int getDelayInSeconds(String suiteParameter) {
        // Priority 1: Suite parameter
        if (suiteParameter != null && !suiteParameter.trim().isEmpty()) {
            try {
                int value = Integer.parseInt(suiteParameter.trim());
                log.debug("Using delay from suite parameter: {} seconds", value);
                return value;
            } catch (NumberFormatException e) {
                log.warn("Invalid suite parameter for delay: '{}', checking system property", suiteParameter);
            }
        }
        
        // Priority 2: System property
        String systemProperty = System.getProperty(SYS_DELAY_KEY);
        if (systemProperty != null && !systemProperty.trim().isEmpty()) {
            try {
                int value = Integer.parseInt(systemProperty.trim());
                log.debug("Using delay from system property: {} seconds", value);
                return value;
            } catch (NumberFormatException e) {
                log.warn("Invalid system property for delay: '{}', using default", systemProperty);
            }
        }
        
        // Priority 3: Default value
        log.debug("Using default delay: {} seconds", DEFAULT_DELAY);
        return DEFAULT_DELAY;
    }
    
    /**
     * Gets the retry enabled flag from suite parameter, system property, or default
     * @param suiteParameter Suite parameter value (can be null)
     * @return true if retry is enabled, false otherwise
     */
    public static boolean isRetryEnabled(String suiteParameter) {
        // Priority 1: Suite parameter
        if (suiteParameter != null && !suiteParameter.trim().isEmpty()) {
            boolean value = Boolean.parseBoolean(suiteParameter.trim());
            log.debug("Using retry enabled from suite parameter: {}", value);
            return value;
        }
        
        // Priority 2: System property
        String systemProperty = System.getProperty(SYS_RETRY_ENABLED_KEY);
        if (systemProperty != null && !systemProperty.trim().isEmpty()) {
            boolean value = Boolean.parseBoolean(systemProperty.trim());
            log.debug("Using retry enabled from system property: {}", value);
            return value;
        }
        
        // Priority 3: Default value
        log.debug("Using default retry enabled: {}", DEFAULT_RETRY_ENABLED);
        return DEFAULT_RETRY_ENABLED;
    }
    
    /**
     * Gets the retry configuration name from suite parameter, system property, or default
     * @param suiteParameter Suite parameter value (can be null)
     * @return Configuration name
     */
    public static String getConfigName(String suiteParameter) {
        // Priority 1: Suite parameter
        if (suiteParameter != null && !suiteParameter.trim().isEmpty()) {
            String value = suiteParameter.trim();
            log.debug("Using config name from suite parameter: '{}'", value);
            return value;
        }
        
        // Priority 2: System property
        String systemProperty = System.getProperty(SYS_CONFIG_NAME_KEY);
        if (systemProperty != null && !systemProperty.trim().isEmpty()) {
            String value = systemProperty.trim();
            log.debug("Using config name from system property: '{}'", value);
            return value;
        }
        
        // Priority 3: Default value
        log.debug("Using default config name: '{}'", DEFAULT_CONFIG_NAME);
        return DEFAULT_CONFIG_NAME;
    }
    
    /**
     * Gets the retry configuration file path from suite parameter, system property, or default
     * @param suiteParameter Suite parameter value (can be null)
     * @return Configuration file path
     */
    public static String getConfigFilePath(String suiteParameter) {
        // Priority 1: Suite parameter
        if (suiteParameter != null && !suiteParameter.trim().isEmpty()) {
            String value = suiteParameter.trim();
            log.debug("Using config file path from suite parameter: '{}'", value);
            return value;
        }
        
        // Priority 2: System property
        String systemProperty = System.getProperty(SYS_CONFIG_FILE_KEY);
        if (systemProperty != null && !systemProperty.trim().isEmpty()) {
            String value = systemProperty.trim();
            log.debug("Using config file path from system property: '{}'", value);
            return value;
        }
        
        // Priority 3: Default value
        log.debug("Using default config file path: '{}'", DEFAULT_CONFIG_FILE);
        return DEFAULT_CONFIG_FILE;
    }


    // Getter methods for property keys (useful for external configuration)
    
    public static String getMaxRetriesKey() {
        return MAX_RETRIES_KEY;
    }
    
    public static String getDelayKey() {
        return DELAY_KEY;
    }
    
    public static String getRetryEnabledKey() {
        return RETRY_ENABLED_KEY;
    }
    
    public static String getConfigNameKey() {
        return CONFIG_NAME_KEY;
    }
    
    public static String getConfigFileKey() {
        return CONFIG_FILE_KEY;
    }
    
    // Getter methods for system property keys
    
    public static String getSysMaxRetriesKey() {
        return SYS_MAX_RETRIES_KEY;
    }
    
    public static String getSysDelayKey() {
        return SYS_DELAY_KEY;
    }
    
    public static String getSysRetryEnabledKey() {
        return SYS_RETRY_ENABLED_KEY;
    }
    
    public static String getSysConfigNameKey() {
        return SYS_CONFIG_NAME_KEY;
    }
    
    public static String getSysConfigFileKey() {
        return SYS_CONFIG_FILE_KEY;
    }
}

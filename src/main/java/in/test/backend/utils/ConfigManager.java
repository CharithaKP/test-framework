package in.test.backend.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();

    public ConfigManager() throws IOException {
        loadPropertyFromClassPath();
    }

    private void loadPropertyFromClassPath() throws IOException {

        //load resource file from classpath
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        if (in != null) {
            properties.load(in);
        }

    }
    public static String get(String key) {
        return properties.getProperty(key);
    }

}

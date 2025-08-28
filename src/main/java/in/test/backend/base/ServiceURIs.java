package in.test.backend.base;

import lombok.Getter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public enum ServiceURIs {

    HEIMDALL_URL(getConfigProperty("base.url"));
    
    private final String service;
    
    ServiceURIs(String service) {
        this.service = service;
    }
    
    public String getService() {
        return service;
    }
    
    private static String getConfigProperty(String key) {
        Properties properties = new Properties();
        try (InputStream input = ServiceURIs.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                return "http://localhost:8080/";
            }
            properties.load(input);
            return properties.getProperty(key, "http://localhost:8080/");
        } catch (IOException e) {
            return "http://localhost:8080/";
        }
    }

}

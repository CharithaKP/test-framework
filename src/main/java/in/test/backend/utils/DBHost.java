package in.test.backend.utils;

import lombok.Getter;


@Getter

public enum DBHost {
    MYSQL("mysql.url", "mysql.driver", "mysql.username", "mysql.password");

    private final String urlKey;
    private final String driverKey;
    private final String usernameKey;
    private final String passwordKey;

    DBHost(String urlKey, String driverKey, String usernameKey, String passwordKey) {
        this.urlKey = urlKey;
        this.driverKey = driverKey;
        this.usernameKey = usernameKey;
        this.passwordKey = passwordKey;
    }

    public String getDbHostname(String dbName) {
        String template = ConfigManager.get(urlKey);
        return template.replace("$db", dbName);
    }

}


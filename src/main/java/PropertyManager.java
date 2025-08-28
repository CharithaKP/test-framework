import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    private final Properties properties = new Properties();

    public PropertyManager() throws IOException {
        loadPropertyFromClassPath();
    }

    private void loadPropertyFromClassPath() throws IOException {

        //load resource file from classpath
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        if (in != null) {
            properties.load(in);
        }

    }

}

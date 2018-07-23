import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hfx on 2018/7/23.
 */
public class PropertiesUtil {

    public  static Properties getProperties(String name)  {
        InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(name);
        Properties properties=new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }


}

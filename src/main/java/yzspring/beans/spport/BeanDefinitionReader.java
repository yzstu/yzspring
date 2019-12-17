package yzspring.beans.spport;

import javax.security.auth.login.Configuration;
import java.util.Properties;

/**
 * 类描述
 *
 * @author： 12405
 * @date: 2019/12/17-23:25
 */
public class BeanDefinitionReader {

    private static final String MY_SPRING_SCAN_PACKAGE = "mySpringScanLocaltion";

    private Properties properties = new Properties();

    public BeanDefinitionReader(String... locations) {

    }

    public Properties getProperties(){
        return properties;
    }
}

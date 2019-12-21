package yzspring.beans.spport;

import java.io.IOException;
import java.io.InputStream;
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
        try {
            //定位
            InputStream inputStream = this.getClass().getResourceAsStream(locations[0].replace("classpath:",""));
            //导入
            properties.load(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        doScanner(properties.getProperty(MY_SPRING_SCAN_PACKAGE));
    }

    private void doScanner(String property) {
    }

    public Properties getProperties(){
        return properties;
    }
}

package yzspring.beans.spport;

import jdk.nashorn.internal.runtime.arrays.ContinuousArrayData;
import yzspring.beans.config.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    /**保存了所有Bean的className*/
    private List<String> registyBeanClasses = new ArrayList<>();

    public BeanDefinitionReader(String... locations) {

        //1.定位，通过URL定位找到配置文件，然后转换为文件流
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:", ""));
        try {
            //2.加载，保存为properties
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //3.扫描，扫描资源文件(class)，并保存到集合中
        doScanner(properties.getProperty(MY_SPRING_SCAN_PACKAGE));
    }

    private void doScanner(String property) {
        URL url = this.getClass().getClassLoader().getResource(property.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                //如果是目录则递归调用，直到找到class
                doScanner(property + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = (property + "." + file.getName().replace(".class", ""));
                //className保存到集合
                registyBeanClasses.add(className);
            }
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public List<BeanDefinition> loadBeanDefinitions() {
        return null ;
    }
}

package yzspring.beans.spport;

import jdk.nashorn.internal.runtime.arrays.ContinuousArrayData;
import yzspring.annotation.YzComponent;
import yzspring.beans.config.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
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
        List<BeanDefinition> resultList = new ArrayList<>();

        try {
            for (String className : registyBeanClasses){

                //根据类名找到类
                Class<?> clazz = Class.forName(className);
                //接口不需要实例化
                if (clazz.isInterface()){
                    continue;
                }

                //获取类的所有注解
                Annotation[] annotations = clazz.getAnnotations();
                //没有注解，跳过
                if (0 == annotations.length){
                    continue;
                }

                for (Annotation annotation : annotations){
                    Class<? extends Annotation> annotationType = annotation.annotationType();
                    //目前只考虑YzComponent
                    if (annotationType.isAnnotationPresent(YzComponent.class)){
                        //这里处理符合我们要求的信息
                        //factoryName 默认首字母小写，所以这里进行了转化
                        //此处不考虑自定义变量名
                        resultList.add(doCreateBeanDefinition(getFactoryName(clazz.getSimpleName()),clazz.getName()));

                        Class<?>[] interfaces = clazz.getInterfaces();
                        //注入接口
                        for (Class<?> interf : interfaces){
                            resultList.add(doCreateBeanDefinition(interf.getName(),interf.getName()));
                        }
                    }
                }
            }

        }catch (ClassNotFoundException e){
            System.out.println(e);
        }
        return resultList;
    }

    private String getFactoryName(String simpleName) {

        char[] factoryNameChars = simpleName.toCharArray();
        factoryNameChars[0] += 32;

        return new String(factoryNameChars);
    }

    private BeanDefinition doCreateBeanDefinition(String factoryName, String beanName) {
        BeanDefinition beanDefinition = new BeanDefinition();

        //保存类的工厂中的名字或者接口的全类名
        beanDefinition.setFactoryBeanName(factoryName);
        //保存类或者接口的全类名
        beanDefinition.setBeanClassName(beanName);

        return beanDefinition;
    }


}

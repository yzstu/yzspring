package yzspring.context.spport;

import yzspring.beans.config.BeanDefinition;
import yzspring.beans.spport.BeanDefinitionReader;
import yzspring.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类描述
 *
 * @author： 12405
 * @date: 2019/12/17-23:14
 */
public class DefaultApplicationContext implements ApplicationContext{

    //配置文件路径
    private String configLocaltion;
    //保存factoryName与BeanDefinition的对应关系
    private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    //reader
    private BeanDefinitionReader reader;

    public DefaultApplicationContext(String configLocaltion) {
        this.configLocaltion = configLocaltion;
        try {
            refresh();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void refresh() throws Exception{

        //1、定位，定位配置文件，将所有需要扫描的类封装到registyBeanClasses里
        reader = new BeanDefinitionReader(this.configLocaltion);
        //2、加载配置文件，扫描相关的类，把它们封装成BeanDefinition
        List<BeanDefinition> beanDefinitions = reader.loadBeanDefinitions();
        //3、注册，把配置信息放到容器里面(伪IOC容器)
        //到这里为止，容器初始化完毕
        doSpringBeanDefinitionRegist(beanDefinitions);

        //4、把不是延时加载的类，提前初始化

    }

    private void doSpringBeanDefinitionRegist(List<BeanDefinition> beanDefinitions) throws Exception {

        for (BeanDefinition beanDefinition : beanDefinitions){
            if (beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName()))
                throw new Exception("beanDefinitionMap中已存在" + beanDefinition.getFactoryBeanName() );
            beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
        }
    }

    public Object getBean(String name) throws Exception {
        return null;
    }

    public <T> T getBean(Class<T> requireType) throws Exception {
        return null;
    }
}

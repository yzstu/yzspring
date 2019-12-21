package yzspring.context.spport;

import yzspring.beans.spport.BeanDefinitionReader;
import yzspring.context.ApplicationContext;

/**
 * 类描述
 *
 * @author： 12405
 * @date: 2019/12/17-23:14
 */
public class DefaultApplicationContext implements ApplicationContext{

    //配置文件路径
    private String configLocaltion;

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

        //1、定位，定位配置文件
        reader = new BeanDefinitionReader(configLocaltion);
        //2、加载配置文件，扫描相关的类，把它们封装成BeanDefinition

        //3、注册，把配置信息放到容器里面(伪IOC容器)
        //到这里为止，容器初始化完毕

        //4、把不是延时加载的类，提前初始化

    }

    public Object getBean(String name) throws Exception {
        return null;
    }

    public <T> T getBean(Class<T> requireType) throws Exception {
        return null;
    }
}

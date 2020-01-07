package yzspring.beans.config;

import lombok.Getter;
import lombok.Setter;

/**
 * 类描述
 * 这里描述了类名，是否懒加载，在factory中的类名
 *
 * @author： 12405
 * @date: 2019/12/17-23:20
 */

public class BeanDefinition {

    private String beanClassName;

    private boolean lazyInit = false;

    private String factoryBeanName;

    public BeanDefinition() {
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }
}

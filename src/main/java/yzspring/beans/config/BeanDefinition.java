package yzspring.beans.config;

import lombok.Getter;
import lombok.Setter;

/**
 * 类描述
 *
 * @author： 12405
 * @date: 2019/12/17-23:20
 */
@Getter
@Setter
public class BeanDefinition {

    private String beanClassName;

    private boolean lazyInit = false;

    private String factoryBeanName;

    public BeanDefinition() {
    }
}

package yzspring.core.factory;

public interface BeanFactory {

    Object getBean(String name) throws Exception;

    <T> T getBean(Class<T> requireType) throws Exception;
}

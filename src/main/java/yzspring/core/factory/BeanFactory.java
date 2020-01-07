package yzspring.core.factory;

public interface BeanFactory {

    /**
     * @描述 根据beanName获取bean
     * @param name
     * @return
     * @throws Exception
     */
    Object getBean(String name) throws Exception;

    /**
     * @描述 根据class类型获取bean
     * @param requireType
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T getBean(Class<T> requireType) throws Exception;
}

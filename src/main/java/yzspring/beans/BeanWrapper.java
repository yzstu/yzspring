package yzspring.beans;

/**
 * 类描述
 *
 * @author： 12405
 * @date: 2019/12/17-23:29
 */
public class BeanWrapper {

    private Object wrapperObject;

    public BeanWrapper(Object wrapperObject) {
        this.wrapperObject = wrapperObject;
    }

    public Object getWrapperInstance(){
        return this.wrapperObject;
    }



    public Class<?> getWrapperClass() {
        return wrapperObject.getClass();
    }
}

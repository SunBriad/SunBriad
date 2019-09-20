package pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  代理类
 */
public class OrderServiceDynameicProxy implements InvocationHandler {
    Object proxyobject;

    public OrderServiceDynameicProxy(Object o){
        this.proxyobject =o;
    }

    public Object getInstance(Object o){
        this.proxyobject =o;
        Class<?> clazz =o.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return null;
    }


    private void after (){

    }

    /**
     *
     * @param target 订单对象
     */
    private void before(Object  target) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            // 进行数据源切换
        System.out.println("Proxy before method");
        // 约定优于配置
        Long time =(Long)target.getClass().getMethod("getCreatrTime").invoke(target);
    }
}

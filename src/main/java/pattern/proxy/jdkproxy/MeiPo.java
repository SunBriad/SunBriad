package pattern.proxy.jdkproxy;

import pattern.proxy.jdkproxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MeiPo implements InvocationHandler {

         private Object target;

        public Object getInstance(Person person){

            this.target = person ;
            Class<?> clazz = target.getClass();
            return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object o = method.invoke(this.target,args);

        after();
        return o;
    }

    private void before(){
        System.out.println(" 我是媒婆");
        System.out.println(" 111111");
    }

    private void after(){
        System.out.println(" 找到对象了");
    }
}

package pattern.proxy.ttproxy;

import pattern.proxy.jdkproxy.Person;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TMeiPo implements TTInvocationHandler {
    private Person target;

    public Object getInstance(Person person){
        this.target = person ;
        Class<?> clazz = target.getClass();

        /**
         * @parma  new TTClassloarder 类从新装载
         * @Parma clazz.getInterfaces()
         * @Parma this 调用 invoke  方法
         */
        return TTProxy.newProxyInstance(new TTClassloader(),clazz.getInterfaces(),this);
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

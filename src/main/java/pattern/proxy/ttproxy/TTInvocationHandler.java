package pattern.proxy.ttproxy;

import java.lang.reflect.Method;

public interface TTInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}

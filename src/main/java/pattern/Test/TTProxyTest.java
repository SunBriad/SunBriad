package pattern.Test;

import pattern.proxy.jdkproxy.Girl;
import pattern.proxy.jdkproxy.Person;
import pattern.proxy.ttproxy.TMeiPo;

/**
 *  手动实现 代理
 */
public class TTProxyTest {

    public static void main(String[] args) {
        Person person = (Person) new TMeiPo().getInstance(new Girl());
        person.findLove();
    }
}

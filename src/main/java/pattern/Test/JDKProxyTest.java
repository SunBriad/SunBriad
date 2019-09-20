package pattern.Test;

import pattern.proxy.jdkproxy.Girl;
import pattern.proxy.jdkproxy.MeiPo;
import pattern.proxy.jdkproxy.Person;
import sun.misc.ProxyGenerator;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *  JDK 代理 并且输出class 文件
 */
public class JDKProxyTest {

    public static void main(String[] args) throws IOException {
            // 对象必须实现一个接口
        Person person = (Person) new MeiPo().getInstance(new Girl());
        person.findLove();
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy",new Class[]{Person.class});
        FileOutputStream os = new FileOutputStream("D://$Proxy.class");
        os.write(bytes);
        os.close();
    }
}

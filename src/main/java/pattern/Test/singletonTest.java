package pattern.Test;

import org.junit.Test;
import pattern.singletonPattern.MyObject;

import java.io.*;

public class singletonTest {

    /**
     *  反序列化的单利模式
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
        public void singletonTest() throws IOException, ClassNotFoundException {
             // 序列化
            MyObject object = MyObject.getInstance();
            FileOutputStream fos  = new FileOutputStream(new File("myObjectFile.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
            objectOutputStream.writeObject(object);

            objectOutputStream.close();
            fos.close();
            System.out.println(object.hashCode());

                // 反序列化
             FileInputStream fis = new FileInputStream(new File("myObjectFile.txt"));
             ObjectInputStream ois = new ObjectInputStream(fis);
            MyObject o = (MyObject) ois.readObject();
            ois.close();
            fis.close();
            System.out.println(o.hashCode());
            System.out.println(o.hashCode() == object.hashCode());
        }
}

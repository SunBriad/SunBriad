package pattern.singletonPattern;

import java.io.ObjectStreamException;
import java.io.Serializable;


/**
 *  实现序列化单例模式
 */
public class MyObject implements Serializable {

    private static final long serialVersionUID =  888L;

    // 内部类方式
     private static  class  MyObjectHanldler{
         private  static  final  MyObject o = new MyObject();
    }

    private MyObject(){

    }

    public  static MyObject getInstance(){
         return MyObjectHanldler.o;
    }

    /**
     *  反序列化在进行读取数据是通过调用了readResolve() 方法进行读取数据
     * @return
     * @throws ObjectStreamException
     */
    protected  Object readResolve() throws ObjectStreamException{
         return MyObjectHanldler.o;

    }
}

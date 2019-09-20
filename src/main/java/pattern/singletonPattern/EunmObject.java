package pattern.singletonPattern;


import java.sql.Connection;

/**
 *  枚举类型
 */
public enum EunmObject {
 connectionFactory;
 private Connection connection;
 private EunmObject(){
     System.out.println("调用了 枚举对象构造方法！！！");
     String url ="";
 }

}

package pattern.proxy.ttproxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 *  用来生成的代码的工具类
 */
public class TTProxy {

    private static String ln ="\r\n";

    public static Object newProxyInstance(TTClassloader classloader ,Class<?>[] interfaces,TTInvocationHandler h)
    {
            try {
                // 1 、 动态生成源代码
                String src =generateSrc(interfaces);
                // 2 、 通过Java 文件输出到磁盘
                String pathfile  = TTProxy.class.getResource("").getPath();
                File file = new File(pathfile +"$Proxy0.java");
                FileWriter fw = new FileWriter(file);
                fw.write(src);
                fw.flush();
                fw.close();
                System.out.println(src);
                // 进行编译
                // 通过代码生成代码
                JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
               StandardJavaFileManager fileManager= compiler.getStandardFileManager(null,null,null);

               Iterable iterable = fileManager.getJavaFileObjects(file);
               JavaCompiler.CompilationTask task = compiler.getTask(null,fileManager,null,null,null,iterable);
               task.call();
               fileManager.close();
                Class proxyClass = classloader.findClass("$Proxy0");
                Constructor c = proxyClass.getConstructor(TTInvocationHandler.class);
                return  c.newInstance(h);
//
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {
            StringBuffer sb =new StringBuffer();
            sb.append("package pattern.proxy.ttproxy;"+ln);
            sb.append("import  pattern.proxy.jdkproxy.Person;"+ln);
            sb.append("import java.lang.reflect.*;"+ln);
            sb.append("public class $Proxy0 implements "+ interfaces[0].getName()+"{"+ln);
            sb.append("TTInvocationHandler h;"+ln);
            sb.append("public $Proxy0(TTInvocationHandler h) {"+ln);
                    sb.append("this.h =h;");
            sb.append("}"+ln);
        for (Method method : interfaces[0].getMethods()){
         sb.append("public "+ method.getReturnType().getName() +" " +method.getName() +"() {"+ln);
             sb.append("try{"+ln);
                sb.append("Method m = "+interfaces[0].getName() +".class.getMethod(\"" + method.getName()+"\",new Class[]{});"+ln);
                sb.append("this.h.invoke(this,m,null);"+ln);

             sb.append("}catch(Throwable e){"+ln);
                sb.append("e.printStackTrace();"+ln);
              sb.append("}");
             sb.append("}");
         sb.append("}");
        }
        return String.valueOf(sb);
    }


}

package pattern.proxy.ttproxy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class TTClassloader  extends  ClassLoader{

    private File classPathFile;

    public TTClassloader(){
        String classPath = TTClassloader.class.getResource("").getPath();
        this.classPathFile = new File(classPath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
    String className=TTClassloader.class.getPackage().getName()+ "."+ name;
        if (classPathFile != null){
            File classFile = new File(classPathFile ,name.replaceAll("\\.","/")+".class");
            if (classFile.exists()){
                FileInputStream in  = null;
                ByteArrayOutputStream out =null;
                try
                {
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte [] bytes =new byte[1024];
                    int len;
                    while ((len =in.read(bytes)) != -1){
                        out.write(bytes,0,len);
                    }
                return defineClass(className,out.toByteArray(),0,out.size());
                }catch ( Exception e){

                }
            }
        }
         return null;
    }
}

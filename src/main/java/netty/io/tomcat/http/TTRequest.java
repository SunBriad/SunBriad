package netty.io.tomcat.http;

import java.io.IOException;
import java.io.InputStream;

public class TTRequest {

    private String method;

    private String url;
    
    public TTRequest(InputStream inputStream) throws IOException {
           //拿到http 内容
            String content ="";
            byte [] buff = new byte[1024];
            int len = 0;
            if ((len = inputStream.read(buff)) >0){
                content = new String(buff,0,len);
            }
        System.out.println(content);
            String line = content.split("\\n")[0];
            String [] arr = line.split("\\s");
            this.method =arr[0];
            if (arr.length> 0)
            this.url = arr[1];
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}

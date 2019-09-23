package netty.io.tomcat.http;

import java.io.IOException;

public abstract class TTServlet {
    public void service(TTRequest request, TTResponse response) throws IOException {
        if ("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request,response);
        }else {
            doPost(request,response);
        }
    }

    public abstract  void doPost(TTRequest request, TTResponse response) throws IOException;

    public abstract  void doGet(TTRequest request, TTResponse response) throws IOException;
}

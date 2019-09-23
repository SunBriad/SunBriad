package netty.io.tomcat;

import netty.io.tomcat.http.TTRequest;
import netty.io.tomcat.http.TTResponse;
import netty.io.tomcat.http.TTServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GPTomcat {
    private int port = 8080;
    private ServerSocket server;
    private Map<String, TTServlet> servletMapping = new HashMap<String, TTServlet>();
        //J2EE 标准
        //Servlet
        //Request
        //Response
        // 1、 配置启动端口 ServerSocket IP : localhost

      // 配置we.xml
    private Properties webxml = new Properties();
     // servlet -name
     // servlet - class
     //url- pattern
    // 读取配置，URL-parrrn  和 Servlet 建立映射关系
    // MapServletmapping
        private void init(){
            try{
                        //获取当前的路径
                String WEN_INF =this.getClass().getResource("/").getPath();
                FileInputStream fis =new FileInputStream(WEN_INF+"web.properties");
                webxml.load(fis);
                for (Object k : webxml.keySet()){

                    String key = k.toString();
                    if (key.endsWith(".url")){
                        String servletName = key.replaceAll("\\.url$","");
                        String url = webxml.getProperty(key);
                        String className = webxml.getProperty(servletName +".className");
                        // 单实例 ， 多线程 servlet 实现化
                        TTServlet obje = (TTServlet) Class.forName(className).newInstance();
                        servletMapping.put(url,obje);
                    }
                }
            }catch (Exception e){

            }finally {

            }

        }

        public void start(){
            init();

            try {
                server =new ServerSocket(port);
                System.out.println("Tomcat 进行监听端口"+port);
                // 等待用户请求，
                while (true){
                  Socket client=  server.accept();
                  // http 请求处理
                  process(client);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    private void process(Socket client) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
        //拿到输入和输出
            inputStream  = client.getInputStream();
            outputStream= client.getOutputStream();
        TTRequest request = new TTRequest(inputStream);
        TTResponse response = new TTResponse(outputStream);
                // 获取URL
            String url = request.getUrl();
            if (servletMapping.containsKey(url)){
                servletMapping.get(url).service(request,response);
            }else {
                response.write("404 -Not Found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            if (inputStream !=null){
                inputStream.close();
            }
            if (outputStream != null){
                inputStream.close();
            }
        }
    }

    public static void main(String[] args) {
        GPTomcat tomcat =new GPTomcat();
        tomcat.start();
    }
}

package netty.io.tomcat.servlet;

import netty.io.tomcat.http.TTRequest;
import netty.io.tomcat.http.TTResponse;
import netty.io.tomcat.http.TTServlet;

import java.io.IOException;

public class FirstServlet extends TTServlet {
    @Override
    public void doPost(TTRequest request, TTResponse response) throws IOException {
        response.write("this is SecondServlet");
    }

    @Override
    public void doGet(TTRequest request, TTResponse response) throws IOException {
        response.write("this is SecondServlet");
    }
}

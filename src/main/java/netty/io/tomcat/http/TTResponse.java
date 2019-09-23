package netty.io.tomcat.http;

import java.io.IOException;
import java.io.OutputStream;

public class TTResponse  {
    private OutputStream out;
    public TTResponse(OutputStream outputStream) {
        this.out=outputStream;
    }

    public void write(String s) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;\n").append("\r\n");
        sb.append(s);
        System.out.println(sb);
        out.write(sb.toString().getBytes());

    }
}

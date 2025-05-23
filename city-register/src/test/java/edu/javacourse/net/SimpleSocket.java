package edu.javacourse.net;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SimpleSocket {
    @Test
    public void simpleSocket() throws IOException {
        Socket socket = new Socket("java-course.ru", 80);

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        String command = "GET /sitemap.xml HTTP/1.1\r\nHost:java-course.ru\r\n\r\n";
        os.write(command.getBytes(StandardCharsets.UTF_8));
        os.flush();

        int c = 0;
        while ((c = is.read()) != -1) {
            System.out.print((char) c);
        }

        socket.close();
    }
}

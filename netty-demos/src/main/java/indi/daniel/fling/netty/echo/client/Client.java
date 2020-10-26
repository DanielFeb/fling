package indi.daniel.fling.netty.echo.client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 8080));
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        Thread ot = new Thread(()->{
            int i = 0;
            while(i < 10) {
                String msg = "hello server!";
                try {
                    os.write((msg + i).getBytes());
                    Thread.sleep(1000);
                    i ++;
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
        Thread it = new Thread(()->{
            byte[] buf = new byte[1024];
            while(!Thread.interrupted()) {
                try {
                    int length = is.available();
                    if (length > 0) {
                        is.read(buf, 0, length);
                        System.out.println("Received: " + new String(buf, 0, length));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
        ot.start();
        it.start();
        Thread.sleep(10000);
        it.interrupt();
        Thread.sleep(1000);
        socket.close();

    }
}

package indi.daniel.fling.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BIOServer {

    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Executor executor = Executors.newCachedThreadPool();
        while(true) {
            Socket socket = serverSocket.accept();
            executor.execute(()-> {
                System.out.println("接收到一个请求，来自：" + socket.getRemoteSocketAddress().toString());
                try {
                    InputStream is = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String message = null;
                    while((message = reader.readLine()) != null) {
                        if(message.length() == 0) {
                            break;
                        }
                        System.out.println("接收到消息：" + message);
                    }

//                    socket的输入输出流的关闭会把socket一起关闭
//                    详情见SocketInputStream.close 和 SocketOutputStream.close
//                    reader.close();

                    OutputStream os = socket.getOutputStream();
                    os.write("HTTP/1.1 200 OK\r\n".getBytes());
                    os.write("Content-Length: 11\r\n\r\n".getBytes());
                    os.write("Hello World".getBytes());
                    //保证数据全部写完
                    os.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}

package indi.daniel.fling.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * 原作者：张峰
 * 直接基于非阻塞的写法,一个线程处理轮询所有请求
 */
//NIO means  Net + IO
public class NIOClient {
    public static void main(String args[]) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);

        socketChannel.connect(new InetSocketAddress("localhost",8080));

        //循环直到请求建立成功
        while (!socketChannel.finishConnect()) {
            Thread.yield();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");


        String message = scanner.nextLine();

        ByteBuffer bufferToSend = ByteBuffer.allocate(1024);
        bufferToSend.put(message.getBytes("UTF-8"));

        bufferToSend.flip();
        //发送数据
        while(bufferToSend.hasRemaining()) {
            socketChannel.write(bufferToSend);
        }
        System.out.println("收到服务端响应：");

        ByteBuffer bufferReceived = ByteBuffer.allocate(1024);

        while(socketChannel.isOpen()
            && socketChannel.read(bufferReceived) != -1) {
            // 长连接情况下,需要手动判断数据有没有读取结束 (此处做一个简单的判断: 超过0字节就认为请求结束了)
            if (bufferReceived.position() > 0) break;
        }

        bufferReceived.flip();

        byte[] bytes = new byte[bufferReceived.limit()];
        bufferReceived.get(bytes);

        String receivedMessage = new String(bytes, "UTF-8");

        System.out.println(receivedMessage);

        scanner.close();
        socketChannel.close();

    }
}

package indi.daniel.fling.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public class NIOServerWithSelector {
    public static void main (String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();

//        SelectionKey selectionKey  = serverSocketChannel.register(selector, 0, serverSocketChannel);
//        selectionKey.interestOps(SelectionKey.OP_ACCEPT);
//        上两行注释等同于下面这行代码
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, serverSocketChannel);

        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        System.out.printf("启动成功");

        while(true) {

            selector.select(); //等同于selector.selectNow();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while(iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if(key.isAcceptable()) {
                    ServerSocketChannel attachedServerSocketChannel = (ServerSocketChannel) key.attachment();
                    SocketChannel socketChannel = attachedServerSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, socketChannel);
                    System.out.println("收到新链接：" + socketChannel.getRemoteAddress());
                }

                if(key.isReadable()) {
                    SocketChannel attachedSocketChannel = (SocketChannel) key.attachment();

                    try{

                        ByteBuffer bufferReceived = ByteBuffer.allocate(1024);

                        while(attachedSocketChannel.isOpen() && attachedSocketChannel.read(bufferReceived) != -1) {
                            // 长连接情况下,需要手动判断数据有没有读取结束 (此处做一个简单的判断: 超过0字节就认为请求结束了)
                            if (bufferReceived.position() > 0) break;
                        }

                        if (bufferReceived.position() == 0) continue; // 如果没数据了, 则不继续后面的处理

                        bufferReceived.flip();
                        byte[] bytesReceived = new byte[bufferReceived.limit()];
                        bufferReceived.get(bytesReceived);
                        String receivedMessage = new String(bytesReceived, "UTF-8");
                        System.out.println("收到数据：" + receivedMessage + " //来自：" + attachedSocketChannel.getRemoteAddress());


                        String response = "HTTP/1.1 200 OK\r\n" +
                                "Content-Length: 11\r\n\r\n" +
                                "Hello World";
                        ByteBuffer buffer = ByteBuffer.wrap(response.getBytes("UTF-8"));
                        while (buffer.hasRemaining()) {
                            attachedSocketChannel.write(buffer);
                        }
                    } catch (IOException e) {
                        key.cancel();
                    }

                }

            }
        }

    }
}

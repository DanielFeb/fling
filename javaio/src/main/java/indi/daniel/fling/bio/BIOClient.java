package indi.daniel.fling.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

public class BIOClient {

    private static Charset charset = Charset.forName("UTF-8");

    public static void main (String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        OutputStream os = socket.getOutputStream();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String message = scanner.nextLine();
        os.write(message.getBytes(charset));
        scanner.close();
        socket.close();
    }
}

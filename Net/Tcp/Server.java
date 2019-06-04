import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.Socket;
import java.net.InetAddress;
import java.net.ServerSocket;

public class Server {

    public static ServerSocket instance(int port) throws IOException {
        return new ServerSocket(port);
    }

    public static void execute(Socket client) throws IOException {

        int ch = 0;

        int port = client.getPort(); // 获得的port

        InetAddress address = client.getInetAddress(); // address

        byte[] ip = address.getAddress(); // byte的IP地址
        String IP = address.getHostAddress(); // 字符串的IP

        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();

        System.out.println(port);

        while((ch = is.read()) != -1) {
            os.write(ch);
            System.out.println(ch);
        }

        os.close();
        is.close();
        client.close();
    }
}

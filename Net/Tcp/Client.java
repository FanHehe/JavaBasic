import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Client {
    public static void main(String args[]) {

        try {

            Socket socket = new Socket("127.0.0.1", 10034);

            // socket.connect(new InetSocketAddress("127.0.0.1", 10034));

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            String message = args.length > 0 ? args[0] : "默认信息";
            byte[] payload = message.getBytes();

            for (int i = 0; i < payload.length; i++) {
                os.write(payload[i]);
            }

            int ch = 0;

            do {
                ch = is.read();
                System.out.println(ch);
            } while (ch != -1);

            System.out.println("=========");

            // is.close();
            // os.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

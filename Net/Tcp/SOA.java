import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.InetSocketAddress;

public abstract class SOA {

    private int timeout = 3000;
    private Socket socket = null;


    SOA(int cmlb) {

        try {
            this.socket = new Socket();
            this.socket.connect(this.getSocketAddress(cmlb));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    abstract int check();

    public void connect(int cmlb) {
        this.socket.connect(this.getSocketAddress(cmlb));
    }


    public byte[] recv(int timeout) {
        long over = 0;
        long start = System.currentTimeMillis(); // System.nanoTime();

        InputStream is = this.socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);

        while (true) {

            over = System.currentTimeMillis();

            if (over - start > this.overtime) {
                throw new Exception("recv overtime exception: ");
            }



        }
    }

    public void send(byte[] data) {

        int length = data.length;
        int written = 0;

        OutputStream os = this.socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
    }

    private SocketAddress getSocketAddress(int cmlb) {
        return new InetSocketAddress("127.0.0.1", 3004);
    }
}

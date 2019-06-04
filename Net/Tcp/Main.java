import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.ServerSocket;

public class Main {

    private static final int THREAD_POOL_SIZE = 1;

    public static void main(String args[]) {

        try {

            ServerSocket server = Server.instance(10034);

            for (int i  = 0; i < THREAD_POOL_SIZE; i++) {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {

                            while (true) {
                                Socket client = server.accept();
                                Server.execute(client);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

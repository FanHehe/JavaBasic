import java.util.concurrent.Exchanger;

public class MyExchanger {
    public static void main(String[] args) {
        handleExchanger();
    }

    public static void handleExchanger() {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(new Runnable () {
            @Override
            public void run() {
                try {
                    String value = exchanger.exchange("first");

                    System.out.println("first =>" + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable () {
            @Override
            public void run() {
                try {
                    String value = exchanger.exchange("second");

                    System.out.println("second =>" + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
package Concurrent.Thread;

import static java.lang.System.out;

public class MyRunnable {

    public static void handleRunnable() {
        Runnable run = () -> { out.println("runnable"); };
        new Thread(run).start();
    }

    public static void main(String[] args) {
        handleRunnable();
    }
}

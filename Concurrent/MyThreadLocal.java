public class MyThreadLocal {

    static volatile Integer value = 1;

    public static void main(String[] args) {
        handleThreadLocal();
    }

    public static void handleThreadLocal() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

                    threadLocal.set(value);

                    System.out.println(threadLocal.get());
                } catch (Exception e) {

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
                    
                    threadLocal.set(value);

                    System.out.println(value);
                } catch (Exception e) {

                }
            }
        }).start();
    }
}

// http://www.jasongj.com/java/threadlocal/
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class MyCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("==============");
            }
        });

        work(cyclicBarrier, 1);
        work(cyclicBarrier, 2);
        work(cyclicBarrier, 3);
        work(cyclicBarrier, 4);
        work(cyclicBarrier, 5);

        work(cyclicBarrier, 6);
        work(cyclicBarrier, 7);
        work(cyclicBarrier, 8);
        work(cyclicBarrier, 9);
        work(cyclicBarrier, 10);

    }

    public static void work(CyclicBarrier barrier, int sequence) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("" + sequence + "before");

                try {
                    barrier.await();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println("" + sequence + "after");
            }
        }).start();
    }
}

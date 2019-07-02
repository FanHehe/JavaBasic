import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;

public class MyBlocking {
    public static void main(String[] args) {

        try {
            handleDelayQueue();
            handleSynchronousQueue();
            handleArrayBlockingQueue();
            handleLinkedBlockingQueue();
        } catch (Exception e) {}

    }

    //  延迟队列
    static void handleDelayQueue() throws InterruptedException{
        long now = System.currentTimeMillis();
        Task task = new Task(now + 10000, "task");
        DelayQueue<Task> queue = new DelayQueue<>();
        ExecutorService pool = Executors.newCachedThreadPool();

        queue.offer(task);

        while(!queue.isEmpty()) {
            try {
                Task item = queue.take();
                pool.execute(item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        pool.shutdown();
    }

    // 同步队列- 无内容容器：put会阻塞
    static void handleSynchronousQueue() throws InterruptedException {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer value = (Integer)queue.take();
                    System.out.println("take away "+ value);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("put into" + 1);
                    queue.put(1);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static void handleArrayBlockingQueue() throws InterruptedException {
        // 必须初始化长度
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(9);

        queue.put(1);
        queue.put(2);
        queue.put(3);

        queue.take();
        queue.take();
        queue.take();
    }

    static void handleLinkedBlockingQueue() throws InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        queue.put(1);
        queue.put(2);
        queue.put(3);

        queue.take();
        queue.take();
        queue.take();
    }
    static void handlePriorityBlockingQueue() throws InterruptedException {
        // E必须实现Comparable
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();

        queue.put(1);
        queue.put(3);
        queue.put(2);

        queue.take();
        queue.take();
        queue.take();
    }
}

class Task implements Runnable, Delayed {

    private long delay = 0;
    private String value =  "";

    public Task() {
        this(0, "default");
    }

    public Task(long delay, String value) {
        this.delay = delay;
        this.value = value;
    }

    public void run() {
        System.out.println(value);
    }

    public long getDelay(TimeUnit unit) {
        return unit.convert(delay - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public int compareTo(Delayed t) {

        Task target = (Task)t;

        if (delay > target.delay) {
            return 1;
        } else if (delay < target.delay) {
            return -1;
        } else {
            return 0;
        }
    }
}

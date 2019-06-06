import java.util.concurrent.DelayQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
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
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class PC {

    private final int BUFFER = 10000;
    private final LinkedList<String> list = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();

    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    public void consume() { consume(1); }
    public void consume(int number) {
        lock.lock();

        if (list.size() == 0) {
            try {
                empty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while(number-- > 0 && list.size() != 0) {
            list.pop();
        }

        System.out.println("【consumer】：消费了一个产品\t【现仓储量为】:" + list.size());

        full.signalAll();
        lock.unlock();
    }


    public void produce() { produce(1); }
    public void produce(int number) {
        lock.lock();

        if (list.size() == BUFFER) {
            try {
                full.await();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        while(number-- > 0) {
            list.push("1");
        }

        System.out.println("【producer】：生产了一个产品\t【现仓储量为】:" + list.size());

        empty.signalAll();
        lock.unlock();
    }



    public static void main(String[] args) {
        PC storage = new PC();

        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 8; i++) {

            service.submit(()-> { while(true) { storage.consume(); }});
            service.submit(()-> { while(true) { storage.produce(); }});

        }
    }
}

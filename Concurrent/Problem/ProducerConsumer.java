import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ProducerConsumer {

    private final int BUFFER = 10;
    private final LinkedList<String> list = new LinkedList<>();

    public void consume() { consume(1); }
    public void consume(int number) {
        synchronized (list) {
            while (list.size() == 0) {
                try {
                    System.out.println("consumer阻塞中");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (number-- > 0 && list.size() != 0) {
                list.remove();
            }

            System.out.println("【consumer】：消费了一个产品\t【现仓储量为】:" + list.size());

            list.notifyAll();
        }
    }


    public void produce() { produce(1); }
    public void produce(int number) {
        synchronized (list) {
            while (list.size() == BUFFER) {
                try {
                    System.out.println("producer阻塞中");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (number-- > 0) {
                list.add("1");
            }

            System.out.println("【producer】：生产了一个产品\t【现仓储量为】:" + list.size());

            list.notifyAll();
        }
    }

    public static void main(String[] args) {
        ProducerConsumer storage = new ProducerConsumer();

        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 1; i++) {

            service.submit(()-> { while(true) { storage.consume(); }});
            service.submit(()-> { while(true) { storage.produce(); }});

        }
    }
}

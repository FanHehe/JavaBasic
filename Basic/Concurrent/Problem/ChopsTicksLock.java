import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyChopsTick extends ReentrantLock {

    public int index = 0;

    MyChopsTick() {}

    MyChopsTick(int index) { this.index = index; }
}

class MyHuman {
    public int index = 0;

    MyHuman() {}

    MyHuman(int index) { this.index = index; }

    void getChopsTicks(MyChopsTick left, MyChopsTick right) {

        if (left.index < right.index) {
            MyChopsTick temp = left;
            left = right;
            right = temp;
        }

        left.lock();
        System.out.println("human[" + this.index + "]获取了" + left.index);
        right.lock();
        System.out.println("human" + this.index + "吃饭了");
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            right.unlock();
            left.unlock();
        }

        try {
            Thread.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ChopsTicksLock {
    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(10);

        MyHuman[] humans = new MyHuman[5];
        MyChopsTick[] chopsticks = new MyChopsTick[5];

        for(int i = 0; i < humans.length; i++) {
            humans[i] = new MyHuman(i);
        }

        for(int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new MyChopsTick(i);
        }

        for (MyHuman human: humans) {
            service.submit(()-> {
                while(true) {
                    human.getChopsTicks(chopsticks[human.index], chopsticks[(human.index + 1) % 5]);
                }
            });
        }
    }
}

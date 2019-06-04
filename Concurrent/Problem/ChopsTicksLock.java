import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ChopsTick extends ReentrantLock {

    public int index = 0;

    ChopsTick() {}

    ChopsTick(int index) { this.index = index; }
}

class Human {
    public int index = 0;

    Human () {}

    Human (int index) { this.index = index; }

    void getChopsTicks(ChopsTick left, ChopsTick right) {

        if (left.index < right.index) {
            ChopsTick temp = left;
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

        Human[] humans = new Human[5];
        ChopsTick[] chopsticks = new ChopsTick[5];

        for(int i = 0; i < humans.length; i++) {
            humans[i] = new Human(i);
        }

        for(int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new ChopsTick(i);
        }

        for (Human human: humans) {
            service.submit(()-> {
                while(true) {
                    human.getChopsTicks(chopsticks[human.index], chopsticks[(human.index + 1) % 5]);
                }
            });
        }
    }
}

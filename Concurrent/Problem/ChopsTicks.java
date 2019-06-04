import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


class ChopsTick {
    public int id;

    ChopsTick() {
        this(0);
    }

    ChopsTick(int id) {
        this.id = id;
    }
}

class Human {

    public int index = 0;

    ChopsTick left = null;
    ChopsTick right = null;

    Human() {}

    Human(int index) {
        this.index = index;
    }

    void getChopsticks(ChopsTick left, ChopsTick right) {
        synchronized(left) {
            System.out.println("human" + this.index + "拿起了" + this.index);
            synchronized(right) {
                System.out.println("human" + this.index + "吃饭ing");
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("human" + this.index + "吃完饭啦");
    }

    void getChopsticksSate(ChopsTick left, ChopsTick right) {

        if (left.id > right.id) {
            ChopsTick temp = left;
            left = right;
            right = temp;
            temp = null;
        }

        synchronized(left) {
            System.out.println("human" + this.index + "拿起了" + this.index);
            synchronized(right) {
                System.out.println("human" + this.index + "吃饭ing");
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("human" + this.index + "吃完饭啦");
    }
}

public class ChopsTicks {
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
                    human.getChopsticksSate(chopsticks[human.index], chopsticks[(human.index + 1) % 5]);
                }
            });
        }
    }
}

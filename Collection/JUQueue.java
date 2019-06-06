import java.util.Queue;
import java.util.LinkedList;

public class JUQueue {
    public static void main(String[] args) {
        handleQueue();
    }

    /**
     * add / offer
     * remove / poll
     * element / peek
     */
    public static void handleQueue() {
        Queue<Integer> queue =  new LinkedList<>();

        try {
            queue.add(1);
            queue.offer(1);

            queue.element();
            queue.peek();

            queue.remove();
            queue.poll();

            queue.remove();
            queue.poll();

            queue.element();
            queue.peek();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

    }
}
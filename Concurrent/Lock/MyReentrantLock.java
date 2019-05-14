import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class MyReentrantLock {

	public static LinkedList<Integer> queue = new LinkedList<>();

	public static void main(String[] args) {
		handleReentrantLock();
	}

	public static void handleReentrantLock() {

		ReentrantLock lock = new ReentrantLock();

		Condition notFull = lock.newCondition();
		Condition notEmpty = lock.newCondition();


		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {

						lock.lock();

						if (queue.size() == 5) {
							notFull.await(); // 等到 notFull
						}

						System.out.println("into =>" + queue.offer((int)Math.ceil(Math.random() * 100)));

						notEmpty.signal();

					} catch(InterruptedException e) {
						e.printStackTrace();
					} finally {
						lock.unlock();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						lock.lock();

						if (queue.size() == 0) {
							notEmpty.await(); // 等到 notFull
						}

						System.out.println("fetch=>" + queue.poll());

						notFull.signal();

					} catch(InterruptedException e) {
						e.printStackTrace();
					} finally {
						lock.unlock();
					}
				}
			}
		}).start();
	}
}
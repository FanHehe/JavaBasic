import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyReadWriteLock {

	public static int value = 1;

	public static void main(String[] args) {
		handleReadWriteLock();
	}

	public static void handleReadWriteLock() {
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

		Lock readLock = lock.readLock();
		Lock writeLock = lock.writeLock();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					readLock.lock();

					System.out.println("current value = " + value);

				} catch(Exception e) {

				} finally {
					readLock.unlock();
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					readLock.lock();
					System.out.println("modify value ===");
					value ++;
				} catch(Exception e) {

				} finally {
					readLock.unlock();
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					readLock.lock();
					System.out.println("current value = " + value);

				} catch(Exception e) {

				} finally {
					readLock.unlock();
				}
			}
		}).start();
	}
}
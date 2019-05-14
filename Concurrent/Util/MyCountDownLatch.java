import java.util.concurrent.CountDownLatch;

public class MyCountDownLatch {
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(5);

		work(latch, 1);
		work(latch, 2);
		work(latch, 3);
		work(latch, 4);
		work(latch, 5);
	}

	public static void work(CountDownLatch latch, int sequence) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("" + sequence + "before");
				latch.countDown();

				try {
					latch.await();
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("" + sequence + "after");
			}
		}).start();
	}
}
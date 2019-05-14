import java.util.concurrent.Semaphore;

public class MySemaphore {

	public static int value = 1;
	public static void main(String[] args) {
		handleSemaphore();
	}

 	public static void handleSemaphore() {
 		Semaphore sem = new Semaphore(1);

 		int i = 0;

 		while(i++ < 10) {
 			semaphoreWork(sem);
 		}

 		System.out.println(value);
 	}

 	public static void semaphoreWork(Semaphore sem) {
 		new Thread(new Runnable() {
			@Override
			public void run() {

				try {
					sem.acquire();
					value ++;
					System.out.println(value + "===========");
					sem.release();

					
				} catch(InterruptedException e) {
					e.printStackTrace();
				}

			}
		}).start();
 	}
}
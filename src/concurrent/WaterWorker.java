package concurrent;

import java.util.concurrent.Semaphore;

public class WaterWorker implements Runnable {
	
	static Semaphore hNumber= new Semaphore(0);
	static Semaphore oNumber= new Semaphore(0);
	
	@Override
	public void run() {
		while(hNumber.availablePermits()>0 || oNumber.availablePermits()>0) {
			try {
				hNumber.acquire(2);
				oNumber.acquire(1);
				System.out.println("One water is produced");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

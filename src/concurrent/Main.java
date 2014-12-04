package concurrent;

public class Main {
	
	public static void main(String[] args) {
		for(int i=0; i<1; i++) {
			//WaterWorker.hNumber.release();
			WaterWorker.hNumber.release();
			WaterWorker.oNumber.release();
		}
		
		Thread w1 = new Thread(new WaterWorker());
		Thread w2 = new Thread(new WaterWorker());
		w1.start();
		w2.start();
		while(w1.isAlive() || w2.isAlive()) {
			System.out.println("Still waiting one of threads to finish");
		}
	}
}

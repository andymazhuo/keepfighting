package concurrent;

public class Worker implements Runnable{

	private String myName;
	public Worker(String name) {
		myName = name;
	}

	@Override
	public void run() {
		for(int i=1; i<=100; i++) {
			System.out.println(myName + " (" + i + ")");
		}
	}
	
	public static void main(String[] args) {
		Worker w1 = new Worker("Thread 1");
		Worker w2 = new Worker("Thread 2");
		Worker w3 = new Worker("Thread 3");
		new Thread(w1).start();
		new Thread(w2).start();
		new Thread(w3).start();
	}
}

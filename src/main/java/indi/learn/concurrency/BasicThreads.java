package indi.learn.concurrency;

public class BasicThreads {

	public static void main(String[] args) {
		Thread t = new Thread(new LiftOff());
		t.start();
		System.out.println("waiting for thread termination");
	}
}

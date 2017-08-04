package indi.learn.concurrency;

public class LiftOff implements Runnable {
	protected int countDown = 10;
	private static int count = 0;
	private final int id = count++;
	
	public LiftOff() {
	}
	
	public LiftOff(int countDown) {
		this.countDown = countDown;
	}
	
	protected String status() {
		return "id: " + id + ", " + "countDown: " + countDown; 
	}
	
	public void run() {
		while(--countDown > 0) {
			System.out.println(status());
			Thread.yield();
		}
	}

}

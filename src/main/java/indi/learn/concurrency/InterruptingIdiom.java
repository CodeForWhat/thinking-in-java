package indi.learn.concurrency;

import java.util.concurrent.TimeUnit;

class NeedsCleanup {
	private final int id;
	public NeedsCleanup(int idn) {
		this.id = idn;
		System.out.println("NeedsCleanup " + id);
	}
	
	public void cleanup() {
		System.out.println("Cleaning up " + id);
	}
}


class Blocked3 implements Runnable {
	private volatile double d = 0D;
	
	public void run() {
		try {
			while(!Thread.currentThread().isInterrupted()) {
				NeedsCleanup nc1 = new NeedsCleanup(1);
				try {
					System.out.println("sleeping");
					TimeUnit.SECONDS.sleep(1);
					NeedsCleanup nc2 = new NeedsCleanup(2);
					try {
						System.out.println("Calculating");
						for(int i = 1; i < 2500000; i++) {
							d = d + (Math.PI + Math.E) / d;
						}
						System.out.println("Finishing time-comsuming operation");
					} finally {
						nc2.cleanup();
					}
				} finally {
					nc1.cleanup();
				}
				System.out.println("Exiting via InterruptedException");
			}
			System.out.println("Exiting via while() test");
		} catch (InterruptedException e) {
			System.out.println("Exiting via InterruptedException");
		}
	}
}
public class InterruptingIdiom {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Blocked3());
		t.start();
		TimeUnit.MILLISECONDS.sleep(3000);
		t.interrupt();
	}

}

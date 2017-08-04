package indi.learn.concurrency;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable {
	
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread() + " " + this);
			try {
				TimeUnit.MICROSECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("sleep() interrupted");
			} 
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		for(int i = 0; i < 10; i++) {
			Thread t = new Thread(new SimpleDaemons());
			t.setDaemon(true);
			t.start();
		}
		
		System.out.println("All daemons were started.");
		TimeUnit.MICROSECONDS.sleep(1000000);
		System.out.println("main exit");
	}

}

package indi.learn.concurrency;

import java.util.concurrent.TimeUnit;

public class SimpleThread extends Thread {
	private int countDown = 5;
	private static int threadCount = 0;
	
	public SimpleThread() {
		super(Integer.toString(++threadCount));
		start();
	}
	
	@Override
	public String toString() {
		return "Thread-" + getName() + "-count: " + countDown;
	}
	
	@Override
	public void run() {
		while(true) {
			System.out.println(this);
			if(--countDown == 0) 
				return;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 5; i++) {
			Thread t = new SimpleThread();
		}
		TimeUnit.SECONDS.sleep(2);
	}

}

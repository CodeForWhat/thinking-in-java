package indi.learn.concurrency;

import java.util.concurrent.TimeUnit;

class ADademon implements Runnable {
	public void run() {
		System.out.println("try clause");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch(InterruptedException e) {
			System.out.println("catch");
		} finally {
			System.out.println("finally");
		}
	}
}

public class DaemonsDontRunFinally {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new ADademon());
//		t.setDaemon(true);
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("main thread exit");
	}
}

package indi.learn.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DaemonFromFactory implements Runnable {

	public void run() {
		while (true) {
			try {
				System.out.println(Thread.currentThread() + " " + this);
				TimeUnit.MICROSECONDS.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("sleep() interrupted");
			}
		}

	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for (int i = 0; i < 5; i++) {
			service.execute(new DaemonFromFactory());
		}
		System.out.println("All daemons started");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("main thread exit");
	}

}

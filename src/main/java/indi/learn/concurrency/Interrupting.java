package indi.learn.concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class SleepBlocked implements Runnable {
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch(InterruptedException e) {
			System.out.println("Interrupted exception");
		}
		System.out.println("Exiting SleepBlocked.run()");
	}
}

class IOBlocked implements Runnable {
	private InputStream in;
	
	public IOBlocked(InputStream in) {
		this.in = in;
	}
	
	public void run() {
		try {
			System.out.println("Waiting for read:");
			in.read();
		} catch(IOException e) {
			if(Thread.currentThread().isInterrupted()) {
				System.out.println("Interrupted from io blocked");
			} else {
				throw new RuntimeException(e);
			}
		}
		
	}
}

class SynchronizedBlocked implements Runnable {
	public synchronized void f() {
		while(true) {
			Thread.yield();
		}
	}
	
	public SynchronizedBlocked() {
		new Thread(){
			public void run() {
				f();
			}
		}.start();
	}
	
	
	public void run() {
		System.out.println("Trying to call f()");
		f();
		System.out.println("Exiting SynchronizedBlocked.run()");
	}
}

public class Interrupting {
	private static ExecutorService service = Executors.newCachedThreadPool();
	
	static void test(Runnable r) throws InterruptedException {
		Future<?> f = service.submit(r);
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Interrupting " + r.getClass().getName());
		f.cancel(true);
		System.out.println("interrupt send to " + r.getClass().getName());
	}

	public static void main(String[] args) throws InterruptedException {
		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(3);
		System.out.println("Aborting with System.exit(0)");
		System.exit(0); // ... since last 2 interrupts failed
	}

}

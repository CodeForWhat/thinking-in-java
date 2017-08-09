package indi.learn.concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Blocker {
	synchronized void waitingCall() {
		try {
			while(!Thread.currentThread().isInterrupted()) {
				wait();
				System.out.println(Thread.currentThread() + " ");
			}
		} catch(InterruptedException e) {
			
		}
	}
	
	synchronized void prod() {
		notify();
	}
	
	synchronized void prodAll() {
		notifyAll();
	}
}

class Task implements Runnable {
	static Blocker blocker = new Blocker();
	public void run() {
		blocker.waitingCall();
	}
}

class Task2 implements Runnable {
	static Blocker blocker = new Blocker();
	public void run() {
		blocker.waitingCall();
	}
}

public class NotifyVsNotifyAll {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++) {
			service.execute(new Task());
		}
		service.execute(new Task2());
		
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
			boolean prod = true;
			public void run() {
				if(prod) {
					System.out.println("\nnotify");
					Task.blocker.prod();
					prod = false;
				} else {
					System.out.println("\nnotifyAll");
					Task.blocker.prodAll();
					prod = true;
				}
			}
		}, 400, 400);
		
		TimeUnit.SECONDS.sleep(5);
		timer.cancel();
		System.out.println("\ntimer canceled");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("\nshuting down");
		service.shutdownNow();
	}
}

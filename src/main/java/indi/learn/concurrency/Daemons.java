package indi.learn.concurrency;

import java.util.concurrent.TimeUnit;

public class Daemons implements Runnable {

	public void run() {
		Thread[] t = new Thread[10];
		for(int i = 0; i < t.length; i++) {
			t[i] = new Thread(new DaemonSpawn());
		}
		
		for(int i = 0; i < t.length; i++) {
			System.out.println("t[" + i  + "] isDaemon = " + t[i].isDaemon());
		}
		
		while(true) 
			Thread.yield();
		
	}
	
	class DaemonSpawn implements Runnable {

		public void run() {
			while(true)
				Thread.yield();
			
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Daemons());
		t.setDaemon(true);
		t.start();
		
		TimeUnit.SECONDS.sleep(2);
		System.out.println("main thread exit");
	}

}

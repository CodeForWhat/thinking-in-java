package indi.learn.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
	private ReentrantLock lock = new ReentrantLock();
	
	public void untimed() {
		boolean captured = lock.tryLock();
		if (captured) {
			try {
				System.out.println("untimed capture a lock");
			} finally {
				lock.unlock();
			} 
		} else {
			System.out.println("untimed try lock failed");
		}
	}
	
	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (captured) {
			try {
				System.out.println("timed captured a lock");
			} finally {
				lock.unlock();
			} 
		} else {
			System.out.println("timed try lock failed");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final AttemptLocking al = new AttemptLocking();
		al.untimed();
		al.timed();
		
		new Thread(){
			{
				setDaemon(true);
			}
			
			public void run() {
				al.lock.lock();
				System.out.println("Thread capture the lock");
			}
		}.start();
		
		TimeUnit.SECONDS.sleep(2);
		al.untimed();
		al.timed();
	}
}

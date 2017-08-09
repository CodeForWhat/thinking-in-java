package indi.learn.concurrency.waxomatic2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Car {
	private boolean waxOn = false;
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void waitForWaxing() throws InterruptedException {
		lock.lock();
		try {
			while(waxOn == true) {
				condition.await();
			}
		} finally {
			lock.unlock();
		}
	}
	
	public void waitForBuffing() throws InterruptedException {
		lock.lock();
		try {
			while(waxOn == false) {
				condition.await();
			}
		} finally {
			lock.unlock();
		}
	}
	
	public void waxOn() {
		lock.lock();
		try {
			waxOn = true;
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public void buffed() {
		lock.lock();
		try {
			waxOn = false;
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
}

class WaxOn implements Runnable {
	private Car car;
	public WaxOn(Car car) {
		this.car = car;
	}
	
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("Wax On");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxOn();
				car.waitForWaxing();
			}
		} catch(InterruptedException e) {
			System.out.println("WaxOn interrupted");
		}
		System.out.println("Ending Wax On task");
	}
}

class WaxOff implements Runnable {
	private Car car;
	public WaxOff(Car car) {
		this.car = car;
	}
	
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car.waitForBuffing();
				System.out.println("Wax Off");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();
			}
		} catch(InterruptedException e) {
			System.out.println("WaxOff interrupted");
		}
		System.out.println("Ending Wax Off task");
	}
}

public class WaxOMatic2 {

	public static void main(String[] args) throws InterruptedException {
		Car car = new Car();
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(new WaxOn(car));
		service.execute(new WaxOff(car));
		
		TimeUnit.SECONDS.sleep(5);
		service.shutdownNow();
	}

}

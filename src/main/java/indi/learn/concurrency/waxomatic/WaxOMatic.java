package indi.learn.concurrency.waxomatic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car {
	private boolean waxOn = false;

	public synchronized void waxOn() {
		waxOn = true;
		notifyAll();
	}

	public synchronized void buffed() {
		waxOn = false;
		notifyAll();
	}

	public synchronized void waitForWaxing() throws InterruptedException {
		while (waxOn == true) {
			wait();
		}
	}

	public synchronized void waitForBuffing() throws InterruptedException {
		while (waxOn == false) {
			wait();
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
			while (!Thread.currentThread().isInterrupted()) {
				System.out.println("Wax On");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxOn();
				car.waitForWaxing();
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting via interrupt");
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
			while (!Thread.currentThread().isInterrupted()) {
				car.waitForBuffing();
				System.out.println("Wax Off");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Ending Wax Off task");
	}
}

public class WaxOMatic {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		Car car = new Car();
		service.execute(new WaxOn(car));
		service.execute(new WaxOff(car));
		TimeUnit.SECONDS.sleep(5);
		service.shutdownNow();
	}

}

package indi.learn.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Accessor implements Runnable {
	private final int id;
	
	public Accessor(int idn) {
		id = idn;
	}
	
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}
	
	public String toString() {
		return "#" + id + ": " + ThreadLocalVariableHolder.get();
	}
}

public class ThreadLocalVariableHolder {
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
		private Random rand = new Random(47);
		
		public synchronized Integer initialValue() {
			return rand.nextInt(10000);
		}
	};
	public static void increment() {
		value.set(value.get() + 1);
	}
	
	public static Integer get() {
		return value.get();
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++) {
			service.execute(new Accessor(i));
		}
		
		TimeUnit.SECONDS.sleep(2);
		service.shutdown();
	}

}

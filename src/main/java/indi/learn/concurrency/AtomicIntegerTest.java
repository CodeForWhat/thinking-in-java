package indi.learn.concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest implements Runnable {
	private AtomicInteger i = new AtomicInteger(0);
	
	public Integer getValue() {
		return i.get();
	}
	
	public void evenGenerator() {
		i.addAndGet(2);
	}
	
	public void run() {
		while(true) {
			evenGenerator();
		}
	}

	public static void main(String[] args) {
		new Timer().schedule(new TimerTask(){
			public void run() {
				System.err.println("Aborting");
				System.exit(0);
			}
		}, 5000);
		
		AtomicIntegerTest test = new AtomicIntegerTest();
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(test);
		
		while(true) {
			int value = test.getValue();
			if(value % 2 != 0) {
				System.out.println(value);
				System.exit(0);
			}
		}
	}

	

}
	
package indi.learn.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
	private IntGenerator generator;
	private int id;
	
	public EvenChecker(IntGenerator generator, int ident) {
		this.generator = generator;
		this.id = ident;
	}
	
	public void run() {
		while(!generator.isCanceled()) {
			int val = generator.next();
			System.out.println(Thread.currentThread().getName() + ":" + val);
			if(val % 2 != 0) {
				System.out.println(val + "is not even");
				generator.cancel();
			}
		}
	}

	public static void test(IntGenerator generator, int counter) {
		System.out.println("Press Ctrl-C to terminate");
		ExecutorService service = Executors.newFixedThreadPool(counter);
		for(int i = 1; i <= counter; i++) {
			service.execute(new EvenChecker(generator, i));
		}
		service.shutdown();
	}
	
	public static void test(IntGenerator generator) {
		test(generator, 10);
	}
}

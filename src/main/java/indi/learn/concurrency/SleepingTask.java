package indi.learn.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepingTask extends LiftOff {
	public void run() {
		while(countDown-- > 0) {
			System.out.println(status());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		
		for(int i = 0 ; i <  5; i++) {
			service.execute(new SleepingTask());
		}
		service.shutdown();
	}

}

package indi.learn.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Count {
	private int count;
	
	private Random rand = new Random(47);
	
	public synchronized int value() {
		return count;
	}
	
	public synchronized int increment() {
		int temp = count;
		if(rand.nextBoolean()) {
			Thread.yield();
		}
		return (count = ++temp);
	}
}

class Entrance implements Runnable {
	private static Count count = new Count();
	private int number;
	private static List<Entrance> entrances = new ArrayList<Entrance>();
	private static volatile boolean canceled = false;
	private final int id;
	
	public static void cancel() {
		canceled = true;
	}
	
	public Entrance(int id) {
		this.id = id;
		// Keep this task in a list. Also prevents
		// garbage collection of dead tasks:
		entrances.add(this);
	}
	
	public void run() {
		while(!canceled) {
			synchronized(this) {
				number++;
			}
			
			System.out.println(this + "total: " + count.increment());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch(InterruptedException e) {
				System.out.println("sleep interrupted");
			}
		}
		
		System.out.println("Stopping this");
	}
	
	public synchronized int getValue() {
		return number;
	}
	
	public static int getTotalCount() {
		return count.value();
	}
	
	public static int sumEntrances() {
		int sum  = 0;
		for(Entrance entrance : entrances) {
			sum += entrance.getValue();
		}
		
		return sum;
	}
	
	public String toString() {
		return "Entrance" + id + ":" + getValue();
	}
}

public class OrnamentalGarden {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++) {
			service.execute(new Entrance(i));
		}
		
		TimeUnit.SECONDS.sleep(3);
		Entrance.cancel();
		
		service.shutdown();
		if(service.awaitTermination(250, TimeUnit.MILLISECONDS)) {
			System.err.println("Some tasks were not terminated!");
		}
		System.out.println("Total: " + Entrance.getTotalCount());
		System.out.println("Sum: " + Entrance.sumEntrances());
	}

}

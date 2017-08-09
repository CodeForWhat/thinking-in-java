package indi.learn.concurrency;

import java.util.concurrent.TimeUnit;

class Sleeper extends Thread {
	private int duration;
	public Sleeper(String name, int duration) {
		super(name);
		this.duration = duration;
		start();
	}
	
	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(duration);
		} catch(InterruptedException e) {
			System.out.println(getName() + " was interrupted. " +
					"isInterrupted(): " + isInterrupted());
			return;
		}
		System.out.println(getName() + " has awakened");
	}
	
}

class Joiner extends Thread {
	private Sleeper sleeper;
	
	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}
	
	@Override
	public void run() {
		try {
			sleeper.join();
		} catch(InterruptedException e) {
			System.out.println(getName() + " Interrupted");
		}
		System.out.println(getName() + " join complete");
	}
}

public class Joining {

	public static void main(String[] args) {
		Sleeper sleepy = new Sleeper("sleepy", 5500), grumpy = new Sleeper("grumpy", 9999);
		Joiner dopey = new Joiner("dopey", sleepy), doc = new Joiner("doc", grumpy);
		
		grumpy.interrupt();
	}

}

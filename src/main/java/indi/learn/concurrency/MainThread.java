package indi.learn.concurrency;

public class MainThread {
	public static void main(String[] args) {
		Runnable r = new LiftOff();
		r.run();
	}

}

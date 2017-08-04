package indi.learn.concurrency;

public class MoreBasicThreads {
	public static void main(String[] args) {
		for(int i = 0; i < 4; i++) {
			Thread t = new Thread(new LiftOff());
			t.start();
		}
	}

}

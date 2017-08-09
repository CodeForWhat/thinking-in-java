package indi.learn.concurrency;

public class EvenGenerator extends IntGenerator {
	private int concurrentValue = 0;

	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}

	@Override
	public synchronized int next() {
		concurrentValue++;
		concurrentValue++;
		return concurrentValue;
	}

}

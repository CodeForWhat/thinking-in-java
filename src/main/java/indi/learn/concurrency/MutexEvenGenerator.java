package indi.learn.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class MutexEvenGenerator extends IntGenerator {
	private int concurrentValue;
	private static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {
		EvenChecker.test(new MutexEvenGenerator());
	}

	@Override
	public int next() {
		lock.lock();
		try {
			concurrentValue++;
			Thread.yield();
			concurrentValue++;
			return concurrentValue;
		} finally {
			lock.unlock();
		}
	}

}

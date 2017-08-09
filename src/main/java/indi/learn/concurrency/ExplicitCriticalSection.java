package indi.learn.concurrency;

import java.util.concurrent.locks.ReentrantLock;

class ExplicitPairManager1 extends PairManager {
	private ReentrantLock lock = new ReentrantLock();

	@Override
	public synchronized void increment() {
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			store(getPair());
		} finally {
			lock.unlock();
		}
	}

}

class ExplicitPairManager2 extends PairManager {
	private ReentrantLock lock = new ReentrantLock();

	@Override
	public void increment() {
		lock.lock();
		Pair temp = null;
		try {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		} finally {
			lock.unlock();
		}
		store(temp);
	}

}

public class ExplicitCriticalSection {

	public static void main(String[] args) {
		PairManager pman1 = new ExplicitPairManager1(), pman2 = new ExplicitPairManager2();
		CriticalSection.testApproaches(pman1, pman2);
	}

}

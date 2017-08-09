package indi.learn.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class ExceptionThread2 implements Runnable {
	public void run() {
		throw new RuntimeException("故意抛个异常>_<");
	}
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("Thread " + t.getName() + " throw an exception cause: " + e.getMessage());
	}
}

class HandlerThreadFactory implements ThreadFactory {
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		return t;
	}
}

public class CaptureUncaughtException {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool(new HandlerThreadFactory());
		service.execute(new ExceptionThread2());
		service.shutdown();
	}
}

package indi.learn.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NaiveExceptionHandling {

	public static void main(String[] args) {
		try {
			ExecutorService service = Executors.newCachedThreadPool();
			service.execute(new ExceptionThread());
		} catch(Exception e) {
			e.printStackTrace(System.out);
		} catch(Throwable t) {
			t.printStackTrace();
		}

	}

}

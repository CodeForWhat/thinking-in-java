package indi.learn.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class TaskWithResult implements Callable<String> {
	private int id;
	
	public TaskWithResult(int id) {
		this.id = id;
	}
	
	public String call() {
		return "the task result is " + id;
	}
}

public class CallableDemo {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		
		try {
			List<Future<String>> result = new ArrayList<Future<String>>();
			for (int i = 0; i < 5; i++) {
				//			service.execute(new TaskWithResult(i));
				result.add(service.submit(new TaskWithResult(i)));
			}
			for (Future<String> fs : result) {
				try {
					String s = fs.get();
					System.out.println(s);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		} finally {
			service.shutdown();
		}
	}

}

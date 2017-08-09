package indi.learn.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class Toast {
	public enum Status {
		DRY, BUTTERED, JAMMED;
	}

	private Status status = Status.DRY;
	private final int id;

	public Toast(int idn) {
		this.id = idn;
	}

	public void butter() {
		status = Status.BUTTERED;
	}

	public void jam() {
		status = Status.JAMMED;
	}

	public Status getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return "Toast " + id + ": " + status;
	}
}

class ToastQueue extends LinkedBlockingQueue<Toast> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6387678735294240440L;
}

class Toaster implements Runnable {
	private ToastQueue queue;
	private int counter;
	private Random rand = new Random(47);

	public Toaster(ToastQueue queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));

				Toast toast = new Toast(counter++);
				// insert into ToastQueue
				queue.put(toast);
			}
		} catch (InterruptedException e) {
			System.out.println("Toaster interrupted");
		}
		System.out.println("Toaster off");
	}
}

// Apply butter to toast
class Butterer implements Runnable {
	private ToastQueue dryQueue, butteredQueue;

	public Butterer(ToastQueue dryQueue, ToastQueue butteredQueue) {
		this.dryQueue = dryQueue;
		this.butteredQueue = butteredQueue;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast toast = dryQueue.take();

				toast.butter();
				System.out.println(toast);
				butteredQueue.put(toast);
			}
		} catch (InterruptedException e) {
			System.out.println("Butterer interrupted");
		}
		System.out.println("Butterer exit");
	}
}

// Apply jam to buttered toast:
class Jammer implements Runnable {
	private ToastQueue butteredQueue, jammedQueue;

	public Jammer(ToastQueue butteredQueue, ToastQueue jammedQueue) {
		this.butteredQueue = butteredQueue;
		this.jammedQueue = jammedQueue;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast toast = butteredQueue.take();
				toast.jam();
				System.out.println(toast);
				jammedQueue.put(toast);

			}
		} catch (InterruptedException e) {
			System.out.println("Jammer interrupted");
		}
		System.out.println("Jammer exit");
	}
}

// Consume the toast:
class Eater implements Runnable {
	private ToastQueue queue;
	private int counter = 0;

	public Eater(ToastQueue queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				// Blocks until next piece of toast is available:
				Toast t = queue.take();
				// Verify that the toast is coming in order,
				// and that all pieces are getting jammed:
				if (t.getId() != counter++ || t.getStatus() != Toast.Status.JAMMED) {
					System.out.println(">>>> Error: " + t);
					System.exit(1);
				} else
					System.out.println("Chomp! " + t);
			}
		} catch (InterruptedException e) {
			System.out.println("Eater interrupted");
		}
		System.out.println("Eater off");
	}
}

public class ToastOMatic {

	public static void main(String[] args) throws InterruptedException {
		ToastQueue dryQueue = new ToastQueue(), 
				butteredQueue = new ToastQueue(), 
				finishedQueue = new ToastQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue, butteredQueue));
		exec.execute(new Jammer(butteredQueue, finishedQueue));
		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}

}

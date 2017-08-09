package indi.learn.concurrency;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.Receiver;

class Sender implements Runnable {
	private PipedWriter writer;

	public Sender() {
		this.writer = new PipedWriter();
	}

	public PipedWriter getWriter() {
		return writer;
	}

	public void run() {
		try {
			for (char c = 'A'; c < 'Z'; c++) {
				writer.write(c);
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (IOException e) {
			System.out.println(e + " Sender write exception");
		} catch (InterruptedException e) {
			System.out.println(e + " Sender sleep interrupted");
		}
	}
}

class Reciever implements Runnable {
	private PipedReader reader;

	public Reciever(Sender sender) throws IOException {
		this.reader = new PipedReader(sender.getWriter());
	}

	public void run() {
		try {
			while (true) {
				// Blocks until characters are there:
				System.out.println("Read: " + (char) reader.read() + ", ");
			}
		} catch (IOException e) {
			System.out.println(e + " Receiver read exception");
		}
	}
}

public class PipedIO {

	public static void main(String[] args) throws Exception {
		Sender sender = new Sender();
		Reciever receiver = new Reciever(sender);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();
	}

}

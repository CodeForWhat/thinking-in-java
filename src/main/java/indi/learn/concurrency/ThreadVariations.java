package indi.learn.concurrency;

class InnerThread1 {
	private int countDown = 5;
	private Inner inner;
	
	public InnerThread1(String name) {
		this.inner = new Inner(name);
	}
	class Inner extends Thread {
		public Inner(String name) {
			super(name);
			start();
		}
		
		@Override
		public void run() {
			while(true) {
				System.out.println(this);
				if(countDown-- == 0) {
					return;
				}
			}
		}
		
		@Override
		public String toString() {
			return getName() + "-" + countDown;
		}
	}
}

class InnerThread2 {
	private int countDown = 5;
	private Thread t;
	
	public InnerThread2(String name) {
		t = new Thread(name) {
			@Override
			public void run() {
				while(true) {
					System.out.println(this);
					if(--countDown == 0) {
						return;
					}
				}
			}
			
			@Override
			public String toString() {
				return getName() + "-" + countDown;
			}
		};
		t.start();
	}
}

class InnerThread3 {
	private int countDown = 5;
	private Inner inner;
	
	class Inner implements Runnable {
		Thread t;
		public void run() {
			while(true) {
				System.out.println(this);
				if(--countDown == 0) {
					return;
				}
			}
		}
		
		@Override
		public String toString() {
			return t.getName() + "-" + countDown;
		}
		
		public Inner() {
			t = new Thread(this);
		}
	}
	
	public InnerThread3(String name) {
		(inner = new Inner()).t.start();
	}
}


class InnerThread4 {
	private int countDown = 5;
	private Thread t;
	
	public InnerThread4(String name) {
		t = new Thread(new Runnable(){
			public void run() {
				while(true) {
					System.out.println(this);
					if(countDown-- == 0) {
						return;
					}
				}
			}
			
			@Override
			public String toString() {
				return t.getName() + "-countDown-" + countDown;
			}
		}, name);
		t.start();
	}
	
}

public class ThreadVariations {

	public static void main(String[] args) {
//		new InnerThread1("InnerThread1");
//		new InnerThread2("InnerThread2");
//		new InnerThread3("InnerThread3");
		new InnerThread4("InnerThread4");
	}

}

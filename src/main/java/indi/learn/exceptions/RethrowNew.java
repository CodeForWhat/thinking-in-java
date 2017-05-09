package indi.learn.exceptions;

class OneException extends Exception {
	public OneException(String s) {
		super(s);
	}
}

class TwoException extends Exception {
	public TwoException(String s) {
		super(s);
	}
}

public class RethrowNew {
	
	public static void f() throws OneException {
		throw new OneException("thrown from f()");
	}

	public static void main(String[] args) {
		try {
			try {
				f();
			} catch(OneException e) {
				e.printStackTrace();
				throw new TwoException(e.getMessage());
			}
		} catch(TwoException e) {
			e.printStackTrace();
		}

	}

}

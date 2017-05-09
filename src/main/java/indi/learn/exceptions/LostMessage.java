package indi.learn.exceptions;

class VeryImportantException extends Exception {
	public String toString() {
		return "a very important exception";
	}
}

class HohumException extends Exception {
	public String toString() {
		return "a HohumException";
	}
}

public class LostMessage {
	void f() throws VeryImportantException {
		throw new VeryImportantException();
	}
	
	void g() throws HohumException {
		throw new HohumException();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			LostMessage lm = new LostMessage();
			try {
				lm.f();
			} finally {
				lm.g();
			}
		} catch(Exception e) {
			System.out.println(e);
		}

	}

}

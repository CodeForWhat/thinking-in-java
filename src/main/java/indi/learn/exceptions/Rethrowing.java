package indi.learn.exceptions;

public class Rethrowing {
	public static void f() throws Exception {
		throw new Exception("thrown from f()");
	}
	
	public static void g() throws Exception {
		try {
			f();
		} catch(Exception e) {
			throw e;
		}
	}
	
	public static void h() throws Exception {
		try {
			f();
		} catch(Exception e) {
			throw (Exception)e.fillInStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			g();
		} catch(Exception e) {
			System.out.println("g(): \n");
			e.printStackTrace();
		}
		
		try {
			h();
		} catch(Exception e) {
			System.out.println("h(): \n");
			e.printStackTrace();
		}
	}

}

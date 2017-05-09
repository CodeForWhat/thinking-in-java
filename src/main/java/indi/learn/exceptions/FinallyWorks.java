package indi.learn.exceptions;

class ThreeException extends Exception {
}

public class FinallyWorks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = 0;
		while (true) {
			try {
				if (count++ == 0) {
					throw new ThreeException();
				}
				System.out.println("Throw no Exception");
			} catch (ThreeException e) {
				System.out.println("ThreeException");
			} finally {
				System.out.println("System.out code in fianlly clause");
				if(count == 2) break;
			} 
		}
	}

}

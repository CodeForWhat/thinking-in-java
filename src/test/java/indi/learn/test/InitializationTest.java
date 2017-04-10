package indi.learn.test;

public class InitializationTest {
	
	private static int i = print("somehitng");
	
	private static int print(String str) {
		System.out.println("Helloworld");
		return ++i;
	}
	
	private static void printi() {
		System.out.println(i);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InitializationTest.printi();
	}

}

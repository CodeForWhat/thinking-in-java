package indi.learn.test;

public class InitializeDemo2 {
	private static InitializeDemo2 demo2 = new InitializeDemo2("static field");
	
	private InitializeDemo2 demo3 = new InitializeDemo2("non-static field");
	
	public static int count = 5;
	
	public InitializeDemo2(String str) {
		System.out.println(str);
//		System.out.println(++count);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new InitializeDemo2("main");

	}

}

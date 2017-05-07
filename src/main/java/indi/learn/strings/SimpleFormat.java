package indi.learn.strings;

public class SimpleFormat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.format("%-20s %-5d %-7.3f", "helloWorld", 100, 102.1111);
		
		System.out.println();
		System.out.println(String.format("%-20s %-5d %-7.3f", "helloWorld", 100, 102.1111));
	}

}

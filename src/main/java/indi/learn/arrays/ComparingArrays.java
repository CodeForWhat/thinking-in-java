package indi.learn.arrays;

import java.util.Arrays;

public class ComparingArrays {

	public static void main(String[] args) {
		int[] i = new int[10];
		int[] j = new int[10];
		Integer[] k = new Integer[10];
		
		Arrays.fill(i, 10);
		Arrays.fill(j, 10);
		
		System.out.println("Array.i == Arrays.j: " + Arrays.equals(i, j));
		
		Arrays.fill(k, new Integer(10));
//		System.out.println("Array.i == Arrays.k: " + Arrays.deepEquals(i, k));
	}

}

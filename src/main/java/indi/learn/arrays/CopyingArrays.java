package indi.learn.arrays;

import java.util.Arrays;

public class CopyingArrays {

	public static void main(String[] args) {
		int[] i = new int[7];
		int[] j = new int[10];
		
		Arrays.fill(i, 47);
		Arrays.fill(j, 99);
		System.out.println(Arrays.toString(i));
		System.out.println(Arrays.toString(j));
		
		System.arraycopy(i, 0, j, 0, i.length);
		System.out.println(Arrays.toString(i));
		System.out.println(Arrays.toString(j));
		
		int[] k = new int[5];
		Arrays.fill(k, 103);

	}

}

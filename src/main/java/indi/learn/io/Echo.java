package indi.learn.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Echo {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while((line = br.readLine()) != null) {
			System.out.println(line);
		}
	}

}

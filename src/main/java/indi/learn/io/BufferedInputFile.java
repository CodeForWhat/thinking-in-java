package indi.learn.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedInputFile {

	public static String read(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null) {
			sb.append(line);
			sb.append(System.getProperty("line.separator"));
		}
		br.close();
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(read("C:\\Users\\YinKailin-PC\\Desktop\\fws.e1.properties"));
	}

}

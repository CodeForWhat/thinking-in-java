package indi.learn.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BasicFileOutput {

	public static void main(String[] args) {
		String filename = "E:\\GitRepoForEclipseLearn\\thinking-in-java\\src\\main\\java\\indi\\learn\\io\\BasicFileOutput.out";
		PrintWriter pw = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					"E:\\GitRepoForEclipseLearn\\thinking-in-java\\src\\main\\java\\indi\\learn\\io\\BasicFileOutput.java"));
			pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			int count = 1;
			String line = null;
			while ((line = br.readLine()) != null) {
				pw.printf("%-2d: %s\r\n", count++, line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}

			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}

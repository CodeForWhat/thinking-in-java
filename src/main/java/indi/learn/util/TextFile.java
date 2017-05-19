package indi.learn.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TextFile extends ArrayList<String> {
	private static final long serialVersionUID = -6931435846819543689L;

	// read the content of a file into a single String object
	public static String read(String filename) {
		StringBuilder builder = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));

			String line = null;
			while ((line = br.readLine()) != null) {
				builder.append(line);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {

				}
			}
		}
		return builder.toString();
	}

	// write a String object into file specified by the filename
	public static void write(String filename, String text) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(filename);
			pw.print(text);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			pw.close();
		}
	}

	// read a file, splited by custom regular expression
	public TextFile(String filename, String splitter) {
		super(Arrays.asList(read(filename).split(splitter)));
	}

	// normally read
	public TextFile(String filename) {
		this(filename, "\n");
	}

	public void write(String filename) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(filename);

			for (String contentFragment : this) {
				pw.print(contentFragment);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			pw.close();
		}
	}

	public static void main(String[] args) {
		String file = read(
				"E:\\GitRepoForEclipseLearn\\thinking-in-java\\src\\main\\java\\indi\\learn\\util\\TextFile.java");
		write("C:\\Users\\YinKailin-PC\\Desktop\\test.txt", file);
		
		TextFile text = new TextFile("C:\\Users\\YinKailin-PC\\Desktop\\test.txt");
		text.write("C:\\Users\\YinKailin-PC\\Desktop\\test2.txt");
		// Break into unique sorted list of words:
		TreeSet<String> words = new TreeSet<String>(
				new TextFile("E:\\GitRepoForEclipseLearn\\thinking-in-java\\src\\main\\java\\indi\\learn\\util\\TextFile.java", "\\W+"));
		// Display the capitalized words:
		System.out.println(words.headSet("a"));
	}

}

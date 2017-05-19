package indi.learn.io;

import java.io.PrintWriter;

public class ChangeSystemOut {

	public static void main(String[] args) {
		PrintWriter pwt = new PrintWriter(System.out, true);
		PrintWriter pwf = new PrintWriter(System.out, false);
		PrintWriter pw = new PrintWriter(System.out);
		
		pwt.println("System.out, true");
		pwf.println("System.out, false");
		pw.println("System.out");
		pwf.flush();
		pw.flush();
		System.out.flush();
	}

}

package indi.learn.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

public class FormattedMemoryInput {

	public static void main(String[] args) throws IOException {
		try {
			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(BufferedInputFile
					.read("E:\\GitRepoForEclipseLearn\\thinking-in-java\\src\\main\\java\\indi\\learn\\io\\MemoryInput.java")
					.getBytes()));
			while(true) {
				System.out.print((char)dis.readByte());
			}
		} catch (EOFException e) {
			System.out.print("end of file");
		}
		
	}

}

package indi.learn.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

public class TestEOF {

	public static void main(String[] args) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(BufferedInputFile
				.read("E:\\GitRepoForEclipseLearn\\thinking-in-java\\src\\main\\java\\indi\\learn\\io\\MemoryInput.java")
				.getBytes()));
		try {
			while(dis.available() > 0) {
				System.out.print((char)dis.readByte());
			}
		} catch(EOFException e) {
			System.out.println(e);
		}
	}

}

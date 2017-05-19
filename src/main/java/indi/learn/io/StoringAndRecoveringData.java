package indi.learn.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StoringAndRecoveringData {
	public static void main(String[] args) throws IOException {
		String filename = "data.txt";
		
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
		dos.writeDouble(3.14159);
		dos.writeUTF("That was pi");
		dos.writeDouble(1.41413);
		dos.writeUTF("Square root of 2");
		dos.flush();
		dos.close();
		
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
//		System.out.println(dis.readDouble());
		// Only readUTF() will recover the
		// Java-UTF String properly:
//		System.out.println(dis.readUTF());
//		System.out.println(dis.readDouble());
//		System.out.println(dis.readUTF());
		
		System.out.println(dis.readUTF());
		dis.close();
		
		
	}

}

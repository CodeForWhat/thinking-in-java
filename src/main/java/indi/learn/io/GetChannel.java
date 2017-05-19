package indi.learn.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {
	private static final int BUFF_SIZE = 1024;

	public static void main(String[] args) throws Exception {
		
		FileChannel fc = new FileOutputStream("C:\\Users\\YinKailin-PC\\Desktop\\data.txt").getChannel();
		fc.write(ByteBuffer.wrap("some text".getBytes()));
		fc.close();
		
		fc = new RandomAccessFile("C:\\Users\\YinKailin-PC\\Desktop\\data.txt", "rw").getChannel();
		fc.position(fc.size());
		fc.write(ByteBuffer.wrap("some more".getBytes()));
		fc.close();
		
		
		fc = new FileInputStream("C:\\Users\\YinKailin-PC\\Desktop\\data.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BUFF_SIZE);
		fc.read(buff);
		fc.close();
		buff.flip();
		while(buff.hasRemaining()) {
			System.out.println((char)buff.get());
		}
	}

}

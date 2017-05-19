package indi.learn.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class BufferToText {

	public static void main(String[] args) throws Exception {
		String filename = "C:\\Users\\YinKailin-PC\\Desktop\\data2.txt";
		
		FileChannel fc = new FileOutputStream(filename).getChannel();
		fc.write(ByteBuffer.wrap("你好世界".getBytes()));
		fc.close();
		
		fc = new FileInputStream(filename).getChannel();
		ByteBuffer buff = ByteBuffer.allocate(1024);
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer().toString());
		fc.close();
		
		String encoding = System.getProperty("file.encoding");
		System.out.println("decoding using the charset: " + encoding + " " + Charset.forName(encoding).decode(buff));
		
		fc = new FileOutputStream(filename).getChannel();
		fc.write(ByteBuffer.wrap("通道处理机".getBytes("UTF-16BE")));
		fc.close();
		
		fc = new FileInputStream(filename).getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());
		
		fc = new FileOutputStream(filename).getChannel();
		buff = ByteBuffer.allocate(24);
		buff.asCharBuffer().put("缓冲区");
		fc.write(buff);
		fc.close();
		
		fc = new FileInputStream(filename).getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());
		
		
	}

}

package indi.learn.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelCopy {

	public static void main(String[] args) throws Exception {
		String srcFilename = "C:\\Users\\YinKailin-PC\\Desktop\\TextFile.java";
		String desFilename = "C:\\Users\\YinKailin-PC\\Desktop\\TextFile1.java";
		
		int bufSize = 2000;
		
		ByteBuffer bb = ByteBuffer.allocate(bufSize);
		FileChannel inFc = new FileInputStream(srcFilename).getChannel();
		FileChannel outFc = new FileOutputStream(desFilename).getChannel();
		long startTime = System.currentTimeMillis();
		while(inFc.read(bb) != -1) {
			bb.flip();
			outFc.write(bb);
			bb.clear();
		}
		inFc.close();
		outFc.close();
		System.out.println(System.currentTimeMillis() - startTime);
		
		bb = ByteBuffer.allocateDirect(bufSize);
		inFc = new FileInputStream(srcFilename).getChannel();
		outFc = new FileOutputStream(desFilename).getChannel();
		startTime = System.currentTimeMillis();
		while(inFc.read(bb) != -1) {
			bb.flip();
			outFc.write(bb);
			bb.clear();
		}
		inFc.close();
		outFc.close();
		System.out.println(System.currentTimeMillis() - startTime);
		
	}

}

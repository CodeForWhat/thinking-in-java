package indi.learn.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class TransferTo {

	public static void main(String[] args) throws Exception {
		String srcFilename = "C:\\Users\\YinKailin-PC\\Desktop\\TextFile.java";
		String desFilename = "C:\\Users\\YinKailin-PC\\Desktop\\TextFile1.java";
		
		FileChannel fc = new FileInputStream(srcFilename).getChannel();
		WritableByteChannel wbc = new FileOutputStream(desFilename).getChannel();
		
		fc.transferTo(0, fc.size(), wbc);
		
		fc.close();
		wbc.close();
	}

}

package indi.learn.io;

import java.io.File;

public class MakeDirectories {

	public static void main(String[] args) {
		
		File file = new File("C:\\Users\\YinKailin-PC\\Desktop\\files");
		if(!file.exists()) {
			file.mkdirs();
		}

	}

}

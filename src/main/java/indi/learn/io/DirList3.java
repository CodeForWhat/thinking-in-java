package indi.learn.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class DirList3 {

	public static void main(final String[] args) {
		File file = new File("E:\\documentations\\jdk1.8.0_92_docs\\api");
		
		String[] list = file.list(new FilenameFilter(){
			public boolean accept(File dir, String name) {
				Pattern pattern = Pattern.compile(args[0]);
				return pattern.matcher(name).matches();
			}
		});
		
		for(String s : list) {
			System.out.println(s);
		}
	}

}

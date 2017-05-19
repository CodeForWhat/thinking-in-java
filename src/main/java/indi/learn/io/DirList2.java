package indi.learn.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class DirList2 {
	
	// anonymous inner class
	public static FilenameFilter filter(final String regex) {
		return new FilenameFilter() {
			private Pattern pattern;
			public boolean accept(File dir, String name) {
				pattern = Pattern.compile(regex);
				return pattern.matcher(name).matches();
			}
		};
	}
	
	public static void main(String[] args) {
		File file = new File("E:\\documentations\\jdk1.8.0_92_docs\\api");
		String[] list = file.list();
		for(String s : list) {
			System.out.println(s);
		}
		
		System.out.println();
		
		list = file.list(filter("[\\w\\W]*\\.html"));
		for(String s : list) {
			System.out.println(s);
		}
	}

}

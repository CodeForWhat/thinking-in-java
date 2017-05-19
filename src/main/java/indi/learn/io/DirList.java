package indi.learn.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {

	static class DirFilter implements FilenameFilter {
		private Pattern pattern;
		
		public DirFilter(String regex) {
			pattern = Pattern.compile(regex);
		}
		
		public boolean accept(File dir, String name) {
			return pattern.matcher(name).matches();
		}
	}
	
	public static void main(String[] args) {
		File file = new File("./../");
		
		// list all
		String[] list = file.list();
		for(String s : list) {
			System.out.println(s);
		}
		
		System.out.println();
		
		// list filtered by filter
		list = file.list(new DirFilter("(\\w)+ing(\\w|\\W)*"));
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		for(String s : list) {
			System.out.println(s);
		}

	}

}

package indi.learn.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class Directory {
	
	private static File[] local(String dir, final String regex) {
		return new File(dir).listFiles(new FilenameFilter(){
			public boolean accept(File dir, String name) {
				return Pattern.compile(regex).matcher(name).matches();
			}
		});
	}
	
	private static File[] local(File dir, final String regex) {
		return dir.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String name) {
				return Pattern.compile(regex).matcher(name).matches();
			}
		});
	}
	
	private static TreeInfo walk(String start) {
		return recurseDir(new File(start), ".*");
	}
	
	private static TreeInfo walk(File start) {
		return recurseDir(start, ".*");
	}
	
	private static TreeInfo walk(String start, String regex) {
		return recurseDir(new File(start), regex);
	}
	
	private static TreeInfo walk(File start, String regex) {
		return recurseDir(start, regex);
	}
	
	static TreeInfo recurseDir(File startDir, String regex) {
		TreeInfo ti = new TreeInfo();
		for(File file : startDir.listFiles()) {
			if(file.isDirectory()) {
				ti.dirs.add(file);
				ti.addAll(recurseDir(file, regex));
			} else {
				ti.files.add(file);
			}
		}
		
		return ti;
	}
	
	public static class TreeInfo implements Iterable<File> {
		public List<File> files = new ArrayList<File>();
		public List<File> dirs = new ArrayList<File>();

		public Iterator<File> iterator() {
			return files.iterator();
		}
		
		public void addAll(TreeInfo other) {
			files.addAll(other.files);
			dirs.addAll(other.dirs);
		}
		
		public String toString() {
			return "files: " + files.toString() + ", dirs: " + dirs.toString();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(walk("E:\\documentations\\jdk1.8.0_92_docs\\api"));
	}

}

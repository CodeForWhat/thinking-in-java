package indi.learn.typeinfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class ShowMethods {
	
	private static String usage = "usage:\n" +
			"ShowMethods qualified.class.name\n" +
			"To show all methods in class or:\n" +
			"ShowMethods qualified.class.name word\n" +
			"To search for methods involving ‘word’";
	
	private static Pattern pattern = Pattern.compile("\\w+\\.");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length < 1) {
			System.out.println(usage);
			System.exit(-1);
		}
		
		int lines = 0;
		
		try {
			Class<?> c = Class.forName(args[0]);
			Method[] methods = c.getMethods();
			Constructor<?>[] constructors = c.getConstructors();
			if(args.length == 1) {
				for(Method method : methods) {
					System.out.println(pattern.matcher(method.toString()).replaceAll(""));
				}
				
				for(Constructor<?> constructor : constructors) {
					System.out.println(pattern.matcher(constructor.toString()).replaceAll(""));
				}
			} else {
				for(Method method : methods) {
					if(method.toString().indexOf(args[1]) >= 0) {
						System.out.println(pattern.matcher(method.toString()).replaceAll(""));
					}
				}
				for(Constructor<?> constructor : constructors) {
					if(constructor.toString().indexOf(args[1]) >= 0) {
						System.out.println(pattern.matcher(constructor.toString()).replaceAll(""));
					}
				}
			}
			
		}catch(ClassNotFoundException e) {
			System.out.println("Class not found");
		}
	}

}

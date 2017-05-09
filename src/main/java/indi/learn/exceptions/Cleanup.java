package indi.learn.exceptions;

public class Cleanup {

	public static void main(String[] args) {
		try {
//			InputFile inputFile = new InputFile("String");
			InputFile inputFile = new InputFile("C:\\Users\\YinKailin-PC\\Desktop\\fws.e1.properties");
			
			try {
				String s = null;
				while((s = inputFile.getLine()) != null) {
					System.out.println(s);
				}
			} catch(Exception e) {
				System.out.println(e);
			} finally {
				inputFile.dispose();
			}
		} catch(Exception e) {
			System.out.println("Construction failed");
		}
	}

}

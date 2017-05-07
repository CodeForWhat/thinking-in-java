package indi.learn.typeinfo.toys;

public class GenericToyTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Class<FancyToy> ftc = FancyToy.class;
		
		FancyToy ft = ftc.newInstance();
		
		Class<? super FancyToy> ftsc = ftc.getSuperclass();
		
//		Compile Error:Type mismatch: cannot convert from capture#2-of ? super FancyToy to Toy
//		Toy t = ftsc.newInstance();
		Object obj = ftsc.newInstance(); // works fine
	}

}

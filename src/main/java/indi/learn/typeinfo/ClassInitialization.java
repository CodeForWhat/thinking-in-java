package indi.learn.typeinfo;

import java.util.Random;

class Initable1 {
	static final int staticFinal = 47;
	static final int staticFinal2 = ClassInitialization.rand.nextInt(100);
	static {
		System.out.println("Initialization Initable1");
	}
}

class Initable2 {
	static int staticNonFinal = 44;
	static {
		System.out.println("Initialization Initable2");
	}
}

class Initable3 {
	static int staticNonFinal = 147;
	static {
		System.out.println("initialization Initable3");
	}
}

public class ClassInitialization {
	public static Random rand = new Random(47);
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class cc = Initable1.class; // does not trigger the initialization
		
		// does not trigger the initialization because the variable is a compile constant
		System.out.println(Initable1.staticFinal); 
		
		// does trigger the initialization
		System.out.println(Initable1.staticFinal2);
		
		// Does trigger initialization:
		System.out.println(Initable2.staticNonFinal);
		
		Class initable3 = Class.forName("indi.learn.typeinfo.Initable3");
		System.out.println("After creating Initable3 ref");
		System.out.println(Initable3.staticNonFinal);
		
		
		
	

	}

}

package indi.learn.typeinfo.toys;

interface HasBatteries {
}

interface Waterproof {
}

interface Shoots {
}

class Toy {
	Toy() {
	}

	Toy(int i) {
	}
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots {
	FancyToy() {
		super(1);
	}
}

public class ToyTest {
	static void printinfo(Class cc) {
		System.out.println("Class Name: " + cc.getName());
		System.out.println("Is Interface? : " + cc.isInterface());
		System.out.println("Simple Name: " + cc.getSimpleName());
		System.out.println("Canonical Name: " + cc.getCanonicalName());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Class cc = null;
		try {
//			cc = Class.forName("FancyToy"); class not found
//			cc = Class.forName("typeinfo.toys.FancyToy"); class not found
			cc = Class.forName("indi.learn.typeinfo.toys.FancyToy");
			
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			System.exit(-1);
		}
		
		printinfo(cc);
		
		System.out.println("\nInterfaces: \n");
		for(Class inter : cc.getInterfaces()) {
			System.out.println(inter);
		}
		
		System.out.println("\nsuper class: \n");
		Class up = cc.getSuperclass();
		try {
			up.newInstance();
		} catch(InstantiationException e) {
			System.out.println(e);
		} catch(IllegalAccessException e) {
			System.out.println(e);
		}
		
		printinfo(up);
	}
}

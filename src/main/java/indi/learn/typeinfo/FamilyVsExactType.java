package indi.learn.typeinfo;

class Base{}
class Derived extends Base {}

public class FamilyVsExactType {
	public static void test(Object x) {
		System.out.println("Tesing x of type: " + x.getClass());
		System.out.println("x is instanceof Base: " + (x instanceof Base));
		System.out.println("x is instanceof Derived: " + (x instanceof Derived));
		System.out.println("Base.isinstance x: " + Base.class.isInstance(x));
		System.out.println("Derived.isinstance x: " + Derived.class.isInstance(x));
		System.out.println("Base.class == x.class: " + (Base.class == x.getClass()));
		System.out.println("Derived.class == x.class: " + (Derived.class == x.getClass()));
		System.out.println("Base.class.equals(x.class): " + (Base.class.equals(x.getClass())));
		System.out.println("Derived.class.equals(x.class): " + (Derived.class.equals(x.getClass())));
	}
	
	public static void main(String[] args) {
		test(new Base());
		System.out.println();
		test(new Derived());
	}
}

package indi.learn.polymorphism;

public class PrivateOverride {
	private void f() {
		System.out.println("private f()");
	}

	// note that the static main method is also a part of
	// PrivateOverride class so it can access the private
	// method f() via the object po.
	public static void main(String[] args) {
		PrivateOverride po = new Derived();
		po.f();
	}
}

class Derived extends PrivateOverride {
	public void f() {
		System.out.println("public f()");
	}
	
//	public static void main(String[] args) {
//		PrivateOverride po = new Derived();
//		po.f();
//	}
}

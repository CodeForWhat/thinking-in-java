package indi.learn.innerclasses;

public class DotThis {
	
	class Inner {
		public DotThis outer() {
			return DotThis.this;
		}
	}
	
	public void f() {
		System.out.println("outer f()");
	}
	
	public Inner inner() {
		return new Inner();
	}
	
	public static void main(String[] args) {
		DotThis dt = new DotThis();
		DotThis.Inner di = dt.inner();
		di.outer().f();
	}

}

package indi.learn.typeinfo;

class Candy {
	static {
		System.out.println("loading Candy");
	}
}

class Gum {
	static {
		System.out.println("loading Gum");
	}
}

class Cookie {
	static {
		System.out.println("loading cookie");
	}
}

public class SweetShop {
	public static void main(String[] args) {
		System.out.println("inside main");
		new Candy();
		System.out.println("After creating candy");
		
		try {
			Class.forName("indi.learn.typeinfo.Gum");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("after loading Gum");
		
		new Cookie();
		System.out.println("after creating cookie");
	}
}

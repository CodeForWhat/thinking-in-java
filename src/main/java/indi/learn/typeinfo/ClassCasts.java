package indi.learn.typeinfo;

class Building{}
class House extends Building{}

public class ClassCasts {

	public static void main(String[] args) {
		Building b = new House();
		
		// casting object method1
		Class<House> hc = House.class;
		House h = hc.cast(b);
		
		// casting object method2
		House hh = (House)b;
	}

}

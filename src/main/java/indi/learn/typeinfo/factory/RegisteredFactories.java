package indi.learn.typeinfo.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Part {
	static List<Factory<? extends Part>> factories = new ArrayList<Factory<? extends Part>>();

	static {
		// factories.add(e)

//		Collections.addAll(factories, new FuelFilter.Factory(), new AirFilter.Factory(), new CabinAirFilter.Factory(),
//				new OilFilter.Factory(), new FanBelt.Factory(), new PowerSteeringBelt.Factory(),
//				new GeneratorBelt.Factory());
		factories.add(new FuelFilter.Factory());
		factories.add(new AirFilter.Factory());
		factories.add(new CabinAirFilter.Factory());
		factories.add(new OilFilter.Factory());
		factories.add(new FanBelt.Factory());
		factories.add(new PowerSteeringBelt.Factory());
		factories.add(new GeneratorBelt.Factory());
	}

	private static Random rand = new Random(47);

	public static Part createRandom() {
		int n = rand.nextInt(factories.size());
		return factories.get(n).create();
	}
}

class Filter extends Part {
}

class FuelFilter extends Filter {
	public static class Factory implements indi.learn.typeinfo.factory.Factory<FuelFilter> {
		public FuelFilter create() {
			return new FuelFilter();
		}
	}
}

class AirFilter extends Filter {
	public static class Factory implements indi.learn.typeinfo.factory.Factory<AirFilter> {
		public AirFilter create() {
			return new AirFilter();
		}
	}
}

class CabinAirFilter extends Filter {
	public static class Factory implements indi.learn.typeinfo.factory.Factory<CabinAirFilter> {
		public CabinAirFilter create() {
			return new CabinAirFilter();
		}
	}
}

class OilFilter extends Filter {
	public static class Factory implements indi.learn.typeinfo.factory.Factory<OilFilter> {
		public OilFilter create() {
			return new OilFilter();
		}
	}
}

class Belt extends Part {
}

class FanBelt extends Belt {
	public static class Factory implements indi.learn.typeinfo.factory.Factory<FanBelt> {
		public FanBelt create() {
			return new FanBelt();
		}
	}
}

class GeneratorBelt extends Belt {
	public static class Factory implements indi.learn.typeinfo.factory.Factory<GeneratorBelt> {
		public GeneratorBelt create() {
			return new GeneratorBelt();
		}
	}
}

class PowerSteeringBelt extends Belt {
	public static class Factory implements indi.learn.typeinfo.factory.Factory<PowerSteeringBelt> {
		public PowerSteeringBelt create() {
			return new PowerSteeringBelt();
		}
	}
}

public class RegisteredFactories {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			System.out.println(Part.createRandom());
	}
}

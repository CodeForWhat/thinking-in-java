package indi.learn.typeinfo;

interface Interface {
	void doSomething();

	void doSomethingElse();
}

class RealObject implements Interface {
	public void doSomething() {
		System.out.println("do something");
	}

	public void doSomethingElse() {
		System.out.println("do something else");
	}
}

class SimpleProxy implements Interface {
	private Interface proxy;

	public SimpleProxy(Interface proxy) {
		this.proxy = proxy;
	}

	public void doSomething() {
		System.out.println("proxy do something");
		proxy.doSomething();
	}

	public void doSomethingElse() {
		System.out.println("proxy do something else");
		proxy.doSomethingElse();
	}
}

public class SimpleProxyDemo {
	public static void consumer(Interface iface) {
		iface.doSomething();
		iface.doSomethingElse();
	}

	public static void main(String[] args) {
		consumer(new RealObject());
		consumer(new SimpleProxy(new RealObject()));
	}
}

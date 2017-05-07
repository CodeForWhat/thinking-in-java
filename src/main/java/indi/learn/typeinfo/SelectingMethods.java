package indi.learn.typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface SomeMethods {
	void boring1();
	void boring2();
	void boring3();
	void interesting(String arg);
}

class Implementation implements SomeMethods {

	public void boring1() {
		// TODO Auto-generated method stub
		System.out.println("boring1");
	}

	public void boring2() {
		// TODO Auto-generated method stub
		System.out.println("boring2");
	}

	public void boring3() {
		// TODO Auto-generated method stub
		System.out.println("boring3");
	}

	public void interesting(String arg) {
		// TODO Auto-generated method stub
		System.out.println("interesting " + arg);
	}
}

class MethodSelector implements InvocationHandler {
	private Object proxied;
	
	public MethodSelector(Object proxied) {
		this.proxied = proxied;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(method.getName().indexOf("interesting") != -1) {
			System.out.println("detecting the interesting method");
		}
		return method.invoke(proxied, args);
	}
	
	
}

public class SelectingMethods {

	public static void main(String[] args) {
		SomeMethods real = new Implementation();
		SomeMethods sm = (SomeMethods)Proxy.newProxyInstance(SomeMethods.class.getClassLoader(), new Class[]{SomeMethods.class}, new MethodSelector(real));
		sm.boring1();
		sm.boring2();
		sm.boring3();
		sm.interesting("hahahah");
	}

}

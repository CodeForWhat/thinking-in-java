package indi.learn.typeinfo;

import java.util.ArrayList;
import java.util.List;

class CountedInteger {
	private static long counter;
	private final long id = counter++;

	public String toString() {
		return Long.toString(id);
	}
}

public class FilledList<T> {
	private Class<T> type;

	public FilledList(Class<T> type) {
		this.type = type;
	}

	public List<T> create(int elementsSize) {
		List<T> elements = new ArrayList<T>(elementsSize);

		for (int i = 0; i < elementsSize; i++) {
			try {
				elements.add(type.newInstance());
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

		return elements;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FilledList<CountedInteger> list = new FilledList<CountedInteger>(CountedInteger.class);
		System.out.println(list.create(15));
	}

}

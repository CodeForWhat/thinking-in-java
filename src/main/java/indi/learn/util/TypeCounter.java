package indi.learn.util;

import java.util.HashMap;
import java.util.Map;

public class TypeCounter extends HashMap<Class<?>, Integer> {
	private Class<?> baseType;

	public TypeCounter(Class<?> baseType) {
		this.baseType = baseType;
	}

	public void countClass(Class<?> type) {

		if (baseType.isAssignableFrom(type)) {
			Integer count = get(type);
			if (count == null) {
				put(type, 1);
			} else {
				put(type, count + 1);
			}
		}

		Class<?> superClass = type.getSuperclass();
		if (superClass != null && baseType.isAssignableFrom(superClass)) {
			countClass(superClass);
		}
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		for (Map.Entry<Class<?>, Integer> pair : entrySet()) {
			result.append(pair.getKey().getSimpleName());
			result.append("=");
			result.append(pair.getValue());
			result.append(", ");
		}
		result.delete(result.length() - 2, result.length());
		result.append("}");
		return result.toString();
	}
}

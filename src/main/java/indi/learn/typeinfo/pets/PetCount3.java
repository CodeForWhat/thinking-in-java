package indi.learn.typeinfo.pets;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PetCount3 {
	static class PetCounter extends LinkedHashMap<Class<? extends Pet>, Integer> {
		static final Map<Class<? extends Pet>, Integer> data = new HashMap<Class<? extends Pet>, Integer>();

		static {
			for (Class<? extends Pet> pet : LiteralPetCreator.allTypes) {
				data.put(pet, 0);
			}
		}

		public PetCounter() {
			super(data);
		}

		public void count(Pet pet) {
			for (Map.Entry<Class<? extends Pet>, Integer> entry : this.entrySet()) {
				if (entry.getKey().isInstance(pet)) {
					put(entry.getKey(), entry.getValue() + 1);
				}
			}
		}

		public String toString() {
			StringBuilder result = new StringBuilder("{");
			for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) {
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PetCounter petCount = new PetCounter();
		for (Pet pet : Pets.createArray(20)) {
			System.out.print(pet.getClass().getSimpleName() + " ");
			petCount.count(pet);
		}
		System.out.println();
		System.out.println(petCount);
	}

}

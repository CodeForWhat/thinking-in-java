package indi.learn.typeinfo;

import indi.learn.typeinfo.pets.Pet;
import indi.learn.typeinfo.pets.Pets;
import indi.learn.util.TypeCounter;

public class PetCount4 {
	public static void main(String[] args) {
		TypeCounter counter = new TypeCounter(Pet.class);
		for (Pet pet : Pets.createArray(20)) {
			System.out.print(pet.getClass().getSimpleName() + " ");
			counter.countClass(pet.getClass());
		}
		System.out.println();
		System.out.println(counter);
	}
}

package indi.learn.holding;

import java.util.Iterator;
import java.util.List;

import indi.learn.typeinfo.pets.Pet;
import indi.learn.typeinfo.pets.Pets;

public class SimpleIteration {
	public static void main(String[] args) {
		List<Pet> pets = Pets.arrayList(12);
		Iterator<Pet> it = pets.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		System.out.println();
		for(Pet p : pets) {
			System.out.println(p);
		}
		System.out.println();
		
		it = pets.iterator();
		for(int i = 0; i < 6; i++) {
			it.next();
			it.remove();
		}
		System.out.println(pets);
	}

}

package indi.learn.containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class StringAddress {
	private String s;
	
	public StringAddress(String s) {
		this.s = s;
	}
	
	public String toString() {
		return super.toString() + " " + s;
	}
}

public class FillingLists {

	public static void main(String[] args) {
		List<StringAddress> saList = Collections.nCopies(4, new StringAddress("China"));
		
		System.out.println(saList);
		
		// the list returned by the ncopies method is unmodifable
		// so this invocation would pose an UnsupportedOperationException
		//saList.add(new StringAddress("Japan"));
		
		//System.out.println(saList);
		
		
		// but we can use the return value to construct a outright new list
		// using the constructor of List
		List<StringAddress> modifableList = new ArrayList<StringAddress>(saList);
		
		// this invocation would be ok
		modifableList.add(new StringAddress("America"));
		
		System.out.println(saList);
		
		// another importance bullet point is 
		// ncopies method would not copy the object 
		// n times but the reference of the object
		// so all reference in the list will reference
		// to the same object which specified by o
		StringAddress sa0 = saList.get(0);
		StringAddress sa1 = saList.get(1);
		StringAddress sa2 = saList.get(2);
		StringAddress sa3 = saList.get(3);
		
		if(sa0 == sa1 && sa1 == sa2 && sa2 == sa3) {
			System.out.println("all refered to the same object");
		}
		
		
		/********************** Another approach ***************/
		
		// the fill method do the same work as nCopies method
		// but fill method just replace existed reference in the list
		Collections.fill(modifableList, new StringAddress("Japan"));
		
		System.out.println(modifableList);
		
		
	}

}

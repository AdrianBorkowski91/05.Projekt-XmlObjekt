package parse;

import java.util.Comparator;

import model.Person;

class CompareId implements Comparator<Person>{
	
		public int compare(Person p1, Person p2) {
	    	
	        return p1.getIdPerson() - p2.getIdPerson();
	    }
}

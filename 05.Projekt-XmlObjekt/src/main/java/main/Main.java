package main;

import java.util.List;

import datebase.Connection;
import model.Person;
import parse.ParseXML;

public class Main {

	public static void main(String[] args) {
		List<Person> listPerson= ParseXML.parseXML();
			
		for (Person person : listPerson) {
			System.out.println(person);
		}
		
		Connection.startConnection(listPerson);
	
	}

}

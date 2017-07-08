package parse;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import model.Person;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParseXML {
	

	public static List<Person> parseXML(){
		List<Person> listPerson= new ArrayList<Person>();
		
	    try {
	    	File fXmlFile = new File("File.xml");
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);

	    	doc.getDocumentElement().normalize();

	    	NodeList nList = doc.getElementsByTagName("staff");
	
	    	for (int temp = 0; temp < nList.getLength(); temp++) {
	    		Node nNode = nList.item(temp);
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	    			Element eElement = (Element) nNode;
	    			
	    			Person person= new Person();
	    			try {
						person.setIdPerson(Integer.parseInt(eElement.getAttribute("id")));
					} catch (Exception e) {
						person.setIdPerson(0);
					}
	    			person.setCategory(eElement.getAttribute("category"));
	    			person.setFirstname(eElement.getElementsByTagName("firstname").item(0).getTextContent());
	    			person.setLastname(eElement.getElementsByTagName("lastname").item(0).getTextContent());
	    			person.setDateofbirth(eElement.getElementsByTagName("lastname").item(0).getTextContent());
	    			try {
						person.setSalary(Double.parseDouble(eElement.getElementsByTagName("salary").item(0).getTextContent()));
					} catch (Exception e) {
						person.setSalary(0);
					}
	    			
	    			listPerson.add(person);
	    		}
	    	}
	    	} catch (Exception e) {
	    	e.printStackTrace();
	        }
	    
	    listPerson= removeDuplicates(listPerson);
	    Collections.sort(listPerson, new CompareId());
		  
	    return listPerson;
	}
	
	private static List<Person> removeDuplicates(List<Person> listPerson){
		List<Person> list= new ArrayList<Person>();
		
		for (int i=0; i<listPerson.size(); i++) {
			int count=0;

			for (int j=i+1; j<listPerson.size(); j++){
				int maks=listPerson.size()-i-1;
				
				
				if(!listPerson.get(i).equals(listPerson.get(j))){
					count++;
				}
				if(maks==count){
					list.add(listPerson.get(i));
				}
				
			}
			if(count==0){
				list.add(listPerson.get(i));
			}
		}
		return list;
	}
	


}

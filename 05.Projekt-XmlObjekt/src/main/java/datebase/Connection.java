package datebase;

import java.sql.*;
import java.util.List;

import model.Person;

	public class Connection{ 
		private static String query = " insert into person (id_person, category, firstname, lastname, dateofbirth, salary)"
    	        + " values (?, ?, ?, ?, ?, ?)";

		
		public static void startConnection(List<Person> listPerson){
			String url = "jdbc:mysql://localhost:3306/"; 
			String dbName = "company";
			String driver = "com.mysql.jdbc.Driver"; 
			String userName = "root"; 
			String password = "admin"; 
			try { 
				Class.forName(driver).newInstance(); 
				java.sql.Connection conn = DriverManager.getConnection(url+dbName,userName,password); 
				conn.createStatement();
				
				for (Person person : listPerson) {
					PreparedStatement preparedStmt = conn.prepareStatement(query);
					preparedStmt.setInt(1, person.getIdPerson());
					preparedStmt.setString(2, person.getCategory());
					preparedStmt.setString(3, person.getFirstname());
					preparedStmt.setString(4, person.getLastname());
					preparedStmt.setString(5, person.getDateofbirth());
					preparedStmt.setDouble(6, person.getSalary());   
					preparedStmt.execute();
				}
				
			    conn.close();
			}catch (Exception e){e.getMessage();}
		}
}
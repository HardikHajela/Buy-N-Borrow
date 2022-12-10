package Sprint2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sprint2UserStory1 {

	public static String getEmployeeCategory(int employee_ID) {
		
		String username = "root", password = "root", dbUrl = "jdbc:mysql://localhost:3306/BuyNBorrow";

		try {
			
			Connection db_connection = DriverManager.getConnection(dbUrl, username, password);
			
			Statement statement = db_connection.createStatement();
			String query;
			
			if(employee_ID < 2000) {
				
				query = "SELECT designation FROM BS_Employee WHERE employee_id = " + employee_ID + ";";
			
			}
			else {
				
				query = "SELECT designation FROM LB_Employee WHERE employee_id = " + employee_ID + ";";
				
			}
			
			ResultSet result = statement.executeQuery(query);
			
			if(!result.next()) {
				
				System.out.println("The specified employee does not exist.");
				return null;
				
			}
			
			String employee_designation = result.getString(1);
			
			db_connection.close();
			
			return employee_designation;
		
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			return null;
		
		}
		
	}
	
}
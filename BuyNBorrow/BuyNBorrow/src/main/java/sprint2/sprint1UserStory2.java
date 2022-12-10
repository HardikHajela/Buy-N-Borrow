package Sprint2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sprint1UserStory2 {
	
	public static String[][] viewMarket() {

		String username = "root", password = "root", dbUrl = "jdbc:mysql://localhost:3306/BuyNBorrow";

		try {
			
			Connection db_connection = DriverManager.getConnection(dbUrl, username, password);			
			
			PreparedStatement query = db_connection.prepareStatement("SELECT * FROM Market;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = query.executeQuery();
			
			if(!result.next()) {
                            System.out.println("There are no books available on the market.");
                            return null;
			}
			
			result.last();
			int number_of_rows = result.getRow();
			String[][] query_result = new String[number_of_rows][3];
			result.first();
			int i = 0, longest_title = result.getString(2).length();
			
			do {
				
				query_result[i][0] = result.getString(1);
				query_result[i][1] = result.getString(2);
				query_result[i][2] = result.getString(3);
				
				if(longest_title < result.getString(2).length())
					longest_title = result.getString(2).length();
				
				i++;
				
			} while(result.next());
			
			if(longest_title < "Title".length())
				longest_title = "Title".length();
			
			result.first();
			//System.out.println("Market");
			
			// Formatting for left aligning the data
			//String left_align = "| %-13s | %-" + longest_title + "s | $%-10s |%n";
			
			// Print opening line of table
			//System.out.format("+---------------+-" + "-".repeat(longest_title) + "-+-------------+%n");

			// Print column names
			//System.out.format("| ISBN          | Title" + " ".repeat(longest_title - "Title".length()) + " | Price       |%n");

			// Print line to separate column names and data
			//System.out.format("+---------------+-" + "-".repeat(longest_title) + "-+-------------+%n");
			
			// Print data
			//for(i = 0; i < number_of_rows; i++) {
				
				//System.out.format(left_align, query_result[i][0], query_result[i][1], query_result[i][2]);
				
			//}

			// Print closing line of table
			//System.out.format("+---------------+-" + "-".repeat(longest_title) + "-+-------------+%n");
			
			db_connection.close();
			
			return query_result;
		
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			return null;
		
		}
		
	}
	
}

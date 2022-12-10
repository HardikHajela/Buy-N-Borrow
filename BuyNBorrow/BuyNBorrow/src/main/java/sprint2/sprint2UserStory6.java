package sprint2;

import java.sql.*;

public class sprint2UserStory6 {	//Code by Hardik Hajela

	public static void main(String[] args) {
		// As a Library Manager, I’d like to refuse a book being sold from Leddy library through Bookstore 
	}
	public static boolean sprint2UserStory6function(long given_ISBN){
		
		try {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BuyNBorrow","root","root");
		Statement stmt = con.createStatement();
		
		String update_for_Bookreq0 = "Update book_requests set quantity_approved = 0, status = 'rejected' where ISBN ="+ given_ISBN +";";
		stmt.executeUpdate(update_for_Bookreq0);	//Simply rejecting the request in book request table		
		return true;	
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
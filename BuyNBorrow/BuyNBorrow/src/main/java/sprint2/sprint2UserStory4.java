package sprint2;

import java.sql.*;

public class sprint2UserStory4 {

	public static void main(String[] args) {
		//As a Library Manager, I’d like to approve a book being sold from Leddy library through Bookstore 
		}
		
	public static int sprint2UserStory4function(long given_ISBN, int approvedQuan) {
		
		try {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BuyNBorrow","root","root");
		Statement stmt = con.createStatement();
				
			ResultSet rsbs= stmt.executeQuery("Select ISBN, quantity from lb_inventory where ISBN ="+given_ISBN+";");
			
			if(!rsbs.next()){return -1;}
			int quanitity_cmp= rsbs.getInt("quantity");
                        
                        if(approvedQuan>quanitity_cmp) return -2;
                        
			long temp_ISBN;
			boolean book_in_market=false;
			
			while(rsbs.next()) {
                            temp_ISBN=rsbs.getLong("ISBN");
                            if(temp_ISBN == given_ISBN) {book_in_market=true;}
			}
			rsbs.close();
			
			if(book_in_market==true) {
				String upd= "UPDATE bs_inventory SET QUANTITY = QUANTITY + "+approvedQuan+" where ISBN = "+ given_ISBN +";";
				stmt.executeUpdate(upd);
                                
			}
			else{
				ResultSet rscreate1= stmt.executeQuery("Select title from lb_inventory where ISBN = "+ given_ISBN +";");
				rscreate1.next();
				String title= rscreate1.getString("title");
				rscreate1.close();
				ResultSet rscreate2= stmt.executeQuery("Select price, academic from book_requests where ISBN = "+ given_ISBN +";");
				rscreate2.next();
				float price= rscreate2.getFloat("price");
                                String acad= rscreate2.getString("academic");
                                rscreate2.close();
				String insert=" Insert into bs_inventory values("+ given_ISBN +", '"+ title +"', "+ approvedQuan +", "+ price +", '"+ acad +"')";
				stmt.executeUpdate(insert);
				}
			
			String update_for_lib="UPDATE lb_inventory SET QUANTITY = QUANTITY - "+ approvedQuan +" where ISBN = "+ given_ISBN +";";
			stmt.executeUpdate(update_for_lib);
			
		
			String update_for_Bookreq = "update book_requests set quantity_approved ="+ approvedQuan +", status = 'Approved' where ISBN ="+ given_ISBN +";";
			stmt.executeUpdate(update_for_Bookreq);	
			
			stmt.close();
		return 1; 
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;
		}
	}
}
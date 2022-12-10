package sprint2;

import java.sql.*;
import java.util.*;

public class sprint1UserStory3 {
		
	public static int sprint1UserStory3function(long given_ISBN, int quantity, float price, char acad) {	//DONE BY HARDIK HAJELA 105070182
            try{
		//False Meaning Order didn't go through, True Meaning, it did!
		if(quantity<1) return -1;
                
                else
		{
                    
		ArrayList<Long> mark = new ArrayList<>();	//Array list for Market
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BuyNBorrow","root","root");
		//System.out.println("Connection Established!");
		
		int book_in_market=0;	//Variable to check if book to be purchased is in the Market
		long book_in_inventory=-1;	//Variable to check if the to be purchased is already in our inventory or not
		Statement smark= con.createStatement();
		ResultSet rsmark= smark.executeQuery("Select ISBN from Market;");	//Getting ISBN from Market Table to check
		
		while(rsmark.next()) {mark.add(rsmark.getLong("ISBN"));}	//Updating Entire from from DB to our Array list 
		//for(int b=0; b<mark.size(); b++) System.out.println(mark.get(b));
		
		for(int b=0; b<mark.size(); b++) if(mark.get(b) == given_ISBN) {book_in_market++;}
		
		if(book_in_market==0) return -2;		//if book is not available in the market
		
		Statement sin= con.createStatement();
		ResultSet rsin= sin.executeQuery("Select ISBN from BS_Inventory;");	//Getting ISBN from Market Table to check
		
		while(rsin.next()){ if(given_ISBN==rsin.getLong("ISBN")) book_in_inventory=given_ISBN;	}
                //storing ISBN in case Book to be purchased in already in our inventory
		
		//for(int a=0; a<inv.size(); a++) System.out.println(inv.get(a));		
		
		Statement stmt = con.createStatement();
		if(book_in_inventory==-1) {
			Statement snew= con.createStatement();
			ResultSet rsnew= snew.executeQuery("Select ISBN, TITLE from Market where ISBN="+given_ISBN+" ;");	//Getting ISBN and Book name from Market Table to Update in Inventory
			
			rsnew.next();
			String newBookName = rsnew.getString("TITLE");
			 
			String ins= "Insert into BS_Inventory values("+given_ISBN+", '"+newBookName+"', "+quantity+", "+price+",'"+acad+"');" ;	//Inserting into Inventory the purchased book
			stmt.executeUpdate(ins);	//Executing in MySQL;
			snew.close();	
		}
		else {
			String upd= "UPDATE BS_Inventory SET QUANTITY = QUANTITY + "+quantity+", PRICE = "+price+", Academic ='"+acad+"' where ISBN = "+ book_in_inventory +";";
			stmt.executeUpdate(upd);
                        //update BS_Inventory set quantity = 9, Price=10.19 where isbn = 1207865748356;
		}
		
		smark.close();
		sin.close();
		con.close();	//close
		
		return 1;	
            }
        }catch(SQLException e){System.out.println(e);return -3;}  
    }
	
}
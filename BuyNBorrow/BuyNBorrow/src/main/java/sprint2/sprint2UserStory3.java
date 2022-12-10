package sprint2;

import Sprint2.sprint2UserStory2;
import java.sql.*;

/**
 * @author anuhyachowdarypaturi, hardikhajela
 */

public class sprint2UserStory3 {

    //public static void main(String args[]) {Requestbook(1389139231091L, 5, 88.32, "Y");}
		//As a bookstore Manager, I’d like to request to sell a book available from the library 

    public static int Requestbook(long ISBN_NO, int book_quantity_requested, double book_price, String acad) {
        String url = "jdbc:mysql://localhost:3306/BuyNBorrow";
        String uname = "root";
        String pass = "root";
        
        if(book_quantity_requested<0) return -1;

        try {
            
            String[][] libinv=sprint2UserStory2.viewLibraryInventory(); 
            boolean isbn_exists = false; 
            String temp = ISBN_NO + "";
            for(int loop=0; loop<libinv.length; loop++) if (temp.equals(libinv[loop][0])) isbn_exists=true;    
            if(isbn_exists==false)   return -2;
            
            long book_in_req=-1;
                        
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, pass);
            
            Statement sin= con.createStatement();
            ResultSet rsin= sin.executeQuery("Select ISBN from book_requests;");	//Getting ISBN from Market Table to check
            while(rsin.next()){ if(ISBN_NO==rsin.getLong("ISBN")) book_in_req=ISBN_NO;	}
            
            if(book_in_req==-1){
            Statement statement = con.createStatement();
            String query = "insert into book_requests(isbn,quantity_requested,quantity_approved,price,status,academic) values(?,?,?,?,?,?)";
            PreparedStatement pst;
            pst = con.prepareStatement(query);
            pst.setLong(1, ISBN_NO);
            pst.setInt(2, book_quantity_requested);
            pst.setInt(3, 0);
            pst.setDouble(4, book_price);
            pst.setString(5, "Pending");
            pst.setString(6, acad);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted == 1) {
                System.out.println("Book request has been successfully inserted.");
            } else {
                System.out.println("Book request is not inserted.");    return -3;
            }
            
                pst.close();
                con.close();
                
            return 1;
            }
            else{
                String upd= "UPDATE book_requests SET quantity_requested = quantity_requested + "+book_quantity_requested+", PRICE = "+book_price+", Academic ='"+acad+"', status = 'Pending' where ISBN = "+ book_in_req +";";
                sin.executeUpdate(upd);
                return 1;
            }

            

        } catch (Exception e) {
            System.out.println(e.getMessage()); return -3;
        }
    }
}
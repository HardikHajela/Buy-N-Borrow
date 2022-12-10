package sprint2;

import java.sql.*;

public class sprint2UserStory5 {
	
	public static String [][] sprint2UserStory5function() {
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BuyNBorrow","root","root");
			PreparedStatement psbr = con.prepareStatement("Select * from book_requests;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rsbr= psbr.executeQuery();
			
			if(!rsbr.next()){
				System.out.println("No books requested at this time!");
				return null;
			}
			
			rsbr.last();
			int numrows = rsbr.getRow();
			String [][] book_requests = new String [numrows][6];
			rsbr.first();
			
			
			int temp=0;
			do {
				book_requests[temp][0] = rsbr.getString("isbn");
				book_requests[temp][1] = rsbr.getString("quantity_requested");
				book_requests[temp][2] = rsbr.getString("quantity_approved");
				book_requests[temp][3] = rsbr.getString("price");
				book_requests[temp][4] = rsbr.getString("status");
				book_requests[temp][5] = rsbr.getString("academic");
				temp++;
			}while(rsbr.next());
			
			//String head="|ISBN\t\t\t||Qtn requsted\t||Qtn approved\t||Price\t\t\t||Status\t\t|";
			
			//System.out.print("+");
			//for(int i1=0; i1<51; i1++) {System.out.print("--");}
			//System.out.print("-+");
			
			//System.out.print("\n"+head+"\n");
			
			//System.out.print("+");
			//for(int i1=0; i1<51; i1++) {System.out.print("--");}
			//System.out.println("-+");
			
			//for(int i=0; i<temp; i++) {
				//for(int j=0; j<5; j++) {
					//System.out.print("| "+book_requests[i][j]+"\t\t|");
				//}
				//System.out.println();
			//}
			//System.out.print("+");
			//for(int i1=0; i1<51; i1++) {System.out.print("--");}
			//System.out.println("-+");
			
			psbr.close();
			con.close();
			
			return book_requests;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}

}
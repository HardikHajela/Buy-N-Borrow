package sprint2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class sprint1UserStory4{
    
	public static String[][] z(int input){
            
            try{
		
		if(input == 0 || input ==1 ) {
				
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BuyNBorrow","root","root");
		Statement s= con.createStatement();
		ResultSet rs=null;
		
		if(input==1) 
			rs= s.executeQuery("Select * from bs_inventory where Academic like 'Y';");
		if(input==0) 
			rs= s.executeQuery("Select * from bs_inventory where Academic like 'N';");
		
		int i=0;
		while(rs.next()) {
		i++;
		}
		//System.out.println(i);
			
		String [][] output = new String[i][4];
		
		if(input==1) 
			rs= s.executeQuery("Select * from bs_inventory where academic like 'Y';");
		if(input==0) 
			rs= s.executeQuery("Select * from bs_inventory where academic like 'N';");
		
		int loop=0;
		rs.next();

		String c1, c2, c3, c4;
		do {
			c1 = rs.getString(1);
			c2 = rs.getString(2);
			c3 = rs.getString(3);
			c4 = rs.getString(4);
			output[loop][0] = c1;
			output[loop][1] = c2;
			output[loop][2] = c3;
			output[loop][3] = c4;
			loop++;
			
		} while(rs.next());
		//System.out.println(" | ISBN          | BOOK_NAME                             | quantity | academic");
		//for (int k = 0; k<i; k++) {
		    //for (int j = 0; j<4; j++) {
		        //System.out.print(" |"+output[k][j]+ "|");
		    //}
		    //System.out.println();
		//}	
		
			//put into array then display then return
		
		s.close();
		con.close();
		
		return output;
                }
		else {
                    return null;
		}
	}catch(SQLException e){return null;}
	
    }
}
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
public class Desplay_error {
	
	public void Display_table() {
		//initialising the numberof columns
		int column=2;
		//initialisinfg the number of rows
		int row=2489;
		//varable holding values of patiant number that we get from database table with error
		String awi_number="";
		//establishing connection
		//initialising the string array, responsible of tamp storing data that is from database.
		String[][] take_val = new String[row][column];
		//variable reperesenting error_discription of values that have errors
		String error_description="";
		//initialising Fframe window that will show error description info
	final JFrame frame = new JFrame("Error Decreption Table");
	//Object data = "{"+arr_awI_number+"}";
	//setting up Fframe table columns
	String[] columns = {"Patiant Number", "Error Descreption"};
	    //initialising a variable for connecting to database
		Connection connection = null;
		//setting up a ariable that will stand for queries sent to database
	    Statement query=null;
		try {
			//assigning variable connection to database logging in details
			connection = DriverManager.getConnection(
					"jdbc:postgresql://146.141.240.87:5432/APIPractice?sslmode=require", "vumile",
					"defect.iota.foxhole");

		// checking if connection is established

		 if(connection!=null ) {
			  query=connection.createStatement();
			  //writting a query thqt selects the description contained in table with error
			ResultSet rs= query.executeQuery("select awi_number,error_description from study_with_error;");
			
		     //initialising the variable that helps with storing data into temp string array
			 int y=0;
			//after the query has been sent to database we retrieve some value
			while(rs.next()) {
				//getting values from awi_number
			awi_number=rs.getString("awi_number");
			//getting values from error_desctription table
		   error_description=rs.getString("error_description");
			//inserting values into the temp array
			   take_val[y][0]=awi_number;
			   take_val[y][1]=error_description;
		

		y++;
		}   //closing database connection
			 rs.close();
		 }
		  //System.out.println(take_val[0][1]);
		 } 
		//if there is a problem with establishing connection prints out that there is a connection error
		catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}
		//transfaring value from tem 2d string array to 2d object array 
   Object[][] data=take_val;
	//print results into swing Fframe in the form of a table
   JTable table = new JTable(data , columns);
	JScrollPane scrollPane = new JScrollPane(table);
   table.setFillsViewportHeight(true);
   JLabel lblHeading = new JLabel("Data with error");
   lblHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT,24));

   frame.getContentPane().setLayout(new BorderLayout());
   

   frame.getContentPane().add(lblHeading,BorderLayout.PAGE_START);
   frame.getContentPane().add(scrollPane,BorderLayout.CENTER);

   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
   
   frame.setSize(550, 200);
   frame.setVisible(true);
  
		
   


 
}


}

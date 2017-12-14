import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import javax.swing.JTextField;
public class retrieve_email_data {
     public int email_sender(String from, String to, String[][] massege1, int size) {
    	 //setting up host number
    	 String host="localhost";
   
      // Setup mail server
         final String username = "vumileking@gmail.com";
 		final String password = "A10:19R77N";

 		Properties props = new Properties();
 		props.put("mail.smtp.auth", "true");
 		props.put("mail.smtp.starttls.enable", "true");
 		props.put("mail.smtp.host", "smtp.gmail.com");
 		props.put("mail.smtp.port", "587");

 		Session session = Session.getInstance(props,
 		  new javax.mail.Authenticator() {
 			protected PasswordAuthentication getPasswordAuthentication() {
 				return new PasswordAuthentication(username, password);
 			}
 		  });
      // Get the default Session object.
         
         
         try {
             // Create a default MimeMessage object.
             MimeMessage message = new MimeMessage(session);

             // Set From: header field of the header.
             message.setFrom(new InternetAddress(from));

             // Set To: header field of the header.
             message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
             
             // Set Subject: header field
             message.setSubject("Error notification");
             //converting Strinh 2d into String
             //intitialising the string that will collect 2d values
             String convertor="";
             convertor=convertor+"awi_nimber \t|"+"error_description \t \n";
             convertor=convertor+"-------------------------------------------------------------------------------------------------------\n";
             for(int y=0;y<size;y++) {
                      		 
            		 convertor=convertor+massege1[y][0]+"      \t| ";
            		 convertor=convertor+massege1[y][1]+" \t \n";
            		 convertor=convertor+"-------------------------------------------------------------------------------------------------------\n";
            	 
             }

             // Now set the actual message
             message.setText(convertor);

             // Send message
             Transport.send(message);
             return 1;
          }
         catch (MessagingException mex) {
             mex.printStackTrace();
             return 0;
          }
     }
	public int retrieve_and_send(JTextField initial, JTextField final_, JTextField send_email) {
		//this variable is for indicating whether the email has been sent or not.
		int results=0;
		//value used to hold patiant number
		String awi_number;
		// Value used to retrieve error_descreption
		String error_description;
		//initialising the temp variable that holds data from database
		String[][] take_val= new String[1000][2];
		//converting final_patiant_number to int
				String f_pat = final_.getText();
				int finl= Integer.parseInt(f_pat);
				//converting intial_patiant_number to int
				String i_pat = initial.getText();
				int initl = Integer.parseInt(i_pat);
				//variable representing ID of patiant 
				String ID;
				int y=0;
				for(int u=initl;u<finl;u++){
					//initialising the variable that helps with storing data into temp string array 
					
					if(u<9&&u>1) {
					 ID="P000"+u;	
						//tretrieve data
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
							ResultSet rs= query.executeQuery("select awi_number,error_description from study_with_error where awi_number='"+ID+"';");
							
						     
							
							//after the query has been sent to database we retrieve some value
							while(rs.next()&& !rs.wasNull()) {
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
							//return;

						}
						
					}else {
						if(u>9&&u<99) {
							ID="P00"+u;
							//retrieve data
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
								ResultSet rs= query.executeQuery("select awi_number,error_description from study_with_error where awi_number='"+ID+"';");
								
							     
								//after the query has been sent to database we retrieve some value
								while(rs.next()&& !rs.wasNull()) {
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
								//return;

							}
							
						}
						else {
							if(u>100&&u<999) {
								ID="P0"+u;
								//retrieve data
								
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
									ResultSet rs= query.executeQuery("select awi_number,error_description from study_with_error where awi_number='"+ID+"';");
									
								     
									//after the query has been sent to database we retrieve some value
									while(rs.next()&& !rs.wasNull()) {
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
									//return;

								}
							}
							else {
								if(u>1000&&u<9999){
									ID="P"+u;
									
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
										ResultSet rs= query.executeQuery("select awi_number,error_description from study_with_error where awi_number='"+ID+"';");
										
									    
										//after the query has been sent to database we retrieve some value
										while(rs.next()&& !rs.wasNull()) {
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
										//return;

									}
								}
								
							}
						}
						
					}
					
				}
		
				
				String email_adress1 = send_email.getText();
				
			//send email
				
		
		return email_sender("vumilrking@gmail.com",email_adress1,take_val,y);
	}
}

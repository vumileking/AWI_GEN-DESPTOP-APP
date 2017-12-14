import javax.swing.*;
import java.awt.*;
import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;


import java.awt.ComponentOrientation;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JTable;
import org.eclipse.wb.swing.FocusTraversalOnArray;

public class update {
	
	public void Updat_o() throws SQLException {
		String ID="";
		Connection connection = null;
	    Statement query=null;
	    MyClass myclass;
	    String sql;
	    String[] funtions_tht_apply= {""};
	    Connect conecta= new Connect();
		try {

			connection = DriverManager.getConnection("jdbc:postgresql://146.141.240.87:5432/APIPractice?sslmode=require", "vumile","defect.iota.foxhole");

		

			 if(connection!=null ) {
				  query=connection.createStatement();
				  //writting a query thqt selects the description contained in table with error
				ResultSet rs= query.executeQuery("select awi_number from study_with_error;");
				
			     //initialising the variable that helps with storing data into temp string array
				
				//after the query has been sent to database we retrieve some value
				while(rs.next()) {
					//getting values from awi_number
				ID=rs.getString("awi_number");
			     
				//////////////////////////////////////////////////////////////////////////////////////////////////
				
				String[] translate;
				String[] strArray;
		        String val="";
				myclass= new MyClass(ID);
			 String t=(myclass.doPost()).toString();
			    strArray = t.split("checklist_complete");
			    translate=strArray[1].split(",");
			   
			   
				//refining the system
				for(int y=0; y<translate.length-1;y++) {
						if("".equals(translate[y])) {
							translate[y]="0";
							//System.out.println("pspspspspspspspsp");
						}
						//System.out.println(translate[y]);
				}
				int y=0;
				System.out.println(translate[7]);
				for(String str:translate) {
					
					if((y<translate.length-1)) {
					
						val=val+"'"+str+"',";
						y++;
					
					}
					
											}
	             System.out.println(val);						
				sql="INSERT INTO AWI_Gen_API_PracticeIII VALUES"+"("+val+"'0');";
			 try {
			 	conecta.connec(sql,ID,val,funtions_tht_apply);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 //JTextField value=new JTextField(get_value);
			 //responce=value;
			}
				///////////////////////////////////////////////////////////////////////////////////////////////////
		
			}   //closing database connection
			
			 }
	
		catch (org.postgresql.util.PSQLException e) {
			System.out.println(e);
		}
		
		
		}
}

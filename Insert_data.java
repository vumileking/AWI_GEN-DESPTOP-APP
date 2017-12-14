import java.sql.SQLException;
import javax.swing.JTextField;
public class Insert_data {
	
	public void insert(JTextField initial, JTextField final_,String table_name,String[] function_name, String[] country_name) {
		
		Connect conecta= new Connect();
		
		
		//converting final_patiant_number to int
		String f_pat = final_.getText();
		int finl= Integer.parseInt(f_pat);
		//converting intial_patiant_number to int
		String i_pat = initial.getText();
		int initl = Integer.parseInt(i_pat);
	for(int x =initl; x<finl+1;x++) {
		String ID;
	    String sql;
		MyClass myclass;
		String val="";
		//designing ID
		if(x>0 && x<=9) {
			//creating ID
			ID="P000"+x;
			
			String[] translate;
			String[] strArray;
	
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
				
				if((y<translate.length-1)&& " ".equals(country_name[0])) {
				
					val=val+"'"+str+"',";
					y++;
				
				}else {
					if(country_name[0]!=" ") {
						for(int i=0; i<country_name.length;i++) {
							
							if((y<translate.length-1)&&translate[7].equals(country_name[i])) {
								val=val+"'"+str+"',";
								y++;
								
							}
							
						}
						
					}
					
				}
				
										}
             System.out.println(val);						
			sql="INSERT INTO "+table_name+" VALUES"+"("+val+"'0');";
		 try {
		 	conecta.connec(sql,ID,val,function_name);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 //JTextField value=new JTextField(get_value);
		 //responce=value;
		}
		else {
			if(x>9 && x<=99 ) {
			//creating ID
				ID="P00"+x;
				String[] translate;
				String[] strArray;
		
				myclass= new MyClass(ID);
			 String t=(myclass.doPost()).toString();
			    strArray = t.split("checklist_complete");
			    translate=strArray[1].split(",");
			    System.out.println(strArray[0]);
			   
				//refining the system
				for(int y=0; y<translate.length-1;y++) {
						if("".equals(translate[y])) {
							translate[y]="0";
							//System.out.println("pspspspspspspspsp");
						}
						//System.out.println(translate[y]);
				}
				int y=0;
				for(String str:translate) {
					
					if(y<translate.length-1) {
					
						val=val+"'"+str+"',";
						y++;
					
					}
					
											}
                 System.out.println(val);						
				sql="INSERT INTO "+table_name+" VALUES"+"("+val+"'0');";
				System.out.print(sql);
			 try {
			 	conecta.connec(sql,ID,val,function_name);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
		  	if(x>99 && x<=999) {
				//creating ID
		  		 ID="P0"+x;
					String[] translate;
					String[] strArray;
			
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
					for(String str:translate) {
						
						if(y<translate.length-1) {
						
							val=val+"'"+str+"',";
							y++;
						
						}
						
												}
                     System.out.println(val);						
					sql="INSERT INTO "+table_name+" VALUES"+"("+val+"'0');";
				 try {
				 	conecta.connec(sql,ID,val,function_name);
				 	
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
		 }else {
			 if(x>999 && x<9999) {
				 //Creating ID
				  ID="P"+x;
					String[] translate;
					String[] strArray;
			
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
					for(String str:translate) {
						
						if(y<translate.length-1) {
						
							val=val+"'"+str+"',";
							y++;
						
						}
						
												}
                   //  System.out.println(val);						
					sql="INSERT INTO "+table_name+" VALUES"+"("+val+"'0');";
					
				 try {
				 	conecta.connec(sql,ID,val,function_name);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 }
			
		}
		
	}
//-------------------------------------------------			

	//System.out.println("Hio   cscxcxsdsdsdsdsdsassaasdsaasdas");
// TODO Auto-generated method stub
//File out = new File("docum");
//out.println(" sdfsdfsf");

/////////////////////////////////////////////////
		}
		
	}
}
}

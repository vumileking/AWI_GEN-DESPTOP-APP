import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/* aim is to create a connect class*/
public class Connect {

	public void connec(String sql, String ID, String val, String[] function_name) throws SQLException {
		MyClass myclass;
        //Defining a variables responsible of establishing connection
		Connection connection = null;
		Statement query = null;
		try {
			//defining connection and all the securities  requered
			connection = DriverManager.getConnection(
					"jdbc:postgresql://xxx.xxx.xxx.xx:5432/APIPractice?sslmode=require", "vumile",
					"*********");
            // if connection is established we swnd thw query to te database
			if (connection != null) {
				//assigning query  to connection
				query = connection.createStatement();
				//sending query to database
				// node the variable sql contains the string of insert query
				// and is from the class insert data
				ResultSet rs = query.executeQuery(sql);
				// connection.commit();
				// while(rs.next()) {
				// String awi_number=rs.getString("awi_number");
				// System.out.print(awi_number);
				// }
				// closing the connection after sending the query to the database
				rs.close();
			}
			// catch is for incase there is some database error
		} catch (org.postgresql.util.PSQLException e) {

			// System.out.println("Connection Failed! Check output console");
			// e.printStackTrace();
			//defininng variables that are responsible of holding error explanation data.
			String[] find_positn;
			String[] fst_spli;
			//variable responsible for identifyin the column that has an error
			int count_column = 0;
			// converting the error statement into a arrray
			find_positn = (e.toString()).split(" ");
			//splits the data from redcap, so we can spedcify the variable that has fault
			fst_spli = val.split("");
			//// now the aim is to find the possition thta has an error by spacifying the exect  data in the  aaray 
			for (int v = 0; v < find_positn.length; v++) {
				//if it happens that the number of column that is specified in the redcap is equal to the error column number specified by the erro 
				//then confims that the column specified is the one that has an erro
				if ("Position:".equals(find_positn[v])) {
					int arry_no = Integer.parseInt(find_positn[v + 1]);
					// 44 is to minase all the junk that we dont need thata stored in the array
					arry_no = arry_no - 44;
					// System.out.println(fst_spli[1]);
					// now the aim is to count through the columns that are specified by the redcap
					for (int x = 0; x < arry_no + 1; x++) {
						if (",".equals(fst_spli[x])) {
							//count trough the redcap  column name
							count_column++;
						}
					}
					// retrieve value that are iin the redcap
					myclass = new MyClass(ID);
					String t = (myclass.doPost()).toString();
					String[] strArray = t.split("checklist_complete");
					String[] translate = strArray[0].split(",");
					// if the  column is no empty we submit the column number and the spacific error
					if (translate[count_column] != null) {

						try {
							query = connection.createStatement();
							ResultSet rs = query.executeQuery("INSERT INTO study_with_error VALUES('" + ID + "','" + e+ " COLUMN_NAME: " + translate[count_column] + "','0','0');");
							// connection.commit();
							rs.close();
						} catch (org.postgresql.util.PSQLException i) {

							System.out.println(i);
						}
					}

				}
			}
			
			// ---------------------applying the functions-----------------------
			//this section is for applying the functions that are stored in the database
			// re-establiting the connection since we closed it
		connection = DriverManager.getConnection("jdbc:postgresql://146.141.240.87:5432/APIPractice?sslmode=require",
				"vumile", "defect.iota.foxhole");
		//variable use when comperring the data that is in the variable
			int indicator=0;
			// etsblising auto confime, even though it is dengerous
		connection.setAutoCommit(true);
		//de finning rs so thata it can work in depth of the code
		ResultSet rs;
		// if connection is ectablished thn we apply the funtions
		if (connection != null) {
			//we stored all the funtions and their repective patiant number in the array so that
			// all the funtions that are in the database are applyed to the data with respect to the Patiant nummber which is ID
			
			String[] funtions_tht_apply = { "age_restriction", " body_mass_index","women_pregnent_more_than_times", "pregenet_man" };
			String[] carry_temp2 =new String[2];
			String columnValue = "";
			int u=0;
			// count through the  funtions called and compere them. 
			for (int k = 0; k < funtions_tht_apply.length; k++) {
				// re-establishing connection
				query = connection.createStatement();
				int w = 0;
				// funtion_name represent the array from the class where you specify the funtions you want to apply on the data you retrieve
				for (int r = 0; r < function_name.length; r++) {
					//checking the comparizon of the funtions that are selected, only if selected.
					// no funtions have been selected tthe n all funtions are applyed to the data retrieved
					//the funtions selected by the user are functions that the user does'nt want to be applyed to the data.
					
					if (funtions_tht_apply[k] == function_name[r]) {
						k += 1;
						//System.out.println(function_name[r]);
						System.out.println("kkkkkk");
						r = function_name.length;
						w = 1;
					} else {
						//if there's non of the funtions have been applyed on the data the, all functions are applied on the data rselected to be retrieved
						if (w == 0 && r + 1 == function_name.length) {
							// here we call the funtion thats in the data base then the funtion will be applyed on the data
							// it will responda about how the data is
							 rs = query.executeQuery("SELECT "+funtions_tht_apply[k]+"_2('"+ID+"');");
							// connection.commit();
							ResultSetMetaData rsmd = rs.getMetaData();
							// System.out.println("querying SELECT * FROM XXX");
							int columnsNumber = rsmd.getColumnCount();
							
							
							
						// the response  of the data will now be retrieved below 
								while (rs.next()) {
									for (int i = 1; i <= columnsNumber; i++) {
										
										//if (i > 1)
											//System.out.print(",  ");
										//columnValue = columnValue + "" + rs.getString(i);
										// if there is response we take the information store it in the array
										if(!"".equals(rs.getString(i))) {
										//take the response from the function take it to the array	
										carry_temp2[u]=rs.getString(i);
										//makes sure that there was response from the function
										indicator =1;
										//System.out.print(columnValue + " " + rsmd.getColumnName(i));
										// count the array index adress
										u++;
										}
									}
									// usless print, waist of bits just for control.
									System.out.println("");
								}
							
							
							//System.out.println(funtions_tht_apply[k]);
						}

					}
				}

			}// if the funtion really responded about the data sent then: 
			if(indicator==1) {
				for(int y=0; y<carry_temp2.length;y++) {
					if(carry_temp2[y]!=null) {
						// take all the content that is in the array to a string.
					columnValue=columnValue+carry_temp2[y];
					}
				}
				
			
		try {
		
		// if we find dert int the  data we delete it from the database
	    rs = query.executeQuery("DELETE FROM awi_gen_api_practiceiii WHERE awi_number='"+ ID +"';");
		
		connection.commit();
	
		}// is there is an error while trying to delete, let us know whats the problem.
		catch(org.postgresql.util.PSQLException qw) {
			System.out.println(qw);
		}
		
		try {//printing out the funtion comment just for control
			System.out.println(columnValue);
			//insert the study that has an error to the database and the type of an error that it has.
			rs = query.executeQuery("INSERT INTO study_with_error VALUES('" + ID + "','"+ columnValue + "','0','0');");
			connection.commit();		
			// close database connection
			rs.close();
			}//if in case there is an error
			catch(org.postgresql.util.PSQLException esd) {
				System.out.println(esd);
			}
		}
			// while(rs.next()) {
			// String awi_number=rs.getString("awi_number");
			// System.out.print(awi_number);
			// }

		}

		}

		
	}

}
//THANK YOU!!!
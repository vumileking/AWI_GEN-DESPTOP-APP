# AWI_GEN-DESPTOP-APP
Desktop application for refining and valideting data in database

# Funtionality of this software

 The program is developed using Java programming language. It consists of seven classes and one main class. All classes consists of different functions when called by the main class. Data used by the this programme is downloaded from redcap and inserted to postgresql after it has been refined and validated.

  # AWI_GEN_API_DESK functionality by class.
   
 #  API_AWIgwn.java:
 is the main class that uses  swing libraries to create a GUI. It consists of fields where you can      type in and specify the range of data you want to download from redcap( class MyClass.Java called in this case), the Exception clickbox and radio button where you can adjust rule for refining data( Connect.java called), a button that project a new window that displays data that has error(s) and specifies where are those error(s) (meaning the data that does not comply with the rules set for accepting data to database), button that allows the data administrator to send the data that has error(s) via email to the data surveyor so he/she can update it, and  a button that updates the data that has error(s). All these activities call different classes. 

# MyClass.Java:
This class is responsible for downloading data from redcap. It only requires patient id (which is passed by  API_AWIgwn.java) then it gets all the content requested. It download one data content at the time.

# Insert_data.jave:
When specifying the range on the GUI, this class ensures that all patient  id(s)  between the initial and final patient id(s) specified by the data administrator is requested from MyClass.java.

# Connect.java:
Ensures that there is database connection. When clicking on Exception the clickbox and radio button disables functions that validate, whether the data does meet the rules or not. Note that if the data administrator did not click on one of the exception boxes then all rules will be applied automatically. This class also checks where the errors are in the data and save the erroneous data separately.

# retrieve_email.java:
This is just a pop up window that allows the user to be able to type in the designated email address to the data administrator.

# Send_email.java:
This class allows the data administrator to be able to send all data that is erroneous to the data surveyor via email.

# update.java:
If it happens that the data surveyor has updated data and the erroneous data, this class  update data.



 

      


import javax.swing.*;
import java.awt.*;
import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
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

public class API_AWIgwn {

	private JFrame frame;
	private JTextField initial_patiant_number;
	private JTextField final_patiant_number;
	private JButton download_data;
	private JButton check_error;	/**
	 * @wbp.nonvisual location=111,367
	 */
	private JTable study_with_error_table;
	private JButton btnErrorNotificationEmail;
	private JSeparator separator;
	private JLabel label;
	private JCheckBox data_validation;
	private JCheckBox age_restriction;
	private JCheckBox body_mass_index;
	private JCheckBox woman_preg_mor_20_tims;
	private JCheckBox man_pregnency;
	private JLabel lblNewLabel_1;
	private JRadioButton sa;
	private JRadioButton Kenya;
	private JRadioButton Ghana;
	private JRadioButton Bf;
	private JButton btnUpdateErrors;
	
     
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					API_AWIgwn window = new API_AWIgwn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public API_AWIgwn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setMaximumSize(new Dimension(147483647, 147483647));
		frame.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 12));
		frame.getContentPane().setFocusTraversalPolicyProvider(true);
		frame.getContentPane().setFocusCycleRoot(true);
		frame.setBounds(100, 100, 450, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 10, 10);
		flowLayout.setAlignOnBaseline(true);
		frame.getContentPane().setLayout(flowLayout);
		
		label = DefaultComponentFactory.getInstance().createTitle("Please tick if you want to make some data exception:");
		frame.getContentPane().add(label);
		
		data_validation = new JCheckBox("Data type validation                                                             ");
		frame.getContentPane().add(data_validation);
		
		sa = new JRadioButton("SA");
		frame.getContentPane().add(sa);
		
		Kenya = new JRadioButton("Kenya");
		frame.getContentPane().add(Kenya);
		
		Ghana = new JRadioButton("Ghana");
		frame.getContentPane().add(Ghana);
		
		Bf = new JRadioButton("BF");
		frame.getContentPane().add(Bf);
		
		age_restriction = new JCheckBox("Age Restriction                                           ");
		frame.getContentPane().add(age_restriction);
		
		body_mass_index = new JCheckBox("Body Mass Index");
		frame.getContentPane().add(body_mass_index);
		
		woman_preg_mor_20_tims= new JCheckBox("Woman pregnent more than 20 times                  ");
		frame.getContentPane().add(woman_preg_mor_20_tims);
		
		man_pregnency =new JCheckBox("Pregnent man                                                ");
		frame.getContentPane().add(man_pregnency);

		
		JLabel lebel2 = new JLabel("Intitial partiant number:");
		frame.getContentPane().add(lebel2);
		
		initial_patiant_number = new JTextField();
		frame.getContentPane().add(initial_patiant_number);
		initial_patiant_number.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Final Pationt number:   ");
		frame.getContentPane().add(lblNewLabel);
		
		final_patiant_number = new JTextField();
		frame.getContentPane().add(final_patiant_number);
		final_patiant_number.setColumns(10);
	    
		
		download_data = new JButton("Retrieve");
		download_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				//call class
				Insert_data insrt_data= new Insert_data();
				//call validation fanction name
				String[] function_name= new String[3];
				String[] country_name= new String[3];
				String ID="";
				country_name[0]=" ";
				    int a=0;
					int b=0;
					int c=0;
					int d=0;
				for(int i=0;i<3;i++) {
					if(sa.isSelected()) {
						country_name[i]="1";
					}else {
						if(Kenya.isSelected()) {
							country_name[i]="2";
						}else {
							if(Ghana.isSelected()) {
							country_name[i]="3";	
							}else {
								if(Bf.isSelected()) {
									country_name[i]="4";
								}
							}
						}
					}
					
					//selecting the restriction
					if(age_restriction.isSelected()&&(a!=1)) {
						function_name[i]="body_mass_index";
					 a=1;
					}else {
						if(body_mass_index.isSelected()&&(b!=1)) {
							function_name[i]="age_restriction";
							
							b=1;
						}else {
							if(woman_preg_mor_20_tims.isSelected()&&(c!=1)) {
								function_name[i]="woman_pregnent_more_than_20_times";
								c=1;

								
							}else {
								if(man_pregnency.isSelected()&&(d!=1)) {
									function_name[i]="pregenet_man";
									
									d=1;
								}
								
							}
							
						}
					}
				}
				
				if(data_validation.isSelected()) {
					insrt_data.insert(initial_patiant_number, final_patiant_number,"ACCEPTION",function_name,country_name);
				}else {
					insrt_data.insert(initial_patiant_number, final_patiant_number,"awi_gen_api_practiceiii",function_name,country_name);
				}
				}
				catch( java.lang.NumberFormatException f){
					
					//custom title, error icon
					JOptionPane.showMessageDialog(frame,
					    "Input error on partiant number! Please insert patiant number and leave no space.",
					    "Input error",
					    JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		frame.getContentPane().add(download_data);
		
		check_error = new JButton("Check table error");
		check_error.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Insert_data insrt_data= new Insert_data();
				
				Desplay_error display=new Desplay_error();
				display.Display_table();
				
			}

		});
		
		lblNewLabel_1 = new JLabel("Data with error is diplayed below:                 ");
		frame.getContentPane().add(lblNewLabel_1);
		frame.getContentPane().add(check_error);
		//for button send email with error
		btnErrorNotificationEmail = new JButton("Error notification email");
		btnErrorNotificationEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Send_email send_email =new Send_email();
				send_email.send_email();
			}
		});
		frame.getContentPane().add(btnErrorNotificationEmail);
		
		separator = new JSeparator();
		frame.getContentPane().add(separator);
		
		btnUpdateErrors = new JButton("Update errors");
		btnUpdateErrors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*insert the values that update data*/
				update up_date_data= new update();
				try {
					up_date_data.Updat_o();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnUpdateErrors);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lebel2, initial_patiant_number, lblNewLabel, final_patiant_number, download_data, check_error}));
		
		

		
			}
		
		

	}

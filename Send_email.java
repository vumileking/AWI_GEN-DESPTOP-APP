import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
public class Send_email {
	// this is simply an interface for sending an email
	final JFrame frame = new JFrame("Send email");
	private JTextField initial_patiant_number;
	private JTextField final_patiant_number;
	private JTextField email_field;
	private JButton send_email;
	public void send_email() {
		
		
		frame.getContentPane().setMaximumSize(new Dimension(147483647, 147483647));
		frame.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 12));
		frame.getContentPane().setFocusTraversalPolicyProvider(true);
		frame.getContentPane().setFocusCycleRoot(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FlowLayout flowLayout = new FlowLayout(0, 10, 10);
		flowLayout.setAlignOnBaseline(true);
		frame.getContentPane().setLayout(flowLayout);

		
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
		
		JLabel lebel3 = new JLabel("enter email address:");
		frame.getContentPane().add(lebel3);
		
		email_field = new JTextField();
		frame.getContentPane().add(email_field);
		email_field.setColumns(10);
		
		send_email= new JButton("Send email");
		send_email.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int get_results=0;
				retrieve_email_data now_sending =new retrieve_email_data();
				get_results=now_sending.retrieve_and_send(initial_patiant_number, final_patiant_number, email_field);
				
				if(get_results==1) {
					System.out.println("Sent");
				}
				else {
					System.out.println("Not Sent");
				}
			}
		});
		frame.getContentPane().add(send_email);
		 frame.setSize(650, 200);
		   frame.setVisible(true);
	
}}

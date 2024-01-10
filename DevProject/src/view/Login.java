package view;

import java.awt.BorderLayout;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Login extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	private JButton btnSubmit;
	
	/**
	 * Create the frame.
	 */
	public Login() {
		
		
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Task Funnel");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		username = new JTextField();
		username.setBounds(193, 333, 86, 20);
		panel.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(193, 366, 86, 20);
		panel.add(password);
		password.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(193, 407, 89, 23);
		panel.add(btnSubmit);
		
		JLabel tFLogo = new JLabel("");
		tFLogo.setIcon(new ImageIcon(Login.class.getResource("/view/TF.png")));
		tFLogo.setBounds(86, 11, 315, 289);
		panel.add(tFLogo);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(123, 336, 70, 14);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(123, 369, 70, 14);
		panel.add(lblPassword);
		
		
	}
	
	
	
	
	public String getUsername() {
		return username.getText();
	}
	
	public String getPassword() {
		return password.getText();
	}
	
	public void clearPassword() {
		password.setText("");
	}


	


	public void addLoginSubmitListener(ActionListener e) {
		btnSubmit.addActionListener(e);
		
	}
}

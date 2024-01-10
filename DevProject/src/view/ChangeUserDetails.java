package view;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;

public class ChangeUserDetails extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField displayNameField;
	private JTextField currentPassField;
	private JTextField newPassField;
	private JButton btnUpdatePassword;
	private JButton btnUpdateName;
	
	public ChangeUserDetails() {
		
		JPanel changeNamePanel = new JPanel();
		changeNamePanel.setBackground(Color.WHITE);
		
		JPanel ChangePassPanel = new JPanel();
		ChangePassPanel.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(changeNamePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(99)
					.addComponent(ChangePassPanel, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
					.addGap(23))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(changeNamePanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
						.addComponent(ChangePassPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(56))
		);
		
		JLabel lblChangeUserPassword = new JLabel("Change User Password:");
		
		JLabel lblEnterCurrentPassword = new JLabel("Enter Current Password:");
		
		currentPassField = new JPasswordField();
		currentPassField.setColumns(10);
		
		JLabel lblEnterNewPassword = new JLabel("Enter New Password:");
		
		newPassField = new JPasswordField();
		newPassField.setColumns(10);
		
		btnUpdatePassword = new JButton("Update Password");
		GroupLayout gl_ChangePassPanel = new GroupLayout(ChangePassPanel);
		gl_ChangePassPanel.setHorizontalGroup(
			gl_ChangePassPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ChangePassPanel.createSequentialGroup()
					.addGroup(gl_ChangePassPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ChangePassPanel.createSequentialGroup()
							.addGap(56)
							.addGroup(gl_ChangePassPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEnterNewPassword)
								.addComponent(lblEnterCurrentPassword)
								.addComponent(currentPassField, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addComponent(newPassField, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnUpdatePassword)))
						.addGroup(gl_ChangePassPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblChangeUserPassword)))
					.addContainerGap(58, Short.MAX_VALUE))
		);
		gl_ChangePassPanel.setVerticalGroup(
			gl_ChangePassPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ChangePassPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblChangeUserPassword)
					.addGap(35)
					.addComponent(lblEnterCurrentPassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(currentPassField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblEnterNewPassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(newPassField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
					.addComponent(btnUpdatePassword)
					.addGap(49))
		);
		ChangePassPanel.setLayout(gl_ChangePassPanel);
		
		JLabel lblChangeDisplayName = new JLabel("Change Display Name:");
		
		JLabel lblCurrentDisplayName = new JLabel("Current Display Name:");
		
		displayNameField = new JTextField();
		displayNameField.setColumns(10);
		
		btnUpdateName = new JButton("Update Name");
		GroupLayout gl_changeNamePanel = new GroupLayout(changeNamePanel);
		gl_changeNamePanel.setHorizontalGroup(
			gl_changeNamePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_changeNamePanel.createSequentialGroup()
					.addGroup(gl_changeNamePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_changeNamePanel.createSequentialGroup()
							.addGap(57)
							.addGroup(gl_changeNamePanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCurrentDisplayName)
								.addGroup(gl_changeNamePanel.createSequentialGroup()
									.addGap(10)
									.addComponent(btnUpdateName))
								.addComponent(displayNameField, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_changeNamePanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblChangeDisplayName)))
					.addContainerGap(65, Short.MAX_VALUE))
		);
		gl_changeNamePanel.setVerticalGroup(
			gl_changeNamePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_changeNamePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblChangeDisplayName)
					.addGap(85)
					.addComponent(lblCurrentDisplayName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(displayNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
					.addComponent(btnUpdateName)
					.addGap(46))
		);
		changeNamePanel.setLayout(gl_changeNamePanel);
		setLayout(groupLayout);
		
	}
	
	public String getDisplayName() {
		return displayNameField.getText();
	}
	
	public void setDisplayName(String name) {
		displayNameField.setText(name);
	}
	
	public String getCurrentPass() {
		return currentPassField.getText();
	}
	
	public String getNewPass() {
		return newPassField.getText();
	}
	
	public void addUpdateNameListener(ActionListener e) {
		btnUpdateName.addActionListener(e);
	}
	
	public void addUpdatePasswordListener(ActionListener e) {
		btnUpdatePassword.addActionListener(e);
	}
}

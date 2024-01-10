package view;

import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionListener;

import model.Task;
import model.User;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class ManageUsers extends JPanel {
	private JTextField username;
	private JTextField password;
	private JTextField displayName;
	private JList<User> list;
	private DefaultListModel<User> items;
	private JButton btnRemoveUser;
	private JButton btnAddUser;
	private JButton btnClearFields;
	private JCheckBox chckbxAdmin;

	public ManageUsers() {
		
		JLabel lblManageUsers = new JLabel("Manage Users:");
		
		items = new DefaultListModel<User>();
		list = new JList<>(items);
		
		username = new JTextField();
		username.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		
		JLabel lblPasswordtempory = new JLabel("Password (tempory):");
		
		password = new JTextField();
		password.setColumns(10);
		
		JLabel lblDisplayName = new JLabel("Display Name:");
		
		displayName = new JTextField();
		displayName.setColumns(10);
		
		chckbxAdmin = new JCheckBox("admin");
		
		btnAddUser = new JButton("Add User");
		
		btnRemoveUser = new JButton("Remove User");
		
		btnClearFields = new JButton("Clear Fields");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblManageUsers))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(76)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 561, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(200)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPasswordtempory)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(password))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblUsername)
									.addGap(18)
									.addComponent(username, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDisplayName)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(displayName))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAddUser)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnRemoveUser)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnClearFields))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(247)
							.addComponent(chckbxAdmin)))
					.addContainerGap(86, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblManageUsers)
					.addGap(18)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPasswordtempory)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDisplayName)
						.addComponent(displayName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxAdmin)
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddUser)
						.addComponent(btnRemoveUser)
						.addComponent(btnClearFields))
					.addContainerGap(162, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	
	public void populateList(ArrayList<User> allUsers) {
		list.clearSelection();
		items.clear();
		allUsers.forEach(x-> items.addElement(x));
	}
	public ArrayList<Object> getFields(){
		ArrayList<Object> fieldList = new ArrayList<Object>();
		fieldList.add(username.getText().toString());
		fieldList.add(password.getText().toString());
		fieldList.add(displayName.getText().toString());
		fieldList.add(chckbxAdmin.isSelected());
		return fieldList;
	}
	public Object getListSelection() {
		return list.getSelectedValue();
	}
		
	public void addMUListListener(ListSelectionListener listListener) {
	   	 list.addListSelectionListener(listListener); 
	   	 }
	
	
	public void addBtnAddUserListener(ActionListener e) {
		btnAddUser.addActionListener(e);
	}
	
	public void addBtnDeleteUserListener(ActionListener e) {
		btnRemoveUser.addActionListener(e);
	}
	
	public void addBtnClearFieldsListener(ActionListener e) {
		btnClearFields.addActionListener(e);
	}

	public void clearFields() {
		username.setText("");
		password.setText("");
		displayName.setText("");
		
	}
}

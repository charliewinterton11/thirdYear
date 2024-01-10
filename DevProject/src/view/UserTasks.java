package view;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionListener;

import model.Status;
import model.Task;

import javax.swing.JTextField;

public class UserTasks extends JPanel {


	private static final long serialVersionUID = 1L;
	private ButtonGroup bGroup;
	private JTextField nameField;
	private JTextField dateField;
	private JTextField durationField;
	private JList<Task> list;
	private DefaultListModel<Task> items;
	private JTextArea descText;
	private JRadioButton notStartedRadioButton;
	private JRadioButton inProgressRadioButton;
	private JRadioButton finishedRadioButton;
	private JButton btnUpdateStatus;

	
	public UserTasks() {
		
		JScrollPane scrollPane = new JScrollPane();
		items = new DefaultListModel<Task>();
		list = new JList<>(items);
		scrollPane.setViewportView(list);
		
		JLabel lblYourTasks = new JLabel("Your Tasks:");
		bGroup = new ButtonGroup();
		
		notStartedRadioButton = new JRadioButton("Not Started");
		
		inProgressRadioButton = new JRadioButton("In Progress");
		
		finishedRadioButton = new JRadioButton("Finished");
		bGroup.add(finishedRadioButton);
		bGroup.add(inProgressRadioButton);
		bGroup.add(notStartedRadioButton);
		
		btnUpdateStatus = new JButton("Update Status");
		
		JLabel lblSelectedTask = new JLabel("Selected Task:");
		
		JLabel lblTaskName = new JLabel("Task Name:");
		
		JLabel lblTaskDescription = new JLabel("Task Description:");
		
		descText = new JTextArea();
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		
		dateField = new JTextField();
		dateField.setColumns(10);
		
		JLabel lblDuration = new JLabel("Duration:");
		
		durationField = new JTextField();
		durationField.setColumns(10);
		
		
		
		JLabel lblTaskStatus = new JLabel("Task Status:");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblYourTasks, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
					.addGap(32))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSelectedTask, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(539, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(118)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTaskDescription, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblTaskName, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
									.addGap(17))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblDuration)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(durationField, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
											.addGap(9))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblStartDate)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(dateField, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)))
									.addGap(15))
								.addComponent(descText, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTaskStatus)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(notStartedRadioButton)
									.addGap(18)
									.addComponent(inProgressRadioButton)
									.addGap(18)
									.addComponent(finishedRadioButton)))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUpdateStatus, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(85))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblYourTasks)
					.addGap(5)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblSelectedTask)
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTaskName)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addComponent(lblTaskDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(descText, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(dateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStartDate))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDuration)
						.addComponent(durationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
					.addComponent(lblTaskStatus)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(notStartedRadioButton)
						.addComponent(inProgressRadioButton)
						.addComponent(btnUpdateStatus)
						.addComponent(finishedRadioButton))
					.addGap(27))
		);
		setLayout(groupLayout);

	}
	public void displayTask(Task task) {
		nameField.setText(task.getTaskName());
		descText.setText(task.getTaskDesc());
		dateField.setText(task.getStartDate().toString());
		durationField.setText(String.valueOf(task.getDuration())+" day(s)");
		
		if(task.getTaskStatus().equals(Status.Not_Started)) {
			notStartedRadioButton.setSelected(true);
		} else if (task.getTaskStatus().equals(Status.In_Progress)){
			inProgressRadioButton.setSelected(true);
		} else if (task.getTaskStatus().equals(Status.Finished)) {
			finishedRadioButton.setSelected(true);
		}
	}
	
	public void populateList(ArrayList<Task> tasks) {
		tasks.forEach(x-> items.addElement(x));
		list.setSelectedIndex(0);
	}
	public void clearList() {
		items.clear();
	}
	
	public void addListListener(ListSelectionListener listListener) {
   	 list.addListSelectionListener(listListener); 
   	 }
	
	public void addUpdateButtonListener(ActionListener e) {
		btnUpdateStatus.addActionListener(e);
}
	
	public Object getListSelection() {
		return list.getSelectedValue();
	}
	
	public ArrayList<JRadioButton> getRadioBtnSelection() {
		ArrayList<JRadioButton> buttons = new ArrayList<JRadioButton>();
		buttons.add(notStartedRadioButton);
		buttons.add(inProgressRadioButton);
		buttons.add(finishedRadioButton);
		return buttons;
	}
	
	
}

package view;

import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionListener;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JEditorPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import model.Project;
import model.Task;
import model.User;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

public class ManageTasks extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JComboBox<User> comboBoxMember;
	private JComboBox<Project> comboBoxProject;
	private JList<Task> list;
	private JEditorPane descPane;
	private JSpinner spinner;
	private DefaultListModel<Task> items;
	private JButton btnAddTask;
	private JButton btnDeleteTask;
	private JButton btnClearFields;
	private UtilDateModel model;
	private JDatePickerImpl dateField;
	private SpringLayout springLayout;

	public ManageTasks() {
		
		JLabel lblManageTasks = new JLabel("Manage Tasks");
		lblManageTasks.setBounds(10, 11, 99, 14);
		items = new DefaultListModel<Task>();
		
		nameField = new JTextField();
		nameField.setBounds(266, 217, 176, 20);
		nameField.setColumns(10);
		
		JLabel lblTaskName = new JLabel("Task Name:");
		lblTaskName.setBounds(200, 220, 77, 14);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(200, 255, 77, 14);
		
		descPane = new JEditorPane();
		descPane.setBounds(200, 275, 242, 59);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(200, 352, 77, 14);
		
		JLabel lblTeamMember = new JLabel("Team Member:");
		lblTeamMember.setBounds(200, 375, 107, 14);
		
		comboBoxMember = new JComboBox<User>();
		comboBoxMember.setBounds(281, 372, 161, 20);
		
		JLabel lblProject = new JLabel("Project:");
		lblProject.setBounds(200, 406, 57, 14);
		
		comboBoxProject = new JComboBox<Project>();
		comboBoxProject.setBounds(256, 403, 186, 20);
		
		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setBounds(200, 437, 77, 14);
		
		spinner = new JSpinner();
		spinner.setBounds(276, 434, 51, 20);
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		
		btnAddTask = new JButton("Add Task");
		btnAddTask.setBounds(178, 472, 99, 23);
		
		btnDeleteTask = new JButton("Delete Task");
		btnDeleteTask.setBounds(283, 472, 89, 23);
		
		btnClearFields = new JButton("Clear Fields");
		btnClearFields.setBounds(378, 472, 107, 23);
		model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
		class DateLabelFormatter extends AbstractFormatter {
			private static final long serialVersionUID = 1L;
			private String datePattern = "yyyy-MM-dd";
		    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		    @Override
		    public Object stringToValue(String text) throws ParseException {
		        return dateFormatter.parseObject(text);
		    }

		    @Override
		    public String valueToString(Object value) throws ParseException {
		        if (value != null) {
		            Calendar cal = (Calendar) value;
		            return dateFormatter.format(cal.getTime());
		        }

		        return "";
		    }

		}
		dateField = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		dateField.setBounds(271, 343, 149, 23);
		add(dateField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 43, 577, 130);
		list = new JList<>(items);
		scrollPane.setViewportView(list);
		setLayout(null);
		add(dateField);
		add(lblManageTasks);
		add(lblDescription);
		add(btnAddTask);
		add(btnDeleteTask);
		add(btnClearFields);
		add(lblDuration);
		add(spinner);
		add(lblStartDate);
		add(lblTaskName);
		add(nameField);
		add(lblTeamMember);
		add(comboBoxMember);
		add(lblProject);
		add(comboBoxProject);
		add(descPane);
		add(scrollPane);

	}
	public void addMTListListener(ListSelectionListener listListener) {
	   	 list.addListSelectionListener(listListener); 
	   	 }
	
	public Object getListSelection() {
		return list.getSelectedValue();
	}
	public void addBtnAddTaskListener(ActionListener e) {
		btnAddTask.addActionListener(e);
	}
	
	public void addBtnDeleteTaskListener(ActionListener e) {
		btnDeleteTask.addActionListener(e);
	}
	
	public void addBtnClearFieldsListener(ActionListener e) {
		btnClearFields.addActionListener(e);
	}
	public void populateList(ArrayList<Task> allTasks) {
		list.clearSelection();
		items.clear();
		allTasks.forEach(x-> items.addElement(x));
		
	}
	public void populateUserBox(ArrayList<User> allUsers) {
		allUsers.forEach(x-> comboBoxMember.addItem(x));
		
	}
	public void populateProjectBox(ArrayList<Project> allProjects) {
		allProjects.forEach(x-> comboBoxProject.addItem(x));
		
	}
	public void clearFields() {
		nameField.setText("");	
		descPane.setText("");
		model.setValue(null);
		spinner.setValue(1);
	}
	
	public ArrayList<Object> getFields(){
		ArrayList<Object> fieldList = new ArrayList<Object>();
		fieldList.add(nameField.getText().toString());
		fieldList.add(descPane.getText().toString());
		fieldList.add(model.getValue());
		fieldList.add(comboBoxMember.getSelectedItem());
		fieldList.add(comboBoxProject.getSelectedItem());
		fieldList.add(spinner.getValue());
		return fieldList;
	}
}

package view;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionListener;
import javax.swing.SpinnerDateModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import model.Project;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class ManageProjects extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField projectNameField;
	private Component dateField;
	private JList list;
	private DefaultListModel<Project> items;
	private JTextArea textArea;
	private UtilDateModel model;
	private JSpinner spinner;
	private JButton btnAddProject;
	private JButton btnClearFields;
	private JButton btnModifyProject;
	private JButton btnDeleteProject;

	
	public ManageProjects() {
		
		items = new DefaultListModel<Project>();
        list = new JList<>(items);
		
		JLabel lblProjects = new JLabel("Projects: ");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(list, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
							.addGap(61)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
							.addGap(11))
						.addComponent(lblProjects))
					.addGap(43))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(lblProjects)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(list, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
					.addGap(32))
		);
		panel.setLayout(null);
		
		JLabel lblSelectedProject = new JLabel("Selected Project:");
		lblSelectedProject.setBounds(10, 11, 125, 14);
		panel.add(lblSelectedProject);
		
		projectNameField = new JTextField();
		projectNameField.setBounds(102, 57, 118, 20);
		panel.add(projectNameField);
		projectNameField.setColumns(10);
		
		JLabel lblProjectName = new JLabel("Project Name:");
		lblProjectName.setBounds(10, 60, 82, 14);
		panel.add(lblProjectName);
		
		JLabel lblProjectDescription = new JLabel("Project Description:");
		lblProjectDescription.setBounds(10, 85, 125, 14);
		panel.add(lblProjectDescription);
		
		
		
		textArea = new JTextArea();
		textArea.setBounds(8, 102, 272, 64);
		panel.add(textArea);
		textArea.setBackground(SystemColor.inactiveCaptionBorder);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		
		JLabel lblStartDate = new JLabel("Project Start Date: ");
		lblStartDate.setBounds(10, 182, 112, 14);
		
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
		dateField.setBounds(112, 177, 125, 23);
		panel.add(dateField);
		
		panel.add(lblStartDate);
		
		JLabel lblTotalProjectDuration = new JLabel("Total Project Duration: ");
		lblTotalProjectDuration.setBounds(10, 219, 125, 14);
		panel.add(lblTotalProjectDuration);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(132, 216, 52, 20);
		panel.add(spinner);
		
		btnAddProject = new JButton("Add Project");
		btnAddProject.setBounds(10, 269, 125, 23);
		panel.add(btnAddProject);
		
		btnClearFields = new JButton("Clear Fields");
		btnClearFields.setBounds(175, 269, 105, 23);
		panel.add(btnClearFields);
		
		btnModifyProject = new JButton("Modify Project");
		btnModifyProject.setBounds(10, 303, 125, 23);
		panel.add(btnModifyProject);
		
		btnDeleteProject = new JButton("Delete Project");
		btnDeleteProject.setBounds(10, 337, 125, 23);
		panel.add(btnDeleteProject);
		setLayout(groupLayout);
		

	}
	
	public void addMPListListener(ListSelectionListener listListener) {
   	 list.addListSelectionListener(listListener); 
   	 }
	
	public Object getListSelection() {
		return list.getSelectedValue();
	}

	public void populateList(ArrayList<Project> allProjects) {
		list.clearSelection();
		items.clear();
		allProjects.forEach(x-> items.addElement(x));
		
		
	}
	public void addToList(Project p) {
		items.addElement(p);
	}

	public void populateProjectFields(Project p) {
		projectNameField.setText(p.getProjectName());
		textArea.setText(p.getProjectDesc());
		model.setValue(p.getProjectStartDate());
		spinner.setValue(p.getProjectDuration());
	}
	
	public void clearFields() {
		projectNameField.setText("");
		textArea.setText("");
		model.setValue(null);
		spinner.setValue(0);
		
	}
	public ArrayList<Object> getFields(){
		ArrayList<Object> fieldList = new ArrayList<Object>();
		fieldList.add(projectNameField.getText());
		fieldList.add(textArea.getText());
		fieldList.add(model.getValue());
		fieldList.add(spinner.getValue());
		return fieldList;
	}
	
	public void addBtnAddProjectListener(ActionListener e) {
		btnAddProject.addActionListener(e);
	}
	
	public void addBtnModifyProjectListener(ActionListener e) {
		btnModifyProject.addActionListener(e);
	}
	
	public void addBtnDeleteProjectListener(ActionListener e) {
		btnDeleteProject.addActionListener(e);
	}
	
	public void addBtnClearFieldsListener(ActionListener e) {
		btnClearFields.addActionListener(e);
	}
}

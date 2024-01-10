package view;
import javax.swing.JList;


import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Project;
import model.Status;
import model.Task;
import model.User;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
public class ActiveProjects extends JPanel {
	

	private static final long serialVersionUID = 8963539775893493991L;
	private JButton btnShowGanttChart;
	private JList<Project> list;
	private DefaultListModel<Project> items;
	private JLabel projectNameLabel;
	private JTextArea projectDescTextArea;
	private JTextArea projectMembersTextArea;
	private DefaultTableModel tableModel;
	private JProgressBar progressBar;
	private JTable table;


	/**
	 * Create the panel.
	 */
	public ActiveProjects() {
		items = new DefaultListModel<Project>();
        list = new JList<>(items);
        list.setBounds(25, 37, 206, 449);
        list.setSelectionInterval(0, 0);
        setSize(730,528);
        
        
        JLabel lblAct = new JLabel("Projects:");
        lblAct.setBounds(25, 11, 90, 14);
        
        JPanel panel = new JPanel();
        panel.setBounds(274, 37, 422, 449);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        
        btnShowGanttChart = new JButton("Show Gantt Chart");
        btnShowGanttChart.setBounds(250, 11, 146, 27);
        panel.add(btnShowGanttChart);
        
        progressBar = new JProgressBar();
        progressBar.setBounds(109, 50, 304, 14);
        
        panel.add(progressBar);
        
        JLabel lblOverallProgress = new JLabel("Overall Progress:");
        lblOverallProgress.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblOverallProgress.setBounds(10, 50, 103, 14);
        panel.add(lblOverallProgress);
        
        JLabel lblProjectDescription = new JLabel("Project Description:");
        lblProjectDescription.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblProjectDescription.setBounds(10, 75, 134, 14);
        panel.add(lblProjectDescription);
        
        JLabel lblProjectMembers = new JLabel("Project Members:");
        lblProjectMembers.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblProjectMembers.setBounds(10, 156, 119, 14);
        panel.add(lblProjectMembers);
        
        
        
        projectNameLabel = new JLabel("");
        projectNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        projectNameLabel.setBounds(10, 16, 228, 22);
        panel.add(projectNameLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 252, 423, 198);
        panel.add(scrollPane);
        
        tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
        	    public boolean isCellEditable(int row, int column) {
        	       return false;
        	    }
        };
        tableModel.addColumn("Duration:"); 
		tableModel.addColumn("Task Name:"); 
		tableModel.addColumn("Team Member:");
		tableModel.addColumn("Start Date:");
		tableModel.addColumn("Status:");
		table = new JTable(tableModel);
		table.setColumnSelectionAllowed(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
        scrollPane.setViewportView(table);
        table.setRowSelectionAllowed(false);
        
        projectDescTextArea = new JTextArea();
        projectDescTextArea.setBounds(6, 90, 406, 60);
        panel.add(projectDescTextArea);
        projectDescTextArea.setForeground(Color.BLACK);
        projectDescTextArea.setEditable(false);
        projectDescTextArea.setBackground(Color.WHITE);
        projectDescTextArea.setWrapStyleWord(true);
        projectDescTextArea.setLineWrap(true);
        
        projectMembersTextArea = new JTextArea();
        projectMembersTextArea.setEditable(false);
        projectMembersTextArea.setBackground(Color.WHITE);
        projectMembersTextArea.setBounds(0, 173, 413, 67);
        panel.add(projectMembersTextArea);
        projectMembersTextArea.setLineWrap(true);
        projectMembersTextArea.setWrapStyleWord(true);
        setLayout(null);
        add(list);
        add(lblAct);
        add(panel);
        
	}
	
	public void populateList(ArrayList<Project> projects) {
		projects.forEach(x-> items.addElement(x));
	
    } 
	
	public void displayProject(Project project, ArrayList<Task> tasks, ArrayList<User> projectMembers) {
		projectNameLabel.setText(project.getProjectName());
		projectDescTextArea.setText(project.getProjectDesc());
		ArrayList<User> projectMembersNoDupes = new ArrayList<>(new HashSet<>(projectMembers));
		String text = "";
		for(int i=0;i < projectMembersNoDupes.size();i++) {
			User user = projectMembersNoDupes.get(i);
			text = text  + user.getDisplayName()+ "\n";
		}
		
		projectMembersTextArea.setText(text);
		tableModel.setRowCount(0);
		for(int i = 0;i<tasks.size();i++) {
			int user = tasks.get(i).getUserId();
			List<User> array = projectMembers.stream()
			.filter(User->User.getUserID()==user)
			.collect(Collectors.toList());
			String userName = array.get(0).getDisplayName();
			tableModel.addRow( new Object[] { tasks.get(i).getDuration()+" day(s)", tasks.get(i).getTaskName(),  userName , 
					tasks.get(i).getStartDate(), tasks.get(i).getTaskStatusString().replace('_',' ') });
			}
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table,
		            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

		        String status = (String)table.getModel().getValueAt(row, 4);
		        if ("Finished".equals(status)) {
		            setBackground(Color.GREEN);
		        }else if("In Progress".equals(status)) {
		        	setBackground(Color.ORANGE);
		        }else if("Not Started".equals(status)) {
		        	setBackground(Color.RED);
		        	
		        } else {
		            setBackground(table.getBackground());
		            setForeground(table.getForeground());
		        }       
		        return this;
		    }   
		});
		
		
		float finished = 0;
		float total = 0;
		List<Integer> completedTasks = tasks.stream()
				.filter(t->t.getTaskStatus().equals(Status.Finished))
				.map(Task::getDuration)
				.collect(Collectors.toList());
		List<Integer> uncompletedTasks = tasks.stream()
				.filter(t-> (t.getTaskStatus().equals(Status.Not_Started) || t.getTaskStatus().equals(Status.In_Progress)))
				.map(Task::getDuration)
				.collect(Collectors.toList());
		finished = completedTasks.stream().mapToInt(Integer::intValue).sum();
		total += uncompletedTasks.stream().mapToInt(Integer::intValue).sum();
		total += finished;
		float barValue = (finished/total)*100;
		progressBar.setValue((int) barValue);
	}
	
		
	
	public Object getListSelection() {
		return list.getSelectedValue();
	}
        
     public void addViewGanttChartListener(ActionListener e) {
    		btnShowGanttChart.addActionListener(e);
    }
     public void addListListener(ListSelectionListener listListener) {
    	 list.addListSelectionListener(listListener); 
    	 }
     
}



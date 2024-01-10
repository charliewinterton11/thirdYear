package view;

import java.awt.BorderLayout;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import model.Project;

public class GanttChart extends JFrame {

	private JPanel contentPane;
	private Project p;
	private ArrayList<model.Task> tasks;

	
	public GanttChart(Project p, ArrayList<model.Task>tasks) {
		this.p = p;
		this.tasks = tasks;
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 713, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setVisible(true);
		IntervalCategoryDataset dataset = getCategoryDataset(this.tasks);  
	    JFreeChart chart = ChartFactory.createGanttChart(  
	            this.p.getProjectName(),  
	            "Tasks", 
	            "Timeline", 
	            dataset, false, false, false);  
	
	  
	      ChartPanel panel = new ChartPanel(chart); 
	      contentPane.add(panel);
	      

	   }  
	  

	private IntervalCategoryDataset getCategoryDataset(ArrayList<model.Task> tasks) {  
		TaskSeries taskList = new TaskSeries("Task");  
		
		for(int i=0; i<tasks.size();i++) {
			Date finished = tasks.get(i).getStartDate();
			Calendar c = Calendar.getInstance(); 
			c.setTime(finished);
			c.add(Calendar.DATE, tasks.get(i).getDuration());
			finished = c.getTime();
			taskList.add(new Task(tasks.get(i).getTaskName(), tasks.get(i).getStartDate(),finished));
		}
	
	 TaskSeriesCollection dataset = new TaskSeriesCollection(); 
	 dataset.add(taskList);  
	 return dataset;  
	   }  
	
	}



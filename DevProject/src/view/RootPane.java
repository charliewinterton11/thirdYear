package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;


public class RootPane extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ActiveProjects ActiveProjects;
	private UserTasks UserTasks;
	private ChangeUserDetails ChangeUserDetails;
	private ManageProjects ManageProjects;
	private JTabbedPane tabbedPane;
	private ManageTasks ManageTasks;
	private ManageUsers ManageUsers;

	


	public RootPane() {
		setTitle("Task Funnel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane();
		contentPane.add(tabbedPane);
		
		ActiveProjects = new ActiveProjects();
		ActiveProjects.setLayout(null);
		UserTasks = new UserTasks();
		ChangeUserDetails = new ChangeUserDetails();
		ManageProjects = new ManageProjects();
		ManageTasks = new ManageTasks();
		ManageUsers = new ManageUsers();
		tabbedPane.addTab("Active Projects", null, ActiveProjects, null);
		tabbedPane.addTab("Your Tasks", null, UserTasks, null);
		tabbedPane.addTab("Account Management", null, ChangeUserDetails);
		tabbedPane.addTab("Manage Projects",null , ManageProjects);
		tabbedPane.addTab("Manage Tasks", ManageTasks);
		tabbedPane.addTab("Manage Users", ManageUsers);
	}
	
	public ActiveProjects getActiveProjects() {
		return ActiveProjects;
	}
	
	public UserTasks getUserTasks() {
		return UserTasks;
	}
	
	public ChangeUserDetails getChangeUserDetails() {
		return ChangeUserDetails;
	}
	
	public ManageProjects getManageProjects() {
		return ManageProjects;
	}
	public ManageUsers getManageUsers() {
		return ManageUsers;
	}
	
	public ManageTasks getManageTasks() {
		return ManageTasks;
	}
	
	public void setUserView() {//removes admin tabs
		tabbedPane.remove(3);
		tabbedPane.remove(3);
		tabbedPane.remove(3);
	}
	
	
}
	
		
		


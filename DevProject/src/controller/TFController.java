package controller;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Bcrypt.BCrypt;
import model.Project;
import model.Task;
import model.User;
import view.ActiveProjects;
import view.ChangeUserDetails;
import view.GanttChart;
import view.Login;
import view.ManageProjects;
import view.ManageTasks;
import view.ManageUsers;
import view.RootPane;
import view.UserTasks;


public class TFController {
	private User model;
	private Login login;
	private JDBC db;
	private RootPane view;
	private ActiveProjects ap;
	private UserTasks ut;
	private ChangeUserDetails cud;
	private ManageProjects mp;
	private ManageTasks mt;
	private ManageUsers mu;

	
	public TFController(Login login, User model, RootPane view) {
		this.model = model;
		this.login = login;
		this.view = view;
		
		ap = view.getActiveProjects();
		ut = view.getUserTasks();
		cud = view.getChangeUserDetails();
		mp = view.getManageProjects();
		mt = view.getManageTasks();
		mu = view.getManageUsers();
		db = new JDBC();
		db.connectDB();
		this.attachActionListener();
	}
	
	
	private void attachActionListener() {
		login.addLoginSubmitListener(new LoginSubmitListener());
		ap.addViewGanttChartListener(new GanttChartListener());
		ap.addListListener(new APListListener());
		ut.addListListener(new UTListListener());
		ut.addUpdateButtonListener(new UpdateButtonListener());
		cud.addUpdateNameListener(new UpdateNameListener());
		cud.addUpdatePasswordListener(new UpdatePasswordListener());
		mp.addMPListListener(new MPListListener());
		mp.addBtnAddProjectListener(new AddProjectListener());
		mp.addBtnModifyProjectListener(new ModifyProjectListener());
		mp.addBtnDeleteProjectListener(new DeleteProjectListener());
		mp.addBtnClearFieldsListener(new ClearMPFieldsListener());
		mt.addMTListListener(new MTListListener());
		mt.addBtnAddTaskListener(new AddTaskListener());
		mt.addBtnDeleteTaskListener(new DeleteTaskListener());
		mt.addBtnClearFieldsListener(new ClearMTFieldsListener());
		mu.addMUListListener(new MUListListener());
		mu.addBtnAddUserListener(new AddUserListener());
		mu.addBtnDeleteUserListener(new DeleteUserListener());
		mu.addBtnClearFieldsListener(new ClearMUFieldsListener());

	}
	
	private class LoginSubmitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String user = login.getUsername();
			String pass = login.getPassword();
			//gets hash from db and compares with user input
			boolean correctLogin = false;
			try {
			String dbHash = db.authenticateUser(user);
			boolean correctPass = BCrypt.checkpw(pass, dbHash);
			if(correctPass) {
				correctLogin = true;
			}else {
				correctLogin = false;
			}
			}catch(Exception e1){
				correctLogin = false;
				
			}
			
			if(correctLogin){
				List<Object> userSetup = new ArrayList<Object>();
				userSetup = db.getUser(user);
				model.setUsername(user);
				model.setUserID((int) userSetup.get(0));
				model.setDisplayName((String) userSetup.get(1));
				if(userSetup.get(2).equals(true)) {
					model.setAdmin(true);
					
				}else {
					model.setAdmin(false);
					view.setUserView();
				}
			//***populating user model***
				//setting tasks
			ArrayList<ArrayList<Object>> taskList = db.getUserTasks(model.getUserID());	
			ArrayList<Task> tasks = setTasks(taskList);
			tasks.forEach(x->model.addTask(x));
			
				//setting projects
			ArrayList<ArrayList<Object>> projectList = db.getUserProjects(model.getUserID());
			ArrayList<Project> projects = setProjects(projectList);
			projects.forEach(x -> model.addProject(x));
			ArrayList<Project> allProjects = setProjects(db.getAllProjects());
			//populating panes
			ap.populateList(allProjects);
			ut.populateList(tasks);
			mp.populateList(allProjects);
			ArrayList<Task> allTasks = setTasks(db.getAllTasks());
			mt.populateList(allTasks);
			ArrayList<User> allUsers = setUsers(db.getAllUsers());
			mt.populateUserBox(allUsers);
			mt.populateProjectBox(allProjects);
			cud.setDisplayName(model.getDisplayName());
			mu.populateList(allUsers);
			
			//closing the login window and opening the main window
			view.setVisible(true);
			login.dispose();
			//failed password attempt
			}else{
				login.clearPassword();
				JOptionPane.showMessageDialog(null,
					    "Incorrect username or password, please try again...",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//***Buttons and list selections***
	//ACTIVE PROJECTS PANE:
	private class GanttChartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object selected = ap.getListSelection();
			Project p = Project.class.cast(selected);
			ArrayList<Task> tasks = setTasks(db.getTasksForProject(p.getProjectID()));
			GanttChart gc = new GanttChart(p, tasks);
			gc.setVisible(true);
			
		}
	}
	
	private class APListListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
			Object selected = ap.getListSelection();
			Project p = Project.class.cast(selected);
			ArrayList<Task> tasks = setTasks(db.getTasksForProject(p.getProjectID()));
			ArrayList<ArrayList<Object>> projectMembers = new ArrayList<ArrayList<Object>>();
			for(int i = 0;i<tasks.size();i++) {
				ArrayList<Object> user = new ArrayList<Object>();
				int num = tasks.get(i).getUserId();
				user = (db.getUserFromID(num));
				projectMembers.add(user);
			}
			ArrayList<User> users = setUsers(projectMembers);
			ap.displayProject(p, tasks, users );
			}
		}
	}
	
	//YOUR TASKS PANE
	private class UTListListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				Object selected = ut.getListSelection();
				if(selected != null) {
				Task task = (Task) selected;
				ut.displayTask(task);
				}
			}
			}
	}
	
	private class UpdateButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<JRadioButton> buttons = ut.getRadioBtnSelection();
			Task selected = (Task) ut.getListSelection();
			
			if (buttons.get(0).isSelected()) {
				db.updateStatus(selected.getTaskId(), "Not started");
			} else if (buttons.get(1).isSelected()) {
				db.updateStatus(selected.getTaskId(), "In progress");
			} else if (buttons.get(2).isSelected()) {
				db.updateStatus(selected.getTaskId(), "Finished");
			}
			ArrayList<ArrayList<Object>> taskList = db.getUserTasks(model.getUserID());	
			ArrayList<Task> tasks = setTasks(taskList);
			ut.clearList();
			ut.populateList(tasks);
			model.clearAllTasks();
			tasks.forEach(x->model.addTask(x));
			JOptionPane.showMessageDialog(null,
				    "Task Status Updated Successfully",
				    "Success",
				    JOptionPane.DEFAULT_OPTION);
		}
		}
	//ACCOUNT MANAGEMENT PANE
	private class UpdateNameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = cud.getDisplayName();
			if(name.length() > 0) {
			db.updateName(model.getUserID(), name);
			model.setDisplayName(name);
			JOptionPane.showMessageDialog(null,
				    "Display Name Updated",
				    "Success",
				    JOptionPane.DEFAULT_OPTION);
			}else {
				JOptionPane.showMessageDialog(null,
					    "Please Enter a Name",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		}
	
	private class UpdatePasswordListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String oldPass = cud.getCurrentPass();
			String newPass = cud.getNewPass();
			if(oldPass.length() > 0 && newPass.length() > 0) {
				String dbHash = db.authenticateUser(model.getUsername());
				if(BCrypt.checkpw(oldPass, dbHash)){
					String hashed = BCrypt.hashpw(newPass, BCrypt.gensalt());
					db.updatePassword(model.getUserID(), hashed);
					JOptionPane.showMessageDialog(null,
						    "Password Updated",
						    "Success",
						    JOptionPane.DEFAULT_OPTION);
				}else{JOptionPane.showMessageDialog(null,
					    "Inccorect Password",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);}
			}else{JOptionPane.showMessageDialog(null,
				    "Please fill out both fields",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
		}
	}
	//MANAGE PROJECTS PANE(ADMIN)
	private class MPListListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				Object selected = mp.getListSelection();
				Project p = Project.class.cast(selected);
				if(p!=null) {
				mp.populateProjectFields(p);
				}
			}
		}
		}
	private class AddProjectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<Object> fields = mp.getFields();
			String pName = fields.get(0).toString();
			String pDesc = fields.get(1).toString();
			Date pDate = (Date) fields.get(2);
			try{
				int pDuration = (int) fields.get(3);
				if(pName.length()>1&&pDesc.length()>1) {
					if(pDate != null && pDuration >0) {
						java.sql.Date sqlDate = new java.sql.Date(pDate.getTime());
						db.insertProject(pName,pDesc, sqlDate, pDuration);
						ArrayList<Project> projects = setProjects(db.getAllProjects());
						mp.populateList(projects);
						JOptionPane.showMessageDialog(null,
							    "Project Added",
							    "Success",
							    JOptionPane.DEFAULT_OPTION);
						
					}else {JOptionPane.showMessageDialog(null,
						    "Missing date and/or duration",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
					}
					
				}else {JOptionPane.showMessageDialog(null,
					    "Please enter a project name and description",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
				}
			}catch(Exception e1){
				System.out.println(e1);
				JOptionPane.showMessageDialog(null,
					    "Please fill out the data correctly",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		}
	private class ModifyProjectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object p = mp.getListSelection();
			Project project = (Project) p;
			ArrayList<Object> fields = mp.getFields();
			String pName = fields.get(0).toString();
			String pDesc = fields.get(1).toString();
			Date pDate = (Date) fields.get(2);
			try{
				int pDuration = (int) fields.get(3);
				if(pName.length()>1&&pDesc.length()>1) {
					if(pDate != null && pDuration >0) {
						java.sql.Date sqlDate = new java.sql.Date(pDate.getTime());
						db.updateProject(pName,pDesc, sqlDate, pDuration,project.getProjectID());
						ArrayList<Project> projects = setProjects(db.getAllProjects());
						mp.populateList(projects);
						JOptionPane.showMessageDialog(null,
							    "Project Modified",
							    "Success",
							    JOptionPane.DEFAULT_OPTION);
						
					}else {JOptionPane.showMessageDialog(null,
						    "Missing date and/or duration",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
					}
					
				}else {JOptionPane.showMessageDialog(null,
					    "Please enter a project name and description",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
				}
			}catch(Exception e1){
				System.out.println("SAD");
				System.out.println(e1);
				JOptionPane.showMessageDialog(null,
					    "Please fill out the data correctly",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
		
		}
		}
	private class DeleteProjectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object p = mp.getListSelection();
			Project project = (Project) p;
			int id = project.getProjectID();
			db.deleteProject(id);
			ArrayList<Project> projects = setProjects(db.getAllProjects());
			mp.populateList(projects);
			JOptionPane.showMessageDialog(null,
				    "Project and tasks under that project deleted",
				    "Success",
				    JOptionPane.DEFAULT_OPTION);
		}
		}
	private class ClearMPFieldsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mp.clearFields();
		}
		}
	
	//**MANAGE TASKS PANE(ADMIN)**
	
	private class MTListListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				
				}
			}
		}
		
	private class AddTaskListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<Object> fields = mt.getFields();
			if (!fields.contains(null)) {
				String taskName = (String) fields.get(0);
				String taskDesc = (String) fields.get(1);
				try{
					Date tDate = (Date) fields.get(2);
					User user = (User) fields.get(3);
					Project project = (Project) fields.get(4);
					int tDuration = (int) fields.get(5);
					java.sql.Date sqlDate = new java.sql.Date(tDate.getTime());
					db.insertTask(user.getUserID(), taskName, taskDesc, "Not started", sqlDate, tDuration, project.getProjectID());
					ArrayList<Task> tasks = setTasks(db.getAllTasks());
					mt.populateList(tasks);
					JOptionPane.showMessageDialog(null,
						    "Task added",
						    "Success",
						    JOptionPane.DEFAULT_OPTION);
					
			
		}catch(Exception e2){
			JOptionPane.showMessageDialog(null,
				    "Please fill out the form correctly",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			
		}
		}else {
			JOptionPane.showMessageDialog(null,
				    "Please fill out the form correctly",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
		}
	}
	
	private class DeleteTaskListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
			Object t = mt.getListSelection();
			Task task = (Task) t;
			task.getTaskId();
			db.deleteTask(task.getTaskId());
			ArrayList<Task> tasks = setTasks(db.getAllTasks());
			mt.populateList(tasks);
			JOptionPane.showMessageDialog(null,
				    "Task Deleted",
				    "Success",
				    JOptionPane.DEFAULT_OPTION);
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null,
					    "Task not deleted",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
				
			}
			
		}
		}
	private class ClearMTFieldsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mt.clearFields();
		}
		}
	
	//**MANAGE USERS PANE(ADMIN)**
	
	private class MUListListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				}
			}
		}
		
	private class AddUserListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<Object> fields = mu.getFields();
			try {
			if (!fields.contains(null)||!fields.contains("")) {
				String username = (String) fields.get(0);
				String pass = (String) fields.get(1);
				String displayName = (String) fields.get(2);
				if(username.length()>0&&pass.length()>0&&displayName.length()>0) {
				boolean admin = (boolean) fields.get(3);
				String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
				db.addUser(username,hashed,displayName,admin);
				ArrayList<User> allUsers = setUsers(db.getAllUsers());
				mu.populateList(allUsers);
				JOptionPane.showMessageDialog(null,
					    "User added",
					    "Success",
					    JOptionPane.DEFAULT_OPTION);
				}else {
					JOptionPane.showMessageDialog(null,
						    "Please enter data",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
					
				}
			}
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null,
					    "User not added",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
			
		}
		}
	
	private class DeleteUserListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object u = mu.getListSelection();
			User user = (User) u;
			db.deleteUser(user.getUserID());
			ArrayList<User> allUsers = setUsers(db.getAllUsers());
			mu.populateList(allUsers);
		}
		}
	private class ClearMUFieldsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mu.clearFields();
		}
		}


	
	//***these methods convert data pulled from DB to model objects***
	public ArrayList<Task> setTasks(ArrayList<ArrayList<Object>> taskList)  {
		ArrayList<Task> tasks = new ArrayList<Task>();
		for(int i = 0; i < taskList.size();i++) {
			Task t = new Task();
			ArrayList<Object> dbTask = taskList.get(i);
			t.setTaskId((int) dbTask.get(0));
			t.setTaskName((String) dbTask.get(1));
			t.setTaskDesc((String) dbTask.get(2));
			t.setTaskStatusString((String) dbTask.get(3));
			t.setStartDate((Date) dbTask.get(4));
			t.setDuration((int) dbTask.get(5));
			t.setUserId((int) dbTask.get(6));
			tasks.add(t);
	}
		return tasks;
}
	
	public ArrayList<Project> setProjects(ArrayList<ArrayList<Object>> projectList) {
		ArrayList<Project> projects = new ArrayList<Project>();
		for(int i = 0; i < projectList.size();i++) {
			Project p = new Project();
			ArrayList<Object> dbProject = projectList.get(i);
			p.setProjectID((int) dbProject.get(0));
			p.setProjectName((String) dbProject.get(1));
			p.setProjectDesc((String) dbProject.get(2));
			p.setProjectStartDate((Date) dbProject.get(3));
			p.setProjectDuration((int) dbProject.get(4));
			projects.add(p);
		}
		return projects;
	}


	public ArrayList<User> setUsers(ArrayList<ArrayList<Object>> userList){
		ArrayList<ArrayList<Object>> userListND = new ArrayList<>(new HashSet<>(userList));
		ArrayList<User> users = new ArrayList<User>();
		for(int i = 0; i < userListND.size();i++) {
			User u = new User();
			ArrayList<Object> dbUser = userListND.get(i);
			u.setUserID((int) dbUser.get(0));
			u.setUsername((String) dbUser.get(1));
			u.setDisplayName((String) dbUser.get(2));
			u.setAdmin((boolean) dbUser.get(3));
			users.add(u);
		}
		return users;
	}
	
}

		
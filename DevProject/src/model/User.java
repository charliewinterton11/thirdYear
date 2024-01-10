package model;

import java.util.ArrayList;

public class User {
	
	private String username;
	private String displayName;
	private int userID;
	private boolean admin;
	private ArrayList<Task> tasks;
	private ArrayList<Project> projects;
	

	public User() {
		userID = 0;
		username = "";
		displayName = "";
		admin = false;
		tasks = new ArrayList<Task>();
		projects = new ArrayList<Project>();
	}
	
	
	
	public int getUserID() {
		return userID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public boolean getAdmin() {
		return admin;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public boolean addTask(Task t) {
		return tasks.add(t);
	}
	
	public boolean addProject(Project p) {
		return projects.add(p);
	}
	
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	public String getUserString() {
		 Integer iD = new Integer(userID);
		 return iD.toString();
	}
	
	public void clearAllTasks() {
		tasks.clear();;
	}

	@Override
    public String toString() {
        return displayName;
    }

	
}



package model;

import java.util.Date;

public class Project implements Comparable<Project> {
	private int projectID;
	private String projectName;
	private String projectDesc;
	private Date projectStartDate;
	private int projectDuration;

	public Project() {
		projectID = 0;
		projectName = "";
		projectDesc = "";
		projectStartDate = null;
		projectDuration = 0;
		
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public Date getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public int getProjectDuration() {
		return projectDuration;
	}

	public void setProjectDuration(int projectDuration) {
		this.projectDuration = projectDuration;
	}

	@Override
	public int compareTo(Project p) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
    public String toString() {
        return projectName;
    }
	
}

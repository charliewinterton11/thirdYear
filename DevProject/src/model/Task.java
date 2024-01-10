package model;

import java.util.Date;

public class Task implements Comparable {
	
	
	private int taskId;
	private int userId;
	private String taskName;
	private String taskDesc;
	private Status taskStatus;
	private Date startDate;
	private int duration;
	
	

	public Task() {
		taskId = 0;
		userId = 0;
		taskName = "";
		taskDesc = "";
		taskStatus = null;
		startDate = null;
		duration = 0;
		
		
	}
	
	
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public Status getTaskStatus() {
		return taskStatus;
	}
	
	public String getTaskStatusString() {
		String status = taskStatus.toString();
		return status;
	}

	public void setTaskStatus(Status taskStatus) {
		this.taskStatus = taskStatus;
	}
	public void setTaskStatusString(String taskStatus) {
		if(taskStatus.toLowerCase().equals("not started")) {
			this.taskStatus = Status.Not_Started;
		} else if (taskStatus.toLowerCase().equals("in progress")) {
			this.taskStatus = Status.In_Progress;
		} else if (taskStatus.toLowerCase().equals("finished")) {
			this.taskStatus = Status.Finished;
		}else {
		System.out.println("error");}
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date date) {
		this.startDate = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}


	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	public String toString() {
		return taskName;
		
	}

}

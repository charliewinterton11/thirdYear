package controller;


import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;


public class JDBC {
	private Connection conn;
	
	public void connectDB() {
		conn = null;
	try {
		conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/tfdb","root","password");
		}catch(Exception e) {
			e.printStackTrace();;
		}
	}
		
	

	  public String authenticateUser(String user) {
	        String select;
	        ResultSet res;
	 
	        select = "SELECT password FROM user WHERE username = ?;";
	        res = null;
	 
	        try(PreparedStatement pstmt = this.conn.prepareStatement(select)) {
	            pstmt.setString(1, user);
	            res = pstmt.executeQuery();
	            res.next(); 
	            String pass = res.getString(1);
	            return pass;
	 
	        } catch( SQLException ex) {
	            return "";
	            
	        } finally {
	            try {
	                if (res instanceof ResultSet && !res.isClosed()) {
	                    res.close();
	                }
	            } catch(SQLException ex) {
	            }
	       
	        }
	    }
	  
	  public ArrayList<Object> getUser(String username) {
		  String select;
	      ResultSet res;
	      ArrayList<Object> user = new ArrayList<>();
	 
	      select = "SELECT userID, displayName, admin FROM user WHERE username = ?;";
	      res = null;
	 
	      try(PreparedStatement pstmt = this.conn.prepareStatement(select)) {
	    	    pstmt.setString(1, username);
	            res = pstmt.executeQuery();
	 
	            res.next(); 
	            int id = res.getInt(1);
	            String displayName = res.getString(2);
	            boolean admin = res.getBoolean(3);
	            user.add(id);
	            user.add(displayName);
	            user.add(admin);
	           return user;
	 
	        } catch( SQLException ex) {
	            return user;
	        } finally {
	            try {
	                if (res instanceof ResultSet && !res.isClosed()) {
	                    res.close();
	                }
	            } catch(SQLException ex) {
	            }
	        }
	    }
		  
	  
	  
	  public ArrayList<ArrayList<Object>> getUserTasks(int userId) {
		  String select;
	      ResultSet res;
	      ArrayList<ArrayList<Object>> records = new ArrayList<ArrayList<Object>>();
	      select = "SELECT taskId, taskName, taskDesc, taskStatus, taskStartDate, taskDuration, userID FROM task WHERE userId = ?;";
	      res = null;
	 
	      try(PreparedStatement pstmt = this.conn.prepareStatement(select)) {
	    	    pstmt.setInt(1, userId);
	            res = pstmt.executeQuery();
	            while(res.next()) {
	            ArrayList<Object> row = new ArrayList<Object>();
	            int id = res.getInt(1);
	            String taskName = res.getString(2);
	            String taskDesc = res.getString(3);
	            String taskStatus = res.getString(4);
	            Date taskStartDate = res.getDate(5);
	            int taskDuration = res.getInt(6);
	            int userID = res.getInt(7);
	            row.add(id);
	            row.add(taskName);
	            row.add(taskDesc);
	            row.add(taskStatus);
	            row.add(taskStartDate);
	            row.add(taskDuration);
	            row.add(userID);
	            records.add(row);
	            }
	           return records;
	 
	        } catch( SQLException ex) {
	            return records;
	        } finally {
	            try {
	                if (res instanceof ResultSet && !res.isClosed()) {
	                    res.close();
	                }
	            } catch(SQLException ex) {
	            }
	        }
	    }
	  
	  
	  
	  public ArrayList<ArrayList<Object>> getUserProjects(int id) {
		  String select;
	      ResultSet res;
	      ArrayList<ArrayList<Object>> project = new ArrayList<ArrayList<Object>>();
	 
	      select = "SELECT DISTINCT * FROM project, task, tasklist, user WHERE project.projectID = tasklist.projectID AND tasklist.taskID = task.taskID AND task.userID = user.userID AND user.userID = ?;";
	      res = null;
	 
	      try(PreparedStatement pstmt = this.conn.prepareStatement(select)) {
	    	    pstmt.setInt(1, id);
	            res = pstmt.executeQuery();
	 
	            while(res.next()) { 
	            ArrayList<Object> row = new ArrayList<Object>();
	            int projectID = res.getInt(1);
	            String projectName = res.getString(2);
	            String projectDesc = res.getString(3);
	            Date projectStartDate = res.getDate(4);
	            int projectDuration = res.getInt(5);
	            row.add(projectID);
	            row.add(projectName);
	            row.add(projectDesc);
	            row.add(projectStartDate);
	            row.add(projectDuration);
	            project.add(row);
	            }
	           return project;
	 
	        } catch( SQLException ex) {
	            return project;
	        } finally {
	            try {
	                if (res instanceof ResultSet && !res.isClosed()) {
	                    res.close();
	                }
	            } catch(SQLException ex) {
	            }
	        }
	    }
	  
	  public ArrayList<ArrayList<Object>> getAllTasks() {
		  String select;
	      ResultSet res;
	      ArrayList<ArrayList<Object>> task = new ArrayList<ArrayList<Object>>();
	 
	      select = "SELECT taskId, taskName, taskDesc, taskStatus, taskStartDate, taskDuration, userID FROM task";
	      res = null;
	 
	      try(PreparedStatement pstmt = this.conn.prepareStatement(select)) {
	            res = pstmt.executeQuery();
	 
	            while(res.next()) { 
	            ArrayList<Object> row = new ArrayList<Object>();
	            int taskID = res.getInt(1);
	            String taskName = res.getString(2);
	            String taskDesc = res.getString(3);
	            String taskStatus = res.getString(4);
	            Date taskStartDate = res.getDate(5);
	            int taskDuration = res.getInt(6);
	            int userID = res.getInt(7);
	            row.add(taskID);
	            row.add(taskName);
	            row.add(taskDesc);
	            row.add(taskStatus);
	            row.add(taskStartDate);
	            row.add(taskDuration);
	            row.add(userID);
	            task.add(row);
	            }
	           return task;
	 
	        } catch( SQLException ex) {
	            return task;
	        } finally {
	            try {
	                if (res instanceof ResultSet && !res.isClosed()) {
	                    res.close();
	                }
	            } catch(SQLException ex) {
	            }
	        }
	    }
	  
	  
	  
	  public ArrayList<ArrayList<Object>> getAllProjects() {
		  String select;
	      ResultSet res;
	      ArrayList<ArrayList<Object>> project = new ArrayList<ArrayList<Object>>();
	 
	      select = "SELECT * FROM project;";
	      res = null;
	 
	      try(PreparedStatement pstmt = this.conn.prepareStatement(select)) {
	            res = pstmt.executeQuery();
	 
	            while(res.next()) { 
	            ArrayList<Object> row = new ArrayList<Object>();
	            int projectID = res.getInt(1);
	            String projectName = res.getString(2);
	            String projectDesc = res.getString(3);
	            Date projectStartDate = res.getDate(4);
	            int projectDuration = res.getInt(5);
	            row.add(projectID);
	            row.add(projectName);
	            row.add(projectDesc);
	            row.add(projectStartDate);
	            row.add(projectDuration);
	            project.add(row);
	            }
	           return project;
	 
	        } catch( SQLException ex) {
	            return project;
	        } finally {
	            try {
	                if (res instanceof ResultSet && !res.isClosed()) {
	                    res.close();
	                }
	            } catch(SQLException ex) {
	            }
	        }
	    }
	  
	  public ArrayList<ArrayList<Object>> getAllUsers() {
		  String select;
	      ResultSet res;
	      ArrayList<ArrayList<Object>> user = new ArrayList<ArrayList<Object>>();
	 
	      select = "SELECT userID, username, displayName, admin FROM user;";
	      res = null;
	 
	      try(PreparedStatement pstmt = this.conn.prepareStatement(select)) {
	            res = pstmt.executeQuery();
	            while(res.next()) { 
	            ArrayList<Object> row = new ArrayList<Object>();
	            int id = res.getInt(1);
	            String username = res.getString(2);
	            String displayName=res.getString(3);
	            boolean admin = res.getBoolean(4);
	            row.add(id);
	            row.add(username);
	            row.add(displayName);
	            row.add(admin);
	            user.add(row);
	            }
	           return user;
	 
	        } catch( SQLException ex) {
	        	System.out.println(ex);
	            return user;
	        } finally {
	            try {
	                if (res instanceof ResultSet && !res.isClosed()) {
	                    res.close();
	                }
	            } catch(SQLException ex) {
	            }
	        }
	    }




	public ArrayList<ArrayList<Object>> getTasksForProject(int projectID) {
		  String select;
	      ResultSet res;
	      ArrayList<ArrayList<Object>> tasks = new ArrayList<ArrayList<Object>>();
	 
	      select = "SELECT taskId, taskName, taskDesc, taskStatus, taskStartDate, taskDuration, userID FROM task WHERE task.taskID IN (SELECT taskID FROM tasklist WHERE projectID = ?) ORDER BY taskStartDate;";
	      res = null;
	 
	      try(PreparedStatement pstmt = this.conn.prepareStatement(select)) {
	    	    pstmt.setInt(1, projectID);
	            res = pstmt.executeQuery();
	 
	            while(res.next()) { 
	            ArrayList<Object> row = new ArrayList<Object>();
	            int taskID = res.getInt(1);
	            String taskName = res.getString(2);
	            String taskDesc = res.getString(3);
	            String taskStatus = res.getString(4);
	            Date taskStartDate = res.getDate(5);
	            int taskDuration = res.getInt(6);
	            int userID = res.getInt(7);
	            row.add(taskID);
	            row.add(taskName);
	            row.add(taskDesc);
	            row.add(taskStatus);
	            row.add(taskStartDate);
	            row.add(taskDuration);
	            row.add(userID);
	            tasks.add(row);
	            }
	           return tasks;
	 
	        } catch( SQLException ex) {
	        	System.out.println(ex);
	        	return tasks;
	            
	        } finally {
	            try {
	                if (res instanceof ResultSet && !res.isClosed()) {
	                    res.close();
	                }
	            } catch(SQLException ex) {
	            }
	        }
	    }
	
	
	 public ArrayList<Object> getUserFromID(int taskId) {
		  String select;
	      ResultSet res;
	      select = "SELECT userId, username, displayName, admin FROM user WHERE userId = ?;";
	      res = null;
	      ArrayList<Object> row = new ArrayList<Object>();
	 
	      try(PreparedStatement pstmt = this.conn.prepareStatement(select)) {
	    	  
	    	    pstmt.setInt(1, taskId);
	            res = pstmt.executeQuery();
	            res.next();
	            int userID = res.getInt(1);
	            String userName = res.getString(2);
	            
	            String displayName = res.getString(3);
	            boolean admin = res.getBoolean(4);
	            
	            row.add(userID);
	            row.add(userName);
	            row.add(displayName);
	            row.add(admin);
	            return row;
	            
	 
	        } catch( SQLException ex) {
	            return row;
	        } finally {
	            try {
	                if (res instanceof ResultSet && !res.isClosed()) {
	                    res.close();
	                }
	            } catch(SQLException ex) {
	            }
	        }
	    }



	public void updateStatus(int id, String status) {
		String update = "UPDATE task SET taskStatus = ? WHERE taskID = ?;";
		    try (PreparedStatement pstmt = this.conn.prepareStatement(update)) {
		    pstmt.setString(1, status);
		    pstmt.setInt(2, id);
		    pstmt.executeUpdate();
		    } catch (SQLException e) {
		    	System.out.println(e);
		    }
		  }
	public void updateName(int id, String name) {
		String update = "UPDATE user SET displayName = ? WHERE userID = ?;";
		    try (PreparedStatement pstmt = this.conn.prepareStatement(update)) {
		    pstmt.setString(1, name);
		    pstmt.setInt(2, id);
		    pstmt.executeUpdate();
		    } catch (SQLException e) {
		    	System.out.println(e);
		    }
		  }
	

	public void updatePassword(int id, String pass) {
		String update = "UPDATE user SET password = ? WHERE userID = ?;";
	    	try (PreparedStatement pstmt = this.conn.prepareStatement(update)) {
	    		pstmt.setString(1, pass);
	    		pstmt.setInt(2, id);
	    		pstmt.executeUpdate();
	    	} catch (SQLException e) {
	    		System.out.println(e);
	    	}
		}
	
	public void insertProject(String name, String desc, Date startDate, int duration) {
		String update = "INSERT into project (projectName, projectDesc, projectStartDate, projectDuration) VALUES (?, ?, ?, ?);";
    	try (PreparedStatement pstmt = this.conn.prepareStatement(update)) {
    		pstmt.setString(1, name);
    		pstmt.setString(2, desc);
    		pstmt.setDate(3, startDate);
    		pstmt.setInt(4, duration);
    		pstmt.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println(e);
    	}
	}
	
	public void updateProject(String name, String desc, Date startDate, int duration, int id) {
		String update = "UPDATE project SET projectName = ?, projectDesc= ?, projectStartDate= ?, projectDuration= ? WHERE projectID = ?;";
    	try (PreparedStatement pstmt = this.conn.prepareStatement(update)) {
    		pstmt.setString(1, name);
    		pstmt.setString(2, desc);
    		pstmt.setDate(3, startDate);
    		pstmt.setInt(4, duration);
    		pstmt.setInt(5, id);
    		pstmt.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println(e);
    	}
	}
	
	public void deleteProject (int id) {
		String deleteTasks = "DELETE FROM task WHERE EXISTS (SELECT * from tasklist where tasklist.taskID = task.taskID AND tasklist.projectID = ?);";
	    	try (PreparedStatement pstmt = this.conn.prepareStatement(deleteTasks)) {
	    		pstmt.setInt(1, id);
	    		pstmt.executeUpdate();
	    	} catch (SQLException e) {
	    		System.out.println(e);
	    	}
	    String deleteProject = "DELETE FROM project WHERE projectID =?";
	    	try (PreparedStatement pstmt2 = this.conn.prepareStatement(deleteProject)) {
	    		pstmt2.setInt(1, id);
	    		pstmt2.executeUpdate();
	    	} catch (SQLException e) {
	    		System.out.println(e);
	    	}
		
		}
	
	public void insertTask( int userID, String taskName, String taskDesc, String taskStatus, Date startDate, int duration, int projectID) {
		int genTaskID = 0;
		String update = "INSERT into task (userID, taskName, taskDesc, taskStatus, taskStartDate, taskDuration) VALUES (?, ?, ?, ?, ?, ?);";
    	try (PreparedStatement pstmt = this.conn.prepareStatement(update)) {
    		pstmt.setInt(1, userID);
    		pstmt.setString(2, taskName);
    		pstmt.setString(3, taskDesc);
    		pstmt.setString(4, taskStatus);
    		pstmt.setDate(5, startDate);
    		pstmt.setInt(6, duration);
    		pstmt.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	String update2 = "SELECT taskID FROM task ORDER BY taskID DESC LIMIT 1;";
    	ResultSet res;
    	res = null;
    	try (PreparedStatement pstmt2 = this.conn.prepareStatement(update2)) {
    		res = pstmt2.executeQuery();
            res.next(); 
            genTaskID = res.getInt(1);
    	} catch (SQLException e2) {
    		e2.printStackTrace();
    	}
    	String update3 = "INSERT into tasklist (projectID, taskID) VALUES (?, ?);";
    	try (PreparedStatement pstmt3 = this.conn.prepareStatement(update3)) {
    		pstmt3.setInt(1, projectID);
    		pstmt3.setInt(2, genTaskID);
    		pstmt3.executeUpdate();
    		
    	} catch (SQLException e3) {
    		e3.printStackTrace();
    	}
	}



	public void deleteTask(int taskId) {
		String deleteTask= "DELETE FROM task WHERE taskID =?";
    	try (PreparedStatement pstmt2 = this.conn.prepareStatement(deleteTask)) {
    		pstmt2.setInt(1, taskId);
    		pstmt2.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println(e);
    	}
		
	}



	public void addUser(String username, String pass, String displayName, boolean admin) {
		String update = "INSERT into user (username, password, displayName,admin ) VALUES (?, ?, ?, ?);";
    	try (PreparedStatement pstmt = this.conn.prepareStatement(update)) {
    		pstmt.setString(1, username);
    		pstmt.setString(2, pass);
    		pstmt.setString(3, displayName);
    		pstmt.setBoolean(4, admin);
    		pstmt.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
		
	}



	public void deleteUser(int userID) {
		String deleteUser= "DELETE FROM user WHERE userID =?";
    	try (PreparedStatement pstmt2 = this.conn.prepareStatement(deleteUser)) {
    		pstmt2.setInt(1, userID);
    		pstmt2.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println(e);
    	}
		
	}
		
	
	
}
	
	


	
	
	
	  
	
	  
	
	
	
	
	
  





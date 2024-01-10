package main;

import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;

import controller.TFController;
import model.User;
import view.Login;
import view.RootPane;

public class ApplicationLoader implements Runnable {
	private Login login;
	
	public void run() {
		FlatLightLaf.install();
		login = new Login();
		User model = new User();
		RootPane view = new RootPane();
		new TFController(login,model,view);
		login.setVisible(true);
		
		
	}
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new ApplicationLoader());
		
		
			
		}

	}



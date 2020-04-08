package testpack;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class LoginFrame extends JFrame {
	
	
	private LoginPanel loginPanel;
	private NewUserPanel newUserPanel;
	private SaveFile sf;
	private ArrayList<Users> userData = new ArrayList<Users>();
	LoginFrame() {
		super("Login");
		loginPanel = new LoginPanel();
		newUserPanel = new NewUserPanel();
		sf = new SaveFile();
		userData = sf.load();


		setSize(200, 200);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		addLoginFrame();


	}
	public ArrayList<Users> getUserData() {
		return userData;
	}
	private void wipeFrame(int x) {
		if (x == 1) {
			getContentPane().remove(loginPanel);
			repaint();
		}
		else if (x == 2) {
			getContentPane().remove(newUserPanel);
			repaint();
		}
		
	}
	private void addNewUserFrame() {
		add(newUserPanel);
		newUserPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormClicks e) {
				String userName = e.getUserName();
				String userPassword = e.getUserPassword();
				String confirm = e.getConfirm();
				
				newUserPanel.setInformationField("Fields cant be blanck");
				if (userName.isEmpty() || userPassword.isEmpty() || confirm.isEmpty()) {
					newUserPanel.setInformationField("Fields cant be blanck");
				}
				else if (!(userPassword.equals(confirm))) {
					newUserPanel.setInformationField("Password does not match!");
				}
				else if (userExist(userName) == true) {
					newUserPanel.setInformationField("Username is taken.");
				}
				else if (userName.length() <= 4 || userName.length() >= 13) {
					newUserPanel.setInformationField("Username must be between\n5 - 12 letters");
				}
				else if (userPassword.length() <= 4 || userPassword.length() >= 13 || !(userPassword.matches(".*\\d.*"))) {
					newUserPanel.setInformationField("Password must be between\n5 - 12 letters and have atleast\n1 numeric symbol");
				}
				else if (userPassword.equalsIgnoreCase(userName)) {
					newUserPanel.setInformationField("Username and password\ncant be the same!");
				}
				else {
					userData.add(new Users(userName, userPassword));
					newUserPanel.setInformationField("Account created!");
					sf.save(userData);
					
				}
			}
			
		});
	}
	private void addLoginFrame() {
		add(loginPanel);
		loginPanel.setFormListener(new FormListener() {

			public void formEventOccurred(FormClicks e) {
				int i = e.getButton();
				if (i == 1) {
					String userName = e.getUserName();
					String userPassword = e.getUserPassword();
					if (userName.isEmpty() || userPassword.isEmpty()) {
						loginPanel.setInformationField("Fields cant be blanck");
					}
					else {
						if	(userAndPassExist(userName, userPassword) == true) {
								loginPanel.setInformationField("Successfully logged in!");
								dispose();
						}
						else {
							loginPanel.setInformationField("Username and password \ndoes not match");
						}
							
					}
				}
				else if (i == 2) {
					wipeFrame(1);
					addNewUserFrame();
					validate();
				}

				
			}
		});
	}
	private boolean userExist(String name) {
		
		for (int i = 0; i < userData.size(); i++) {
			if (name.equals(userData.get(i).getUserName())) 
				return true;
		}
		return false;
	}
	private boolean userAndPassExist(String name, String password) {
		if (userData.isEmpty()) {
			return false;
		}
		for (int i = 0; i < userData.size(); i++) {
			if (name.equals(userData.get(i).getUserName())) {
				if (password.equals(userData.get(i).getUserPassword())) {
					userData.get(i).setOnline(true);
					return true;
				}
			}	
		}
		return false;
	}
}

	class LoginPanel extends JPanel{
		
		private JLabel userNameLabel;
		private JTextField userNameField;
		private JLabel userPasswordLabel;
		private JPasswordField userPasswordField;
		private JButton loginButton;
		private JButton newUserButton;
		private JTextArea informationField;
		private FormListener formListener;
		
		LoginPanel() {
			Dimension dim = getPreferredSize();
			dim.width = 250;
			setPreferredSize(dim);
			informationField = new JTextArea();
			userNameLabel = new JLabel("Username: ");
			userNameField = new JTextField(10);
			userPasswordLabel = new JLabel("Password: ");
			userPasswordField = new JPasswordField(10);
			loginButton = new JButton("Login");
			loginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String userName = userNameField.getText();
					String userPassword = userPasswordField.getText();
					
					FormClicks fc = new FormClicks(this, userName, userPassword, 1);
					if (formListener != null) {
						formListener.formEventOccurred(fc);
					}
				}
			});
			// Checks for user activity on the loginButton.
			newUserButton = new JButton("new user");
			newUserButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						FormClicks fc = new FormClicks(this, 2);
						
						if (formListener != null) {
							formListener.formEventOccurred(fc);
						}	
				}
				
			});
			// checks for user activity on the newUserButton.
			userNameLabel.setBorder(BorderFactory.createEmptyBorder());
			userPasswordLabel.setBorder(BorderFactory.createEmptyBorder());
			informationField.setBorder(BorderFactory.createEtchedBorder());
			informationField.setEditable(false);
			
			Border innerBorder = BorderFactory.createTitledBorder("");
			Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
			setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
			
			setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			
			//////////// FIRST ROW //////////////
			gc.weightx = 1;
			gc.weighty = 0.1;
			
			gc.gridx = 0;
			gc.gridy = 0;
			
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.LINE_START;
			gc.insets = new Insets(0, 0, 0, 0);
			add(userNameLabel, gc);
			// new item on the row		
			gc.weightx = 2.0;
			
			gc.gridx = 1;
			gc.gridy = 0;
			
			gc.fill = GridBagConstraints.BOTH;
			gc.anchor = GridBagConstraints.LINE_END;
			gc.insets = new Insets(0, 0, 0, 0);
			add(userNameField, gc);
			
			//////////// SECOND ROW //////////////
			gc.weightx = 1;
			gc.weighty = 0.1;
			
			gc.gridx = 0;
			gc.gridy = 1;
			
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.LINE_START;
			gc.insets = new Insets(5, 0, 0, 0);
			add(userPasswordLabel, gc);
			// new item on the row			
			gc.gridx = 1;
			gc.gridy = 1;
			
			gc.fill = GridBagConstraints.BOTH;
			gc.anchor = GridBagConstraints.LINE_END;
			gc.insets = new Insets(5, 0, 0, 0);
			add(userPasswordField, gc);
			
			////////////THIRD ROW //////////////
			
			gc.weightx = 1;
			gc.weighty = 0.1;
			
			gc.gridx = 0;
			gc.gridy = 2;
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.FIRST_LINE_END;
			gc.insets = new Insets(0, 0, 0, 0);
			add(loginButton, gc); 
			// new item on the row
			
			gc.gridx = 1;
			gc.gridy = 2;
			
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.FIRST_LINE_START;
			add(newUserButton, gc);
			////////// FORTH ROW ///////////
			gc.weightx = 1;
			gc.weighty = 1.0;
			
			gc.gridx = 0;
			gc.gridy = 3;
			gc.gridwidth = 3;
			
			gc.fill = GridBagConstraints.HORIZONTAL;
			gc.anchor = GridBagConstraints.FIRST_LINE_START;
			add(informationField, gc);
		}
		public void setFormListener(FormListener listener) {
			this.formListener = listener;
		}
		public void setInformationField(String text) {
			informationField.setText(text);
		}
}
	class NewUserPanel extends JPanel{
		
		private JTextField userName;
		private JLabel userNameLabel;
		private JTextField userPassword;
		private JLabel userPasswordLabel;
		private JTextField userRepeatPassword;
		private JLabel userRepeatPasswordLabel;
		private JTextArea informationField;
		private JButton createUser;
		private FormListener formListener;
		
		NewUserPanel() {
			Dimension dim = getPreferredSize();
			dim.width = 250;
			setPreferredSize(dim);
			informationField = new JTextArea();
			informationField.setBorder(BorderFactory.createEtchedBorder());
			informationField.setEditable(false);
			userNameLabel = new JLabel("Username: ");
			userName = new JTextField(10);
			userPasswordLabel = new JLabel("Password: ");
			userPassword = new JTextField(10);
			userRepeatPasswordLabel = new JLabel("Confirm");
			userRepeatPassword = new JTextField(10);
			createUser = new JButton("Create user");
			createUser.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String userID = userName.getText();
					String userPass = userPassword.getText();
					String userConfirmPassword = userRepeatPassword.getText();
					FormClicks fd = new FormClicks(this, userID, userPass, userConfirmPassword);
					if (formListener != null) {
						formListener.formEventOccurred(fd);
					}
					
				} 
				
			});
			// Checks for user input in the new user panel.
			setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			
			/////////// FIRST ROW ////////////////
			gc.weightx = 1;
			gc.weighty = 0.1;
			
			gc.gridx = 0;
			gc.gridy = 0;
			
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.LINE_START;
			
			add(userNameLabel, gc);
			/////// NEW ITEM IN ROW ///////
			gc.weightx = 1;
			gc.weighty = 0.1;
			
			gc.gridx = 1;
			gc.gridy = 0;
			
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.LINE_END;
			
			add(userName, gc);
			/////////// SECOND ROW ////////////////
			gc.weightx = 1;
			gc.weighty = 0.1;
			
			gc.gridx = 0;
			gc.gridy = 1;
			
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.LINE_START;
			
			add(userPasswordLabel, gc);
			/////// NEW ITEM IN ROW ///////
			gc.weightx = 1;
			gc.weighty = 0.1;
			
			gc.gridx = 1;
			gc.gridy = 1;
			
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.LINE_END;
			
			add(userPassword, gc);
			/////////// THIRD ROW ////////////////
			gc.weightx = 1;
			gc.weighty = 0.1;
			
			gc.gridx = 0;
			gc.gridy = 2;
			
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.LINE_START;
			
			add(userRepeatPasswordLabel, gc);
			/////// NEW ITEM IN ROW ///////
			gc.weightx = 1;
			gc.weighty = 0.1;
			
			gc.gridx = 1;
			gc.gridy = 2;
			
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.LINE_END;
			
			add(userRepeatPassword, gc);
			////////// FOURTH ROW ///////////
			gc.weightx = 1;
			gc.weighty = 0.1;
			
			gc.gridx = 0;
			gc.gridy = 3;
			
			gc.gridwidth = 2;
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.LINE_START;
			add(createUser, gc);
			///////// FIFTH ROW ////////////
			gc.weightx = 1;
			gc.weighty = 2.0;
			
			gc.gridx = 0;
			gc.gridy = 4;
			
			gc.gridwidth = 2;
			gc.fill = GridBagConstraints.HORIZONTAL;
			gc.anchor = GridBagConstraints.FIRST_LINE_START;
			
			add(informationField, gc);
			
		}
		public void setFormListener(FormListener listener) {
			this.formListener = listener;
		}
		public void setInformationField(String text) {
			informationField.setText(text);
			//ssssssssssssssssssssssssssss
		}

	}
		/////////////// DEAD CODE /////////////////
	/* 					long start = System.currentTimeMillis();
					long end = start + 5*1000; // 60 seconds * 1000 ms/sec
					while (System.currentTimeMillis() < end)
					{
					    System.out.println(end);
					}
					wipeFrame(2);
					addLoginFrame();
					validate();*/

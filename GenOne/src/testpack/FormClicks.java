package testpack;

import java.util.EventObject;

public class FormClicks extends EventObject {
	
	private int button;
	private String userName;
	private String userPassword;
	private String confirm;
	
	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public FormClicks(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public FormClicks(Object source, int x) {
		super(source);
		this.button = x;
	}
	public FormClicks(Object source, String userName, String userPassword, int i) {
		super(source);
		this.button = i;
		this.userName = userName;
		this.userPassword = userPassword;
	}
	public FormClicks(Object source, String userName, String userPassword, String confirm) {
		super(source);
		this.userName = userName;
		this.userPassword = userPassword;
		this.confirm = confirm;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getButton() {
		return button;
	}

	public void setButton(int button) {
		this.button = button;
	}
	
}

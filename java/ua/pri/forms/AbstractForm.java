package ua.pri.forms;

import java.io.Serializable;

public class AbstractForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1594834007860999731L;
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

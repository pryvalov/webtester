package ua.pri.forms;


public class SignUpForm extends AbstractForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4890957646900331262L;
	private String login;
	private String firstName;
	private String lastName;
	private String middleName;
	private String password;
	private String email;
	private String confirm;
	
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}

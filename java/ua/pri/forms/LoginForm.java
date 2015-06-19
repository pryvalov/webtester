package ua.pri.forms;

public class LoginForm extends AbstractForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5982365777444923211L;
	/*private int role;*/
	private Roles role;
	public enum Roles{
		Student, Tutor, Advanced_tutor, Administrator;
	}
	
/*	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}*/
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles eRole) {
		this.role = eRole;
	}
	
	
}

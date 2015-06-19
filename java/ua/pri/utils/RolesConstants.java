package ua.pri.utils;

import java.util.Arrays;
import java.util.List;

import ua.pri.forms.LoginForm.Roles;


public interface RolesConstants {

	public final static Roles ADMIN = Roles.Administrator;
	
	public final static Roles STUDENT = Roles.Student;
	
	public final static Roles TUTOR = Roles.Tutor;
	
	public final static Roles ADVANCED_TUTOR = Roles.Advanced_tutor;
	
	public static List<Roles> roles = Arrays.asList(ADMIN, ADVANCED_TUTOR, STUDENT, TUTOR);
	
	

}

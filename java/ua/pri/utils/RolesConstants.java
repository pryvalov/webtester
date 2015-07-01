package ua.pri.utils;

import java.util.Arrays;
import java.util.List;


import ua.pri.forms.LoginForm.Roles;


public interface RolesConstants {

	public final static int ADMIN_ROLE_ID = 0;
	
	public final static int STUDENT_ROLE_ID = 1;
	
	public final static int TUTOR_ROLE_ID = 2;
	
	public final static int ADVANCED_TUTOR_ROLE_ID = 3;
	
	public final static Roles ADMIN = Roles.Administrator;
	
	public final static Roles STUDENT = Roles.Student;
	
	public final static Roles TUTOR = Roles.Tutor;
	
	public final static Roles ADVANCED_TUTOR = Roles.Advanced_tutor;
	
	public static List<Roles> roles = Arrays.asList(ADMIN, ADVANCED_TUTOR, STUDENT, TUTOR);
	
	public static List<Integer> role_ids = Arrays.asList(ADMIN_ROLE_ID, STUDENT_ROLE_ID, TUTOR_ROLE_ID, ADVANCED_TUTOR_ROLE_ID);
	
	
		
	

}

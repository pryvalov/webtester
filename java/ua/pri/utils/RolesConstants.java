package ua.pri.utils;

import java.util.Arrays;
import java.util.List;


public interface RolesConstants {

	public final static int ADMIN = 0;
	
	public final static int STUDENT = 1;
	
	public final static int TUTOR = 2;
	
	public final static int ADVANCED_TUTOR = 3;
	
	public static List<Integer> roles = Arrays.asList(ADMIN, ADVANCED_TUTOR, STUDENT, TUTOR);
	
	

}

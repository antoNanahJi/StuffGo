package utilities;

/**
 * This class used to validate inputs from SQL injections and XSS
 * @author antoji
 */
public class Security {
	// private variables
	private static String[] SQLCommandsList = {			
	        	           		    "CREATE",
	        	        			"DROP",
	        	        			"ALTER",
	        	        			"TRUNCATE",			
	        	        			"INSERT",
	        	        			"UPDATE",
	        	        			"DELETE",			
	        	        			"GRANT",
	        	        			"REVOKE",
	        	        			"COMMIT",			
	        	        			"ROLLBACK",
	        	        			"SAVE",
	        	        			"SELECT"};
	
	/*
	 * @return true if given string does not contain SQL commands
	 */
	public static boolean containsSQL(String s) {
		if (s.length() == 0) {
			return false;
		}
		String arr[] = s.split(" ");
		int size = 	SQLCommandsList.length;
		
		for (int i=0; i<size; i++) {
			if (SQLCommandsList[i].equals(arr[0].toUpperCase())) {
				return true;
			}
		}

		return false;
	}
	
	/*
	 * @return true if given string does not contain XSS commands
	 */
	public static boolean containsXSS(String s) {
		if (s.length() == 0) {
			return false;
		}
		if (s.contains("script") || s.contains("style")) {
			return true;
		}


		return false;
	}
	
}

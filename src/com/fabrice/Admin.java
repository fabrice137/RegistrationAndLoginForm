package com.fabrice;

public class Admin extends User {
	
	public Admin() {
		
	}
	
	@Override
	public boolean register(String uname, String pword, String fname, String lname, String age, String sex, String phone, String role) {
		
		if(isPasswordValid(pword)) {
			super.init(uname, pword, fname, lname, age, sex, phone, role);
//			memory.addUser(pword, new User().init(uname, pword, fname, lname, age, sex, phone, rol));
			return true;
		}
		return false;
	}

	@Override
	public String login(String uname, String pword) {

		if(isPasswordValid(pword)) {
			return "admin";
		}
		return "error";
	}
	
	public static boolean isPasswordValid(String password) {
    	if(password.length() < 10) return false;
    	
    	boolean hasDigit = false, hasLetter = false;
    	
    	for(int i=0; i<password.length(); i++) {
    		if(!hasDigit) if(Character.isDigit( password.charAt(i) )) hasDigit = true;
    		if(!hasLetter) if(Character.isLetter( password.charAt(i) )) hasLetter = true;
    	}
    	if(!hasDigit || !hasLetter) return false;
    	
    	return true;
    }

}

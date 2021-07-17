package com.fabrice;

public class Guest extends User {
	
	public Guest() {
		
	}

	@Override
	public boolean register(String uname, String pword, String fname, String lname, String age, String sex, String phone, String role) {
		
		if(isPasswordValid(pword)) {
			super.init(uname, pword, fname, lname, age, sex, phone, role);
			return true;
		}
		return false;
	}

	@Override
	public String login(String uname, String pword) {

		if(isPasswordValid(pword)) {
			return "guest";
		}
		return "error";
	}
	
	private boolean isPasswordValid(String password) {
    	if(password.length() < 5) return false;
    	
    	boolean hasDigit = false, hasLetter = false;
    	
    	for(int i=0; i<password.length(); i++) {
    		if(!hasDigit) if(Character.isDigit( password.charAt(i) )) hasDigit = true;
    		if(!hasLetter) if(Character.isLetter( password.charAt(i) )) hasLetter = true;
    	}
    	if(!hasDigit || !hasLetter) return false;
    	
    	return true;
    }
	
}

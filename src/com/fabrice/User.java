package com.fabrice;

public abstract class User {
	
	@SuppressWarnings("unused") 
	private String username, encryptedPassword;
	private String firstname, lastname;
    private String age, sex, phone;
    private String role;
    
    public void init(String uname, String pword, String fname, String lname, String age, String sex, String phone, String role){
        this.username = uname;
        this.firstname = fname;
        this.lastname = lname;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.role = role;
        passwordEncrypt(pword, age);
    }
    
	public abstract boolean register(String uname, String pword, String fname, String lname, String age, String sex, String phone, String role);
    public abstract String login(String uname, String pword);
    
    public String getUsername() {
    	return username;
    }
    
	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}
	
	public String getAge() {
		return age;
	}

	public String getSex() {
		return sex;
	}

	public String getPhone() {
		return phone;
	}

	public String getRole() {
		return role;
	}

	private void passwordEncrypt(String pass, String age){
        StringBuilder sb = new StringBuilder(pass);
        sb.reverse();
        sb.append(pass).append(age).append("**");
        this.encryptedPassword = "**".concat(sb.toString());
    }
}

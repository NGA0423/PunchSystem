package com.attendance.upwork;

public class Returnsa {
	private String user_id;
	private String user_pwd;
	public String getUser_id() {
		System.out.println(user_id);
		return user_id;
	}
	public void setUser_id(String user_id) {
		System.out.println(user_id);
		this.user_id = user_id;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	
}

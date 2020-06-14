package com.attendance.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionMysql {
	public Statement getCreateStatement() {
		Connection con;
		Statement createStatement = null;
		PreparedStatement prepareStatement = null;
		String URL="jdbc:mysql://localhost:3306/employee_information?useSSL=false&characterEncoding=utf-8&serverTimezone=GMT&allowPublicKeyRetrieval=true";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, "root", "123456");
			
			createStatement = con.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		return createStatement;
	}
	public PreparedStatement prepareStatement(String sql) {
		Connection con;
		PreparedStatement prepareStatement = null;
		String URL="jdbc:mysql://localhost:3306/employee_information?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, "root", "123456");
			
			prepareStatement = con.prepareStatement(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return prepareStatement;
		
	}
}

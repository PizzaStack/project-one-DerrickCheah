package com.revature.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnection {

	private final String url = "jdbc:postgresql://projectonedb.cshthkt1n48j.us-east-2.rds.amazonaws.com:5432/projectonedb";
	private final String username = "derrick_cheah";
	private final String password = "c98woD9hoMGq";
	private Connection connection;

	public Connection getConnection() {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (java.lang.ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
//		try {
//			DriverManager.registerDriver(new org.postgresql.Driver());
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		try {
			if (connection == null) {
				connection = DriverManager.getConnection(url, username, password);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

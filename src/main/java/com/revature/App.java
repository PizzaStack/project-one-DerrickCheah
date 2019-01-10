package com.revature;

import java.sql.Connection;

import com.revature.service.CreateConnection;

public class App {
	
	public static void main(String[] args) {
		CreateConnection CreateConnection = new CreateConnection();
		Connection connection = CreateConnection.getConnection();
	}
}

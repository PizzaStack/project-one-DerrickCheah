package com.revature.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {

	public boolean login(Connection connection, String username, String password) {
		try {
			Statement statement = connection.createStatement();

			String sql = String.format(
					"SELECT login.password, manager.manager FROM login, manager WHERE login.id = manager.id AND login.username = '%s'",
					username);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				if (rs.getString(1).equals(password)) {
					return true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}

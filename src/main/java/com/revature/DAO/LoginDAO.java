package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.entity.User;

public class LoginDAO {

	private static final String LOGIN_BY_USERNAME_AND_PASSWORD = "SELECT employee.firstname, employee.lastname, login.username, login.password, info.email, info.address, info.dob, info.phone, info.ssn, manager.manager from info left join employee on employee.id = info.id left join login on login.id = info.id left join manager on manager.id = info.id where login.username = ? and login.password = ?";

	public User find(Connection connection, String username, String password) {
		User user = null;

		try {
			PreparedStatement statement = connection.prepareStatement(LOGIN_BY_USERNAME_AND_PASSWORD);

			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				user = map(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	private static User map(ResultSet rs) throws SQLException {
		User user = new User();
		user.setFirstName(rs.getString("firstname"));
		user.setLastName(rs.getString("lastname"));
		user.setEmail(rs.getString("email"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setAddress(rs.getString("address"));
		user.setDob(rs.getDate("dob"));
		user.setPhoneNumber(rs.getString("phone"));
		user.setSsn(rs.getString("ssn"));
		user.setManager(rs.getBoolean("manager"));
		return user;
	}
}

package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SettingsDAO {
	
	private static final String UPDATE_FIRSTNAME = "UPDATE employee SET firstname = ? where id = ?";
	private static final String UPDATE_LASTNAME = "UPDATE employee SET lastname = ? where id = ?";
	private static final String UPDATE_PASSWORD = "UPDATE login SET password = ? where id = ?";
	private static final String UPDATE_ADDRESS = "UPDATE info SET address = ? where id = ?";
	private static final String UPDATE_PHONE = "UPDATE info SET phone = ? where id = ?";
	
	public void updateFirstName(Connection connection, String firstName, long id) {
		try {
			PreparedStatement ps = connection.prepareStatement(UPDATE_FIRSTNAME);
			ps.setString(1, firstName);
			ps.setLong(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateLastName(Connection connection, String lastName, long id) {
		try {
			PreparedStatement ps = connection.prepareStatement(UPDATE_LASTNAME);
			ps.setString(1, lastName);
			ps.setLong(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePassword(Connection connection, String password, long id) {
		try {
			PreparedStatement ps = connection.prepareStatement(UPDATE_PASSWORD);
			ps.setString(1, password);
			ps.setLong(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateAddress(Connection connection, String address, long id) {
		try {
			PreparedStatement ps = connection.prepareStatement(UPDATE_ADDRESS);
			ps.setString(1, address);
			ps.setLong(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePhone(Connection connection, String phone, long id) {
		try {
			PreparedStatement ps = connection.prepareStatement(UPDATE_PHONE);
			ps.setString(1, phone);
			ps.setLong(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

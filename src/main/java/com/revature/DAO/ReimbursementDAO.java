package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReimbursementDAO {

	private static final String ADD_REIMBURSEMENT_REQUEST = "INSERT INTO reimbursement VALUES (?, ?, ?, true)";

	public void uploadReimbursement(Connection connection, String description, double expenseCost, long id) {
		try {
			PreparedStatement ps = connection.prepareStatement(ADD_REIMBURSEMENT_REQUEST);
			ps.setString(1, description);
			ps.setDouble(2, expenseCost);
			ps.setLong(3, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

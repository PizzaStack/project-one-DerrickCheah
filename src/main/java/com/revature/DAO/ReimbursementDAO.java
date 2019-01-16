package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

public class ReimbursementDAO {

	private static final String ADD_REIMBURSEMENT_REQUEST = "INSERT INTO reimbursement VALUES (?, ?, ?, 'pending')";
	private static final String VIEW_PENDING_REQUEST = "SELECT reimbursement.description, reimbursement.cost FROM reimbursement where id = ? and status = 'pending'";
	private static final String VIEW_RESOLVED_REQUEST = "SELECT reimbursement.description, reimbursement.cost FROM reimbursement where id = ? and status = 'approved'";
	private static final String VIEW_DENIED_REQUEST = "SELECT reimbursement.description, reimbursement.cost FROM reimbursement where id = ? and status = 'denied'";

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

	public JsonArray viewPending(Connection connection, long id) {
		JsonArrayBuilder values = Json.createArrayBuilder();
		try {
			PreparedStatement ps = connection.prepareStatement(VIEW_PENDING_REQUEST);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				values.add(Json.createObjectBuilder().add("description", rs.getString(1)).add("cost", rs.getDouble(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JsonArray table = values.build();
		return table;
	}

	public JsonArray viewResolved(Connection connection, long id) {
		JsonArrayBuilder values = Json.createArrayBuilder();
		try {
			PreparedStatement ps = connection.prepareStatement(VIEW_RESOLVED_REQUEST);
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				values.add(Json.createObjectBuilder().add("description", rs.getString(1)).add("cost", rs.getDouble(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JsonArray table = values.build();
		return table;
	}

	public JsonArray viewDenied(Connection connection, long id) {
		JsonArrayBuilder values = Json.createArrayBuilder();
		try {
			PreparedStatement ps = connection.prepareStatement(VIEW_DENIED_REQUEST);
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				values.add(Json.createObjectBuilder().add("description", rs.getString(1)).add("cost", rs.getDouble(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JsonArray table = values.build();
		return table;
	}
}

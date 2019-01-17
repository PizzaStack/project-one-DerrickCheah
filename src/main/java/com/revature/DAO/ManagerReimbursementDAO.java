package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

public class ManagerReimbursementDAO {

	private static final String VIEW_PENDING_REQUEST = "SELECT reimbursement.id, reimbursement.description, reimbursement.cost, reimbursement.reimbursementid FROM reimbursement where status = 'pending'";
	private static final String VIEW_RESOLVED_REQUEST = "SELECT reimbursement.id, reimbursement.description, reimbursement.cost, reimbursement.manager, reimbursement.reimbursementid FROM reimbursement where status = 'approved'";
	private static final String VIEW_SINGLE_EMPLOYEE_REQUEST = "SELECT reimbursement.description, reimbursement.cost, reimbursement.reimbursementid FROM reimbursement where id = ? and status = 'pending'";
	private static final String APPROVE_EMPLOYEE_REQUEST = "UPDATE reimbursement SET status = 'approved', manager = ? WHERE reimbursementid = ?";
	private static final String DENY_EMPLOYEE_REQUEST = "UPDATE reimbursement SET status = 'denied', manager = ? WHERE reimbursementid = ?";
	
	public JsonArray getPending(Connection connection) {
		JsonArrayBuilder values = Json.createArrayBuilder();
		try {
			PreparedStatement ps = connection.prepareStatement(VIEW_PENDING_REQUEST);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				values.add(Json.createObjectBuilder().add("id", rs.getInt(1)).add("description", rs.getString(2))
						.add("cost", rs.getDouble(3)).add("refid", rs.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JsonArray table = values.build();
		return table;
	}

	public JsonArray getResolved(Connection connection) {
		JsonArrayBuilder values = Json.createArrayBuilder();
		try {
			PreparedStatement ps = connection.prepareStatement(VIEW_RESOLVED_REQUEST);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				values.add(Json.createObjectBuilder().add("id", rs.getInt(1)).add("description", rs.getString(2))
						.add("cost", rs.getDouble(3)).add("manager", rs.getString(4)).add("refid", rs.getInt(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JsonArray table = values.build();
		return table;
	}

	public JsonArray searchEmployee(Connection connection, long id) {
		JsonArrayBuilder values = Json.createArrayBuilder();
		try {
			PreparedStatement ps = connection.prepareStatement(VIEW_SINGLE_EMPLOYEE_REQUEST);
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				values.add(Json.createObjectBuilder().add("description", rs.getString(1)).add("cost", rs.getDouble(2)).add("refid", rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JsonArray table = values.build();
		return table;
	}

	public void approveRequest(Connection connection, String manager, Integer refId) {
		try {
			PreparedStatement ps = connection.prepareStatement(APPROVE_EMPLOYEE_REQUEST);
			ps.setString(1, manager);
			ps.setInt(2, refId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void denyRequest(Connection connection, String manager, Integer refId) {
		try {
			PreparedStatement ps = connection.prepareStatement(DENY_EMPLOYEE_REQUEST);
			ps.setString(1, manager);
			ps.setInt(2, refId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}

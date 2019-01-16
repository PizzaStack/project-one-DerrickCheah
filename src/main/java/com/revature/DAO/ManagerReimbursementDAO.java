package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

public class ManagerReimbursementDAO {

	private static final String VIEW_PENDING_REQUEST = "SELECT reimbursement.id, reimbursement.description, reimbursement.cost FROM reimbursement where status = 'pending'";
	private static final String VIEW_RESOLVED_REQUEST = "SELECT reimbursement.id, reimbursement.description, reimbursement.cost, reimbursement.manager FROM reimbursement where status = 'approved'";

	public JsonArray getPending(Connection connection) {
		JsonArrayBuilder values = Json.createArrayBuilder();
		try {
			PreparedStatement ps = connection.prepareStatement(VIEW_PENDING_REQUEST);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				values.add(Json.createObjectBuilder().add("id", rs.getInt(1)).add("description", rs.getString(2))
						.add("cost", rs.getDouble(3)));
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
						.add("cost", rs.getDouble(3)).add("manager", rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JsonArray table = values.build();
		return table;
	}

}

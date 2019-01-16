package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

public class ViewEmployeeDAO {
	
	private static final String VIEW_EMPLOYEE_LIST = "SELECT employee.id, employee.firstname, employee.lastname FROM employee LEFT JOIN manager on employee.id = manager.id where manager.manager = false";
	
	public JsonArray viewEmployees(Connection connection) {
		JsonArrayBuilder values = Json.createArrayBuilder();
		try {
			PreparedStatement ps = connection.prepareStatement(VIEW_EMPLOYEE_LIST);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				values.add(Json.createObjectBuilder()
						.add("id", rs.getInt(1))
						.add("firstName", rs.getString(2))
						.add("lastName", rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JsonArray table = values.build();
		return table;
	}

}

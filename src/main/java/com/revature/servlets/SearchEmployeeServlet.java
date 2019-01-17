package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.DAO.ManagerReimbursementDAO;
import com.revature.service.CreateConnection;

public class SearchEmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CreateConnection createConnection = new CreateConnection();
		Connection connection = createConnection.getConnection();
		
		BufferedReader br = request.getReader();
		String line = "";
		long id = 0;
		while ((line = br.readLine()) != null) {
			id = Long.parseLong(line);
		}
		
		ManagerReimbursementDAO mrd = new ManagerReimbursementDAO();
		JsonArray requests = mrd.searchEmployee(connection, id);
		
		PrintWriter out = response.getWriter();
		out.println(requests);
	}

}

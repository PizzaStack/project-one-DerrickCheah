package com.revature.servlets;

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
		
		String input = request.getParameter("id");
		long id = Long.parseLong(input);
		
		ManagerReimbursementDAO mrd = new ManagerReimbursementDAO();
		JsonArray requests = mrd.searchEmployee(connection, id);
		
		PrintWriter out = response.getWriter();
		out.println(requests);
		
		response.sendRedirect("pages/manager.html");
	}

}

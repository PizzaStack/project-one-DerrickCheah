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

public class ResolvedReimbursementManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CreateConnection createConnection = new CreateConnection();
		Connection connection = createConnection.getConnection();
		
		ManagerReimbursementDAO mrd = new ManagerReimbursementDAO();
		
		JsonArray resolved = mrd.getResolved(connection);

		PrintWriter out = response.getWriter();
		out.println(resolved);
	}
}

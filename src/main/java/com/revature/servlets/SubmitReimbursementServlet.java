package com.revature.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.DAO.ReimbursementDAO;
import com.revature.entity.User;
import com.revature.service.CreateConnection;

public class SubmitReimbursementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String description = request.getParameter("description");
		String cost = request.getParameter("expensecost");
		double expenseCost = Double.parseDouble(cost);
		
		User user = (User) request.getSession(false).getAttribute("user");
		long id = user.getId();
		
		CreateConnection createConnection = new CreateConnection();
		Connection connection = createConnection.getConnection();
		
		ReimbursementDAO reimburse = new ReimbursementDAO();
		
		reimburse.uploadReimbursement(connection, description, expenseCost, id);
		
		response.sendRedirect("pages/employee.html");
	}
}

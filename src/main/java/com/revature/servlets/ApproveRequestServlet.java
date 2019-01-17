package com.revature.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.DAO.ManagerReimbursementDAO;
import com.revature.entity.User;
import com.revature.service.CreateConnection;

public class ApproveRequestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CreateConnection createConnection = new CreateConnection();
		Connection connection = createConnection.getConnection();
		
		User user = (User) request.getSession(false).getAttribute("user");
		String manager = user.getFirstName() + " " + user.getLastName();
		
		Integer refId = Integer.parseInt(request.getParameter("refid"));
		
		ManagerReimbursementDAO mrd = new ManagerReimbursementDAO();
		mrd.approveRequest(connection, manager, refId);
	}

}

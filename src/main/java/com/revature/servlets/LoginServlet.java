package com.revature.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.DAO.LoginDAO;
import com.revature.service.CreateConnection;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CreateConnection createConnection = new CreateConnection();
		Connection connection = createConnection.getConnection();

		LoginDAO login = new LoginDAO();
		boolean isAbleToLogin = login.login(connection, request.getParameter("username"),
				request.getParameter("password"));

		if (isAbleToLogin) {
			response.sendRedirect("http://localhost:8080/project-one-DerrickCheah/pages/employee.html");
		} else {
			response.sendRedirect("http://localhost:8080/project-one-DerrickCheah/");
		}

		createConnection.closeConnection(connection);
	}

}

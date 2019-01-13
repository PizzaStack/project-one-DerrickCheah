package com.revature.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.DAO.LoginDAO;
import com.revature.entity.User;
import com.revature.service.CreateConnection;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CreateConnection createConnection = new CreateConnection();
		Connection connection = createConnection.getConnection();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		LoginDAO login = new LoginDAO();

		User user = login.find(connection, username, password);

		if (user != null && user.getManager() == false) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect("/project-one-DerrickCheah/pages/employee.html");
		} else if (user != null && user.getManager() == true) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect("/project-one-DerrickCheah/pages/manager.html");
		} else {
			request.getRequestDispatcher("/index.html").forward(request, response);
		}

		createConnection.closeConnection(connection);
	}

}

package com.revature.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.DAO.SettingsDAO;
import com.revature.entity.User;
import com.revature.service.CreateConnection;

public class UpdateSettingsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CreateConnection createConnection = new CreateConnection();
		Connection connection = createConnection.getConnection();
		
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String number = request.getParameter("number");
		
		System.out.println(number);
		
		User user = (User) request.getSession(false).getAttribute("user");
		SettingsDAO sd = new SettingsDAO();
		
		long id = user.getId();
		
		if (firstName != null && !firstName.isEmpty()) {
			user.setFirstName(firstName);
			sd.updateFirstName(connection, firstName, id);
		}
		
		if (lastName != null && !lastName.isEmpty()) {
			user.setLastName(lastName);
			sd.updateLastName(connection, lastName, id);
		}
		
		if (password != null && !password.isEmpty()) {
			user.setPassword(password);
			sd.updatePassword(connection, password, id);
		}
		
		if (address != null && !address.isEmpty()) {
			user.setAddress(address);
			sd.updateAddress(connection, address, id);
		}
		
		if (number != null && !number.isEmpty()) {
			user.setPhoneNumber(number);
			sd.updatePhone(connection, number, id);
		}
		
		request.getSession(false).setAttribute("user", user);
		
		if (user.getManager()) {
			response.sendRedirect("pages/manager.html");
		} else {
			response.sendRedirect("pages/employee.html");
		}
	}

}

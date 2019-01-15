package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.revature.entity.User;

public class UserInfoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession(false).getAttribute("user");
		
		response.setContentType("application/json");
		JSONObject jo = new JSONObject();
		
		jo.put("Name", user.getFirstName() + " " + user.getLastName());
		jo.put("Email", user.getEmail());
		jo.put("Username", user.getUsername());
		jo.put("Birthdate", user.getDob());
		jo.put("Address", user.getAddress());
		jo.put("Number", user.getPhoneNumber());
		jo.put("SSN", user.getSsn());
		
		response.getWriter().print(jo);
	}
}

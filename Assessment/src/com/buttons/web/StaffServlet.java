package com.buttons.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.buttons.models.Staff;

/**
 * Servlet implementation class StaffServlet
 */
public class StaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StaffServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// the action element
		String cmd;

		cmd = request.getParameter("action");
		if (cmd.equals("home")){
			goHome(request,response);
		} else if (cmd.equals("login")) {
			try {
				login(request, response);
			} catch (ClassNotFoundException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			//no command
			out.println("Error: No page set");
		}
		out.flush();
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		String name = (request.getParameter("name"));
		String password = (request.getParameter("password"));
		boolean log = false;
		
		Staff sta = new Staff();
		log = sta.loginStaff(name, password);
		
		if(log){
			String stringURL = "/staff.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(stringURL);
			rd.forward(request, response);
		}
		else{
			String stringURL = "/index.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(stringURL);
			rd.forward(request, response);
		}
	}
	
	private void goHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String stringURL = "/staff.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(stringURL);
		rd.forward(request, response);

	}
}

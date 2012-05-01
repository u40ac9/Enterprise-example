package com.buttons.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.buttons.db.DbBean;
import com.buttons.models.Customer;
import com.buttons.models.Orders;

/**
 * Servlet implementation class ButtonServlet
 */
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();
        // the action element that we'll check for
        String cmd;

        cmd = request.getParameter("action");
        if (cmd != null) {
            if (cmd.equals("home")) {
                goHome(request, response);
           	
            } else if (cmd.equals("loginCustomer")){
            	int ID = Integer.parseInt(request.getParameter("customerID"));
                String password = request.getParameter("password");
            	try {
					login(request, response, ID, password);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else if (cmd.equals("newCustomer")){
            	try {
					newCustomer(request, response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else if (cmd.equals("myOrders")){
            	try {
					displayMyOrders(request, response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
                // no command set
                out.println("Error: No page set from CustomerServlet");
            }
            out.flush();
        }
    }
	
	private void displayMyOrders(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, ServletException, IOException{
	 
	  HttpSession session = request.getSession(true);
		
	  Orders orders = new Orders();
	  String ID = Integer.toString((Integer) session.getValue("customerID"));
	  
	  Vector <Orders> vec = new Vector <Orders>();
	  vec = orders.listOrders(ID);
	  
	  request.setAttribute("customerID", ID);
	  request.setAttribute("items", vec);
	  
	  String stringURL = "/MyOrders.jsp";
	  RequestDispatcher rd = request.getRequestDispatcher(stringURL);
	  rd.forward(request, response);
	  
	  
	}
	
	private void newCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		
		
		
		DbBean db = new DbBean();
		int ID = 0;
		String name, address, password = null;
		System.out.println("Creating new customer");
		try {
			db.connect();
			int max = db.getMaxValue("app.customer", "ID");
			//int max = 1;
			max = max+1;
			PreparedStatement inst = db.prepareStatement("INSERT INTO customer VALUES (?,?,?,?)");
			inst.setInt(1,max);
			name = request.getParameter("name");
			inst.setString(2, name);
			address = request.getParameter("address");
			inst.setString(3, address);
			password = request.getParameter("newPassword");
			inst.setString(4, password);
			
			inst.executeUpdate();
			inst.close();
			db.close();
			System.out.println("New customer created");
		} catch(SQLException e){
			System.out.println(
					"SQLException from db: " + e.getMessage());
			e.printStackTrace();
		}
		
		login(request, response, ID, password);
			
			
		}
	
	private void login(HttpServletRequest request, HttpServletResponse response, int ID, String password) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, ServletException, IOException{
		
		System.out.println("Logging in");
		HttpSession session = request.getSession(true);
		session.setAttribute("loggedIn", true);
		
		ResultSet rs = null;
		DbBean db = new DbBean();
		StringBuffer qry = new StringBuffer(1024);
		
		qry.append("SELECT name, address FROM customer WHERE ID = "); 
		qry.append(ID);
		qry.append(" AND password = '");
		qry.append(password);
		qry.append("'");
		
		db.connect();
		System.out.println(qry);
		rs = db.execSQL(qry);
		
		while(rs.next()){
			Customer customer = new Customer();
			customer.setCustomerID(ID);
			customer.setName(rs.getString("name"));
			customer.setAddress(rs.getString("address"));
		}
		
		rs.close();
		db.close();
		
		System.out.println("Logged in");
		
		ButtonServlet bs = new ButtonServlet();
		session.putValue("customerID", ID);
		bs.listMotifs(request, response, session);
	}
	
	private void loadCustomerHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String stringURL = "/customer.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(stringURL);
		rd.forward(request, response);
	}

	private void goHome(HttpServletRequest request, HttpServletResponse response) {

		String stringURL = "/index.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(stringURL);
		//rd.forward(request, response);
		
	}

}

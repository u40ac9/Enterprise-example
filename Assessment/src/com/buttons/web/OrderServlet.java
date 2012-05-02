package com.buttons.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.buttons.models.Motifs;
import com.buttons.models.Orders;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
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
		
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		// the action element
		String cmd;
		
		cmd = request.getParameter("action");
		if (cmd != null) {
			if (cmd.equals("home")){
				goHome(request,response);
			} else if (cmd.equals("listOrders")) {
				try {
					listOrders(request, response);
				} catch (ClassNotFoundException e) {
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
			} else if (cmd.equals("insertOrder")){
				try {
					addOrder(request, response, session);
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
			} else if(cmd.equals("delete")){
				deleteOrder(request, response);
			} else {
				//no command
				out.println("Error: No page set");
			}
			out.flush();
		}
	}
	
	private void deleteOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String stringURL = "/delete.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(stringURL);
		rd.forward(request, response);
	}
	
	private void addOrder(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws ServletException, 				  IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		
		String message = null;
		if(!(Boolean) session.getValue("loggedIn")){
			message = "Please login first or create an account first.";
			request.setAttribute("message", message);
			
			String stringURL = "/customer.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(stringURL);
			rd.forward(request, response);
			
		}
		
		else {
		
	    int motifID = Integer.parseInt(request.getParameter("motifID"));
	    int quantity = Integer.parseInt(request.getParameter("quantity"));
	    int customerID = (Integer) session.getValue("customerID");
	    
	    Vector<Motifs> vec = new Vector<Motifs>();
	    Motifs mot = new Motifs();
	    vec = mot.listMotifs("all");
	    double price = 0.0;
	    
	    Iterator it =  vec.iterator();
	    while(it.hasNext()){
	    	mot = (Motifs) it.next();
	    	if(mot.getID() == motifID){
	    		price = mot.getPrice() * quantity;
	    	}
	    }
	     
		try {
            Orders orders = new Orders();
        	orders.insertorder(price, motifID, quantity, customerID);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CustomerServlet cs = new CustomerServlet();
		cs.displayMyOrders(request, response);
		
		}
	}
	
	private void listOrders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Orders ord = new Orders();
		Vector<Orders> vec = new Vector<Orders>();
		vec = ord.listOrders("all");
		
		request.setAttribute("orders", vec);
		
		String stringURL = "/listOrders.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(stringURL);
		rd.forward(request, response);
	}
	
	private void goHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String stringURL = "/index.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(stringURL);
		rd.forward(request, response);

	}
}

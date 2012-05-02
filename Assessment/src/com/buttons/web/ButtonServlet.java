package com.buttons.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.buttons.models.Motifs;

/**
 * Servlet implementation class ButtonServlet
 * 
 */
public class ButtonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ButtonServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		session.setAttribute("customer", false);
		session.setAttribute("staff", false);
	
		PrintWriter out = response.getWriter();
        // the action element that we'll check for
        String cmd;

        cmd = request.getParameter("action");
        if (cmd != null) {
            if (cmd.equals("home")) {
                goHome(request, response);
            } else if (cmd.equals("listMotifs")) {
                try {
					listMotifs(request, response, session);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
            } else if (cmd.equals("addMotif")){
                try {
					addMotif(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
            } else if (cmd.equals("deleteMotif")){
                try {
					deleteMotif(request, response);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
            } else if(cmd.equals("updatePrice")){
            	try {
					updatePrice(request, response);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
            } else if(cmd.equals("updateName")){
            	try {
					updateName(request, response);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
            } else if(cmd.equals("updateQuantity")){
            	try {
					updateQuantity(request, response);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
            } else {
                // no command set
                out.println("Error: No page set");
            }
            out.flush();
        }
    }
	
	private void addMotif(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NumberFormatException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		String name = request.getParameter("name");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");
                
        Motifs mot = new Motifs();
        mot.insertMotif(name,Double.parseDouble(price),Integer.parseInt(quantity));
        
        //Return confirmation message to browser
        String id = "Motif added";
        StringBuffer message = new StringBuffer(1024);
        message.append("The magnificent motif: ");
        message.append("<b>"+name + "</b> ");
        message.append("has been added to our store.");
        
        String stringURL = "/message.jsp";
        request.setAttribute("id", id);
        request.setAttribute("message", message);
        //Forward message to message page
        RequestDispatcher rd = request.getRequestDispatcher(stringURL);
        rd.forward(request, response);
	}
	
	private void deleteMotif(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Motifs mot =new Motifs();
		mot.deleteMotif(id);
		//TODO use message.jsp instead and display message that button is gone
		String stringURL = "/deleteMotif.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(stringURL);
		rd.forward(request, response);
	}
	
	public void listMotifs(HttpServletRequest request,
	HttpServletResponse response, HttpSession session) throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Motifs mot = new Motifs();
        Vector <Motifs> vec = new Vector <Motifs>( );
        vec = mot.listMotifs("all");
        
        
        //YOU CAN USE THIS TO CHECK IF STAFF AND DISPLAY CORRECT CONTENT
        if((Boolean)session.getAttribute("loggedIn")){
        int customerID = (Integer) session.getAttribute("customerID"); 
        request.setAttribute("customerID", customerID);
        }
        request.setAttribute("items", vec);
		
        
		String stringURL = "/list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(stringURL);
		rd.forward(request, response);
		
	}
	
	private void updatePrice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		int id = Integer.parseInt(request.getParameter("id"));
		double price = Double.parseDouble(request.getParameter("price"));
		Motifs mot = new Motifs();
		mot.updatePrice(id, price);
	}
	
	private void updateQuantity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		int id = Integer.parseInt(request.getParameter("id"));
		int quantity = Integer.parseInt(request.getParameter("price"));
		Motifs mot = new Motifs();
		mot.updatePrice(id, quantity);
	}
	
	private void updateName(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Motifs mot = new Motifs();
		mot.updateName(id, name);
	}

	private void goHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String stringURL = "/index.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(stringURL);
		rd.forward(request, response);
		
	}

}

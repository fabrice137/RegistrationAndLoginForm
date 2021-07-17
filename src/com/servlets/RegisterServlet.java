package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fabrice.Admin;
import com.fabrice.Guest;
import com.fabrice.User;
import com.fabrice.memory;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		        
		String uname = req.getParameter("username");
		String pword = req.getParameter("password");
		String age = req.getParameter("age");
		
		String fname = req.getParameter("firstname");
		String lname = req.getParameter("lastname");
		String phone = req.getParameter("phone");
		
		String sex = req.getParameter("sex");
		String role = req.getParameter("role");
				
		if(pword != null && uname != null && age != null && role != null) {
			boolean canRegister = false;
			
			if(role.equals("admin")) {
				User admin = new Admin();
				canRegister = admin.register(uname, pword, fname, lname, age, sex, phone, role);
				if(canRegister) memory.addUser(uname + pword, admin);
			}
			else {
				User guest = new Guest();
				canRegister = guest.register(uname, pword, fname, lname, age, sex, phone, role);
				if(canRegister) memory.addUser(uname + pword, guest);
			}
			
			if(canRegister) {
				res.sendRedirect(req.getContextPath() +"/login.html"); 
			}
		}
		else {
			prepareErrorPage(res); // should be created
			System.out.println("Should open error html page");
		}
		
		System.out.println("ClassEnd: RegisterServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void prepareErrorPage(HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		out.print("<html>");
		out.print("<body class=\"body body-s\">");
		out.print("<h1>Password rules not met</h1>");
        out.print("</body>");
        out.print("</html>");
		
		out.close();
	}
	

}

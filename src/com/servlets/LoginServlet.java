package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fabrice.User;
import com.fabrice.memory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String uname = req.getParameter("username");
		String pword = req.getParameter("password");
				
		if(pword != null && uname != null) {
			User user = memory.getUser(uname + pword);
			if(user != null) {
				String loginInfo = user.login(uname, pword);
				
				if(loginInfo.equals("error")) {
					prepareErrorPage(res); // should be created
					System.out.println("Should open error html page: loginInfo = error");
				}
				else {
					prepareHomePage(res, user);
//					res.sendRedirect(req.getContextPath() +"/home.html");
					System.out.println("Successful: should see home.html => "+ loginInfo);
				}
				
			}
			else {
				prepareErrorPage(res); // should be created
				System.out.println("Should open error html page: user = null");
			}
			
		}
		else {
			prepareErrorPage(res);
			System.out.println("Should open error html page: missing password/username");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub  
		doGet(request, response);
	}
	
	private void prepareHomePage(HttpServletResponse res, User u) throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		out.print("<html>");
		out.print("<body>");
		out.print("<h1>"+ u.getRole().toUpperCase() + " Home page</h1>");
		out.print("<h3>Name: "+ u.getFirstname() +" "+ u.getLastname() + "</hh3>");
		out.print("<h3>UserName: " + u.getUsername() + "</h3>");
		out.print("<h3>PhoneNumber: " + u.getPhone() + "</h3>");
        out.print("</body>");
        out.print("</html>");
		
		out.close();
	}

	private void prepareErrorPage(HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		out.print("<html>");
		out.print("<body class=\"body body-s\">");
		out.print("<h1> Password rules not met</h1>");
        out.print("</body>");
        out.print("</html>");
		
		out.close();
	}

}

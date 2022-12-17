import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class LoginDispatcher
 */
@WebServlet("/LoginDispatcher")
public class LoginDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String url = "jdbc:mysql://localhost:3306/PAUsers";
    
    private static final String user = "root";
    private static final String pwd = "rootroot";
    

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO
    	
    	response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		String error = "";

		String email = request.getParameter("email1");
		String pass = request.getParameter("psw1");
		String name = "";
		
		
		
		if((!email.endsWith(".net") && !email.endsWith(".com") && !email.endsWith(".org") && !email.endsWith(".edu"))|| !email.contains("@"))
			error += "<p>Invalid Email</p>";
	
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
	    }
			
		
		
    	String sql2 = "SELECT * FROM User";
		try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql2);
			boolean exists = false;
			while(rs.next()) {
				String emails = rs.getString("email");
				if(emails.contentEquals(email))
				{
					exists = true;
					name = rs.getString("name");
				}
			}
			if(!exists) {
				error += "<p>Invalid Credentials</p>";
			}

		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		if (error.equals("")) {
			Cookie firstName = new Cookie("first_name", name);
			
			firstName.setMaxAge(60*60);
			Cookie[] cookies  = request.getCookies();
			response.addCookie(firstName);
			response.addCookie(firstName);
			Cookie[] cookies1  = request.getCookies();
			//response.setIntHeader("Refresh", 1);
			response.setContentType("text/html");
	        //request.getRequestDispatcher("index.jsp").forward(request, response);
	        response.sendRedirect("index.jsp");
		
		}
	        else
		{
			request.setAttribute("error", error);
			request.getRequestDispatcher("auth.jsp").include(request, response);
		}
    	
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.sql.*;

/**
 * Servlet implementation class RegisterDispatcher
 */
@WebServlet("/RegisterDispatcher")
public class RegisterDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String url = "jdbc:mysql://localhost:3306/PAUsers";
    private static final String user = "root";
    private static final String pwd = "rootroot"; //your secret database pwd
    private static final String url1 = "jdbc:mysql://localhost:3306/";
    
	
    /**
     * Default constructor.
     */
    public RegisterDispatcher() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

	} catch (ClassNotFoundException e) {
		e.printStackTrace();
    }
	try(Connection conn = DriverManager.getConnection(url1, user,pwd);
		         Statement stmt = conn.createStatement();
		      ) {		      
		         String sql = "CREATE DATABASE PAUsers;";
		         String sql1 = "DROP DATABASE IF EXISTS PAUsers;";
		         String sql2 = "use PAUsers;";
		         String sql3 = "create table User(\n"
		         		+ "  email varchar(50) not null unique primary key, \n"
		         		+ "  name varchar(50),\n"
		         		+ "  password varchar(50)\n"
		         		+ "  );";
		         stmt.executeUpdate(sql1);
		         stmt.executeUpdate(sql);
		         stmt.executeUpdate(sql2);
		         stmt.executeUpdate(sql3);
		         //System.out.println("Database created successfully...");   	  
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } 
		   }

    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");

	} catch (ClassNotFoundException e) {
		e.printStackTrace();
    }
    	
    	response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		String error = "";

		String email = request.getParameter("email");
		String pass = request.getParameter("psw");
		String passRep = request.getParameter("psw-repeat");
		String name = request.getParameter("first_name");
		
		boolean err = false;
		
		if((!email.endsWith(".net") && !email.endsWith(".com") && !email.endsWith(".org") && !email.endsWith(".edu"))|| !email.contains("@"))
		{	error += "<p>Invalid Email</p>"; err = true;}
		
		if(!pass.contentEquals(passRep))
		{	error += "<p>Passwords do not match</p>"; err = true; }
    	
    	String sql = "INSERT INTO User (email, name, password) VALUES (?, ?, ?)";
    	String sql2 = "SELECT * FROM User";
		try (Connection conn = DriverManager.getConnection(url, user, pwd);
			  PreparedStatement ps = conn.prepareStatement(sql);) {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql2);
			boolean exists = false;
			while(rs.next()) {
				String emails = rs.getString("email");
				if(emails.contentEquals(email))
					exists = true;
			}
			if(!exists && error == "") {
				ps.setString(1, email);				
				ps.setString(2, name);
				ps.setString(3, pass);
				int row = ps.executeUpdate(); //the number of rows affected
				//System.out.println(String.format("Row affected %d", row));
			}
			else if(exists)
			{
				error += "<p>An account with that email already exists</p>";
			}
			Statement st1 = conn.createStatement();
			ResultSet rs1 = st1.executeQuery(sql2);
			while(rs1.next()) {
				String emails = rs1.getString("email");
				String names = rs1.getString("name");
				String passes = rs1.getString("password");
				//System.out.println(emails + " " + names + " " + passes);
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
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}

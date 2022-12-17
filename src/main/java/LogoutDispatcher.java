import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

/**
 * Servlet implementation class LogoutDispatcher
 */
@WebServlet("/LogoutDispatcher")
public class LogoutDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @throws ServletException 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // TODO Remove userID cookie
    	Cookie[] cookies  = request.getCookies();
    	for (Cookie aCookie : cookies)
			if((aCookie.getName( )).equals("first_name")  )
			{
				aCookie.setMaxAge(0);
				response.addCookie(aCookie);
			}
    	//request.getRequestDispatcher("index.jsp").include(request, response);
    	response.sendRedirect("index.jsp");

    }

    /**
     * @throws ServletException 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //doGet(request, response);
    	Cookie[] cookies  = request.getCookies();
    	for (Cookie aCookie : cookies)
			if((aCookie.getName( )).equals("first_name")  )
			{
				aCookie.setMaxAge(0);
				response.addCookie(aCookie);
			}
    	request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}

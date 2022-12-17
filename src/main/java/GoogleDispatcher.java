import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.Restaurant;
import Util.RestaurantDataParser;

import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;

/**
 * Servlet implementation class GoogleDispatcher
 */
@WebServlet("/GoogleDispatcher")
public class GoogleDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private RestaurantDataParser pr = new RestaurantDataParser();;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String id = request.getParameter("rname");
        if (id == null) id = "";
        //TODO 
        ServletContext servletContext = getServletContext();
        // TODO get json file as stream, Initialize FakeYelpAPI by calling its initalize
        // method
        String path = servletContext.getRealPath("restaurant_data.json");
        pr.Init(path);
        Restaurant r = pr.getBusiness(id);
        
        
        String res= "";
		
		res+="<h6 style='font-size:20px; margin-left: 25px;'>" + r.getName() + "</h6>";
		//res += "<br />";
		
		
			String star ="";
			for(int y = 0; y< r.getRating().intValue(); y++)
			{
				star += "<i class=\"fas fa-star\"></i>";
			}
			if((double)r.getRating().intValue() < r.getRating())
			{
				star += "<i class=\"fas fa-star-half-alt\"></i>";
			}
			res += "<img src='"+ r.getImageurl() +"'height='150' style='max-height: 150px;border-radius: 8px; float: left;'>";
			res += "<h10>Address: " + r.getAddy() +"</h10> ";
			res += "<br /><br />";
			res += "<h10>"+ r.getDisplayphone()+ "</h10> ";
			res += "<br /><br />";
			res += "<h8>Categories: " + r.getCat() +"</h8> ";
			res += "<br /><br />";
			res += "<h8>Price: " + r.getPrice() +"</h8> ";
			res += "<br /><br />";
			res += "<h9>Rating: " + star +"</h9> ";
			res += "<br /><br />";
	
		
		request.setAttribute("po", res);
		request.getRequestDispatcher("details.jsp").forward(request, response);
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

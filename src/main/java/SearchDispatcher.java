
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.*;

import javax.servlet.*;
import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;


/**
 * Servlet implementation class SearchDispatcher
 */
@WebServlet("/SearchDispatcher")
public class SearchDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
   private RestaurantDataParser pr;
    public SearchDispatcher() {
    	pr = new RestaurantDataParser();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = getServletContext();
        // TODO get json file as stream, Initialize FakeYelpAPI by calling its initalize
        // method
        String path = servletContext.getRealPath("restaurant_data.json");
        pr.Init(path);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO
    	String keyWord = (String) request.getParameter("gsearch");
		if (keyWord == null) keyWord = "";
		String jl = (String)request.getParameter("jl");
		if (jl == null) jl = "";
		String cat = (String)request.getParameter("searchbar");
		if (cat == null) cat = "";
		String res= "";
		ArrayList<Restaurant> l = pr.getBusinesses(keyWord, jl, cat);
		
		res+="<h6 style='font-size:20px'>Results for " + keyWord + " in " + cat + "</h6>";
		res += "<hr />";
		
		for(Restaurant x : l)
		{
			String star ="";
			for(int y = 0; y< x.getRating().intValue(); y++)
			{
				star += "<i class=\"fas fa-star\"></i>";
			}
			if((double)x.getRating().intValue() < x.getRating())
			{
				star += "<i class=\"fas fa-star-half-alt\"></i>";
			}
			res += "<img src='"+ x.getImageurl() +"'height='150' style='max-height: 151px;border-radius: 8px; float: left;'>";
			res += "<h7 style = 'color:-webkit-link;text-decoration:underline;font-size: 16px;float: left;'><form action ='GoogleDispatcher' method='GET'><label for='fname'></label><input type='text' id='rname' name='rname' value ='" + x.getId()+"' style=\"display:none\"><input type='submit' id='dets' value='" + x.getName() +"'></i></input></form></h7> ";
			res += "<br /><br />";
			res += "<h8>Price: " + x.getPrice() +"</h8> ";
			res += "<br /><br />";
			res += "<h8>Review Count: " + x.getReviewcount() +"</h8> ";
			res += "<br /><br />";
			res += "<h9>Rating: " + star +"</h9> ";
			res += "<br /><br />";
			res += "<h10><a href='" +x.getUrl()+"'><b>Yelp Link</b></a> </h10> ";
			//res += "<br /><br /><br /><br /><br /><br /><br /><br /><br /><hr /><br />";
			res += "<hr />";
		}
		
		request.setAttribute("se", res);
		request.getRequestDispatcher("search.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
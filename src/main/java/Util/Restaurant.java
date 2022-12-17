package Util;
import java.util.*;
/**
 * The class used to model a business
 */
public class Restaurant {
    private String id = "";
    private String name = "";
    private String image_url = "";
    private String url = "";
    private int review_count = 0;
    private Double rating = 0.0;
    private String price = "";
    private String display_phone = "";
    private String addy= "";
    private String cats = "";
    
    public Restaurant(String ida, String namea, String imga, String u, int rev,
    		Double rat,
    		String pri,
    		String disp, String add) {
        //TODO Initialization code
    	
    	id = ida;
    	name = namea;
    	image_url = imga;
    	url = u;
    	review_count = rev;
    	rating = rat;
    	display_phone = disp;
    	price = pri;
    	addy = add;
    }
    
    public String getId() {
    	return id;
    }
    public void setCat(HashSet<String> cat) {
    	for(String x: cat)
    	{
    		cats += x + ", ";
    	}
    	cats = cats.substring(0,cats.length()-2);
    }
    
    public String getCat() {
    	return cats;
    }
    
    public String getAddy() {
    	return addy;
    }
    

    public String getName() {
    	return name;
    }
    
    
    public String getImageurl() {
    	return image_url;
    }
    
  
    
    public String getUrl() {
    	return url;
    }
    
    public int getReviewcount() {
    	return review_count;
    }
    
  
    
    public Double getRating() {
    	return rating;
    }
    
    
    public String getDisplayphone() {
    	return display_phone;
    }
    

	public String getPrice() {
		// TODO Auto-generated method stub
		return price;
	}
    
    
    
    //TODO Add Getters (No Setters as the business does not change in our assignment once constructed)
}


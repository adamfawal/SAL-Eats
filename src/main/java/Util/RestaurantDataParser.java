package Util;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

/**
 * A class that pretends to be the Yelp API
 */
public class RestaurantDataParser { //FakeYelpAPI
    private static Boolean ready = false;
    private  Business results;
    /**
     * Initializes the DB with json data
     *
     * @param responseString the Yelp json string
     */
    public static void Init(String responseString) {
        if (ready) {
            return;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //TODO check if you've done the initialization
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ready = true;
        
       
        String url = "jdbc:mysql://localhost:3306/businesses";
        String user = "root";
        String pwd = "rootroot";
        String url1 = "jdbc:mysql://localhost:3306/";

        
        try(Connection conn = DriverManager.getConnection(url1, user,pwd);
		         Statement stmt = conn.createStatement();
		      ) {		      
		         String sql = "CREATE DATABASE businesses;";
		         String sql1 = "DROP DATABASE IF EXISTS businesses;";
		         String sql2 = "use businesses;";
		         String sql3 = "create table RDetails(\n"
		         		+ "id varchar(100) not null unique primary key,\n"
		         		+ "image_url varchar(1000),\n"
		         		+ "display_address varchar(200),\n"
		         		+ "phone varchar(50),\n"
		         		+ "price varchar(50),\n"
		         		+ "url varchar(1000)\n"
		         		+ ");";
		         
		         String sql4 ="create table Rating(\n"
		         		+ "id varchar(100) not null unique primary key,\n"
		         		+ "review_count INT,\n"
		         		+ "rating double\n"
		         		+ ");";
		         String sql5 ="create table Restaurant(\n"
		         		+ "id varchar(100) not null unique primary key,\n"
		         		+ "name varchar(50),\n"
		         		+ "foreign key (id) references RDetails(id),\n"
		         		+ "foreign key (id) references Rating(id)\n"
		         		+ ");";
		         String sql6 ="create table Category(\n"
		         		+ "num INT primary key auto_increment,\n"
		         		+ "id varchar(100) not null,\n"
		         		+ "title varchar(50),\n"
		         		+ "foreign key (id) references Restaurant(id)\n"
		         		+ ");";
		         stmt.executeUpdate(sql1);
		         stmt.executeUpdate(sql);
		         stmt.executeUpdate(sql2);
		         stmt.executeUpdate(sql3);
		         stmt.executeUpdate(sql4);
		         stmt.executeUpdate(sql5);
		         stmt.executeUpdate(sql6);
		         //System.out.println("Database created successfully...");   	  
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } 
        
        
        String sql = "INSERT INTO RDetails (id, image_url, display_address, phone, price, url) VALUES (?, ?, ?, ?, ?, ?)";
    	String sql2 = "INSERT INTO Rating (id, review_count, rating) VALUES (?, ?, ?)";
    	String sql3 = "INSERT INTO Restaurant (id, name) VALUES (?, ?)";
    	String sql4 = "INSERT INTO Category (num, id, title) VALUES (?, ?, ?)";
        Gson gson = new Gson();
		BufferedReader br = null;
		boolean b = true;
		//while(b) {
		String fileName = responseString;
		
		try {
			br = new BufferedReader(new FileReader(fileName));
			BusinessHelper result = gson.fromJson(br, BusinessHelper.class);
			//results = result;
			//result.print();
			if(result != null) {
				//b = false;
				int count = 0;
		    	for(Business x : result.getBusinesses()) {
		    		count++;
					if(b) {
					try (Connection conn = DriverManager.getConnection(url, user, pwd);
						PreparedStatement ps1 = conn.prepareStatement(sql);
						PreparedStatement ps2 = conn.prepareStatement(sql2);
						PreparedStatement ps3 = conn.prepareStatement(sql3);
						PreparedStatement ps4 = conn.prepareStatement(sql4);) {
							ps1.setString(1, x.getId());	
							//System.out.println((x.getImageUrl()).length());
							ps1.setString(2, x.getImageUrl());
							ps1.setString(3, x.getLocation().getAddy());
							ps1.setString(4, x.getDisplayPhone());
							ps1.setString(5, x.getPrice());
							ps1.setString(6, x.getUrl());
							ps1.executeUpdate();
							
							//DBTablePrinter.printTable(conn, "RDetails");
							
							ps2.setString(1, x.getId());
							ps2.setInt(2, x.getReviewCount());
							ps2.setDouble(3, x.getRating());
							ps2.executeUpdate();
							//DBTablePrinter.printTable(conn, "Rating");
							
							ps3.setString(1, x.getId());
							ps3.setString(2, x.getName());
							ps3.executeUpdate();
							//DBTablePrinter.printTable(conn, "Restaurant");
							
							for(Category y : x.getCategories()) {
								ps4.setInt(1, count);
								ps4.setString(2, x.getId());
								ps4.setString(3, y.getTitle());
								count++;
								ps4.executeUpdate();
							}
							//DBTablePrinter.printTable(conn, "Category");
							

							
							//int row = ps.executeUpdate(); //the number of rows affected					
	
					} catch (SQLException sqle) {
						System.out.println ("SQLException: " + sqle.getMessage());
						b = false;
					}
		    	}
				}
				
				
				
				
				
			}
		}
		catch (FileNotFoundException e){
			System.out.println("The file " + fileName + " could not be found.\n");
			return;
		}
		catch(IllegalStateException e) {
			System.out.println("The file " + fileName + " has a wrong format1.\n");
		}
		catch(com.google.gson.JsonSyntaxException e) {
			System.out.println("The file " + fileName + " has a wrong format2.\n");
		}
		finally {
			if(br != null) {
				try {
					br.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		//}
        //TODO get businessHelper array from json
        //TODO iterate the businessHelper array and insert every business into the DB
    }

    public static Restaurant getBusiness(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO return business based on id
        String url = "jdbc:mysql://localhost:3306/businesses";
        String user = "root";
        String pwd = "rootroot";
        String idx = "'" + id + "'";
        HashSet<String> cat = new HashSet<String>();
        String sql = "SELECT * FROM Restaurant r, Category c, Rating a, RDetails d WHERE d.id = "
        		+ idx +" AND r.id = c.id AND r.id = a.id AND r.id = d.id;";        
        try (Connection conn = DriverManager.getConnection(url, user, pwd);
        		Statement st = conn.createStatement();) {
				
				
				ResultSet rr = st.executeQuery(sql);
				Restaurant r = null;
				while(rr.next()) {
					
					r = new Restaurant(rr.getString("id"), rr.getString("name"),rr.getString("image_url"),rr.getString("url"),
					rr.getInt("review_count"),	rr.getDouble("rating"),rr.getString("price"),
					rr.getString("phone"),rr.getString("display_address"));
					cat.add(rr.getString("title"));
					}
				if(r!= null)
					r.setCat(cat);
				return r;
					

			} catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());

			
			}
        return null;
        
    }

    /**
     * @param keyWord    the search keyword
     * @param sort       the sort option (price, review count, rating)
     * @param searchType search in category or name
     * @return the list of business matching the criteria
     */
    public static ArrayList<Restaurant> getBusinesses(String keyWord, String sort, String searchType) {
        ArrayList<Restaurant> R = new ArrayList<Restaurant>();
        Set<String> ids = new HashSet<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO get list of business based on the param
        String url = "jdbc:mysql://localhost:3306/businesses";
        String user = "root";
        String pwd = "rootroot";
        String sql = "SELECT * FROM Restaurant r, Category c, Rating a, RDetails d WHERE ? = ? AND r.id = c.id AND r.id = a.id AND r.id = d.id ORDER BY ?;";
        String newKey = "'" + keyWord + "%'";
        String sqlprint = "SELECT * FROM Restaurant r, Category c, Rating a, RDetails d WHERE "
        		+ searchType +" LIKE " + newKey +" AND r.id = c.id AND r.id = a.id AND r.id = d.id ORDER BY ";
        		//+ "d.price;";
       // System.out.println(sqlprint);
        try (Connection conn = DriverManager.getConnection(url, user, pwd);
				PreparedStatement ps = conn.prepareStatement(sql);
        		Statement st = conn.createStatement();) {
				if(searchType.contentEquals("category"))
				{
					//System.out.println("here");
					ps.setString(1, "title");
					searchType = "title";
				}
				else
				{
					//System.out.println("here1");
					ps.setString(1, "name");
					searchType = "name";
				}
				sqlprint = "SELECT * FROM Restaurant r, Category c, Rating a, RDetails d WHERE "
		        		+ searchType +" LIKE " + newKey +" AND r.id = c.id AND r.id = a.id AND r.id = d.id ORDER BY ";
				ps.setString(2, newKey);
				
				if(sort.contentEquals("price"))
				{
					//System.out.println("here2");
					ps.setString(3, "d.price");
					sqlprint += "d.price ASC;";
				}
				else if(sort.contentEquals("review_count"))
				{
					//System.out.println("here3");
					ps.setString(3, "a.review_count");
					sqlprint += "a.review_count DESC;";
				}
				else
				{
					//System.out.println("here4");
					ps.setString(3, "a.rating");
					sqlprint += "a.rating DESC;";
				}
				//DBTablePrinter.printTable(conn, "Category");
				
				ResultSet rr = st.executeQuery(sqlprint);
				while(rr.next()) {
					if(!ids.contains(rr.getString("id"))) {
					ids.add(rr.getString("id"));
					R.add(new Restaurant(rr.getString("id"), rr.getString("name"),rr.getString("image_url"),rr.getString("url"),
					rr.getInt("review_count"),	rr.getDouble("rating"),rr.getString("price"),
					rr.getString("phone"),rr.getString("display_address")) );
					}
					}
					

			} catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());

			
			}
        return R;

    }
}

//Code adapted from https://stackoverflow.com/questions/23070298/get-nested-json-object-with-gson-using-retrofit
//class BusinessDeserializer implements JsonDeserializer<Business> {
//    @Override
//    public Business deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
//        JsonElement content = je.getAsJsonObject();
//        return new Gson().fromJson(content, Business.class);
//    }
//}
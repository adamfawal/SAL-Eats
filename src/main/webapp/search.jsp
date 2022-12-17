<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
            href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
            rel="stylesheet">
    <link rel="stylesheet" href="index.css">
    <script src="https://kit.fontawesome.com/3204349982.js"
            crossorigin="anonymous"></script>

    <%
        //TODO perform search
        String se = (String) request.getAttribute("se");
			if (se == null || se.contentEquals("")) se = "No results found";
			
			
			boolean cooks = false;
	        Cookie[] cookies  = request.getCookies();
	        if(cookies.length > 1)
	        	cooks = true;
	        
	        String log ="";
	        String user = "";
	        String keyWord = (String) request.getParameter("gsearch");
			if (keyWord == null) keyWord = "";
			String jl = (String)request.getParameter("jl");
			if (jl == null) jl = "";
			String cat = (String)request.getParameter("searchbar");
			if (cat == null) cat = "";
	        
	        if(cooks)
			{
				/* for (Cookie aCookie : cookies)
				{
					System.out.println("Name : " + aCookie.getName( ) + ",  ");
					System.out.println("Value: " + aCookie.getValue( ) + " <br/>");
				} */
				user = "Hi, " + cookies[1].getValue();
				log = "<form action ='LogoutDispatcher' method='GET'><input type='submit' id='log' value='Logout'></i></input></form>";
			}
			else
			{
				//System.out.println("none");
				log = "<a href='auth.jsp'><b>Login / Register</b></a>";
			}
				
		
        //TODO check if logged in
    %>
    <style>
        .head{
        display:inline;
        
        }
        </style>
</head>
<body>

  			<div class="head">
       <h1><a href="index.jsp">SalEats!</a> </h1>
       <h1 id="hi" style="display: inline; font-family: 'Roboto', sans serif; font-size: 12px;  margin-top: 1.5%;
    	color: gray;"><%=user %></h1>
       <h3><%=log %> </h3>
       <h2><a href="index.jsp"><b>Home</b></a> </h2> 
        </div>
        
        <br /><br /><br />
        <br />
        <hr />
   
        
        <form action = "SearchDispatcher" method = "GET">
        
       
        <label for="searchbar"></label>
		<select id="searchbar" name="searchbar">
		<option value="category">Category</option>
		<option value="name">Name</option>
		</select>
		
        
        
        <label for="gsearch"></label>
  		<input type="search" placeholder="Search" id="gsearch" name="gsearch" value = <%=keyWord%>>
  		<button type="submit" class="search" ><i class="fas fa-search"></i></button>
  		<input type="radio" id="price" name="jl" value="price" required>
		<label for="price">Price</label>
		<input type="radio" id="rating" name="jl" value="rating">
		<label for="rating">Rating</label>
		<input type="radio" id="reviewcount" name="jl" value="review_count">
		<label for="reviewcount">Review Count</label>
        </form>
        

<%=se %>
</body>
</html>
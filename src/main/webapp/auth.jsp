<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="301645777112-2rlc9gth0f5d4reimjcm9bf0kj7ahec0.apps.googleusercontent.com"
          name="google-signin-client_id">
    <title>Login / Register</title>
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link crossorigin href="https://fonts.gstatic.com" rel="preconnect">
    <link
            href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
            rel="stylesheet">
    <script crossorigin="anonymous"
            src="https://kit.fontawesome.com/3204349982.js"></script>
    <script async defer src="https://apis.google.com/js/platform.js"></script>
    <link href="index.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto"
          rel="stylesheet" type="text/css">
    <script src="https://apis.google.com/js/api:client.js"></script>

</head>
<body>
<!-- TODO -->
<div style="color:black; text-align: center; background-color: red; opacity: 70%; margin-top: 0%; padding: 1px;">
			<% String er = (String) request.getAttribute("error");
			if (er != null) out.println(er);
			%>
	</div>
	<% 
		String emaillogin1 = (String) request.getParameter("email1");
		if (emaillogin1 == null) emaillogin1 = "";

		String pswlogin1 = (String) request.getParameter("psw1");
		if (pswlogin1 == null) pswlogin1 = "";
		String emaillogin = (String) request.getParameter("email");
		if (emaillogin == null) emaillogin = "";

		String pswlogin = (String) request.getParameter("psw");
		if (pswlogin == null) pswlogin = "";
		
		String first_name = (String) request.getParameter("first_name");
		if (first_name == null) first_name = "";
	%>
		
	



<h1><a href="index.jsp">SalEats!</a> </h1>
       <h3><a href="auth.jsp">Login / Register</a> </h3>
       <h2><a href="index.jsp">Home</a> </h2> 
        
        
        <br /><br /><br />
        <br />
        <hr />
         
  <form action = "LoginDispatcher" method = "GET">
  
  <div class="container1">
    <br /> <br /> <br /> <br />
   <h4>Login</h4>
  <label for="email1" style=""><b>Email</b></label><br />
    <input type="text"  name="email1" id="email1" required><br /><br />
    
    <label for="psw1"><b>Password</b></label><br />
    <input type="password"  name="psw1" id="psw1" required style="width:200%"><br /><br />

   <button type="submit" class="submit1"><i class="fas fa-sign-in-alt"></i> Sign In</button>

<!--  <input type = "submit" value = "Submit" /> -->
 </div>
  </form>
  <form action = "RegisterDispatcher" method = "GET">
  <div class="container">
   <h5>Register</h5>
  <label for="email" style="width:100%" ><b>Email</b></label><br />
    <input type="text" name="email" id="email" required style="width:100%" ><br /><br />
    
    <label for="first_name">Name</label>
	<input type = "text"  name = "first_name" required><br />
    
    <label for="psw"><b>Password</b></label><br />
    <input type="password" name="psw" id="psw" required><br /><br />

    <label for="psw-repeat"><b>Repeat Password</b></label><br />
    <input type="password"  name="psw-repeat" id="psw-repeat" required><br /><br />
    <hr>
	<input type ="checkbox" name="habit1" value="reading"  required />I have read and agree to all terms and conditions of SalEats
    <button type="submit" class="registerbtn"><i class="fas fa-user-plus"></i> Register</button>
  </div>
  
  
  
 
  

 </form>
  
        
</body>
</html>
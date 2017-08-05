
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
            if(session.getAttribute("username")==null)
            {
                response.sendRedirect("index.jsp");
            }
            
            
        %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "css/form_style.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Header</title>
    </head>
    <body>
        <%
            
            String username = (String)session.getAttribute("username");
            String usertype = (String)session.getAttribute("usertype");
            if(username!=null)
            {
               // Logged in
                out.print("<div class=\"heading\">");
                out.print("&nbspHey "+username+" ! <div class=\"heading2\" style=\"float:right\">Computer Help Desk Support &nbsp&nbsp</div></div>");
                
                out.print("<ul><li> <a href=\"index.jsp\">Home</a> </li>");
                out.print(" <li><a href=\"welcome.jsp\">Dashboard</a></li> ");
                if(usertype.equalsIgnoreCase("7") || usertype.equalsIgnoreCase("6") )
                {
                  out.print(" <li><a href=\"signup.jsp\">Add Users</a></li> ");  
                }
                out.print("\n");
            }
            else
            {
                // logged out user
                out.print(" <li><a href=\"index.jsp\">HOME</a> </li>");
                out.print(" <li><a href=\"login.jsp\">Login</a> </li>");
                out.print(" <li><a href=\"signup.jsp\">SignUp</a></li> ");
                out.print("\n");
            }
        %>

       
    <li><a href="ticketaddition.jsp" > Launch Ticket </a> </li>
    <li><a href="welcome.jsp" > Display Tickets </a> </li>
        <%
            if(usertype.equalsIgnoreCase("7")||usertype.equalsIgnoreCase("6"))
            {
        %><li> <a href="userdisplay.jsp" > Display Users </a></li> <%
            }
        %>
        <%
            if(usertype.equalsIgnoreCase("7")||usertype.equalsIgnoreCase("6")||usertype.equalsIgnoreCase("5"))
            {
               %> 
    <li><a href="addproduct.jsp" > Add Products </a> </li>
    <li><a href="displayproduct.jsp" > Display Products </a></li>
               <%
            }
        %>
    <li style="float:right"><a href="logout">Logout</a></li>        
    </ul>
        
        <%
            if(session.getAttribute("usertype")!=null)
            {
            usertype = (String)session.getAttribute("usertype");
            if(usertype.equalsIgnoreCase("6")||usertype.equalsIgnoreCase("7")){               
                ;
            }
            }
            else if(session.getAttribute("username")!=null)
            {
                response.sendRedirect("welcome.jsp");
            }
            
            String UID = request.getParameter("userID");
            String oldusername = request.getParameter("username");
            String oldpassword = request.getParameter("password");
            String oldusertype = request.getParameter("usertype");
            String oldphoneno = request.getParameter("phoneno");
            String oldfirstname = request.getParameter("firstname");
            String oldlastname = request.getParameter("lastname");
            String oldemail = request.getParameter("email");
            
        %>
        
        <div class="container">
        <h2>Edit User Details -</h2><br/>
        <form action="edituser" method="post">
            Username : </br>
            <input type="text" name="username" value="<%out.print(oldusername);%>" required/><br>
            Password : </br>
            <input type="password" name="password" value="<%out.print(oldpassword);%>" required /><br>
            User Type : </br> 
            <select class="drop-down" name="usertype" required>  
                <option disabled selected > Select new user type</option>
                        <option value="1">Regular Customer</option>  
                        <option value="2">Bulk Customer</option>  
                        <option value="3">Prime Customer</option> 
                        <%
                        if(usertype.equalsIgnoreCase("6")||usertype.equalsIgnoreCase("7"))
                           {%>
                            <option value="4">Technician</option>  
                            <option value="5">Senior Technician</option>  
                            <%
                                if(usertype.equalsIgnoreCase("7"))
                                {
                            %>
                            <option value="6">Supervisor</option>  
                            <option value="7">Administrator</option>  
                           <%}}
                        %>
                       </select>  
            First Name : </br>
            <input type="text" name="firstname" value="<%out.print(oldfirstname);%>" required/><br>
            Last Name : </br>
            <input type="text" name="lastname" value="<%out.print(oldlastname);%>" required/><br>
            Email ID : </br>
            <input type="email" name="email" value="<%out.print(oldemail);%>" required/><br>
            Phone Number : </br>
            <input type="text" name="phonenumber" value="<%out.print(oldphoneno);%>" required/>
            <input type="hidden" name="userID" value="<%out.print(UID);%>" /><br>
            <input type="hidden" name="oldusername" value="<%out.print(oldusername);%>" />
            <input type="hidden" name="oldpassword" value="<%out.print(oldpassword);%>" />
            <input type="hidden" name="oldusertype" value="<%out.print(oldusertype);%>" />
            <button type="submit" value="register"> Update Details </button>
        </form>
    </div>
        <div class="footer"> Computer HelpDesk Support. © 2017 LNMIIT Developers </div>
    </body>
</html>
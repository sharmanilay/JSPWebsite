
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
            }
            else
            {
                // logged out user
                out.print("<ul>");
                out.print(" <li><a href=\"index.jsp\">HOME</a> </li>");
                out.print(" <li><a href=\"login.jsp\">Login</a> </li>");
                out.print(" <li><a href=\"signup.jsp\">SignUp</a></li> </ul>");
                usertype="9";
            }
        %>

       

        <%// header over .... here   
            
            if(session.getAttribute("usertype")!=null)
            {
            if(usertype.equalsIgnoreCase("6")||usertype.equalsIgnoreCase("7")){               
                ;
            }
            }
            else if(session.getAttribute("username")!=null)
            {
                response.sendRedirect("welcome.jsp");
            }
        %>
        
        <div class="container">
        <% if(usertype.equalsIgnoreCase("7")||usertype.equalsIgnoreCase("6"))
            {
                %><h2>Add Users -</h2><br/>
          <%}else {%>
        <h2>REGISTER HERE -</h2>
        <% } %> 
        <form action="signup" method="post">
            <input type="text" placeholder="Username" name="username" required/><br>
            <input type="password" placeholder="Password" name="password" required /><br>
            <select name="usertype" class="drop-down" required>  
                <option disabled selected>Select User Type </option>
                        <option value="1" >Regular Customer</option>  
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
                       </select>  </br>
            <input type="text" placeholder="First Name" name="firstname" required/></br>
            <input type="text" placeholder="Last Name" name="lastname"/></br>
            <input type="email" placeholder="Email Address" name="email" required/></br>
            <input type="text" placeholder="Phone Number" name="phonenumber" required/></br>
            <button type="submit" value="register"> Register </button>
        </form>
  
        </div>
        <div class="footer"> Computer HelpDesk Support. © 2017 LNMIIT Developers </div>
    </body>
</html>

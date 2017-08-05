
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "css/index_style.css">
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

        <div class="container">
        <h3>Computer Help Desk Support Website Front</h3>
        <div class="containerimg"> <img src="css/index.png"> 
    <%if(username==null)
    {%>    <a class="login" href="login.jsp">Login</a>
        <a class ="signup" href="signup.jsp">Signup</a>
    <%}%>
        </div>
        </div>
        <div class="footer"> Computer HelpDesk Support. © 2017 LNMIIT Developers </div>
    </body>
</html>
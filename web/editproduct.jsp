
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
            if(session.getAttribute("username")==null)
            {
                response.sendRedirect("index.jsp");
            }
            if(request.getParameter("pID")==null)
            {
                response.sendRedirect("welcome.jsp");
            }
            
                String productname = request.getParameter("productname");
                String pID = request.getParameter("pID");
                String producttype = request.getParameter("producttype");
           
    %>
    
       <div class="container">
        <h2> Edit Product -</h2><br/>
        <form action="editproduct" method="post">
            Product Name : </br><input type="text" name="productname" value="<%out.print(productname);%>" required /><br>
            Product Type : </br><select class="drop-down" name="producttype" required>  
                        <option disabled selected> select </option>
                        <option value="1">I/O Device</option>  
                        <option value="2">Storage Device</option>  
                        <option value="3">Processor</option>
                        <option value="4">Network</option>  
                        <option value="5">CPU and Motherboard</option>  
                        <option value="6">Other Appliances</option> 
                       </select>  
            <input type="hidden" name="pID" value="<%out.print(pID);%>"/>
            <button type="submit" value="submit"> Save Changes </button>
        </form>    
       </div>
        <div class="footer"> Computer HelpDesk Support. © 2017 LNMIIT Developers </div>
    </body>
</html>
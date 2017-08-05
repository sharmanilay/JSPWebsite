   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "css/login_form_style.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Header</title>
    </head>
    <body>
        <%
            String username = (String)session.getAttribute("username");
            String usertype = (String)session.getAttribute("usertype");
            if(username!=null)
            {
               response.sendRedirect("welcome.jsp");
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

       
        <div class="modal-content">
        <div class="imgcontainer">
            <h2>Login</h2>
        </div>
        <div class="container">
        <form action="logincheck" method="post">
           
            <input type="text" placeholder="Enter Username" name="username" id="name" required>
           
            <input type="password" placeholder="Enter Password" name="password" id="password" required>
            <button type="submit" name="submit">Login</button>
        </form>
        </div>
        </div>
       
        <div class="footer">
         Computer HelpDesk Support. © 2017 LNMIIT Developers.   
        </div>
     </body>
</html>


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;

public class userdisplay extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
  
        try {
            
            HttpSession session =request.getSession(); 
            String Utype = (String)session.getAttribute("usertype");
            
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project"; 
            Connection con = DriverManager.getConnection(url,"root","root");
            Statement st = con.createStatement();
            String query = "select * from user u, login l where u.userID = l.userID";
            ResultSet rs = st.executeQuery(query);
            
            out.print(" <h2> Users : &nbsp&nbsp&nbsp <a class=\"button\" href=\"signup.jsp\" > Add New User</a></h2> "
                    + "<table>"
                    + "<tr>"
                    + "<th><b> User ID </b></th>"
                    + "<th><b> UserName </b></th>"
                    + "<th><b> FirstName </b></th>"
                    + "<th><b> LastName </b></th>"
                    + "<th><b> Email </b></th>"
                    + "<th><b> PhoneNo. </b></th>"
                    + "<th><b> UserType </b></th>"
                    + "<th><b> Edit User </b></th>"
                    + "<th><b> Tickets </b></th>"
                    + "<th><b> Delete User </b></th>"
                    + "</tr>" 
            );
            while(rs.next()) {
                int UID = rs.getInt("userID");
                String uID = Integer.toString(UID);
                int usertype = rs.getInt("usertype");
                String utype = Integer.toString(usertype);
                String email = rs.getString("email");
                String phoneno = rs.getString("phonenumber");
                String firstname=rs.getString("firstname");
                String lastname=rs.getString("lastname");
                String username=rs.getString("username");
                String password=rs.getString("password");
                if((Utype.equalsIgnoreCase("6")&&usertype==7) || (Utype.equalsIgnoreCase("6")&& usertype==6))
                {
                    continue;
                }
                out.print(""
                        + "<tr>"
                        + "<td> "+UID+"</td>"
                        + "<td> "+username+"</td>"
                        + "<td> "+firstname+"</td>"
                        + "<td> "+lastname+"</td>"
                        + "<td> "+email+"</td>"
                        + "<td> "+phoneno+"</td>"
                        + "<td> "+usertype+"</td>");
                
                out.print(""
                        
                        + "<td> <a class = \"button\" href=\"edituser.jsp?userID="+uID+"&username="+username+"&usertype="+utype+"&password="+password+"&firstname="+firstname+"&lastname="+lastname+"&email="+email+"&phoneno="+phoneno+" \" > Edit </a></td>"
                      + "<td> <a class = \"button\" href=\"userdisplayticket.jsp?userID="+uID+"\" > Tickets </a></td>"
                       + "<td> <a class = \"button\" href=\"deleteuser.jsp?userID="+uID+"&username="+username+"\" > Delete </a></td>"
                        + "</tr>"
                );
            }
            out.print("</table>");
        }
        catch (Exception e) {
            
            out.print(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
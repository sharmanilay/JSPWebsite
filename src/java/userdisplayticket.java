
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class userdisplayticket extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
  
        int userID = Integer.parseInt(request.getParameter("userID"));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project"; 
            Connection con = DriverManager.getConnection(url,"root","root");
            Statement st = con.createStatement();
            String query = "select tID,theading,tdescription,status,producttype,productname from ticket t, product p where t.pID=p.pID and userID="+userID;
            ResultSet rs = st.executeQuery(query);
            
            out.print(" <h2> Tickets : User ID = "+userID+"&nbsp&nbsp&nbsp  <a class=\"button\" href=\"userdisplay.jsp\" > Back </a></h2>"
                    + "<table>"
                    + "<tr>"
                    + "<th><b> User ID </b></th>"
                    + "<th><b> Ticket ID </b></th>"
                    + "<th><b> Product Name </b></th>"
                    + "<th><b> Product Type </b></th>"
                    + "<th><b> Heading </b></th>"
                    + "<th><b> Description </b></th>"
                    + "<th><b> Status </b></th>"
                    + "<th><b> Edit Ticket </b></th>"
                    + "<th><b> Edit Status </b></th>"
                    + "<th><b> Delete </b></th>"
                    + "</tr>" 
            );
            while(rs.next()) {
                int tID = rs.getInt("tID");
                int status = rs.getInt("status");
                String thead = rs.getString("theading");
                String tdesc = rs.getString("tdescription");
                String productname=rs.getString("productname");
                String producttype=rs.getString("producttype");
                
                out.print(""
                        + "<tr>"
                        + "<td> "+userID+"</td>"
                        + "<td> "+tID+"</td>"
                        + "<td> "+productname+"</td>"
                        + "<td> "+producttype+"</td>"
                        + "<td> "+thead+"</td>"
                        + "<td> "+tdesc+"</td>");
                if(status==1){
                    out.print("<td> Pending Review </td>");
                }else if(status==2){
                    out.print("<td> Unresolved </td>");
                }else if(status==3){
                    out.print("<td> Resolved</td>");
                }
                out.print(""
                        
                        + "<td> <a class=\"button\" href=\"editticket.jsp?tID="+tID+"\" > Edit ticket </a></td>"
                      + "<td> <a class=\"button\" href=\"editstatus.jsp?tID="+tID+"\" > Edit status </a></td>"
                       + "<td> <a class=\"button\" href=\"deleteticket.jsp?tID="+tID+"&productname="+productname+"\" > Delete </a></td>"
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
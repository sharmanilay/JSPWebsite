
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class ticketaddition extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        int ptype = Integer.parseInt(request.getParameter("producttype"));

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project"; 
            Connection con = DriverManager.getConnection(url,"root","root");
            Statement st = con.createStatement();
            String productnames = "select * from product where producttype="+ptype;
            ResultSet rs = st.executeQuery(productnames);
            out.print("<h2>File a Ticket :</h2></br>"
                    + "<form action=\"ticketaddition2\" method=\"post\">"
                    + "<select name=\"productID\" class=\"drop-down\" required>"
            );
         
                out.print("<option selected disabled> Select Product</option>");

            while(rs.next()) {
                out.print("<option value=\""+rs.getInt("pID")+"\">"+rs.getString("productname")+"</option>");
            }
            
            out.print(""
                    + "</select>"
                    + "<input type=\"text\" name=\"theading\" placeholder=\"Ticket Heading\" required />"
                    + "<input type=\"text\" name=\"tdescription\" placeholder=\"Ticket Description\" required />"
                    + "<button type=\"submit\" name=\"add ticket\">Add Ticket</button>"
            );
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

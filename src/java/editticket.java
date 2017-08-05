
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class editticket extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String tID = request.getParameter("tID");
        int ptype = Integer.parseInt(request.getParameter("newproducttype"));

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project"; 
            Connection con = DriverManager.getConnection(url,"root","root");
            Statement st = con.createStatement();
            Statement ss = con.createStatement();
            String productnames = "select * from product where producttype="+ptype;
            String query = "select * from ticket where tID="+tID;
            ResultSet rt = st.executeQuery(query);
            ResultSet rs = ss.executeQuery(productnames);
            out.print("<h2>Edit Ticket Details :</h2></br>"
                    + "<form action=\"editticket2\" method=\"post\">"
                    + "Product Name : <select name=\"productname\" class=\"drop-down\" required>"
            );
            
                out.print("<option disabled selected> select </option>");

            while(rs.next()) {
                out.print("<option value=\""+rs.getInt("pID")+"\">"+rs.getString("productname")+"</option>");
            }
            
            out.print("</select>");
            rt.next();
            out.print(""
                    + "Ticket Heading : </br><input type=\"text\" name=\"theading\" value=\""+rt.getString("theading")+"\" required />"
                    + "Ticket Description : </br><input type=\"text\" name=\"tdescription\" value=\""+rt.getString("tdescription")+"\" required />"
                    + "<input type=\"hidden\" name=\"tID\" value="+tID+" />"
                    + "<button type=\"submit\" name=\"add ticket\">Save Changes</button>"
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

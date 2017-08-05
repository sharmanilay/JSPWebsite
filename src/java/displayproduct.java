import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class displayproduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project"; 
            Connection con = DriverManager.getConnection(url,"root","root");
            Statement st = con.createStatement();
            String query = "select * from product";
            ResultSet rs = st.executeQuery(query);
            
            out.print(" <h2> Products : &nbsp&nbsp&nbsp <a class=\"button\" href=\"addproduct.jsp\" > Add New Product</a></h2>"
                    + "<table>"
                    + "<tr>"
                    + "<th><b> Product Name </b></th>"
                    + "<th><b> Product Type </b></th>"
                    + "<th><b> Product ID </b></th>"
                    + "<th><b> Edit </b></th>"
                    + "<th><b> Delete </b></th>"
                    + "</tr>" 
            );
            while(rs.next()) {
                String productname = rs.getString("productname");
                int producttype = rs.getInt("producttype");
                int pID = rs.getInt("pID");
                out.print(""
                        + "<tr>"
                        + "<td> "+productname+"</td>");
                if(producttype==1){                
                    out.print("<td>I/O Device</td>");
                }else if(producttype==2){
                    out.print("<td>Storage Device</td>");
                }else if(producttype==3){
                    out.print("<td>Processor</td>");
                }else if(producttype==4){
                    out.print("<td>Network</td>");
                }else if(producttype==5){
                    out.print("<td>CPU and Motherboard</td>");
                }else if(producttype==6){
                    out.print("<td>Other Appliances</td>");
                }
                out.print( "<td> "+pID+"</td>"
                        + "<td> <a class = \"button\" href=\"editproduct.jsp?pID="+pID+"&productname="+productname+"&pproducttype="+producttype+"\" > Edit </a></td>"
                        + "<td> <a class = \"button\" href=\"deleteproduct.jsp?pID="+pID+"&productname="+productname+"&pproducttype="+producttype+"\" > Delete </a></td>"
                        + "</tr>"
                );
            }
            out.print("</table>");
        }
        catch (Exception e) {
            
            out.print("<p><b> No Product Found ! </b>");
            response.sendRedirect("login.jsp");
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


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;

public class ticketdisplay extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
  
        try {
            HttpSession session =request.getSession(); 
            String usertype = (String)session.getAttribute("usertype");
            int userID = Integer.parseInt((String)session.getAttribute("userID"));
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project"; 
            Connection con = DriverManager.getConnection(url,"root","root");
            Statement st = con.createStatement();
            String query; 
            if(usertype.equalsIgnoreCase("7") || usertype.equalsIgnoreCase("6") || usertype.equalsIgnoreCase("5")|| usertype.equalsIgnoreCase("4") ){
            query= "select tID,theading,tdescription,userID,status,producttype,productname from ticket t, product p where t.pID=p.pID;";
            }else{
                query= "select tID,theading,tdescription,userID,status,producttype,productname from ticket t, product p where t.pID=p.pID and userID="+userID+";";
            }
            ResultSet rs = st.executeQuery(query);
                       
            out.print(" <h2> Tickets : &nbsp&nbsp&nbsp <a class=\"button\" href=\"ticketaddition.jsp\" > Launch New Ticket</a></h2>"
                    + "<table>"
                    + "<tr>");
            if(usertype.equalsIgnoreCase("7") || usertype.equalsIgnoreCase("6") || usertype.equalsIgnoreCase("5")|| usertype.equalsIgnoreCase("4") ){
            out.print("<th><b> User ID </b></th>");
            }
            out.print("<th><b> Ticket ID </b></th>"
                    + "<th><b> Product Name </b></th>"
                    + "<th><b> Product Type </b></th>"
                    + "<th><b> Heading </b></th>"
                    + "<th><b> Description </b></th>"
                    + "<th><b> Status </b></th>"
                    + "<th><b> Edit Ticket </b></th>");
            if(usertype.equalsIgnoreCase("7") || usertype.equalsIgnoreCase("6") || usertype.equalsIgnoreCase("5")|| usertype.equalsIgnoreCase("4") ){
            out.print( "<th><b> Edit Status </b></th>");
            }
            out.print( "<th><b> Delete </b></th>"
                    + "</tr>" 
            );
            while(rs.next()) {
                int tID = rs.getInt("tID");
                int UID = rs.getInt("userID");
                int status = rs.getInt("status");
                String thead = rs.getString("theading");
                String tdesc = rs.getString("tdescription");
                String productname=rs.getString("productname");
                int producttype=rs.getInt("producttype");
                
                out.print(""
                        + "<tr>");
                if(usertype.equalsIgnoreCase("7") || usertype.equalsIgnoreCase("6") || usertype.equalsIgnoreCase("5")|| usertype.equalsIgnoreCase("4") ){
            out.print(  "<td> "+UID+"</td>");
                }
            out.print(  "<td> "+tID+"</td>"
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
                out.print("<td> "+thead+"</td>"
                        + "<td> "+tdesc+"</td>");
                if(status==1){
                    out.print("<td> Pending Review </td>");
                }else if(status==2){
                    out.print("<td> Unresolved </td>");
                }else if(status==3){
                    out.print("<td> Resolved</td>");
                }
                
            if(userID==UID || usertype.equalsIgnoreCase("7") || usertype.equalsIgnoreCase("6")){
            out.print("<td> <a href=\"editticket.jsp?tID="+tID+"\" class=\"button\">Edit ticket</a></td>");
            }else{
            out.print(  "<td> - </td>");
            }
            if(usertype.equalsIgnoreCase("7") || usertype.equalsIgnoreCase("6") || usertype.equalsIgnoreCase("5")|| usertype.equalsIgnoreCase("4") ){
            out.print(  "<td> <a href=\"editstatus.jsp?tID="+tID+"\" class=\"button\">Edit status</a></td>");
            }
            if(userID==UID ||  usertype.equalsIgnoreCase("7") || usertype.equalsIgnoreCase("6")){
            out.print(  "<td> <a href=\"deleteticket.jsp?tID="+tID+"&productname="+productname+"\" class=\"button\">Delete</a></td>"
                        + "</tr>");}
            else{
            out.print(  "<td> - </td>"
                        + "</tr>");
            }
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

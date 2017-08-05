/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;
/**
 *
 * @author lonewolf
 */
public class logincheck extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
            String username = request.getParameter("username");
            String password = request.getParameter("password");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project"; 
            Connection con = DriverManager.getConnection(url,"root","root");
            Statement st = con.createStatement();
            String query = "select * from login where username='"+username+"'";
            ResultSet rs = st.executeQuery(query);
            rs.next();
            if(password.equals(rs.getString("password"))) {
                //out.print("<p><b>Welcome :</b> "+username);
                //session wala kaam below -
                HttpSession session =request.getSession();  
                session.setAttribute("username",username);
                String usertype = Integer.toString(rs.getInt("usertype"));
                session.setAttribute("usertype",usertype );
                String userID = Integer.toString(rs.getInt("userID"));
                session.setAttribute("userID",userID );
                
                response.sendRedirect("welcome.jsp");
            }
            else {
                out.print("<script>alert(\"Incorrect Password !\"); window.location= \"login.jsp\";</script>");
                //response.sendRedirect("login.jsp");
            }
        }
        catch (Exception e) {
            out.print("<script>alert(\"Invalid Username or You haven't registered yet !\"); window.location= \"login.jsp\";</script>");
            //response.sendRedirect("login.jsp");
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

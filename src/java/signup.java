
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class signup extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int usertype =  Integer.parseInt(request.getParameter("usertype"));
        
        user one= new user();
        one.setfirstname(request.getParameter("firstname"));
        one.setlastname(request.getParameter("lastname"));
        one.setemail(request.getParameter("email"));
        one.setphonenumber(request.getParameter("phonenumber"));
        int flag=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project";
            String query = "insert into login (usertype,username,password) values ("+usertype+",'"+username+"','"+password+"')";
            Connection con = DriverManager.getConnection(url,"root","root");
            Statement st = con.createStatement();
            st.execute(query);
            
        }
        catch (Exception e) {
            flag=1;
            out.print("<script>alert(\"Username Already Taken !\"); window.location= \"signup.jsp\"; </script>");
                
        }
        if(flag==0)
        {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project";
            String query = "select userID from login where username='"+username+"'";
            Connection con = DriverManager.getConnection(url,"root","root");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            one.setuserID(Integer.parseInt(rs.getString("userID")));
            query = "insert into user (firstname,lastname, email, phonenumber,userID) values ('"+one.getfirstname()+"','"+one.getlastname()+"','"+one.getemail()+"','"+one.getphonenumber()+"',"+one.getuserID()+")";
            st.execute(query);
            
            out.print("<p><b> Successfully registered</b>");
            response.sendRedirect("login.jsp");
        }
        catch (Exception e2){
            try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/project";
            String query = "delete from login where userID='"+one.getuserID()+"'";
            Connection con = DriverManager.getConnection(url,"root","root");
            Statement st = con.createStatement();
            st.executeUpdate(query);
            
            out.print("<script>alert(\"Email Already Registered !\"); window.location= \"signup.jsp\";</script>");
                
            }
            catch (Exception e3){
                out.println(e3);
            }
        }
        }//close of if
        
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

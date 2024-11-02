
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author prana
 */
public class Add_user extends HttpServlet
{
    protected void processRequest(HttpServletRequest request , HttpServletResponse response)
            throws IOException,ServletException
    {
       HttpSession session=request.getSession(false);
       if(session!=null)
       {
            // Set the Cache-Control header
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            // Set the Pragma header to no-cache to support HTTP 1.0
            response.setHeader("Pragma", "no-cache");
            // Set the Expires header to 0 to prevent caching at the proxy server
            response.setDateHeader("Expires", 0);
            
            int user_id=Integer.parseInt((String) session.getAttribute("user_id"));
             PrintWriter out=response.getWriter();
             try
             {
                 String fname=request.getParameter("fname");
                 String lname=request.getParameter("lname");
                 String email=request.getParameter("email");
                 String password=request.getParameter("password");
                 String role=request.getParameter("role");
                 int department=Integer.parseInt(request.getParameter("department"));
                 Connection con=Database_Connection.initializeDatabase();
                 Statement stml=con.createStatement();
                 PreparedStatement st=con.prepareStatement("INSERT INTO `user`( `dept_id`, `email`, `password`, `fname`, `lname`, `role`) VALUES (?,?,?,?,?,?);");
                 out.println(role+" "+department+" "+email+" "+password+" "+fname+" "+lname);
                 st.setInt(1, department);
                 st.setString(2,email);
                 st.setString(3,password);
                 st.setString(4, fname);
                 st.setString(5,lname);
                 st.setString(6,role);
                 st.executeUpdate();
                 response.sendRedirect("/demo_bootstrap/Admin_user");
             }
             catch(SQLException e)
             {
                 out.println("SQLException"+e);
             }
             catch(ClassNotFoundException e)
             {
                 out.println("ClassNotFoundException"+e);
             }
       }
       else
       {
           response.sendRedirect("login.html");
       }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        {
            processRequest(request, response);
        } 
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        {
            processRequest(request, response);
        } 
        
    }
}

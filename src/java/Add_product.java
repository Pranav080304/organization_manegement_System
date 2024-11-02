
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
public class Add_product extends HttpServlet
{
    protected void processRequest(HttpServletRequest request , HttpServletResponse response)
            throws IOException,ServletException
    {
       HttpSession session=request.getSession(false);
       if(session!=null)
            {
             int user_id=Integer.parseInt((String) session.getAttribute("user_id"));
             PrintWriter out=response.getWriter();
              // Set the Cache-Control header
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            // Set the Pragma header to no-cache to support HTTP 1.0
            response.setHeader("Pragma", "no-cache");
            // Set the Expires header to 0 to prevent caching at the proxy server
            response.setDateHeader("Expires", 0);
             try
             {
                 String pro_name=request.getParameter("pro_name");
                 String pro_details=request.getParameter("pro_details");
                 int pro_price=Integer.parseInt(request.getParameter("pro_price"));
                 Connection con=Database_Connection.initializeDatabase();
                 Statement stml=con.createStatement();
                 PreparedStatement st=con.prepareStatement("INSERT INTO `product`( `pro_name`, `pro_details`, `pro_price`) VALUES (?,?,?);");
                 st.setString(1, pro_name);
                 st.setString(2,pro_details);
                 st.setInt(3,pro_price);
                 st.executeUpdate();
                 response.sendRedirect("/demo_bootstrap/Admin_product");
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

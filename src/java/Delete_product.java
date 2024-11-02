
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author prana
 */
public class Delete_product extends HttpServlet
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        HttpSession session=request.getSession();
        int user_id=Integer.parseInt((String) session.getAttribute("user_id"));
        PrintWriter out=response.getWriter();
        if(user_id>0)
        {
            try
            {
               int pro_id=Integer.parseInt(request.getParameter("pro_id"));
               Connection con=Database_Connection.initializeDatabase();
               Statement stml=con.createStatement();
               PreparedStatement st=con.prepareStatement("delete from product where pro_id=?; ");
               st.setInt(1,pro_id);
               st.executeUpdate();
               response.sendRedirect("/demo_bootstrap/Admin_product");
            }
            catch(SQLException e)
            {
                out.println("SQLexception"+e);
            }
            catch(ClassNotFoundException e)
            {
                out.println("ClassNotFoundException"+e);
            }
        }
        else
        {
            out.println("Session error from delete product");
        }
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        processRequest(request,response);
    }
    
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        processRequest(request,response);
    }
}

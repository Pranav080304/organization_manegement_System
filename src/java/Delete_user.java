
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author prana
 */
public class Delete_user extends HttpServlet
{
    protected void processRequest(HttpServletRequest request , HttpServletResponse response)
            throws IOException,ServletException
    {
       HttpSession session=request.getSession();
       int user_id=Integer.parseInt((String) session.getAttribute("user_id"));
        PrintWriter out=response.getWriter();
        try
        {           
            int id=Integer.parseInt(request.getParameter("id"));
            Connection con=Database_Connection.initializeDatabase();
            Statement stml=con.createStatement();
            PreparedStatement st=con.prepareStatement("DELETE FROM `user` WHERE user_id=?;");
            st.setInt(1, id);
            st.executeUpdate();
            
            PreparedStatement st1=con.prepareStatement("DELETE FROM `buy` WHERE user_id=?;");
            st1.setInt(1, id);
            st1.executeUpdate();
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

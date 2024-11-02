/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;
import java.io.*;
/**
 *
 * @author prana
 */
public class Delete_emp extends HttpServlet
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(false);
        if(session!=null)
        {
            try
            {
                int id=Integer.parseInt(request.getParameter("id"));    
                Connection con=Database_Connection.initializeDatabase();
                PreparedStatement st=con.prepareStatement("UPDATE `user` SET `show`=? WHERE user_id=?;");
                st.setInt(1,0);
                st.setInt(2, id);
                st.executeUpdate();
                response.sendRedirect("Emp_details");
            }
            catch(SQLException e)
            {
                out.println("SQLException" +e);
            }
            catch(ClassNotFoundException e)
            {
                out.println("ClassNotFoundException "+e);
            }
            
        }
        else
        {
            response.sendRedirect("login.html");
        }
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException
    {
        processRequest(request,response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException
    {
        processRequest(request,response);
    }
}

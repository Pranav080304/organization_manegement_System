
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
public class Buy_product extends HttpServlet
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException , ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(false);
        if(session!=null)
        {
            int user_id=Integer.parseInt((String)session.getAttribute("user1_id"));

            if(user_id>0)
            {
                response.setContentType("text/html");
                try
                {
                   Connection con =Database_Connection.initializeDatabase();
                   Statement stml=con.createStatement();

                   int pro_id=Integer.parseInt(request.getParameter("pro_id"));
                   int pro_quantity=Integer.parseInt(request.getParameter("pro_quantity"));
                   PreparedStatement st=con.prepareStatement("INSERT INTO `buy`( `user_id`, `pro_id`,`pro_quantity`) VALUES (?,?,?);");
                   st.setInt(1, user_id);
                   st.setInt(2, pro_id);
                   st.setInt(3, pro_quantity);
                   st.executeUpdate();
                   response.sendRedirect("User_product");
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
                out.println("Session error");
            }
        }
        else
        {
            response.sendRedirect("login.html");
        }
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException , ServletException
    {
        processRequest(request,response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws IOException , ServletException
    {
        processRequest(request,response);
    }
}

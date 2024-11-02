
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author prana
 */
public class Product_reject extends HttpServlet
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        int user_id=Integer.parseInt((String)session.getAttribute("user_id"));
        if(user_id>0)
        {
            RequestDispatcher head=request.getRequestDispatcher("head.html");
            head.include(request,response);
            RequestDispatcher navbar=request.getRequestDispatcher("navbar");
            navbar.include(request , response);
            
            try
            {
               int buy_id=Integer.parseInt(request.getParameter("buy_id"));
                String reason=request.getParameter("reason");
                Connection con=Database_Connection.initializeDatabase();
                PreparedStatement st=con.prepareStatement("UPDATE `buy` SET `status`=?,`reason`= ?WHERE buy_id=?;");
                st.setString(1,"Rejected");
                st.setString(2,reason);
                st.setInt(3, buy_id);
                st.executeUpdate();
                response.sendRedirect("Action_form");
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
            out.println("Session Error from Producrt_reject_form");
        }
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        processRequest(request,response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException
    {
        processRequest(request,response);
    }
}


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author prana
 */
public class pro_quantity_add extends HttpServlet
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        int admin_id=Integer.parseInt((String)session.getAttribute("user_id"));
        if(admin_id>0)
        {
            RequestDispatcher head=request.getRequestDispatcher("head.html");
            head.include(request, response);
            RequestDispatcher navbar=request.getRequestDispatcher("navbar");
            navbar.include(request, response);
            
             try
                 {
                     int pro_id=Integer.parseInt(request.getParameter("pro_id"));
                     int pro_quantity=Integer.parseInt(request.getParameter("pro_quantity"));
                     Connection con=Database_Connection.initializeDatabase();
                     PreparedStatement st=con.prepareStatement("UPDATE `product` SET `quantity`=? WHERE pro_id=?;");
                     st.setInt(1, pro_quantity);
                     st.setInt(2, pro_id);
                     st.executeUpdate();
                     response.sendRedirect("Admin_product");
                 }
                catch (ClassNotFoundException e)
                {
                    out.println("Class not found Exception"+" "+e);
                }
                catch (SQLException e)
                 {
                        out.println("SQLException"+" "+e);
                 }  
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

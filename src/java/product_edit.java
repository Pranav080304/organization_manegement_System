
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
public class product_edit extends HttpServlet
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
                     String pro_name=request.getParameter("pro_name");
                     String pro_details=request.getParameter("pro_details");
                     int pro_price=Integer.parseInt(request.getParameter("pro_price"));
                     Connection con=Database_Connection.initializeDatabase();
                     PreparedStatement st=con.prepareStatement("UPDATE `product` SET `pro_name`=?,`pro_details`=?,`pro_price`=? WHERE pro_id=?;");
                     st.setString(1, pro_name);
                     st.setString(2, pro_details);
                     st.setInt(3, pro_price);
                     st.setInt(4, pro_id);
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

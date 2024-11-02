
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
public class Product_approve extends HttpServlet
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        int user_id=Integer.parseInt((String)session.getAttribute("user_id"));
        if(user_id>0)
        {
           response.setContentType("text/html"); 
           RequestDispatcher head=request.getRequestDispatcher("head.html");
           head.include(request, response);
           
           RequestDispatcher navbar=request.getRequestDispatcher("navbar");
           navbar.include(request, response);
           try
           {
               int buy_id=Integer.parseInt(request.getParameter("buy_id"));
               Connection con=Database_Connection.initializeDatabase();
               Statement stml=con.createStatement();
               int pro_quantity=0,pro_id=0,stock_quantity=0;
               ResultSet rs=stml.executeQuery("SELECT * from buy join product on buy.pro_id=product.pro_id WHERE buy.buy_id="+buy_id+";");
               while(rs.next())
               {
                   pro_quantity=Integer.parseInt(rs.getString("pro_quantity"));
                   pro_id=Integer.parseInt(rs.getString("pro_id"));
                   stock_quantity=Integer.parseInt(rs.getString("quantity"));
               }
               
               int pro_quan=stock_quantity-pro_quantity;
               PreparedStatement st1=con.prepareStatement("UPDATE `product` SET `quantity`=? WHERE pro_id=?;");
               st1.setInt(1,pro_quan);
               st1.setInt(2,pro_id);
               st1.executeUpdate();
               
               
               PreparedStatement st=con.prepareStatement("UPDATE `buy` SET `status`=? ,`reason`=? WHERE buy.buy_id=? ;");
               st.setString(1,"Approved");
               st.setString(2,"Approved");
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
            out.println("Session Error");
        }
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException
    {
        processRequest(request,response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws IOException , ServletException
    {
        processRequest(request,response);
    }
}

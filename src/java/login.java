
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author prana
 */
public class login extends HttpServlet
{
   public void processRequest(HttpServletRequest request,HttpServletResponse response)
           throws IOException,ServletException
   {
       PrintWriter out=response.getWriter();
       try
       {
           String email=request.getParameter("email");
           String password=request.getParameter("password");
           boolean flag = false;
           Connection con=Database_Connection.initializeDatabase();
           Statement stml=con.createStatement();
           String str = "Select user_id from user where email='"+email+"' and password='"+password+"';";
           //out.println(str);
           ResultSet rs=stml.executeQuery("Select user_id,role from user where email='"+email+"' and password='"+password+"';");
           while(rs.next())
           {
             flag=true;
             int id=Integer.parseInt(rs.getString("user_id"));
             String user_id=rs.getString("user_id");
             String role=rs.getString("role");
             if(id>0 && role.equals("admin"))
             {
                 HttpSession session=request.getSession();
                 session.setAttribute("user_id", user_id);
                 response.sendRedirect("Admin_dashbord");
             }
             
             if(id>0 && role.equals("user"))
             {
                 HttpSession session=request.getSession();
                 session.setAttribute("user1_id", user_id);
                 response.sendRedirect("User_dashbord");
             }
             
              if(id>0 && role.equals("emp"))
             {
                 HttpSession session=request.getSession();
                 session.setAttribute("emp_id", user_id);
                 response.sendRedirect("emp_dashbord");
             }
               
           }
           
           if(flag==false)
           {
               out.println("wrong password");
               response.sendRedirect("/login.html");
           }
       }
       catch(SQLException e)
       {
           out.println("SQL Error"+e);
       }
       catch(ClassNotFoundException e)
       {
           out.println("Class not found Error");
       }
   }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}

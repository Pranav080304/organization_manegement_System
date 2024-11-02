
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author prana
 */
public class resister extends HttpServlet
{
   public void processRequest(HttpServletRequest request,HttpServletResponse response)
           throws IOException,ServletException
   {
       PrintWriter out=response.getWriter();
       try
       {
                Connection con=Database_Connection.initializeDatabase();
                Statement stml=con.createStatement();

                String fname=request.getParameter("fname");
                String lname=request.getParameter("lname");
                String dept_id=request.getParameter("dept_id");
                String email=request.getParameter("email");
                String password=request.getParameter("password");

                PreparedStatement st=con.prepareStatement("INSERT INTO `user`( `dept_id`, `email`, `password`, `fname`, `lname`) VALUES (?,?,?,?,?);");
                st.setString(1, dept_id);
                st.setString(2, email);
                st.setString(3,password );
                st.setString(4, fname);
                st.setString(5, lname);
                st.executeUpdate();
                
                out.println("<b>Saved Seccessfuly</b>");
                 response.sendRedirect("login.html");
       }
       catch(SQLException e)
       {
           out.println("SQL Error");
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;
/**
 *
 * @author prana
 */
public class Add_emp extends HttpServlet
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(false);
        if(session!=null)
        {
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            
            try
            {
                String fname=request.getParameter("fname");
                String lname=request.getParameter("lname");
                String email=request.getParameter("email");
                String password=request.getParameter("password");
                String date=request.getParameter("date");
                int salary=Integer.parseInt(request.getParameter("salary"));
                int dept_id=Integer.parseInt(request.getParameter("dept_id"));
                Connection con=Database_Connection.initializeDatabase();
                PreparedStatement st=con.prepareStatement("INSERT INTO `user`(`dept_id`, `email`, `password`, `fname`, `lname`, `role`) VALUES (?,?,?,?,?,?);");
                st.setInt(1,dept_id);
                st.setString(2,email);
                st.setString(3,password);
                st.setString(4,fname);
                st.setString(5,lname);
                st.setString(6,"emp");
                st.executeUpdate();
                
                int emp_id=0;
                Statement stml=con.createStatement();
                ResultSet rs=stml.executeQuery("SELECT * FROM `user` WHERE email='"+email+"' and password="+password+" ;");
                while(rs.next())
                {
                    emp_id=Integer.parseInt(rs.getString("user_id"));
                }
                
                PreparedStatement ps=con.prepareStatement("INSERT INTO `salaries`(`user_id`, `salary`, `from_date`) VALUES (?,?,?);");
                ps.setInt(1, emp_id);
                ps.setInt(2, salary);
                ps.setString(3,date);
                ps.executeUpdate();
                
                response.sendRedirect("Emp_details");
            }
            catch(SQLException e)
            {
                out.println("SQLException "+e);
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

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
public class Edit_emp extends HttpServlet
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException ,ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(false);
        if(session!=null)
        {
            try
            {
                response.setHeader("Cache-Control", "no-store, no-cache,must-revalidate,max-age=0");
                response.setHeader("pragma","no-cache");
                response.setHeader("Expire","0");
                int emp_id=Integer.parseInt(request.getParameter("emp_id"));
                String fname=request.getParameter("fname");
                String lname=request.getParameter("lname");
                int dept_id=Integer.parseInt(request.getParameter("dept_id"));
                Connection con=Database_Connection.initializeDatabase();
                PreparedStatement st=con.prepareStatement("UPDATE `user` SET dept_id=?,`fname`=?,`lname`=? where user_id=?;");
                st.setInt(1,dept_id);
                st.setString(2,fname);
                st.setString(3, lname);
                st.setInt(4, emp_id);
                st.executeUpdate();
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

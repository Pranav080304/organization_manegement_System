/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.annotation.*;
/**
 *
 * @author prana
 */
@MultipartConfig
  (
    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 100   // 100 MB
  )
@WebServlet("/AdminProfileUpdate")
public class AdminProfileUpdate extends HttpServlet
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException ,ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(false);
        if(session!=null)
        {
             // Set the Cache-Control header
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            // Set the Pragma header to no-cache to support HTTP 1.0
            response.setHeader("Pragma", "no-cache");
            // Set the Expires header to 0 to prevent caching at the proxy server
            response.setDateHeader("Expires", 0);
            int admin_id=Integer.parseInt((String)session.getAttribute("admin_id"));
            
            if(admin_id>0)
            {
                try
                {
                    String fname=request.getParameter("fname");
                    String lname=request.getParameter("lname");
                    String password=request.getParameter("password");
                    Part filePart = request.getPart("image");
                    String fileName = filePart.getSubmittedFileName();
                    for (Part part : request.getParts()) 
                    {
                      part.write("C:/Users/prana/OneDrive/Documents/NetBeansProjects/demo_bootstrap/web/assets/img/" + fileName);
                    }
                    Connection con=Database_Connection.initializeDatabase();
                    Statement stml=con.createStatement();
                    PreparedStatement st=con.prepareStatement("UPDATE `user` SET `password`=?,`fname`=?,`lname`=?,`image`=? WHERE user_id=?;");
                   
                    st.setString(1, password);
                    st.setString(2, fname);
                    st.setString(3,lname);
                    st.setString(4, "assets/img/"+fileName);
                    st.setInt(5,admin_id);
                    st.executeUpdate();
                    response.sendRedirect("AdminDashbord");
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
                response.sendRedirect("index.html");
            }
        }
        else
        {
            response.sendRedirect("index.html");
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

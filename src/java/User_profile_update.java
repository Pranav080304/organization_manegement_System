
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.annotation.MultipartConfig;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author prana
 * 
 */
@MultipartConfig
  (
    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 100   // 100 MB
  )
public class User_profile_update extends HttpServlet
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException , ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        int user_id=Integer.parseInt((String)session.getAttribute("user1_id"));
        if(user_id>0)
        {
            try
            {
                String fname=request.getParameter("fname");
                String lname=request.getParameter("lname");
                String email=request.getParameter("email");
                String password=request.getParameter("password");
                Part filePart = request.getPart("image");
                String fileName = filePart.getSubmittedFileName();
                for (Part part : request.getParts()) 
                    {
                      part.write("C:/Users/prana/OneDrive/Documents/NetBeansProjects/demo_bootstrap/web/assets/img/" + fileName);
                    }
                
                Connection con=Database_Connection.initializeDatabase();
                PreparedStatement st=con.prepareStatement("UPDATE `user` SET `email`=?,`password`=?,`fname`=?,`lname`=?,`image`=? WHERE user_id=?;");
                st.setString(1, email);
                st.setString(2, password);
                st.setString(3, fname);
                st.setString(4,lname);
                st.setString(5, "assets/img/"+fileName);
                st.setInt(6,user_id);
                st.executeUpdate();
                response.sendRedirect("User_dashbord");
            }
            catch(SQLException e)
            {
                out.println("SQLException "+e);
            }
            catch(ClassNotFoundException e)
            {
                out.println("ClaaNotFoundException "+e);
            }
        }
        else
        {
            out.println("Session Error");
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

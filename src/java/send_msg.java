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
public class send_msg extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
    throws IOException,ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session =request.getSession();
        int user_id=Integer.parseInt((String)session.getAttribute("user1_id"));
        
        if(user_id>0)
        {
            try
           {
               int sender_id=Integer.parseInt(request.getParameter("sender_id"));
               int reciver_id=Integer.parseInt(request.getParameter("reciver_id"));
               String msg=request.getParameter("msg");
               Connection con=Database_Connection.initializeDatabase();
               PreparedStatement st=con.prepareStatement("INSERT INTO `chat`( `sender_id`, `reciver_id`, `msg`) VALUES (?,?,?);");
               st.setInt(1, sender_id);
               st.setInt(2,reciver_id);
               st.setString(3,msg);
               st.executeUpdate();
               response.sendRedirect("chat_person?reciver_id="+reciver_id+"");
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
            out.println("Session error");
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

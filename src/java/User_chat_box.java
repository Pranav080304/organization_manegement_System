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
public class User_chat_box extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
    throws IOException,ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session =request.getSession(false);
        if(session!=null)
         {
            // Set the Cache-Control header
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            // Set the Pragma header to no-cache to support HTTP 1.0
            response.setHeader("Pragma", "no-cache");
            // Set the Expires header to 0 to prevent caching at the proxy server
            response.setDateHeader("Expires", 0);
            
            int user_id=Integer.parseInt((String)session.getAttribute("user1_id"));

            RequestDispatcher head=request.getRequestDispatcher("head.html");
            head.include(request, response);
            if(user_id>0)
            {

                String reciver_id=request.getParameter("reciver_id");
               try
               {
                  Connection con=Database_Connection.initializeDatabase();
                   Statement stml1=con.createStatement();
                   ResultSet re=stml1.executeQuery("select * from user where user_id="+user_id+";");
                   String sender_name="", sender_image="";
                   while(re.next())
                   {
                       sender_name=re.getString("fname")+" "+re.getString("lname");
                       sender_image=re.getString("image");
                   }

                   Statement stml2=con.createStatement();
                   ResultSet rss=stml1.executeQuery("select * from user where user_id="+reciver_id+";");
                   String reciver_image="",reciver_name="";
                   while(rss.next())
                   {
                       reciver_image=rss.getString("image");
                       reciver_name=rss.getString("fname")+" "+rss.getString("lname");
                   }

                   Statement stml=con.createStatement();
                   ResultSet rs=stml.executeQuery("select * from chat WHERE chat.sender_id="+user_id+" and chat.reciver_id="+reciver_id+" OR chat.sender_id="+reciver_id+" AND chat.reciver_id="+user_id+" ORDER BY chat.time ASC;");
                   while(rs.next())
                   {
                       String msg=rs.getString("msg");
                       String time=rs.getString("time");
                       int send_id=Integer.parseInt(rs.getString("sender_id"));
                       int reciv_id=Integer.parseInt(rs.getString("reciver_id"));


                       if(send_id==user_id)
                       {
                              out.println("                  <!-- Message to the right -->\n" +
                                    "                  <div class=\"direct-chat-msg right\">\n" +
                                    "                    <div class=\"direct-chat-infos clearfix\">\n" +
                                    "                      <span class=\"direct-chat-name float-right\">"+sender_name+"</span>\n" +
                                    "                    </div>\n" +
                                    "                    <!-- /.direct-chat-infos -->\n" +
                                    "                    <img class=\"direct-chat-img\" src=\""+sender_image+"\" alt=\"message user image\">\n" +
                                    "                    <!-- /.direct-chat-img -->\n" +
                                    "                    <div class=\"direct-chat-text\">\n" +
                                    "                     "+msg+"\n" +
                                    "                    </div>\n" +
                                    "                      <span class=\"direct-chat-timestamp float-right\">"+time+"</span>\n" +
                                    "                    <!-- /.direct-chat-text -->\n" +
                                    "                  </div>\n" +
                                    "                  <!-- /.direct-chat-msg -->");

                       }
                        else
                       {
                        out.println("<!-- Conversations are loaded here -->\n" +
                                    "                  <!-- Message. Default to the left -->\n" +
                                    "                  <div class=\"direct-chat-msg left\">\n" +
                                    "                    <div class=\"direct-chat-infos clearfix\">\n" +
                                    "                      <span class=\"direct-chat-name float-left\">"+reciver_name+"</span>\n" +
                                    "                    </div>\n" +
                                    "                    <!-- /.direct-chat-infos -->\n" +
                                    "                    <img class=\"direct-chat-img\" src=\""+reciver_image+"\" alt=\"message user image\">\n" +
                                    "                    <!-- /.direct-chat-img -->\n" +
                                    "                    <div class=\"direct-chat-text\">\n" +
                                    "                     "+msg+"\n" +
                                    "                    </div>\n" +
                                    "                      <span class=\"direct-chat-timestamp float-left\">"+time+"</span>\n" +
                                    "                    <!-- /.direct-chat-text -->\n" +
                                    "                  </div>\n" +
                                    "                  <!-- /.direct-chat-msg -->");
                       }
                   }
               }
               catch(SQLException e)
               {
                   out.println("SQLException "+e);
               }
               catch(ClassNotFoundException e)
               {
                   out.println("ClassNotFoundException "+e);
               }   
                out.println(" </div>\n" +
                            "              <!-- /.card-body -->");
                response.setIntHeader("refresh",5);
            }
            else
            {
                out.println("Session error");
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

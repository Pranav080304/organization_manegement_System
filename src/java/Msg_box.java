
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;
import java.io.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author prana
 */
public class Msg_box extends HttpServlet
{
    protected void processRequest (HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
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
            
            int user_id=Integer.parseInt((String)session.getAttribute("user1_id"));
            if(user_id>0)
            {   
                RequestDispatcher head=request.getRequestDispatcher("head.html");
                head.include(request, response);
                RequestDispatcher chat_head=request.getRequestDispatcher("chat_head.html");
                RequestDispatcher navbar=request.getRequestDispatcher("User_navbar");
                navbar.include(request, response);
                RequestDispatcher chat_option=request.getRequestDispatcher("chat_option_bar");
                chat_option.include(request, response);

                try
                { 
                    Connection con=Database_Connection.initializeDatabase();
                    Statement stml=con.createStatement();
                    out.println("<!-- DIRECT CHAT -->\n" +
                                "            <div class=\"card direct-chat direct-chat-primary col-8\">\n" +
                                "              <div class=\"card-header\">\n" +
                                "                <h3 class=\"card-title\">Direct Chat</h3>\n" +
                                "\n" +
                                "                <div class=\"card-tools\">\n" +
                                "                  <span title=\"3 New Messages\" class=\"badge badge-primary\">3</span>\n" +
                                "                  <button type=\"button\" class=\"btn btn-tool\" data-card-widget=\"collapse\">\n" +
                                "                    <i class=\"fas fa-minus\"></i>\n" +
                                "                  </button>\n" +
                                "                  <button type=\"button\" class=\"btn btn-tool\" title=\"Contacts\" data-widget=\"chat-pane-toggle\">\n" +
                                "                    <i class=\"fas fa-comments\"></i>\n" +
                                "                  </button>\n" +
                                "                  <button type=\"button\" class=\"btn btn-tool\" data-card-widget=\"remove\">\n" +
                                "                    <i class=\"fas fa-times\"></i>\n" +
                                "                  </button>\n" +
                                "                </div>\n" +
                                "              </div>\n" +
                                "              <!-- /.card-header -->\n" +
                                "              <div class=\"card-body\">");

                    out.println("<div class=\"card-footer \">\n" +
                            "                <form action=\"send_msg\" method=\"post\">\n" +
                            "                  <div class=\"input-group\">\n" +
                            "                    <input type=\"text\" name=\"msg\" placeholder=\"Type Message ...\" class=\"form-control\">\n" +
                            "                    <span class=\"input-group-append\">\n" +
                            "                      <button type=\"submit\" class=\"btn btn-primary\">Send</button>\n" +
                            "                    </span>\n" +
                            "                  </div>\n" +
                            "                </form>\n" +
                            "              </div>\n" +
                            "              <!-- /.card-footer-->\n" +
                            "            </div>\n" +
                            "            <!--/.direct-chat -->\n" +
                            "\n" +
                            "           \n" +
                            "      </div><!-- /.container-fluid -->\n" +
                            "    </section>");

                }
                catch(SQLException e)
                {
                    out.println("SQLException "+e);
                }
                catch(ClassNotFoundException e)
                {
                    out.println("ClassNotFoundException "+e);
                }

                RequestDispatcher script=request.getRequestDispatcher("scripts.html");
                script.include(request, response);

            }
            else
            {
                out.println("Session Error");
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
            throws IOException,ServletException
    {
        processRequest(request,response);
    }
}

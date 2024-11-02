
import jakarta.servlet.http.*;
import jakarta.servlet.*;
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
public class chat_option_bar extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        int user_id=Integer.parseInt((String)session.getAttribute("user1_id"));
        if(user_id>0)
        {
            RequestDispatcher head=request.getRequestDispatcher("head.html");
            head.include(request, response);
            
            try
            {
                out.println("<!-- Content Wrapper. Contains page content -->\n" +
                            "  <div class=\"content-wrapper kanban\">\n" +
                            "    <section class=\"content-header\">\n" +
                            "      <div class=\"container-fluid\">\n" +
                            "        <div class=\"row\">\n" +
                            "          <div class=\"col-sm-6\">\n" +
                            "            <h1>Kanban Board</h1>\n" +
                            "          </div>\n" +
                            "          <div class=\"col-sm-6 d-none d-sm-block\">\n" +
                            "            <ol class=\"breadcrumb float-sm-right\">\n" +
                            "              <li class=\"breadcrumb-item\"><a href=\"#\">Home</a></li>\n" +
                            "              <li class=\"breadcrumb-item active\">Kanban Board</li>\n" +
                            "            </ol>\n" +
                            "          </div>\n" +
                            "        </div>\n" +
                            "      </div>\n" +
                            "    </section>");
                
                out.println("<section class=\"content pb-3\">\n" +
                            "      <div class='row'>\n"+
                            "      <div>\n" +
                            "        <div class=\"card card-row card-secondary\">\n" +
                            "          <div class=\"card-header\">\n" +
                            "            <h3 class=\"card-title\">\n" +
                            "              Users\n" +
                            "            </h3>\n" +
                            "          </div>\n" +
                            "          <div class=\"card-body\">");
                
                out.println("<div class=\"card-body\">");
                out.println("<ul class=\"nav nav-pills nav-sidebar flex-column\" data-widget=\"treeview\" role=\"menu\" data-accordion=\"false\">");
                
                Connection con=Database_Connection.initializeDatabase();
                Statement stml=con.createStatement();
                ResultSet rs=stml.executeQuery("select * from user");
                while(rs.next())
                {
                    String name=rs.getString("fname")+" "+rs.getString("lname");
                    int id=Integer.parseInt(rs.getString("user_id"));
                    String image=rs.getString("image");
                    if(user_id!=id)
                    {
                        
                        out.println("<li class=\"nav-item\">\n" +
                                    "            <a href=\"chat_person?reciver_id="+id+"\" class=\"nav-link bg-secondary\">\n" +
                                    "              \n" +
                                    "              <p>\n" +
                                    "               <img class=\"bi me-2\" width=\"16\" height=\"16\" src='"+image+"'></img>"+
                                    "               "+name+"\n" +
                                    "              </p>\n" +
                                    "            </a>           \n" +
                                    "          </li>");
                    }
                }
                out.println("</ul>");
                out.println(" </div>\n" +
                            "          </div>\n" +
                            "        </div>\n"+
                            "       </div>");
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

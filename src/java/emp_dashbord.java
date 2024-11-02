
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.util.*;
import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author prana
 */
public class emp_dashbord extends HttpServlet
{
    protected void ProcessRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(false);
        if(session!=null)
        {
            int emp_id=Integer.parseInt((String)session.getAttribute("emp_id"));
            
             // Set the Cache-Control header
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            // Set the Pragma header to no-cache to support HTTP 1.0
            response.setHeader("Pragma", "no-cache");
            // Set the Expires header to 0 to prevent caching at the proxy server
            response.setDateHeader("Expires", 0);
            
            RequestDispatcher head=request.getRequestDispatcher("head.html");
            head.include(request, response);
            RequestDispatcher navbar=request.getRequestDispatcher("emp_navbar");
            navbar.include(request, response);
            
            try
            {
                Connection con=Database_Connection.initializeDatabase();
                Statement stml=con.createStatement();
                ResultSet rs=stml.executeQuery("select *from user where user.user_id='"+emp_id+"';");
                while(rs.next())
                {
                    String fname=rs.getString("fname");
                    String lname=rs.getString("lname");
                    String image=rs.getString("image");

                          

                    out.println("<div class=\"content-wrapper\">\n" +
                                 "    <!-- Content Header (Page header) -->\n" +
                                 "    <section class=\"content-header\">\n" +
                                 "      <div class=\"container-fluid\">\n" +
                                 "        <div class=\"row mb-2\">\n" +
                                 "          <div class=\"col-sm-6\">\n" +
                                 "            <h1>Widgets</h1>\n" +
                                 "          </div>\n" +
                                 "          <div class=\"col-sm-6\">\n" +
                                 "            <ol class=\"breadcrumb float-sm-right\">\n" +
                                 "              <li class=\"breadcrumb-item\"><a href=\"#\">Home</a></li>\n" +
                                 "              <li class=\"breadcrumb-item active\">Widgets</li>\n" +
                                 "            </ol>\n" +
                                 "          </div>\n" +
                                 "        </div>\n" +
                                 "      </div><!-- /.container-fluid -->\n" +
                                 "    </section>");

                              out.println("<div class=\"col-md-4\">\n" +
                                             "\n" +
                                             "<div class=\"card card-widget widget-user\">\n" +
                                             "\n" +
                                             "<div class=\"widget-user-header bg-info\">\n" +
                                             "<h3 class=\"widget-user-username\">"+fname+" "+lname+"</h3>\n" +
                                             "<h5 class=\"widget-user-desc\">Admin</h5>\n" +
                                             "</div>\n" +
                                             "<div class=\"widget-user-image\">\n" +
                                             "<img class=\"img-circle elevation-2\" src=\""+image+"\" alt=\"User Avatar\">\n" +
                                             "</div>\n" +
                                             "<div class=\"card-footer\">\n" +
                                             "<div class=\"row\">\n" +
                                             "<div class=\"col-sm-4 border-right\">\n" +
                                             "<div class=\"description-block\">\n" +
                                             "<h5 class=\"description-header\">3,200</h5>\n" +
                                             "<span class=\"description-text\">SALES</span>\n" +
                                             "</div>\n" +
                                             "\n" +
                                             "</div>\n" +
                                             "\n" +
                                             "<div class=\"col-sm-4 border-right\">\n" +
                                             "<div class=\"description-block\">\n" +
                                             "<h5 class=\"description-header\">13,000</h5>\n" +
                                             "<span class=\"description-text\">FOLLOWERS</span>\n" +
                                             "</div>\n" +
                                             "\n" +
                                             "</div>\n" +
                                             "\n" +
                                             "<div class=\"col-sm-4\">\n" +
                                             "<div class=\"description-block\">\n" +
                                             "<h5 class=\"description-header\">35</h5>\n" +
                                             "<span class=\"description-text\">PRODUCTS</span>\n" +
                                             "</div>\n" +
                                             "\n" +
                                             "</div>\n" +
                                             "\n" +
                                             "</div>\n" +
                                             "\n" +
                                             "</div>\n" +
                                             "</div>\n" +
                                             "\n" +
                                             "</div>");  
                }
                
                Statement stml1=con.createStatement();
                double Net_Salary=0,gross=0,basic=0;
                ResultSet rs1=stml1.executeQuery("SELECT salaries.salary,salaries.basic_salaries,salaries.gross_salary FROM salaries JOIN user ON salaries.user_id=user.user_id JOIN department ON user.dept_id=department.dept_id WHERE salaries.user_id="+emp_id+" order by from_date DESC LIMIT 1;");
                while(rs1.next())
                {
                    Net_Salary=Double.parseDouble(rs1.getString("salary"));
                    gross=Double.parseDouble(rs1.getString("gross_salary"));
                    basic=Double.parseDouble(rs1.getString("basic_salaries"));
                }
                
                

                             out.println("<section class=\"content\">\n" +
                                         "      <div class=\"container-fluid\">\n" +
                                         "        <!-- Small boxes (Stat box) -->\n" +
                                         "        <div class=\"row\">\n" +
                                         "          <div class=\"col-lg-3 col-6\">\n" +
                                         "            <!-- small box -->\n" +
                                         "            <div class=\"small-box bg-info\">\n" +
                                         "              <div class=\"inner\">\n" +
                                         "                <h3>"+basic+"</h3>\n" +
                                         "\n" +
                                         "                <p>Basic Salary</p>\n" +
                                         "              </div>\n" +
                                         "              <div class=\"icon\">\n" +
                                         "                <i class=\"ion ion-bag\"></i>\n" +
                                         "              </div>\n" +
                                         "              <a href=\"#\" class=\"small-box-footer\">More info <i class=\"fas fa-arrow-circle-right\"></i></a>\n" +
                                         "            </div>\n" +
                                         "          </div>\n" +
                                         "          <!-- ./col -->\n" +
                                         "          <div class=\"col-lg-3 col-6\">\n" +
                                         "            <!-- small box -->\n" +
                                         "            <div class=\"small-box bg-success\">\n" +
                                         "              <div class=\"inner\">\n" +
                                         "                <h3>"+Net_Salary+"</h3>\n" +
                                         "\n" +
                                         "                <p>Total Salary</p>\n" +
                                         "              </div>\n" +
                                         "              <div class=\"icon\">\n" +
                                         "                <i class=\"ion ion-stats-bars\"></i>\n" +
                                         "              </div>\n" +
                                         "              <a href=\"#\" class=\"small-box-footer\">More info <i class=\"fas fa-arrow-circle-right\"></i></a>\n" +
                                         "            </div>\n" +
                                         "          </div>\n" +
                                         "          <!-- ./col -->\n" +
                                         "          <div class=\"col-lg-3 col-6\">\n" +
                                         "            <!-- small box -->\n" +
                                         "            <div class=\"small-box bg-warning\">\n" +
                                         "              <div class=\"inner\">\n" +
                                         "                <h3>"+gross+"</h3>\n" +
                                         "\n" +
                                         "                <p>Gross Salary</p>\n" +
                                         "              </div>\n" +
                                         "              <div class=\"icon\">\n" +
                                         "                <i class=\"ion ion-person-add\"></i>\n" +
                                         "              </div>\n" +
                                         "              <a href=\"#\" class=\"small-box-footer\">More info <i class=\"fas fa-arrow-circle-right\"></i></a>\n" +
                                         "            </div>\n" +
                                         "          </div>\n" +
                                         "          <!-- ./col -->\n" +
                                         "          <div class=\"col-lg-3 col-6\">\n" +
                                         "            <!-- small box -->\n" +
                                         "          </div>\n" +
                                         "          <!-- ./col -->\n" +
                                         "        </div>\n" +
                                         "        <!-- /.row -->\n" +
                                         "      </div><!-- /.container-fluid -->\n" +
                                         "    </section>\n" +
                                         "    <!-- /.content -->");
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
            response.sendRedirect("login.html");
        }
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        ProcessRequest(request,response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        ProcessRequest(request,response);
    }
    
}

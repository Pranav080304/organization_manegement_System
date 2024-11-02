
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author prana
 */
public class Admin_dashbord extends HttpServlet
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException, SQLException
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
           int user_id=Integer.parseInt((String) session.getAttribute("user_id"));

            if(user_id>0)
            {
                response.setContentType("text/html;charset=UTF-8");
                {
                    RequestDispatcher head =request.getRequestDispatcher("/head.html");
                    head.include(request, response);

                    try
                      {
                          Connection con=Database_Connection.initializeDatabase();
                          Statement stml=con.createStatement();
                          ResultSet rs=stml.executeQuery("select *from user where user.user_id='"+user_id+"';");
                          while(rs.next())
                          {
                              String fname=rs.getString("fname");
                              String lname=rs.getString("lname");
                              String image=rs.getString("image");


                            RequestDispatcher navbar=request.getRequestDispatcher("navbar");
                            navbar.include(request, response);

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
                            HashMap<String,Integer>hashmap=new HashMap<String,Integer>();
                            HashMap<String,Integer>profit=new HashMap<String,Integer>();
                            Statement stml1=con.createStatement();
                            Statement stml2=con.createStatement();
                            ResultSet rs1=stml1.executeQuery("SELECT count(status) as count, status FROM buy GROUP by status;");
                             while(rs1.next())
                             {
                                 hashmap.put(rs1.getString("status"),rs1.getInt("count"));
                             }

                             ResultSet rs2=stml.executeQuery("SELECT sum(product.pro_price) as sum, status FROM buy JOIN product ON buy.pro_id=product.pro_id GROUP by status;");
                             while(rs2.next())
                             {
                                 profit.put(rs2.getString("status"),rs2.getInt("sum"));
                             }

                              int approv_price=profit.get("Approved");
                              int reject_price=profit.get("Rejected");
                              int inpro_price=profit.get("under process");
                              int approv=hashmap.get("Approved");
                              int reject=hashmap.get("Rejected");
                              int inpro=hashmap.get("under process");
                              int total=approv+reject+inpro;
                              int total_price=approv_price+reject_price+inpro_price;
                             out.println("<!-- Main content -->\n" +
                                         "    <section class=\"content\">\n" +
                                         "      <div class=\"container-fluid\">\n" +
                                         "        <!-- Small boxes (Stat box) -->\n" +
                                         "        <div class=\"row\">\n" +
                                         "          <div class=\"col-lg-3 col-6\">\n" +
                                         "            <!-- small box -->\n" +
                                         "            <div class=\"small-box bg-info\">\n" +
                                         "              <div class=\"inner\">\n" +
                                         "                <h3>"+total+"</h3>\n" +
                                         "\n" +
                                         "                <p>Total Orders</p>\n" +
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
                                         "                <h3>"+approv+"</h3>\n" +
                                         "\n" +
                                         "                <p> Approved Orders</p>\n" +
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
                                         "                <h3>"+inpro+"</h3>\n" +
                                         "\n" +
                                         "                <p>New Orders</p>\n" +
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
                                         "            <div class=\"small-box bg-danger\">\n" +
                                         "              <div class=\"inner\">\n" +
                                         "                <h3>"+reject+"</h3>\n" +
                                         "\n" +
                                         "                <p>Rejected Orders</p>\n" +
                                         "              </div>\n" +
                                         "              <div class=\"icon\">\n" +
                                         "                <i class=\"ion ion-pie-graph\"></i>\n" +
                                         "              </div>\n" +
                                         "              <a href=\"#\" class=\"small-box-footer\">More info <i class=\"fas fa-arrow-circle-right\"></i></a>\n" +
                                         "            </div>\n" +
                                         "          </div>\n" +
                                         "          <!-- ./col -->\n" +
                                         "        </div>\n" +
                                         "        <!-- /.row -->\n" +
                                         "      </div><!-- /.container-fluid -->\n" +
                                         "    </section>\n" +
                                         "    <!-- /.content -->");

                             out.println("<section class=\"content\">\n" +
                                         "      <div class=\"container-fluid\">\n" +
                                         "        <!-- Small boxes (Stat box) -->\n" +
                                         "        <div class=\"row\">\n" +
                                         "          <div class=\"col-lg-3 col-6\">\n" +
                                         "            <!-- small box -->\n" +
                                         "            <div class=\"small-box bg-info\">\n" +
                                         "              <div class=\"inner\">\n" +
                                         "                <h3>"+total_price+"</h3>\n" +
                                         "\n" +
                                         "                <p>Total</p>\n" +
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
                                         "                <h3>"+approv_price+"</h3>\n" +
                                         "\n" +
                                         "                <p>Profit</p>\n" +
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
                                         "                <h3>"+inpro_price+"</h3>\n" +
                                         "\n" +
                                         "                <p>New Orders</p>\n" +
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
                                         "            <div class=\"small-box bg-danger\">\n" +
                                         "              <div class=\"inner\">\n" +
                                         "                <h3>"+reject_price+"</h3>\n" +
                                         "\n" +
                                         "                <p>Skip orders</p>\n" +
                                         "              </div>\n" +
                                         "              <div class=\"icon\">\n" +
                                         "                <i class=\"ion ion-pie-graph\"></i>\n" +
                                         "              </div>\n" +
                                         "              <a href=\"#\" class=\"small-box-footer\">More info <i class=\"fas fa-arrow-circle-right\"></i></a>\n" +
                                         "            </div>\n" +
                                         "          </div>\n" +
                                         "          <!-- ./col -->\n" +
                                         "        </div>\n" +
                                         "        <!-- /.row -->\n" +
                                         "      </div><!-- /.container-fluid -->\n" +
                                         "    </section>\n" +
                                         "    <!-- /.content -->");

                      }
                     catch (ClassNotFoundException e)
                     {
                         out.println("Class not found Exception"+" "+e);
                     }
                     catch (SQLException e)
                      {
                             out.println("SQLException"+" "+e);
                      }  
                }
            }        
       }
       else
       {
           response.sendRedirect("login.html");
       }
    }
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(User_dashbord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(User_dashbord.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

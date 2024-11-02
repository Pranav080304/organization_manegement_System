
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author prana
 */
public class User_product extends HttpServlet
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
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
            response.setContentType("text/html");
            RequestDispatcher head=request.getRequestDispatcher("head.html");
            head.include(request, response);
            RequestDispatcher navbar=request.getRequestDispatcher("User_navbar");
            navbar.include(request, response);

            try
            {
                out.println("<div class=\"content-wrapper\">\n" +
                                         "    <!-- Content Header (Page header) -->\n" +
                                         "    <section class=\"content-header\">\n" +
                                         "      <div class=\"container-fluid\">\n" +
                                         "        <div class=\"row mb-2\">\n" +
                                         "          <div class=\"col-sm-6\">\n" +
                                         "            <h1>Products</h1>\n" +
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
                   out.println(" <!-- Main content -->\n" +
                                         "    <section class=\"content\">\n" +
                                         "      <div class=\"container-fluid\">\n" +
                                         "        <div class=\"row\">\n" +
                                         "          <div class=\"col-12\">\n" +
                                         "            <div class=\"card\">\n" +
                                         "              <div class=\"card-header\">\n" +
                                         "                <h3 class=\"card-title col-10\">DataTable with default features</h3>\n" +
                                         "                </div>\n" +
                                         "              <!-- /.card-header -->\n" +
                                         "              <div class=\"card-body\">\n" +
                                         "                <table id=\"example1\" class=\"table table-bordered table-striped\">\n" +
                                         "                  <thead>\n" +
                                         "                  <tr>\n" +
                                         "                    <th>product name</th>\n" +
                                         "                    <th>product Details</th>\n" +
                                         "                    <th>Product price</th>\n" +
                                         "                    <th>Buy Product</th>\n" +
                                         "                  </tr>\n"+
                                         "                  <thead>");



                Connection con=Database_Connection.initializeDatabase();
                Statement stml=con.createStatement();
                ResultSet rs=stml.executeQuery("select * from product");
                while(rs.next())
                {

                   String pro_name=rs.getString("pro_name");
                   String pro_details=rs.getString("pro_details");
                   int pro_price=Integer.parseInt(rs.getString("pro_price"));
                   int pro_id=Integer.parseInt(rs.getString("pro_id"));


                   out.println(" <tbody>\n" +
                                        "                  <tr>\n" +
                                        "                    <td>"+pro_name+"</td>\n" +
                                        "                    <td>"+pro_details +"</td>\n" +                   
                                        "                    <td>"+pro_price+"</td>\n" +
                                        "                    <td><a href='User_product_form?pro_id="+pro_id+"'><button class='btn-sm btn border bg-primary '>Buy Product</button></td>\n" +        
                                        "                  </tr>\n" );
                }

                   out.println("                  </tbody>\n" +
                                        "                </table>\n" +
                                        "              </div>\n" +
                                        "              <!-- /.card-body -->\n" +
                                        "            </div>\n" +
                                        "            <!-- /.card -->\n" +
                                        "          </div>\n" +
                                        "          <!-- /.col -->\n" +
                                        "        </div>\n" +
                                        "        <!-- /.row -->\n" +
                                        "      </div>\n" +
                                        "      <!-- /.container-fluid -->\n" +
                                        "    </section>\n" +
                                        "    <!-- /.content -->");

                   RequestDispatcher table_script=request.getRequestDispatcher("datatable_script.html");
                   table_script.include(request, response);
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
            throws IOException ,ServletException
    {
        processRequest(request,response); 
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        processRequest(request,response);
    }
}

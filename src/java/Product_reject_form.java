
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author prana
 */
public class Product_reject_form extends HttpServlet
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
            
            int user_id=Integer.parseInt((String)session.getAttribute("user_id"));
            if(user_id>0)
            {
                RequestDispatcher head=request.getRequestDispatcher("head.html");
                head.include(request,response);
                RequestDispatcher navbar=request.getRequestDispatcher("navbar");
                navbar.include(request , response);

                String buy_id=request.getParameter("buy_id");
                                out.println(" <div class=\"content-wrapper\">\n" +
                                            "    <!-- Content Header (Page header) -->\n" +
                                            "    <section class=\"content-header\">\n" +
                                            "      <div class=\"container-fluid\">\n" +
                                            "        <div class=\"row mb-2\">\n" +
                                            "          <div class=\"col-sm-6\">\n" +
                                            "            <h1>General Form</h1>\n" +
                                            "          </div>\n" +
                                            "          <div class=\"col-sm-6\">\n" +
                                            "            <ol class=\"breadcrumb float-sm-right\">\n" +
                                            "              <li class=\"breadcrumb-item\"><a href=\"#\">Home</a></li>\n" +
                                            "              <li class=\"breadcrumb-item active\">General Form</li>\n" +
                                            "            </ol>\n" +
                                            "          </div>\n" +
                                            "        </div>\n" +
                                            "      </div><!-- /.container-fluid -->\n" +
                                            "    </section>");


                            String form="<section class='content'>\n" +
                                        "      <div class='container-fluid'>\n" +
                                        "          <!-- left column -->\n" +
                                        "          <div class='col-md-12'>\n" +
                                        "            <!-- general form elements -->\n" +
                                        "            <div class='card card-primary'>\n" +
                                        "              <div class='card-header'>\n" +
                                        "                <h3 class='card-title'>Quick Example</h3>\n" +
                                        "              </div>\n" +
                                        "              <!-- /.card-header -->\n" +
                                        "              <!-- form start -->\n" +
                                        "              <form action='Product_reject' method='post'>\n" +
                                        "                <div class='card-body'>\n" +
                                        "			<div class=\"col-sm-12\">\n"+
                                        "                      <div class=\"form-group\">\n" +
                                        "                           <label>Reason For Reject</label>\n" +
                                        "                           <textarea class=\"form-control\" rows=\"3\" name='reason' placeholder=\"Enter Product Details\"></textarea>\n" +
                                        "                           <input type='hidden' value='"+buy_id+"' name='buy_id'>\n"+
                                       "                   </div>\n" +
                                        "                     </div>"+
                                        "                                   <div class=\"card-footer\">\n" +
                                        "					<button type=\"submit\" class=\"btn btn-primary\">Submit</button>\n" +
                                        "					<button type=\"clear\" class=\"btn btn-danger\">Clear</button>\n" +
                                        "					</div>\n" +
                                        "					</div>\n" +
                                        "                  </div>\n" +
                                        "                </div>\n" +
                                        "                <!-- /.card-body -->\n" +
                                        "        </div>\n" +
                                        "        <!-- /.row -->\n" +
                                        "      </div><!-- /.container-fluid -->\n" +
                                        "    </section>\n" +
                                        "    <!-- /.content -->";

                        out.println(form);
            }
            else
            {
                out.println("Session Error from Producrt_reject_form");
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
            throws IOException, ServletException
    {
        processRequest(request,response);
    }
}

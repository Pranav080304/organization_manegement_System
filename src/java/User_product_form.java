
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
public class User_product_form extends HttpServlet 
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
            if(user_id>0)
            {
                 RequestDispatcher head=request.getRequestDispatcher("head.html");
                 head.include(request, response);
                 RequestDispatcher navbar=request.getRequestDispatcher("User_navbar");
                 navbar.include(request, response);

                  try
                      {
                          int pro_id=Integer.parseInt(request.getParameter("pro_id"));
                          Connection con=Database_Connection.initializeDatabase();
                          Statement stml=con.createStatement();
                          ResultSet rs=stml.executeQuery("select *from product where pro_id='"+pro_id+"';");
                          String pro_name="",pro_details="";
                          int pro_price=0;
                          while(rs.next())
                            {
                                pro_name=rs.getString("pro_name");
                                pro_details=rs.getString("pro_details");
                                pro_price=Integer.parseInt(rs.getString("pro_price"));

                             }

                              out.println(" <div class=\"content-wrapper\">\n" +
                                             "    <!-- Content Header (Page header) -->\n" +
                                             "    <section class=\"content-header\">\n" +
                                             "      <div class=\"container-fluid\">\n" +
                                             "        <div class=\"row mb-2\">\n" +
                                             "          <div class=\"col-sm-6\">\n" +
                                             "            <h1>"+pro_details+"</h1>\n" +
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
                                         "              <form action='Buy_product' method='post'>\n" +
                                         "                <div class='card-body'>\n" +
                                         "		   <div class='row'>\n" +
                                         "                  <div class='form-group col-6'>\n" +
                                         "                    <label>Product Name </label>\n" +
                                         "                    <input type='text' class='form-control' value='"+pro_name+"'  name='pro_name' placeholder='Enter Product Name' disabled>\n" +
                                         "                  </div>\n" +
                                         "                  <div class='form-group col-6'>\n" +
                                         "                    <label for='exampleInputPassword1'>Product Price</label>\n" +
                                         "                    <input type='number' class='form-control '  value='"+pro_price+"' name='pro_price' placeholder='Product Price'disabled>\n" +
                                         "                  </div>\n" +
                                         "		    </div>\n" +
                                         "			<div class=\"col-sm-12\">\n"+
                                         "                      <div class=\"form-group\">\n" +
                                         "                           <label>Product Details</label>\n" +
                                         "                           <textarea class=\"form-control\" rows=\"3\"  value='"+pro_details+"' name='pro_details' placeholder=\"Enter Product Details\"disabled>"+pro_details+"</textarea>\n" +
                                         "                      </div>\n" +
                                         "                     </div>"+
                                         "                  <div class='form-group col-6'>\n" +
                                         "                    <label for='exampleInputPassword1'>Add Quantity</label>\n" +
                                         "                    <input type='number' class='form-control '   name='pro_quantity' placeholder='Add Quantity'>\n" +
                                         "                    <input type='hidden' class='form-control ' value="+pro_id+"   name='pro_id' placeholder='Add Quantity'>\n" +
                                         "                  </div>\n" +
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

                        RequestDispatcher table_script=request.getRequestDispatcher("datable_script.html");
                        table_script.include(request, response);

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

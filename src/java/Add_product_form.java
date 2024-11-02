
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
public class Add_product_form extends HttpServlet
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException, SQLException
    {
       PrintWriter out=response.getWriter();
       HttpSession session=request.getSession(false);
       if(session!=null)
       {
            int user_id=Integer.parseInt((String) session.getAttribute("user_id"));
             // Set the Cache-Control header
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            // Set the Pragma header to no-cache to support HTTP 1.0
            response.setHeader("Pragma", "no-cache");
            // Set the Expires header to 0 to prevent caching at the proxy server
            response.setDateHeader("Expires", 0);
            
            if(user_id>0)
            {
                response.setContentType("text/html;charset=UTF-8");
                {
                   RequestDispatcher head=request.getRequestDispatcher("head.html");
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

                             RequestDispatcher navbar=request.getRequestDispatcher("navbar"); 
                             navbar.include(request, response);
                             }

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
                                         "              <form action='Add_product' method='post'>\n" +
                                         "                <div class='card-body'>\n" +
                                         "		   <div class='row'>\n" +
                                         "                  <div class='form-group col-6'>\n" +
                                         "                    <label>Product Name </label>\n" +
                                         "                    <input type='text' class='form-control'  name='pro_name' placeholder='Enter Product Name'>\n" +
                                         "                  </div>\n" +
                                         "                  <div class='form-group col-6'>\n" +
                                         "                    <label for='exampleInputPassword1'>Product Price</label>\n" +
                                         "                    <input type='number' class='form-control'  name='pro_price' placeholder='Product Price'>\n" +
                                         "                  </div>\n" +
                                         "		    </div>\n" +
                                         "			<div class=\"col-sm-12\">\n"+
                                         "                      <div class=\"form-group\">\n" +
                                         "                           <label>Product Details</label>\n" +
                                         "                           <textarea class=\"form-control\" rows=\"3\" name='pro_details' placeholder=\"Enter Product Details\"></textarea>\n" +
                                         "                      </div>\n" +
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


                         out.println("<!-- DataTables  & Plugins -->\n" +
                                     "<script src=\"plugins/datatables/jquery.dataTables.min.js\"></script>\n" +
                                     "<script src=\"plugins/datatables-bs4/js/dataTables.bootstrap4.min.js\"></script>\n" +
                                     "<script src=\"plugins/datatables-responsive/js/dataTables.responsive.min.js\"></script>\n" +
                                     "<script src=\"plugins/datatables-responsive/js/responsive.bootstrap4.min.js\"></script>\n" +
                                     "<script src=\"plugins/datatables-buttons/js/dataTables.buttons.min.js\"></script>\n" +
                                     "<script src=\"plugins/datatables-buttons/js/buttons.bootstrap4.min.js\"></script>\n" +
                                     "<script src=\"plugins/jszip/jszip.min.js\"></script>\n" +
                                     "<script src=\"plugins/pdfmake/pdfmake.min.js\"></script>\n" +
                                     "<script src=\"plugins/pdfmake/vfs_fonts.js\"></script>\n" +
                                     "<script src=\"plugins/datatables-buttons/js/buttons.html5.min.js\"></script>\n" +
                                     "<script src=\"plugins/datatables-buttons/js/buttons.print.min.js\"></script>\n" +
                                     "<script src=\"plugins/datatables-buttons/js/buttons.colVis.min.js\"></script>");

                         out.println("<script>\n" +
                                     "  $(function () {\n" +
                                     "    $(\"#example1\").DataTable({\n" +
                                     "    }).buttons().container().appendTo('#example1_wrapper ');\n" +
                                     "    $('#example2').DataTable({\n" +
                                     "      \"paging\": true,\n" +
                                     "      \"lengthChange\": false,\n" +
                                     "      \"searching\": false,\n" +
                                     "      \"ordering\": true,\n" +
                                     "      \"info\": true,\n" +
                                     "      \"autoWidth\": true,\n" +
                                     "      \"responsive\": true,\n" +
                                     "    });\n" +
                                     "  });\n" +
                                     "</script>");
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

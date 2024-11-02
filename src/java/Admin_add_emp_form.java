
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
public class Admin_add_emp_form extends HttpServlet
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
            
          int admin_id=Integer.parseInt((String)session.getAttribute("user_id"));
          if(admin_id>0)
          {
             RequestDispatcher head=request.getRequestDispatcher("head.html");
             head.include(request, response);
             RequestDispatcher navbar=request.getRequestDispatcher("navbar");
             navbar.include(request, response);
             
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
                                            "             <li class=\"breadcrumb-item active\">Add Empolye Form</li>\n" +
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
                                        "              <form action='Add_emp' method='post'>\n" +
                                        "                <div class='card-body'>\n" +
                                        "				<div class='row'>\n" +
                                        "                  <div class='form-group col-6'>\n" +
                                        "                    <label for='exampleInputEmail1'>Email address</label>\n" +
                                        "                    <input type='email' class='form-control' id='exampleInputEmail1' name='email' placeholder='Enter email'>\n" +
                                        "                  </div>\n" +
                                        "                  <div class='form-group col-6'>\n" +
                                        "                    <label for='exampleInputPassword1'>Password</label>\n" +
                                        "                    <input type='password' class='form-control' id='exampleInputPassword1' name='password' placeholder='Password'>\n" +
                                        "                  </div>\n" +
                                        "					</div>\n" +
                                        "					\n" +
                                        "					<div class='row'>\n" +
                                        "                  <div class='form-group col-6'>\n" +
                                        "                    <label >First name</label>\n" +
                                        "                    <input type='text' class='form-control' id='exampleInputEmail1' name='fname' placeholder='Enter first Name'>\n" +
                                        "                  </div>\n" +
                                        "                  <div class='form-group col-6'>\n" +
                                        "                    <label >Last name</label>\n" +
                                        "                    <input type='text' class='form-control' id='exampleInputEmail1' name='lname' placeholder='Enter Last Name'>\n" +
                                        "                  </div>\n" +
                                        "					</div>\n" +
                                        "					\n" +
                                        "				<div class='row'>\n" +
                                        "                  <div class='form-group col-6'>\n" +
                                        "                    <label >Salary</label>\n" +
                                        "                    <input type='number' class='form-control' id='exampleInputEmail1' name='salary' placeholder='Enter Salary'>\n" +
                                        "                  </div>\n" +
                                        "                  <div class='form-group col-6'>\n" +
                                        "                    <label >Department</label>\n" +
                                        "					 <div class=\"form-group\">\n" +
                                        "						<select class=\"form-control\" name='dept_id'>\n" +
                                        "						<option value='1'>Sells</option>\n" +
                                        "						<option value='2'>purches</option>\n" +
                                        "						<option value='3'>Devlopment</option>\n" +
                                        "						</select>\n" +
                                        "                  </div> \n" +
                                        "                  </div>\n" +
                                        "                  <div class='form-group col-6'>\n" +
                                        "                    <label >Joing Date</label>\n" +
                                        "                    <input type='date' class='form-control' id='exampleInputEmail1' name='date' placeholder='Joing Date'>\n" +
                                        "                  </div>\n" +
                                        "				</div>\n" +
                                        "					\n" +
                                        "					<div class=\"card-footer\">\n" +
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
          else
          {
              out.println("sessionError ");
          }
        }
        else
        {
            response.sendRedirect("login.html");
        }
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException
    {
        processRequest(request,response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException
    {
        processRequest(request,response);
    }
}

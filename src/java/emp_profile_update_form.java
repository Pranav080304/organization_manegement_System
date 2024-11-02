/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
/**
 *
 * @author prana
 */
public class emp_profile_update_form extends HttpServlet
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
            
            try
            {
              Connection con=Database_Connection.initializeDatabase();
              Statement stml=con.createStatement();
              String email="",password="",fname="",lname="";
              ResultSet rs=stml.executeQuery("select * from user where user_id="+emp_id+";");
              while(rs.next())
              {
                    email=rs.getString("email");
                    password=rs.getString("password");
                    fname=rs.getString("fname");
                    lname=rs.getString("lname");
              }
              
              
                out.println("<section class='content'>\n" +
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
                                        "              <form action='emp_profile_update' method='post' enctype=\"multipart/form-data\">\n" +
                                        "                <div class='card-body'>\n" +
                                        "				<div class='row'>\n" +
                                        "                  <div class='form-group col-6'>\n" +
                                        "                    <label for='exampleInputEmail1'>Email address</label>\n" +
                                        "                    <input type='email' class='form-control' id='exampleInputEmail1' name='email'value='"+email+"' placeholder='Enter email'>\n" +
                                        "                  </div>\n" +
                                        "                  <div class='form-group col-6'>\n" +
                                        "                    <label for='exampleInputPassword1'>Password</label>\n" +
                                        "                    <input type='password' class='form-control' id='exampleInputPassword1'value='"+password+"' name='password' placeholder='Password'>\n" +
                                        "                  </div>\n" +
                                        "					</div>\n" +
                                        "					\n" +
                                        "					<div class='row'>\n" +
                                        "                  <div class='form-group col-6'>\n" +
                                        "                    <label >First name</label>\n" +
                                        "                    <input type='text' class='form-control' id='exampleInputEmail1' value='"+fname+"' name='fname' placeholder='Enter first Name'>\n" +
                                        "                  </div>\n" +
                                        "                  <div class='form-group col-6'>\n" +
                                        "                    <label >Last name</label>\n" +
                                        "                    <input type='text' class='form-control' id='exampleInputEmail1' name='lname' value='"+lname+"' placeholder='Enter Last Name'>\n" +
                                        "                  </div>\n" +
                                        "			<div class=\"form-group col-6\">\n" +
                                        "                  <label for=\"exampleInputFile\">File input</label>\n" +
                                        "                  <div class=\"input-group\">\n" +
                                        "                  <div class=\"custom-file\">\n" +
                                        "                  <input type=\"file\" name='image' class=\"custom-file-input\" id=\"exampleInputFile\">\n" +
                                        "                  <label class=\"custom-file-label\" for=\"exampleInputFile\">Choose file</label>\n" +
                                        "                  </div>\n" +
                                        "                  <div class=\"input-group-append\">\n" +
                                        "                  <span class=\"input-group-text\">Upload</span>\n" +
                                        "                  </div>\n" +
                                        "                  </div>\n" +
                                        "                  </div>	\n"+
                                        "                                 </div>\n" +
                                        "					\n" +
                                        "					<div class=\"card-footer\">\n" +
                                        "					<button type=\"submit\" class=\"btn btn-primary swalDefaultSuccess\">Submit</button>\n" +
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

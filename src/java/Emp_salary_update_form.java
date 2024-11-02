/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;
import java.io.*;

/**
 *
 * @author prana
 */
public class Emp_salary_update_form extends HttpServlet
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(false);
        if(session!=null)
        {
            response.setHeader("Cache-Control", "no-store,no-cache,must-validate,max-age=0");
            response.setHeader("pragma", "no-cache");
            response.setHeader("Expire","0");
            
            RequestDispatcher head=request.getRequestDispatcher("head.html");
            head.include(request, response);
            RequestDispatcher navbar=request.getRequestDispatcher("navbar");
            navbar.include(request, response);
            try
            {
                
                int emp_id=Integer.parseInt(request.getParameter("emp_id"));
                
                out.println(" <!-- Content Wrapper. Contains page content -->\n" +
                            "  <div class=\"content-wrapper\">\n" +
                            "    <!-- Content Header (Page header) -->\n" +
                            "    <section class=\"content-header\">\n" +
                            "      <div class=\"container-fluid\">\n" +
                            "        <div class=\"row mb-2\">\n" +
                            "          <div class=\"col-sm-6\">\n" +
                            "            <h1>details</h1>\n" +
                            "          </div>\n" +
                            "        </div>\n" +
                            "      </div><!-- /.container-fluid -->\n" +
                            "    </section>\n" +
                            "\n" +
                            "    <!-- Main content -->\n" +
                            "    <section class=\"content\">\n" +
                            "\n" +
                            "      <!-- Default box -->\n" +
                            "      <div class=\"card card-solid\">\n" +
                            "        <div class=\"card-body pb-0\">\n" +
                            "          <div class=\"row\">\n" +
                            "            <div class=\"col-12 col-sm-6 col-md-4 d-flex align-items-stretch flex-column\">\n" +
                            "              <div class=\"card bg-light d-flex flex-fill\">\n" +
                            "                <div class=\"card-header text-muted border-bottom-0\">\n" +
                            "                  Empolye Details\n" +
                            "                </div>");
                
                String form="<div class='col-md-8'>\n" +
                            "            <!-- general form elements -->\n" +
                            "            <div class='card card-primary'>\n" +
                            "              <div class='card-header'>\n" +
                            "                <h3 class='card-title'>Quick Example</h3>\n" +
                            "              </div>\n" +
                            "              <!-- /.card-header -->\n" +
                            "              <!-- form start -->\n" +
                            "              <form action='Update_emp_salary' method='post'>\n" +
                            "                <div class='card-body'>\n" +
                            "				<div class='row'>\n" +
                            "                  <div class='form-group col-6'>\n" +
                            "                    <label for='exampleInputEmail1'>Change Date</label>\n" +
                            "                    <input type='date' class='form-control' id='exampleInputEmail1'  name='date' placeholder='Change Date'>\n" +
                            "                    <label for='exampleInputPassword1'>DA In Persent (%)</label>\n" +
                            "                    <input type='number' class='form-control' id='exampleInputPassword1'  name='da' placeholder='DA in persent %'>\n" +
                            "                    <label for='exampleInputPassword1'>PF In Persent (%)</label>\n" +
                            "                    <input type='number' class='form-control' id='exampleInputPassword1'  name='pf' placeholder='PF in persent %'>\n" +
                            
                            "                  </div>\n" +
                            "                  <div class='form-group col-6'>\n" +
                            "                    <label for='exampleInputPassword1'>Base Salary</label>\n" +
                            "                    <input type='number' class='form-control' id='exampleInputPassword1'  name='base_salary' placeholder='Base Salary'>\n" +
                            "                    <label for='exampleInputPassword1'>HRA In Persent (%)</label>\n" +
                            "                    <input type='number' class='form-control' id='exampleInputPassword1'  name='hra' placeholder='HRA In Persent %'>\n" +
                            
                            "                  </div>\n" +
                            "					</div>\n" +
                            "                    <input type='hidden' value='"+emp_id+"' name='emp_id' >\n" +
                            "					<div class=\"card-footer\">\n" +
                            "					<button type=\"submit\" class=\"btn btn-primary\">Submit</button>\n" +
                            "					<button type=\"clear\" class=\"btn btn-danger\">Clear</button>\n" +
                            "					</div>\n" +
                            "					</div>\n" +
                            "                  </div>\n" +
                            "                </div>\n" +
                            "                <!-- /.card-body -->\n" +
                            "        </div>\n" ;
                
                
                String image="",name="",email="",dept_name="",date="";
                double salary=0;
                Connection con=Database_Connection.initializeDatabase();
                Statement stml=con.createStatement();
                ResultSet rs=stml.executeQuery("SELECT * FROM salaries JOIN user ON salaries.user_id=user.user_id JOIN department ON user.dept_id=department.dept_id WHERE salaries.user_id="+emp_id+" order by from_date DESC LIMIT 1;");
                while(rs.next())
                {
                     image=rs.getString("image");
                     name=rs.getString("fname")+" "+rs.getString("lname");
                     email=rs.getString("email");
                     dept_name=rs.getString("dept_name");
                     salary=Double.parseDouble(rs.getString("salary"));
                     date=rs.getString("from_date");
                }
                
                out.println("<div class=\"card-body pt-0\">\n" +
                            "                  <div class=\"row\">\n" +
                            "                    <div class=\"col-7\">\n" +
                            "                      <h2 class=\"lead\"><b>"+name+"</b></h2>\n" +
                            "                      <p class=\"text-muted text-sm\"><b>Department: </b> "+dept_name+" </p>\n" +
                            "                      <ul class=\"ml-4 mb-0 fa-ul text-muted\">\n" +
                            "                        <li class=\"\"><span class=\"fa-li\"><i class=\"fa-solid fa-money-check\"></i></span> Salary:"+salary+" </li>\n" +
                            "                        <li class=\"\"><span class=\"fa-li\"><i class=\"fa-regular fa-envelope\"></i></span> Email #:"+email+"</li>\n" +
                            "                        <li class=\"\"><span class=\"fa-li\"><i class=\"fa-regular fa-envelope\"></i></span> from date #:"+date+"</li>\n" +
                            "                      </ul>\n" +
                            "                    </div>\n" +
                            "                    <div class=\"col-5 text-center\">\n" +
                            "                      <img src='"+image+"' alt=\"user-avatar\" class=\"img-circle img-fluid\">\n" +
                            "                    </div>\n" +
                            "                  </div>\n" +
                            "                </div>");
                
                
                // fotter
                out.println("<div class=\"card-footer\">\n" +
                            "                  <div class=\"text-right\">\n" +
                            "                    <a href=\"#\" class=\"btn btn-sm bg-teal\">\n" +
                            "                      <i class=\"fas fa-comments\"></i>\n" +
                            "                    </a>\n" +
                            "                    <a href=\"#\" class=\"btn btn-sm btn-primary\">\n" +
                            "                      <i class=\"fas fa-user\"></i> View Profile\n" +
                            "                    </a>\n" +
                            "                  </div>\n" +
                            "                </div>\n" +
                            "              </div>\n" +
                            "            </div>\n" );
                out.println(form);
                
                out.println("        </div>\n" +
                            "          </div>\n"+
                            "      </div>\n" +
                            "      <!-- /.card -->\n"+
                            "    </section>\n" +
                            "    <!-- /.content -->\n" +
                            "  </div>\n" +
                            "  <!-- /.content-wrapper -->");
                
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author prana
 */

public class Emp_details extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(false);
        if(session !=null)
        {
            
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0); 
            
             RequestDispatcher head=request.getRequestDispatcher("head.html");
             head.include(request, response);
             RequestDispatcher navbar=request.getRequestDispatcher("navbar");
             navbar.include(request, response);
            
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
                                            "                <a href='Admin_add_emp_form'\n"+
                                            "                <button class='btn-lg btn border bg-primary '>Add Empolye</button>\n"+
                                            "                </a>\n"+
                                            "                </div>\n" +
                                            "              <!-- /.card-header -->\n" +
                                            "              <div class=\"card-body\">\n" +
                                            "                <table id=\"example1\" class=\"table table-bordered table-striped\">\n" +
                                            "                  <thead>\n" +
                                            "                  <tr>\n" +
                                            "                    <th>Empolye name</th>\n" +
                                            "                    <th>Empolye Department</th>\n" +
                                            "                    <th>salaries</th>\n" +
                                            "                    <th>Edit Empolye</th>\n" +
                                            "                  </tr>\n"+
                                            "                  <thead>");
            
            try
            {
                HashSet<Integer> emp_id_check = new HashSet<Integer>();
                Connection con=Database_Connection.initializeDatabase();
                Statement stml=con.createStatement();
                ResultSet rs=stml.executeQuery("SELECT *  FROM  salaries JOIN user ON salaries.user_id=user.user_id JOIN department on user.dept_id=department.dept_id WHERE user.show=1  ORDER BY salaries.from_date DESC;");
                while(rs.next())
                {
                    String name=rs.getString("fname")+" "+rs.getString("lname");
                    String dept_name=rs.getString("dept_name");
                    String salary=rs.getString("salary");
                    int id=Integer.parseInt(rs.getString("user_id"));
                    
                    if(emp_id_check.contains(id)==false)
                    {
                        out.println(" <tbody>\n" +
                                "                  <tr>\n" +
                                "                    <td>"+name+"</td>\n" +
                                "                    <td>"+dept_name +"</td>\n" +                   
                                "                    <td>"+salary+"</td>\n" +
                                "                    <td><a href='Emp_edit_form?user_id="+id+"'><button class='btn-sm btn border bg-primary '>Edit Empolye</button><a href='Delete_emp?id="+id+"'><button class='btn-sm btn border bg-danger '>Delete Empolye</button></a><a href='Emp_salary_update_form?emp_id="+id+"'><button class='btn-sm btn border bg-info'>Empolye Salary</button></a></td>\n" +        
                                "                  </tr>\n" ); 
                        emp_id_check.add(id);
                    }
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

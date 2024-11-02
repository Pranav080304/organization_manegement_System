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
public class emp_navbar extends HttpServlet
{
    protected void ProcessRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(false);
        if(session!=null)
        {
            int emp_id=Integer.parseInt((String)session.getAttribute("emp_id"));
            
            RequestDispatcher head=request.getRequestDispatcher("head.html");
            head.include(request, response);
            
            try
            {
                Connection con=Database_Connection.initializeDatabase();
                Statement stml=con.createStatement();
                ResultSet rs=stml.executeQuery("SELECT * FROM user WHERE user_id="+emp_id+"; ");
                while(rs.next())
                {
                   String name=rs.getString("fname")+" "+rs.getString("lname");
                   String image=rs.getString("image");
                   
                   out.println("<body class=\"hold-transition sidebar-mini layout-navbar-fixed layout-fixed\">\n" +
                            "<!-- Site wrapper -->\n" +
                            "<div class=\"wrapper\">\n" +
                            "  <!-- Navbar -->\n" +
                            "  <nav class=\"main-header navbar navbar-expand navbar-white navbar-light navbar-fixed\">\n" +
                            "    <!-- Left navbar links -->\n" +
                            "    <ul class=\"navbar-nav\">\n" +
                            "      <li class=\"nav-item\">\n" +
                            "        <a class=\"nav-link\" data-widget=\"pushmenu\" href=\"#\" role=\"button\"><i class=\"fas fa-bars\"></i></a>\n" +
                            "      </li>\n" +
                            "      <li class=\"nav-item d-none d-sm-inline-block\">\n" +
                            "        <a href=\"../../index3.html\" class=\"nav-link\">Home</a>\n" +
                            "      </li>\n" +
                            "      <li class=\"nav-item d-none d-sm-inline-block\">\n" +
                            "        <a href=\"#\" class=\"nav-link\">Contact</a>\n" +
                            "      </li>\n" +
                            "    </ul>\n" +
                            "\n" +
                            "    <!-- Right navbar links -->\n" +
                            "    <ul class=\"navbar-nav ml-auto\">\n" +
                            "      <!-- Navbar Search -->\n" +
                            "      <li class=\"nav-item\">\n" +
                            "        <a class=\"nav-link\" data-widget=\"navbar-search\" href=\"#\" role=\"button\">\n" +
                            "          <i class=\"fas fa-search\"></i>\n" +
                            "        </a>\n" +
                            "        <div class=\"navbar-search-block\">\n" +
                            "          <form class=\"form-inline\">\n" +
                            "            <div class=\"input-group input-group-sm\">\n" +
                            "              <input class=\"form-control form-control-navbar\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\">\n" +
                            "              <div class=\"input-group-append\">\n" +
                            "                <button class=\"btn btn-navbar\" type=\"submit\">\n" +
                            "                  <i class=\"fas fa-search\"></i>\n" +
                            "                </button>\n" +
                            "                <button class=\"btn btn-navbar\" type=\"button\" data-widget=\"navbar-search\">\n" +
                            "                  <i class=\"fas fa-times\"></i>\n" +
                            "                </button>\n" +
                            "              </div>\n" +
                            "            </div>\n" +
                            "          </form>\n" +
                            "        </div>\n" +
                            "      </li>\n" +
                            "      <li class=\"nav-item\">\n" +
                            "        <a class=\"nav-link\" data-widget=\"fullscreen\" href=\"#\" role=\"button\">\n" +
                            "          <i class=\"fas fa-expand-arrows-alt\"></i>\n" +
                            "        </a>\n" +
                            "      </li>\n" +
                            "      <li class=\"nav-item\">\n" +
                            "        <a class=\"nav-link\" data-widget=\"control-sidebar\" data-slide=\"true\" href=\"#\" role=\"button\">\n" +
                            "          <i class=\"fas fa-th-large\"></i>\n" +
                            "        </a>\n" +
                            "      </li>\n" +
                            "    </ul>\n" +
                            "  </nav>\n" +
                            "  <!-- /.navbar -->\n" +
                            "\n" +
                            "  <!-- Main Sidebar Container -->\n" +
                            "  <aside class=\"main-sidebar sidebar-dark-primary elevation-4\">\n" +
                            "    <!-- Brand Logo -->\n" +
                            "    <a href=\"../../index3.html\" class=\"brand-link\">\n" +
                            "      <img src=\"dist/img/AdminLTELogo.png\" alt=\"AdminLTE Logo\" class=\"brand-image img-circle elevation-3\" style=\"opacity: .8\">\n" +
                            "      <span class=\"brand-text font-weight-light\"></span>\n" +
                            "    </a>\n" +
                            "\n" +
                            "    <!-- Sidebar -->\n" +
                            "    <div class=\"sidebar\">\n" +
                            "      <!-- Sidebar user (optional) -->\n" +
                            "      <div class=\"user-panel mt-3 pb-3 mb-3 d-flex\">\n" +
                            "        <div class=\"image\">\n" +
                            "          <img src='"+image+"' class=\"img-circle elevation-2\" alt=\"User Image\">\n" +
                            "        </div>\n" +
                            "        <div class=\"info\">\n" +
                            "             <div class=\"dropdown\">\n" +
                            "                <button class=\"btn btn-secondary dropdown-toggle\" type=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">\n" +
                            "                 "+name+" \n" +
                            "                </button>\n" +
                            "                <ul class=\"dropdown-menu\">\n" +
                            "                <li><a class=\"dropdown-item\" href=\"#\">Update Profile</a></li>\n" +
                            "                <li><a class=\"dropdown-item bg-dengar\" href=\"#\">Logout</a></li>\n" +
                            "                </ul>\n" +
                            "             </div>"+
                            "        </div>\n" +
                            "      </div>\n" +
                            "\n" +
                            "      <!-- SidebarSearch Form -->\n" +
                            "      <div class=\"form-inline\">\n" +
                            "        <div class=\"input-group\" data-widget=\"sidebar-search\">\n" +
                            "          <input class=\"form-control form-control-sidebar\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\">\n" +
                            "          <div class=\"input-group-append\">\n" +
                            "            <button class=\"btn btn-sidebar\">\n" +
                            "              <i class=\"fas fa-search fa-fw\"></i>\n" +
                            "            </button>\n" +
                            "          </div>\n" +
                            "        </div>\n" +
                            "      </div>\n" +
                            "\n" +
                            "      <!-- Sidebar Menu -->\n" +
                            "      <nav class=\"mt-2\">\n" +
                            "        <ul class=\"nav nav-pills nav-sidebar flex-column\" data-widget=\"treeview\" role=\"menu\" data-accordion=\"false\">\n" +
                            "          <!-- Add icons to the links using the .nav-icon class\n" +
                            "               with font-awesome or any other icon font library -->\n" +
                            "          <li class=\"nav-item\">\n" +
                            "            <a href=\"emp_profile_update_form\" class=\"nav-link\">\n" +
                            "              <i class=\"nav-icon fa-regular fa-user\"></i></i>\n" +
                            "              <p>\n" +
                            "                Update Profile\n" +
                            "              </p>\n" +
                            "            </a>\n" +
                            "          </li>\n"+         
                            "          <li class=\"nav-item \">\n" +
                            "            <a href=\"emp_dashbord\" class=\"nav-link \">\n" +
                            "              <i class=\"nav-icon fas fa-tachometer-alt\"></i>\n" +
                            "              <p>\n" +
                            "                Dashboard\n" +
                            "                <i class=\"right fas \"></i>\n" +
                            "              </p>\n" +
                            "            </a>\n" +
                            "          </li>\n" +
                            "          <li class=\"nav-item\">\n" +
                            "            <a href=\"log_out\" class=\"nav-link\">\n" +
                            "              <i class=\"nav-icon fa-solid fa-right-from-bracket\"></i>\n" +
                            "              <p>\n" +
                            "                Log out\n" +
                            "              </p>\n" +
                            "            </a>\n" +
                            "          </li>\n"+         
                            "      </ul>\n"+
                            "   </nav>\n"+
                            "<!-- /.sidebar-menu -->\n"+
                            "</div>\n"+
                            "<!-- /.sidebar -->\n"+
                            "</aside>");
                }
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
            out.println("Session not Found");
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

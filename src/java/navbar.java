
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
public class navbar extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        HttpSession session=request.getSession();
        int user_id=Integer.parseInt((String)session.getAttribute("user_id"));
        
        if(user_id>0)
        {
          PrintWriter out=response.getWriter();
          response.setContentType("text/html;charset=UTF-8");
          
          RequestDispatcher head=request.getRequestDispatcher("/head.html");
          head.include(request, response);
          
         try
         {
            Connection con=Database_Connection.initializeDatabase();
            Statement stml=con.createStatement();
            ResultSet rs=stml.executeQuery("select * from user where user_id="+user_id+";");
            while(rs.next())
            {
                String fname=rs.getString("fname");
                String lname=rs.getString("lname");
                String image=rs.getString("image");

                // navbar start

                out.println("<body class= \"hold-transition sidebar-mini layout-navbar-fixed layout-fixed \">  \n" +
                            "<!-- Site wrapper -->  \n" +
                            " <div class= \"wrapper \">  \n" +
                            "   <!-- Navbar -->  \n" +
                            "   <nav class= \"main-header navbar navbar-expand navbar-white navbar-light navbar-fixed \">  \n" +
                            "	 <!-- Left navbar links -->  \n" +
                            "	 <ul class= \"navbar-nav \">  \n" +
                            "	   <li class= \"nav-item \">  \n" +
                            "		 <a class= \"nav-link \" data-widget= \"pushmenu \" href= \"# \" role= \"button \"><i class= \"fas fa-bars \"></i></a>  \n" +
                            "	   </li>  \n" +
                            "	   <li class= \"nav-item d-none d-sm-inline-block \">  \n" +
                            "		 <a href= \"../../index3.html \" class= \"nav-link \">Home</a>  \n" +
                            "	  </li>  \n" +
                            "	   <li class= \"nav-item d-none d-sm-inline-block \">  \n" +
                            "		 <a href= \"# \" class= \"nav-link \">Contact</a>  \n" +
                            "	   </li>  \n" +
                            "	 </ul>  \n" +
                            "   \n" +
                            "	<!-- Right navbar links -->  \n" +
                            "	 <ul class= \"navbar-nav ml-auto \">  \n" +
                            "	   <!-- Navbar Search -->  \n" +
                            "	   <li class= \"nav-item \">  \n" +
                            "		 <a class= \"nav-link \" data-widget= \"navbar-search \" href= \"# \" role= \"button \">  \n" +
                            "		   <i class= \"fas fa-search \"></i>  \n" +
                            "		 </a>  \n" +
                            "		 <div class= \"navbar-search-block \">  \n" +
                            "		   <form class= \"form-inline \">  \n" +
                            "			 <div class= \"input-group input-group-sm \">  \n" +
                            "			   <input class= \"form-control form-control-navbar \" type= \"search \" placeholder= \"Search \" aria-label= \"Search \">  \n" +
                            "			   <div class= \"input-group-append \">  \n" +
                            "				 <button class= \"btn btn-navbar \" type= \"submit \">  \n" +
                            "				   <i class= \"fas fa-search \"></i>  \n" +
                            "				 </button>  \n" +
                            "				 <button class= \"btn btn-navbar \" type= \"button \" data-widget= \"navbar-search \">  \n" +
                            "				   <i class= \"fas fa-times \"></i>  \n" +
                            "				 </button>  \n" +
                            "			   </div>  \n" +
                            "			 </div>  \n" +
                            "		   </form>  \n" +
                            "		 </div>  \n" +
                            "	   </li>  \n" +
                            "	   <li class= \"nav-item \">  \n" +
                            "		 <a class= \"nav-link \" data-widget= \"fullscreen \" href= \"# \" role= \"button \">  \n" +
                            "		   <i class= \"fas fa-expand-arrows-alt \"></i>  \n" +
                            "		 </a>  \n" +
                            "	  </li>  \n" +
                            "	   <li class= \"nav-item \">  \n" +
                            "		 <a class= \"nav-link \" data-widget= \"control-sidebar \" data-slide= \"true \" href= \"# \" role= \"button \">  \n" +
                            "		   <i class= \"fas fa-th-large \"></i>  \n" +
                            "		 </a>  \n" +
                            "	   </li>  \n" +
                            "	 </ul>  \n" +
                            "   </nav>  \n" +
                            "   <!-- /.navbar -->  \n" +
                            "  \n" +
                            "   <!-- Main Sidebar Container -->  \n" +
                            "   <aside class= \"main-sidebar sidebar-dark-primary elevation-4 \">  \n" +
                            "	 <!-- Brand Logo -->  \n" +
                            "	 <a href= \"../../index3.html \" class= \"brand-link \">  \n" +
                            "	   <img src= \"dist/img/AdminLTELogo.png \" alt= \"AdminLTE Logo \" class= \"brand-image img-circle elevation-3 \" style= \"opacity: .8 \">  \n" +
                            "	   <span class= \"brand-text font-weight-light \"></span>  \n" +
                            "	 </a>  \n" +
                            "   \n" +
                            "	 <!-- Sidebar -->  \n" +
                            "	 <div class= \"sidebar \">  \n" +
                            "	   <!-- Sidebar user (optional) -->  \n" +
                            "	   <div class= \"user-panel mt-3 pb-3 mb-3 d-flex \">  \n" +
                            "		 <div class= \"image \">  \n" +
                            "		   <img src= \""+image+" \" class= \"img-circle elevation-2 \" alt= \"User Image \">  \n" +
                            "		 </div>  \n" +
                            "		 <div class= \"info \">  \n" +
                            "		   <a href= \"# \" class= \"d-block \">"+fname+" "+lname+"</a>  \n" +
                            "		 </div>  \n" +
                            "	   </div>  \n" +
                            "   \n" +
                            "	   <!-- SidebarSearch Form -->  \n" +
                            "	   <div class= \"form-inline \">  \n" +
                            "		 <div class= \"input-group \" data-widget= \"sidebar-search \">  \n" +
                            "		   <input class= \"form-control form-control-sidebar \" type= \"search \" placeholder= \"Search \" aria-label= \"Search \">  \n" +
                            "		   <div class= \"input-group-append \">  \n" +
                            "			 <button class= \"btn btn-sidebar \">  \n" +
                            "			   <i class= \"fas fa-search fa-fw \"></i>  \n" +
                            "			 </button>  \n" +
                            "		   </div>  \n" +
                            "		 </div>  \n" +
                            "	   </div>  \n" +
                            "   \n" +
                            "	   <!-- Sidebar Menu -->  \n" +
                            "	   <nav class= \"mt-2 \">  \n" +
                            "		 <ul class= \"nav nav-pills nav-sidebar flex-column \" data-widget= \"treeview \" role= \"menu \" data-accordion= \"false \">  \n" +
                            "		   <!-- Add icons to the links using the .nav-icon class  \n" +
                            "				with font-awesome or any other icon font library -->  \n" +
                            "		   <li class= \"nav-item  \">  \n" +
                            "			 <a href= \"Admin_dashbord \" class= \"nav-link  \">  \n" +
                            "			   <i class= \"nav-icon fas fa-tachometer-alt \"></i>  \n" +
                            "			   <p>  \n" +
                            "				 Dashboard  \n" +
                            "				 <i class= \"right fas  \"></i>  \n" +
                            "			   </p>  \n" +
                            "			</a>  \n" +
                            "		   </li>  \n" +
                            "		   <li class= \"nav-item \">  \n" +
                            "			 <a href= \"Admin_product\" class= \"nav-link \">  \n" +
                            "			 <i class=\"nav-icon fa-solid fa-store\"></i> \n" +
                            "			   <p>  \n" +
                            "				 Product  \n" +
                            "			   </p>  \n" +
                            "			 </a>  \n" +
                            "		   </li> \n" +
                            "	 <li class= \"nav-item \">  \n" +
                            "			 <a href= \"Admin_user \" class= \"nav-link \">  \n" +
                            "			   <i class= \"nav-icon fa-solid fa-users \" style= \"color: #f7f7f7; \"></i>  \n" +
                            "			   <p>  \n" +
                            "			        User  \n" +
                            "			   </p>  \n" +
                            "			 </a>  \n" +
                            "		   </li>         \n" +
                             "	 <li class= \"nav-item \">  \n" +
                            "			 <a href= \"Emp_details \" class= \"nav-link \">  \n" +
                            "			   <i class=\"nav-icon fa-solid fa-address-card\"></i>  \n" +
                            "			   <p>  \n" +
                            "			        Empolye   \n" +
                            "			   </p>  \n" +
                            "			 </a>  \n" +
                            "		   </li>         \n" +        
                            "	 <li class= \"nav-item \">  \n" +
                            "			 <a href= \"Action_form \" class= \"nav-link \">  \n" +
                            "			   <i class=\"nav-icon fa-solid fa-cart-shopping\"></i>  \n" +
                            "			   <p>  \n" +
                            "			        Order  \n" +
                            "			   </p>  \n" +
                            "			 </a>  \n" +
                            "		   </li>         \n" +       
                            "	 <li class= \"nav-item \">  \n" +
                            "			 <a href= \"Action_form \" class= \"nav-link \">  \n" +
                            "			   <i class=\"nav-icon fa-solid fa-cart-shopping\"></i>  \n" +
                            "			   <p>  \n" +
                            "			        List Manufacturer  \n" +
                            "			   </p>  \n" +
                            "			 </a>  \n" +
                            "		   </li>         \n" +  
                            "          <li class=\"nav-item\">\n" +
                            "            <a href=\"log_out\" class=\"nav-link\">\n" +
                            "              <i class=\"nav-icon fa-solid fa-right-from-bracket\"></i>\n" +
                            "              <p>\n" +
                            "                Log out\n" +
                            "              </p>\n" +
                            "            </a>\n" +
                            "          </li>\n"+          
                            "	   </ul> \n" +
                            "	</nav> \n" +
                            " <!-- /.sidebar-menu --> \n" +
                            " </div> \n" +
                            " <!-- /.sidebar --> \n" +
                            " </aside>");
                
//                out.println("<script src=\"plugins/jquery/jquery.min.js\"></script> \n" +
//                            "<!-- Bootstrap 4 --> \n" +
//                            "<script src=\"plugins/bootstrap/js/bootstrap.bundle.min.js\"></script> \n" +
//                            "<!-- AdminLTE App --> \n" +
//                            "<script src=\"dist/js/adminlte.min.js\"></script> \n" +
//                            "<!-- AdminLTE for demo purposes --> \n" +
//                            "<script src=\"dist/js/demo.js\"></script>\n" +
//                            "    </body>\n" +
//                            "\n");
                RequestDispatcher script=request.getRequestDispatcher("scripts.html");
                script.include(request, response);
            }                
         }
         catch(SQLException e)
            {
             out.println("SQLException from navbar"+e);
            }
         catch(ClassNotFoundException e)
         {
             out.println("ClassNotFoundException from navbar"+e);
         }
          
        }
    }
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            processRequest(request, response);
        } 

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        } 
        

}

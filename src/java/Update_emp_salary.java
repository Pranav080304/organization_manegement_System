/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;
/**
 *
 * @author prana
 */
public class Update_emp_salary extends HttpServlet
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(false);
        if(session!=null)
        {
            try
            {
                int emp_id=Integer.parseInt(request.getParameter("emp_id"));
                String date=request.getParameter("date");
                double base_salary=Integer.parseInt(request.getParameter("base_salary"));
                double pf=Integer.parseInt(request.getParameter("pf"))*base_salary/100;
                double hra=Integer.parseInt(request.getParameter("hra"))*base_salary/100;
                double da=Integer.parseInt(request.getParameter("da"))*base_salary/100;
                double gross_salary=base_salary+pf+hra+da;
                double net_salary=gross_salary-pf;
                out.println(hra+""+pf+""+da+"");
                
                Connection con=Database_Connection.initializeDatabase();
                PreparedStatement st=con.prepareStatement("UPDATE `salaries` SET `to_date`=? where user_id=? ORDER BY salaries.from_date DESC LIMIT 1;");
                st.setString(1,date);
                st.setInt(2, emp_id);
                st.executeUpdate();
                
                PreparedStatement st1=con.prepareStatement("INSERT INTO `salaries`(`user_id`, `salary`, `from_date`, `hra`, `basic_salaries`, `da`, `pf`, `gross_salary`) VALUES (?,?,?,?,?,?,?,?);");
                st1.setInt(1, emp_id);
                st1.setDouble(2,net_salary);
                st1.setString(3,date);
                st1.setDouble(4,hra);
                st1.setDouble(5,base_salary); 
                st1.setDouble(6,da);
                st1.setDouble(7,pf);
                st1.setDouble(8,gross_salary);
                st1.executeUpdate();
                
                response.sendRedirect("Emp_details");
            }
            catch(SQLException e)
            {
                out.println("SQLException "+e);
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
        processRequest(request,response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        processRequest(request,response);
    }
}

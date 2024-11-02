/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;
import java.io.*;
/**
 *
 * @author prana
 */
public class log_out extends HttpServlet
{
    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        HttpSession session=request.getSession(false);
       session.invalidate();
       response.sendRedirect("login.html");
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        processRequest(request,response);
    }
}

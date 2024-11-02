
import jakarta.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author prana
 */
public class Database_Connection extends HttpServlet
{
     protected static Connection initializeDatabase()
            throws SQLException,ClassNotFoundException
    {
      String dbDriver = "com.mysql.cj.jdbc.Driver";
       String dbUrl = "jdbc:mysql://localhost:3306/"; 
       
       String dbName="demo_bootstrap";
       String dbUsername="root";
       String dbPassword="";
       
       Class.forName(dbDriver);
       Connection con=DriverManager.getConnection(dbUrl+dbName,dbUsername,dbPassword );
       return con;
    }
}

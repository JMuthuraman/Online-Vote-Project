package onlinevote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection 
{
     public static final String URL="jdbc:mysql://localhost:3306/votingDB";
     public static final String USERNAME="root";
     public static final String PASSWORD="root";
     
     public static Connection getConnection() throws SQLException 
     {
		return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    	 
     }
}

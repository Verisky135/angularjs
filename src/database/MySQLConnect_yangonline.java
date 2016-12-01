package database;

import java.sql.DriverManager;
import java.sql.Connection;

public class MySQLConnect_yangonline {

	public static Connection conn;
	
	public static void createConnection() {

		try {

			// This is needed to use Connector/J. It basically creates a new instance
			// of the Connector/J jdbc driver. 
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
		
			
			/* To connect to the database, you need to use a JDBC url with the following 
			   format ([xxx] denotes optional url components):
			   jdbc:mysql://[hostname][:port]/[dbname][?param1=value1][&param2=value2]... 
			   By default MySQL's hostname is "localhost." The database used here is 
			   called "mydb" and MySQL's default user is "root". If we had a database 
			   password we would add "&password=xxx" to the end of the url.   
			*/ 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/saleproject02_yangonline?user=root"); 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

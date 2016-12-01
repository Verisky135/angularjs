package web_services_soap.functionalities;

import database.MySQLConnect_yangonline;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Locale;


public class daftaronlinefunc {	


	public daftaronlinefunc() {	
	}

	public String search() {
		
		String xml;
		
		// fetch the items from database
		PreparedStatement statement = null;
		ResultSet sqlResult = null;
		
		MySQLConnect_yangonline.createConnection();
		
		
		try {
			// Generate the SQL query
			
				statement = MySQLConnect_yangonline.conn.prepareStatement("SELECT * FROM tabelyangonline");
				
				System.out.println("DAFTAR ONLINE HERE BRO");	
			
			
			// Get the query results and display them. 
			sqlResult = statement.executeQuery();
			
			
			// insert the results into an array
			int iterate_row;
			
			//int numColumns = 9;		
			int rowCount = sqlResult.last() ? sqlResult.getRow() : 0;
			
			//String[] fetched_products = new String[rowCount][numColumns];
			
			/*
			System.out.println("row search_catalog: " + rowCount + " " + sqlResult.last() + " " + 
					
					sqlResult.getRow());
			 */
		
			// create an XML text as the response
			
			if (rowCount > 0) {
				
				
				sqlResult.first();
				
				xml = "";
				xml += "<?xml version='1.0' encoding='utf-8'?>\n";
				xml += "<daftaronline xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
				xml += "<numOfSearch>\n";
				xml += String.valueOf(rowCount) + "\n";
				xml += "</numOfSearch>\n";
				
				
				iterate_row = 0;
				
				
				do {	
					
					

					xml += "<username" + iterate_row + ">\n";
					xml += sqlResult.getString("username")+"\n";
					xml += "</username" + iterate_row + ">\n";
					
					
					iterate_row++;
					
				} while (sqlResult.next());
			
				xml += "</daftaronline>";
		
			}
			else {
			
				System.out.println("daftaronline numOfSearch 0");
				
				xml = "";
				xml += "<?xml version='1.0' encoding='utf-8'?>\n";
				xml += "<daftaronline xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
				xml += "<numOfSearch>\n";
				xml += "0\n";
				xml += "</numOfSearch>\n";
				xml += "</daftaronline>";
				
			}
			 
			
			return xml;
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
	
			// Close the connection. 
			try {
				sqlResult.close(); 
				statement.close(); 
				MySQLConnect_yangonline.conn.close();
			} catch (Exception ec) {
				ec.printStackTrace();
			}
		}
	
		System.out.println("daftaronline exception");
		
		xml = "";
		xml += "<?xml version='1.0' encoding='utf-8'?>\n";
		xml += "<daftaronline xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
		xml += "<numOfSearch>\n";
		xml += "0\n";
		xml += "</numOfSearch>\n";
		xml += "</daftaronline>";
		
		return xml;
		
	}
	
}


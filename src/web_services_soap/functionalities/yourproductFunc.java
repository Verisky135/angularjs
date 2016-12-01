package web_services_soap.functionalities;

import database.MySQLConnect_marketplace;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Locale;


public class yourproductFunc {

	private String uglob;
	private String atglob;
	private String etglob;
	private String ieglob;
	
	
	public yourproductFunc(String uglob, String atglob, String etglob, String ieglob) {
		this.uglob = uglob;
		this.atglob = atglob;
		this.etglob = etglob;
		this.ieglob = ieglob;
	}
	
	
	public String toRupiahFormat(String nominal) {
        
		String rupiah = "";
        Locale locale = null;
        NumberFormat rupiahFormat = null;
        
        locale = new Locale("ca", "CA");
        rupiahFormat = NumberFormat.getCurrencyInstance(locale);
         
        rupiah = rupiahFormat.format(Double.parseDouble(nominal)).substring(4);
         
        return rupiah;
    }
	
	
	public String yourprod() {
		
		String xml;
		
		// fetch the items from database
		PreparedStatement statement = null;
		ResultSet sqlResult = null;
		
		MySQLConnect_marketplace.createConnection();
		
		
		try {
			// Generate the SQL query
			statement = MySQLConnect_marketplace.conn.prepareStatement("SELECT id, username, name, description, price, photo, likes, purchases, added_time FROM product_sell WHERE username=? ORDER BY added_time DESC");
			statement.setString(1, uglob);														
			
			
			// Get the query results and display them. 
			sqlResult = statement.executeQuery();
			
			
			// insert the results into an array
			int iterate_row;
			//int numColumns = 9;		
			int rowCount = sqlResult.last() ? sqlResult.getRow() : 0;
			
			//String[] fetched_products = new String[rowCount][numColumns];
					
			System.out.println("row your_product: " + rowCount);
		
		
			// create an XML text as the response
			
			if (rowCount > 0) {
				
				sqlResult.first();
				
				xml = "";
				xml += "<?xml version='1.0' encoding='utf-8'?>\n";
				xml += "<yourproduct xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
				xml += "<access-validity>\n";
				xml += "200\n";
				xml += "</access-validity>\n";			
				xml += "<numOfSearch>\n";
				xml += String.valueOf(rowCount) + "\n";
				xml += "</numOfSearch>\n";
				
				iterate_row = 0;
				
				do {
					
					xml += "<product" + iterate_row + ">\n";
					xml += "<id" + iterate_row + ">\n";
					xml += sqlResult.getString("id")+"\n";
					xml += "</id" + iterate_row + ">\n";
					xml += "<username" + iterate_row + ">\n";
					xml += sqlResult.getString("username")+"\n";
					xml += "</username" + iterate_row + ">\n";
					xml += "<name" + iterate_row + ">\n";
					xml += sqlResult.getString("name")+"\n";
					xml += "</name" + iterate_row + ">\n";
					xml += "<description" + iterate_row + ">\n";
					xml += sqlResult.getString("description")+"\n";
					xml += "</description" + iterate_row + ">\n";
					xml += "<price" + iterate_row + ">\n";
					xml += toRupiahFormat(String.valueOf(sqlResult.getInt("price")))+"\n";
					xml += "</price" + iterate_row + ">\n";
					xml += "<photo" + iterate_row + ">\n";
					xml += sqlResult.getString("photo")+"\n";
					xml += "</photo" + iterate_row + ">\n";
					xml += "<likes" + iterate_row + ">\n";
					xml += String.valueOf(sqlResult.getInt("likes"))+"\n";
					xml += "</likes" + iterate_row + ">\n";
					xml += "<purchases" + iterate_row + ">\n";
					xml += String.valueOf(sqlResult.getInt("purchases"))+"\n";
					xml += "</purchases" + iterate_row + ">\n";
					xml += "<added_time" + iterate_row + ">\n";
					xml += sqlResult.getTimestamp("added_time")+"\n";
					xml += "</added_time" + iterate_row + ">\n";
					
					xml += "</product" + iterate_row + ">\n";
				
					iterate_row++;	
					
				} while (sqlResult.next());
				
				
				xml += "</yourproduct>";
			
			} else {
			
				xml = "";
				xml += "<?xml version='1.0' encoding='utf-8'?>\n";
				xml += "<yourproduct xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
				xml += "<access-validity>\n";
				xml += "200\n";
				xml += "</access-validity>\n";				
				xml += "<numOfSearch>\n";
				xml += "0\n";
				xml += "</numOfSearch>\n";
				xml += "</yourproduct>";
				
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
				MySQLConnect_marketplace.conn.close();
			} catch (Exception ec) {
				ec.printStackTrace();
			}
		}
	
		
		xml = "";
		xml += "<?xml version='1.0' encoding='utf-8'?>\n";
		xml += "<yourproductxmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
		xml += "<access-validity>\n";
		xml += "200\n";
		xml += "</access-validity>\n";
		xml += "<numOfSearch>\n";
		xml += "-1\n";
		xml += "</numOfSearch>\n";
		xml += "</yourproduct>";
		
		return xml;
		
	}
	
}


// insert into the array
/*
fetched_products[iterate_row][0] = sqlResult.getString("id");
fetched_products[iterate_row][0] = sqlResult.getString("username");
fetched_products[iterate_row][0] = sqlResult.getString("name");
fetched_products[iterate_row][0] = sqlResult.getString("description");
fetched_products[iterate_row][0] = toRupiahFormat(String.valueOf(sqlResult.getInt("price")));
fetched_products[iterate_row][0] = sqlResult.getString("photo");
fetched_products[iterate_row][0] = String.valueOf(sqlResult.getInt("likes"));
fetched_products[iterate_row][0] = String.valueOf(sqlResult.getInt("purchases"));
fetched_products[iterate_row][0] = String.valueOf(sqlResult.getInt("added_time"));
*/


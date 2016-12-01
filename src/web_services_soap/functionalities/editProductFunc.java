package web_services_soap.functionalities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.MySQLConnect_marketplace;

public class editProductFunc {

	
	private String product_id;
	
	
	public editProductFunc(String product_id) {
		
		this.product_id = product_id;
		
	}
	
	public String edit() {
		
		String xml = "";
		
		PreparedStatement statement = null;
		ResultSet sqlResult2 = null;
		int rowAffected = 0;
		
		MySQLConnect_marketplace.createConnection();
		
		try {
			
			
			// Generate the SQL query. 
			statement = MySQLConnect_marketplace.conn.prepareStatement("SELECT id, name, description, price, photo FROM product_sell WHERE id=?");
		
			statement.setInt(1, Integer.parseInt(product_id));
			
			// Get the query results and display them. 
			sqlResult2 = statement.executeQuery();
						

			// insert the results into an array
			int rowCount = sqlResult2.last() ? sqlResult2.getRow() : 0;
			
			System.out.println("ROW COUNT EDIT PRODUCT: " + rowCount);
			System.out.println("ROW COUNT EDIT PRODUCT ID: " + product_id);
			
			if (rowCount > 0) {
				
				xml += "<?xml version='1.0' encoding='utf-8'?>\n";
				xml += "<editprod xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
				xml += "<access-validity>\n";
				xml += "200\n";
				xml += "</access-validity>\n";
				xml += "<numOfSearch>\n";
				xml += String.valueOf(rowCount) + "\n";
				xml += "</numOfSearch>\n";
				xml += "<id>\n";
				xml += String.valueOf(sqlResult2.getInt("id")) + "\n";
				xml += "</id>\n";
				xml += "<name>\n";
				xml += sqlResult2.getString("name") + "\n";
				xml += "</name>\n";
				xml += "<description>\n";
				xml += sqlResult2.getString("description") + "\n";
				xml += "</description>\n";
				xml += "<price>\n";
				xml += String.valueOf(sqlResult2.getInt("price")) + "\n";
				xml += "</price>\n";
				xml += "<photo>\n";
				xml += sqlResult2.getString("photo") + "\n";
				xml += "</photo>\n";
				xml += "</editprod>";
				
				
			} else {
				
				xml += "<?xml version='1.0' encoding='utf-8'?>\n";
				xml += "<editprod xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
				xml += "<access-validity>\n";
				xml += "200\n";
				xml += "</access-validity>\n";
				xml += "<numOfSearch>\n";
				xml += String.valueOf(rowCount) + "\n";
				xml += "</numOfSearch>\n";
				xml += "</editprod>";
					
			}
			
			
			return xml;
				
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			// Close the connection. 
			try {
				statement.close(); 
				MySQLConnect_marketplace.conn.close();
			} catch (Exception ec) {
				ec.printStackTrace();
			}
		}
		
		// undefined error
		xml = "";
		xml += "<?xml version='1.0' encoding='utf-8'?>\n";
		xml += "<editprod xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
		xml += "<access-validity>\n";
		xml += "200\n";
		xml += "</access-validity>\n";
		xml += "<numOfSearch>\n";
		xml += "-1\n";
		xml += "</numOfSearch>\n";
		xml += "</editprod>";
		
		return xml;
	}
	
}

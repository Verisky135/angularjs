package web_services_soap.functionalities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.MySQLConnect_marketplace;

public class modifyLikeFunc {

	private String uiglob;
	private int product_id;
	private String is_liked;
	
	
	public modifyLikeFunc(String uiglob, int product_id, String is_liked) {
		
		this.uiglob = uiglob;
		this.product_id = product_id;
		this.is_liked = is_liked;
		
	}
	
	
	public int getCurrLikes() {
		
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		ResultSet sqlResult = null;
		
		
		MySQLConnect_marketplace.createConnection();
		
		try {
			
			String pst = "SELECT likes FROM product_sell WHERE id=?";
			
			
			// Generate the SQL query. 
			statement = MySQLConnect_marketplace.conn.prepareStatement(pst);
			statement.setInt(1, product_id);
			
			// Get the query results
			sqlResult = statement.executeQuery();
						
			
			// insert the results into an array
			int rowCount = sqlResult.last() ? sqlResult.getRow() : 0;
			
			
			if (rowCount > 0) {
				
				// successful query execution
				sqlResult.first();
				
				return sqlResult.getInt("likes");
				
			} else {
				
				// failure at query execution
				return -1;
						
			}
			
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
		
		return -999;
		
	}
	
	public String modifyLike() {
		
		String xml = "";
		
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		int rowAffected = 0;
		int rowAffected2 = 0;
		
		MySQLConnect_marketplace.createConnection();
		
		try {
			
			String pst;
			
			if (is_liked.equals("no")) {
				
				// increment likes number
				pst = "UPDATE product_sell SET likes = likes + 1 WHERE id=?";
			
			} else {
				
				// decrement likes number
				pst = "UPDATE product_sell SET likes = likes - 1 WHERE id=?";
			
			}
			
			
			// Generate the SQL query. 
			statement = MySQLConnect_marketplace.conn.prepareStatement(pst);
			statement.setInt(1, product_id);
			
			
			rowAffected = statement.executeUpdate(); 
			
			if (rowAffected > 0) {
				
				// successful query execution
				if (is_liked.equals("no")) {
					
					pst = "INSERT INTO user_likes (userID, productID) VALUES (?, ?)";
				
				} else {
				
					pst = "DELETE FROM user_likes WHERE userID = ? AND productID = ?";
					
				}
				
				// Generate the SQL query. 
				statement2 = MySQLConnect_marketplace.conn.prepareStatement(pst);
				statement2.setInt(1, Integer.parseInt(uiglob));
				statement2.setInt(2, product_id);
				
				
				rowAffected2 = statement2.executeUpdate(); 
				
				
				if (rowAffected2 > 0) {
					
					int fetched_currlikes = getCurrLikes();
					
					xml += "<?xml version='1.0' encoding='utf-8'?>\n";
					xml += "<modifylike xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
					xml += "<access-validity>\n";
					xml += "200\n";
					xml += "</access-validity>\n";
					xml += "<dbtype>\n";
					xml += "userlikes\n";
					xml += "</dbtype>\n";
					xml += "<currlikes>\n";
					xml += String.valueOf(fetched_currlikes) + "\n";
					xml += "</currlikes>\n";
					xml += "</modifylike>";
					
					
				} else {
					
					xml += "<?xml version='1.0' encoding='utf-8'?>\n";
					xml += "<modifylike xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
					xml += "<access-validity>\n";
					xml += "200\n";
					xml += "</access-validity>\n";
					xml += "<dbtype>\n";
					xml += "userlikes\n";
					xml += "</dbtype>\n";
					xml += "</modifylike>";
					
					
				}
				
				
			} else {
				
				// failure at query execution

				xml += "<?xml version='1.0' encoding='utf-8'?>\n";
				xml += "<modifylike xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
				xml += "<access-validity>\n";
				xml += "200\n";
				xml += "</access-validity>\n";
				xml += "<dbtype>\n";
				xml += "productsell\n";
				xml += "</dbtype>\n";
				xml += "</modifylike>";
				
				
			}
			
			return xml;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			// Close the connection. 
			try {
				statement.close(); 
				statement2.close();
				MySQLConnect_marketplace.conn.close();
			} catch (Exception ec) {
				ec.printStackTrace();
			}
		}
		
		// undefined error

		xml += "<?xml version='1.0' encoding='utf-8'?>\n";
		xml += "<modifylike xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
		xml += "<access-validity>\n";
		xml += "200\n";
		xml += "</access-validity>\n";
		xml += "<dbtype>\n";
		xml += "undefined\n";
		xml += "</dbtype>\n";
		xml += "</modifylike>";
		
		return xml;
	}
	
}

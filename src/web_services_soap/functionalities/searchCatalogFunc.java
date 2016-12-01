package web_services_soap.functionalities;

import database.MySQLConnect_marketplace;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Locale;


public class searchCatalogFunc {

	private int userid_active;
	private String query;
	private String category;
	
	
	public searchCatalogFunc(int userid_active, String query, String category) {
		
		this.userid_active = userid_active;
		this.query = query;
		this.category = category;
		
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
	
	
	public int getIsLiked(int prod_id) {
		
		int isliked_state = 0;
		
		// fetch the items from database
		PreparedStatement statement2 = null;
		ResultSet sqlResult2 = null;
		
		MySQLConnect_marketplace.createConnection();
		
		
		try {
			
			String pst = "SELECT productID FROM user_likes WHERE userID=?";
			
			statement2 = MySQLConnect_marketplace.conn.prepareStatement(pst);
			statement2.setInt(1, userid_active);
						
			sqlResult2 = statement2.executeQuery();
		
			int rowCount = sqlResult2.last() ? sqlResult2.getRow() : 0;
			
			if (rowCount > 0) {
				
				sqlResult2.first();
				
				do {
					
					if (sqlResult2.getInt("productID") == prod_id) {
						
						isliked_state = 1;
						break;
						
					}
					
				} while (sqlResult2.next());
				
			}
			
			
			return isliked_state;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	
			// Close the connection. 
			try {
				sqlResult2.close(); 
				statement2.close(); 
				MySQLConnect_marketplace.conn.close();
			} catch (Exception ec) {
				ec.printStackTrace();
			}
		}
			
		// undefined error
		return -999;
	}


	public String search() {
		
		String xml;
		
		// fetch the items from database
		PreparedStatement statement = null;
		ResultSet sqlResult = null;
		
		MySQLConnect_marketplace.createConnection();
		
		
		try {
			// Generate the SQL query
			if (category.equals("p")) {
				
				// search based on product's name
				statement = MySQLConnect_marketplace.conn.prepareStatement("SELECT id, username, name, description, price, photo, likes, purchases, added_time FROM product_sell WHERE name LIKE ? ORDER BY added_time DESC");
				statement.setString(1, "%"+query+"%");
						
			} else if (category.equals("s")) {
				
				// search based on username
				statement = MySQLConnect_marketplace.conn.prepareStatement("SELECT id, username, name, description, price, photo, likes, purchases, added_time FROM product_sell WHERE username LIKE ? ORDER BY added_time DESC");
				statement.setString(1, "%"+query+"%");
				
			} else {
				
				// search all products (ret_all)
				statement = MySQLConnect_marketplace.conn.prepareStatement("SELECT id, username, name, description, price, photo, likes, purchases, added_time FROM product_sell ORDER BY added_time DESC");
				
				System.out.println("RIGHT HERE BRO");
				
			}
			
			
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
				
				// manages LIKE and UNLIKE
				//managesLIKE();
				
				sqlResult.first();
				
				xml = "";
				xml += "<?xml version='1.0' encoding='utf-8'?>\n";
				xml += "<catalog xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
				xml += "<numOfSearch>\n";
				xml += String.valueOf(rowCount) + "\n";
				xml += "</numOfSearch>\n";
				
				
				iterate_row = 0;
				
				
				do {	
					
					int get_is_liked = getIsLiked(Integer.parseInt(sqlResult.getString("id")));
					
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
					xml += "<is_liked" + iterate_row + ">\n";
					xml += String.valueOf(get_is_liked)+"\n";
					xml += "</is_liked" + iterate_row + ">\n";
					
					xml += "</product" + iterate_row + ">\n";
					
					iterate_row++;
					
				} while (sqlResult.next());
			
				xml += "</catalog>";
		
			}
			else {
			
				System.out.println("searchCatalog numOfSearch 0");
				
				xml = "";
				xml += "<?xml version='1.0' encoding='utf-8'?>\n";
				xml += "<catalog xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
				xml += "<numOfSearch>\n";
				xml += "0\n";
				xml += "</numOfSearch>\n";
				xml += "</catalog>";
				
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
	
		System.out.println("searchCatalog exception");
		
		xml = "";
		xml += "<?xml version='1.0' encoding='utf-8'?>\n";
		xml += "<catalog xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
		xml += "<numOfSearch>\n";
		xml += "0\n";
		xml += "</numOfSearch>\n";
		xml += "</catalog>";
		
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


package web_services_soap.functionalities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Locale;

import database.MySQLConnect_marketplace;
import database.MySQLConnect;


public class fetchConfPurchaseDataFunc {

	private int id;
	private String uglob;
	private String atglob;
	private String etglob;
	private String ieglob;
	
	public fetchConfPurchaseDataFunc(int id, String uglob, String atglob, String etglob, String ieglob) {
		this.id = id;		
		this.uglob = uglob;
		this.atglob = uglob;
		this.etglob = uglob;
		this.ieglob = uglob;
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
	

	public String fetchBuyingUserData() {
		
		String xml2 = "";
		
		// fetch the user buying this product
		PreparedStatement statement2 = null;
		ResultSet sqlResult2 = null;
		
		MySQLConnect.createConnection();
		
		try {
			
			// search the selected product
			statement2 = MySQLConnect.conn.prepareStatement("SELECT username, full_name, full_address, postal_code, phone_number FROM user WHERE username=?");
			statement2.setString(1, uglob);
		
			
			// Get the query results and display them. 
			sqlResult2 = statement2.executeQuery();
			
			
			// insert the results into an array
			int rowCount = sqlResult2.last() ? sqlResult2.getRow() : 0;
			
			
			System.out.println("ROW COUNT USER DATA: " + rowCount + ":" + uglob);
			
			if (rowCount > 0) {
		
				xml2 += "<numOfSearch_user>\n";
				xml2 += String.valueOf(rowCount) + "\n";
				xml2 += "</numOfSearch_user>\n";
				xml2 += "<username>\n";
				xml2 += sqlResult2.getString("username") + "\n";
				xml2 += "</username>\n";
				xml2 += "<fullname>\n";
				xml2 += sqlResult2.getString("full_name") + "\n";
				xml2 += "</fullname>\n";
				xml2 += "<fulladdress>\n";
				xml2 += sqlResult2.getString("full_address") + "\n";
				xml2 += "</fulladdress>\n";
				xml2 += "<postalcode>\n";
				xml2 += sqlResult2.getString("postal_code") + "\n";
				xml2 += "</postalcode>\n";
				xml2 += "<phonenumber>\n";
				xml2 += sqlResult2.getString("phone_number") + "\n";
				xml2 += "</phonenumber>\n";
				
				
			}
			
			System.out.println("USER DATA");
			System.out.println(xml2);
			
			return xml2;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	
			// Close the connection. 
			try {
				sqlResult2.close(); 
				statement2.close(); 
				MySQLConnect.conn.close();
			} catch (Exception ec) {
				ec.printStackTrace();
			}
		}
		
		xml2 += "<numOfSearch_user>\n";
		xml2 += "-1\n";
		xml2 += "</numOfSearch_user>\n";
		
		return xml2;
	}
	
	public String fetchData() {
		
		String xml;
		String xmlUser;
		
		// fetch the items from database
		PreparedStatement statement = null;
		ResultSet sqlResult = null;
		
		MySQLConnect_marketplace.createConnection();
		
		
		try {
				
			// search the selected product
			statement = MySQLConnect_marketplace.conn.prepareStatement("SELECT name, price, photo FROM product_sell WHERE id=?");
			statement.setInt(1, id);
		
			
			// Get the query results and display them. 
			sqlResult = statement.executeQuery();
			
			
			// insert the results into an array
			int rowCount = sqlResult.last() ? sqlResult.getRow() : 0;
			
			
			xml = "";
			
			if (rowCount > 0) {
				
				// fetch the buying user data
				xmlUser = fetchBuyingUserData();
				
				
				xml += "<?xml version='1.0' encoding='utf-8'?>\n";
				xml += "<confpurch xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
				xml += "<access-validity>\n";
				xml += "200\n";
				xml += "</access-validity>\n";
				xml += "<numOfSearch>\n";
				xml += String.valueOf(rowCount) + "\n";
				xml += "</numOfSearch>\n";
				xml += "<name>\n";
				xml += sqlResult.getString("name") + "\n";
				xml += "</name>\n";
				xml += "<price>\n";
				xml += toRupiahFormat(String.valueOf(sqlResult.getInt("price"))) + "\n";
				xml += "</price>\n";
				xml += "<photo>\n";
				xml += sqlResult.getString("photo") + "\n";
				xml += "</photo>\n";
				xml += xmlUser;
				xml += "</confpurch>";
				
				
			} else {
				
				xml += "<?xml version='1.0' encoding='utf-8'?>\n";
				xml += "<confpurch xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
				xml += "<access-validity>\n";
				xml += "200\n";
				xml += "</access-validity>\n";
				xml += "<numOfSearch>\n";
				xml += String.valueOf(rowCount) + "\n";
				xml += "</numOfSearch>\n";
				xml += "</confpurch>";
				
			}
			
			
			return xml;
			
			
		} catch (Exception e) {
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
		xml += "<confpurch xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
		xml += "<access-validity>\n";
		xml += "200\n";
		xml += "</access-validity>\n";
		xml += "<numOfSearch>\n";
		xml += "-1\n";
		xml += "</numOfSearch>\n";
		xml += "</confpurch>";
		
		return xml;
	
	}
	
}

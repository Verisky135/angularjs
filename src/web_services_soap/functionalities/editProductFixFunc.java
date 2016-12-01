package web_services_soap.functionalities;

import java.sql.PreparedStatement;

import database.MySQLConnect_marketplace;

public class editProductFixFunc {

	private String product_id;
	private String product_name;
	private String product_desc;
	private String product_price;
	private String product_photo;
	
	
	public editProductFixFunc(String product_id, String product_name,
								String product_desc, String product_price,
								String product_photo) {
		
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_desc = product_desc;
		this.product_price = product_price;
		this.product_photo = product_photo;
		
	}
	
	public int editFix() {
		
		PreparedStatement statement = null;
		int rowAffected = 0;
		
		MySQLConnect_marketplace.createConnection();
		
		try {
			
			
			String pst = "UPDATE product_sell SET name=?, description=?, price=? WHERE id=?";
			
			// Generate the SQL query. 
			statement = MySQLConnect_marketplace.conn.prepareStatement(pst);
			
			
			product_id = product_id.replaceAll("[\n\r]", "");
			product_price = product_price.replaceAll("[\n\r]", "");
			
			System.out.println("PROD_NAME: " + product_name);
			System.out.println("PROD_PRICE_MATCHES: " + product_price + ":" + product_id);
			
			if (product_price.matches("-?\\d+") && product_id.matches("-?\\d+")) {
				
				System.out.println("MATCHESSSSSS");
				
			} else {
				
				System.out.println("ELSE_PRICE_MATCHES: " + product_price);
			}
			
			
			
			statement.setString(1, product_name);
			statement.setString(2, product_desc);
			statement.setInt(3, Integer.parseInt(product_price));
			statement.setInt(4, Integer.parseInt(product_id));
			
			rowAffected = statement.executeUpdate(); 
			
			
			if (rowAffected > 0) {
				
				// successful query execution
				return 0;
				
			} else {
				
				// failure at query execution
				return 1;
			
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
		return -1;
		
	}
	
	
}

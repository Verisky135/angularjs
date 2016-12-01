package web_services_soap.functionalities;

import java.sql.PreparedStatement;

import database.MySQLConnect_marketplace;

public class deleteProductFunc {

	private String product_id;
	
	
	public deleteProductFunc(String product_id) {
		
		this.product_id = product_id;
		
	}
	
	public int delete() {
		
		PreparedStatement statement = null;
		int rowAffected = 0;
		
		MySQLConnect_marketplace.createConnection();
		
		try {
			
			System.out.println("DELETE " + product_id);
			
			// Generate the SQL query. 
			statement = MySQLConnect_marketplace.conn.prepareStatement("DELETE FROM product_sell WHERE id=?");
			
			statement.setInt(1, Integer.parseInt(product_id));
			
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

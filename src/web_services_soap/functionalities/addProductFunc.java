package web_services_soap.functionalities;
import java.sql.PreparedStatement;

import database.MySQLConnect;
import database.MySQLConnect_marketplace;

public class addProductFunc {
	
	private String uglob;
	private String atglob;
	private String etglob;
	private String ieglob;
	private String prod_name;
	private String prod_desc;
	private String prod_price;
	private String prod_pic;
	
	
	public addProductFunc(String uglob, String atglob, String etglob, String ieglob,
							String prod_name, String prod_desc, String prod_price, 
							String prod_pic) {
		
		this.uglob = uglob;
		this.atglob = atglob;
		this.etglob = etglob;
		this.ieglob = ieglob;
		this.prod_name = prod_name;
		this.prod_desc = prod_desc;
		this.prod_price = prod_price;
		this.prod_pic = prod_pic;
		
	}
	
	
	public int add() {
		
		PreparedStatement statement = null;
		int rowAffected = 0;
		
		MySQLConnect_marketplace.createConnection();
		
		try {
			
			System.out.println("ADD " + prod_price);
			
			// Generate the SQL query. 
			statement = MySQLConnect_marketplace.conn.prepareStatement("INSERT into product_sell (username, name, description, price, photo, purchases, likes) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)");
			
			statement.setString(1, uglob);
			statement.setString(2, prod_name);
			statement.setString(3, prod_desc);
			statement.setInt(4, Integer.parseInt(prod_price));
			statement.setString(5, prod_pic);
			statement.setInt(6, 0);
			statement.setInt(7, 0);
			
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

package web_services_soap.functionalities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.MySQLConnect;
import database.MySQLConnect_marketplace;

public class confPurchaseFunc {
	
	private String s0;
	private String s1;
	private String s2;
	private String s3;
	private String s4;
	private String s5;
	private String s6;
	private String s7;
	private String s8;
	private String s9;
	private String s10;
	
	public confPurchaseFunc(String s0, String s1, String s2, String s3, String s4, String s5,
							String s6, String s7, String s8, String s9, String s10) {
		
		this.s0 = s0;
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;
		this.s5 = s5;
		this.s6 = s6;
		this.s7 = s7;
		this.s8 = s8;
		this.s9 = s9;
		this.s10 = s10;
		
	}
	
	
	public int updateNumOfPurchase() {
		
		PreparedStatement statement = null;
		int sqlResult;
		
		MySQLConnect_marketplace.createConnection();
		
		try {
			
			// search the selected product
		
			String pst = "UPDATE product_sell SET purchases = purchases + 1 WHERE id=?";
			
			statement = MySQLConnect_marketplace.conn.prepareStatement(pst);
			statement.setString(1, s10);
			
			
			// Get the query results and display them. 
			sqlResult = statement.executeUpdate();
			
			
			if (sqlResult > 0) {
				
				return 0;
				
			}
			else {
				
				return 1;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	
			// Close the connection. 
			try {
				statement.close(); 
				MySQLConnect.conn.close();
			} catch (Exception ec) {
				ec.printStackTrace();
			}
		}
	 
		return -1;
		
	}
	
	
	public String getBoughtFromUser() {
		
		PreparedStatement statement2 = null;
		ResultSet sqlResult2;
		
		MySQLConnect_marketplace.createConnection();
		
		try {
			
			String pst = "SELECT username FROM product_sell WHERE id=?";
			
			statement2 = MySQLConnect_marketplace.conn.prepareStatement(pst);
			statement2.setInt(1, Integer.parseInt(s10));
		
			// Get the query results and display them. 
			sqlResult2 = statement2.executeQuery();
			
			String boughtfrom_username;
			
			sqlResult2.next();
				 
			boughtfrom_username = sqlResult2.getString("username");
			System.out.println("SELECT boughtfrom: " + sqlResult2.getString("username"));
		
			
			return boughtfrom_username;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	
			// Close the connection. 
			try {
				statement2.close(); 
				MySQLConnect.conn.close();
			} catch (Exception ec) {
				ec.printStackTrace();
			}
		}
		
		return "error";
	}
	
	
	public int confPurchase() {
		
			PreparedStatement statement2 = null;
			int sqlResult2;
			
			MySQLConnect_marketplace.createConnection();
			
			try {
				
				
				// get the boughtfrom username
				String bought_from = getBoughtFromUser();
				
				
				// search the selected product
				String pst = "INSERT INTO product_buy (product_boughtby, product_boughtfrom, product_name, price, totalprice, photo, quantity, consignee, full_address, postal_code, phone_number) " + 
						"VALUES (?, ?, ?, ?, ?, ?, ?, " +
								"?, ?, ?, ?)";
			
				
				String price_filtered = s2.replaceAll("[\\s.]", "");
				String totalprice_filtered = s3.replaceAll("[\\s.]", "");
				
				price_filtered = price_filtered.substring(0, price_filtered.length()-3);
				totalprice_filtered = totalprice_filtered.substring(0, totalprice_filtered.length()-3);
				
				s2 = price_filtered;
				s3 = totalprice_filtered;
				
				
				System.out.println("filtered price: " + price_filtered);
				System.out.println("filtered totalprice: " + totalprice_filtered);
				
				
				
				statement2 = MySQLConnect_marketplace.conn.prepareStatement(pst);
				statement2.setString(1, s0);
				statement2.setString(2, bought_from);
				statement2.setString(3, s1);
				statement2.setInt(4, Integer.parseInt(s2));
				statement2.setInt(5, Integer.parseInt(s3));
				statement2.setString(6, s4);
				statement2.setInt(7, Integer.parseInt(s5));
				statement2.setString(8, s6);
				statement2.setString(9, s7);
				statement2.setString(10, s8);
				statement2.setString(11, s9);
				
				
				// Get the query results and display them. 
				sqlResult2 = statement2.executeUpdate();
				
				
				if (sqlResult2 > 0) {
					
					// successful query execution (return 0)
					int updPurch = updateNumOfPurchase();
					
					return updPurch;	
				
				} else {
				
					return 1;
					
				}
				
						
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
		
				// Close the connection. 
				try {
					statement2.close(); 
					MySQLConnect.conn.close();
				} catch (Exception ec) {
					ec.printStackTrace();
				}
			}
			
		
			return -1;
	
	}
	
	
}
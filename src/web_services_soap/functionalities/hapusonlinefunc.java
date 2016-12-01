package web_services_soap.functionalities;

import database.MySQLConnect_yangonline;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Locale;


public class hapusonlinefunc {	

	private String uname;

	public hapusonlinefunc(String _uname) {	
		this.uname=_uname;
	}

	public String hapus() {
		
		String xml=uname+ " tetap semangat ya";
		
		// fetch the items from database
		PreparedStatement statement = null;
		
		
		MySQLConnect_yangonline.createConnection();
		

		
		try {
			
				statement = MySQLConnect_yangonline.conn.prepareStatement("DELETE FROM tabelyangonline WHERE username=?");
				statement.setString(1,uname);

				
				statement.executeUpdate();

				System.out.println("HAPUS ONLINE TELAH DILAKUKAN BRO");	
			
				return xml;
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
	
			// Close the connection. 
			try {
				
				statement.close(); 
				MySQLConnect_yangonline.conn.close();
			} catch (Exception ec) {
				ec.printStackTrace();
			}
		}
	
		System.out.println("hapusonline exception");
		
		return xml;
		
	}
	
}


package servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MySQLConnect;

/**
 * Servlet implementation class ServletIdentity_validate
 */
@WebServlet("/ServletIdentity_validate")
public class ServletIdentity_validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    public ServletIdentity_validate() {
        super();
    }

    
    protected String validateAccess(String uglob, String atglob, String etglob, String ieglob) {
    	
    	// fetch the user buying this product
		PreparedStatement statement2 = null;
		ResultSet sqlResult2 = null;
		
		MySQLConnect.createConnection();
		
		try {
			
			String pts = "SELECT id, username, access_token, expire_time, is_expired FROM token_and_expire WHERE username=? AND access_token=?";
			
			// search the selected product
			statement2 = MySQLConnect.conn.prepareStatement(pts);
			statement2.setString(1, uglob);
			statement2.setString(2, atglob);
			
			
			// Get the query results and display them. 
			sqlResult2 = statement2.executeQuery();
			
			
			// insert the results into an array
			int rowCount = sqlResult2.last() ? sqlResult2.getRow() : 0;
			
			
			System.out.println("ROW LOGIN USER: " + rowCount);
			
			
			if (rowCount > 0) {
				
				// got the token history
				
				sqlResult2.first();
				
				
				// check the validity of access token
				if (sqlResult2.getString("access_token").equals(atglob)) {
				
					// check the validity of expiry time
					
					/** get the current timestamp **/
					Date date= new Date();
					Timestamp currTS = new Timestamp(date.getTime());
					
					/** compare two timestamps **/
					if (currTS.after(sqlResult2.getTimestamp("expire_time"))) {
						
						/** invalid session **/
						return "expired";
						
					} else {
						
						/** valid session **/
						return "valid";
						
					}
					
				} else {
					
					// invalid access token
					return "invalid_token";
					
				}
			
			} else {
				
				// error in getting the token
				return "no_token";
				
			}
			
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
		
		// undefined error
		return "undefined_error";
    	
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String uglob = request.getParameter("uglob");
		String atglob = request.getParameter("atglob");
		String etglob = request.getParameter("etglob");
		String ieglob = request.getParameter("ieglob");
		
	
		/**
		 * VALIDATES THE USER ACCESS
		 */
		String validityStatus = validateAccess(uglob, atglob, etglob, ieglob);
		
		if (validityStatus.equals("no_token")) {
			
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		
		} else if (validityStatus.equals("expired")) {
			
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				
		} else if (validityStatus.equals("undefined_error")) {
			
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
		} else if (validityStatus.equals("invalid_token")) {
			
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			
		} else {
		
			response.setStatus(HttpServletResponse.SC_OK);
		
		}
		
		System.out.println("validityStatus: " + validityStatus);
		
		response.getWriter().append(validityStatus);
	
	}
	
}

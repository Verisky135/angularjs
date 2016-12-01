package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MySQLConnect;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class ServletIdentity
 */
@WebServlet("/ServletIdentity_register")
public class ServletIdentity_register extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	
	/**
	 * REST Service for register
	 */
	private boolean validateUnique(String username, String email) {
		
		PreparedStatement statement = null;
		ResultSet sqlResult = null;
		
		MySQLConnect.createConnection();
		
		try {
			// Generate the SQL query. 
			statement = MySQLConnect.conn.prepareStatement("SELECT username, email FROM user WHERE username = ? OR email = ?");
			statement.setString(1, username);
			statement.setString(2, email);
			
			
			// Get the query results and display them. 
			sqlResult = statement.executeQuery(); 
			int rowCount = sqlResult.last() ? sqlResult.getRow() : 0;
	
			System.out.println("row: " + rowCount);
			
			if (rowCount > 0) {
				
				return false;
				
			}
			
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the connection. 
			try {
				sqlResult.close(); 
				statement.close(); 
				MySQLConnect.conn.close();
			} catch (Exception ec) {
				ec.printStackTrace();
			}
		}
		
		return false;
	}
	
	
	/**
	 * GET the ID of the new user
	 * @param newUsername registered username
	 * @return user id
	 */
	private int getNewUserID(String newUsername) {
		
		PreparedStatement statement = null;
		ResultSet sqlResult = null;
		
		MySQLConnect.createConnection();
		
		try {
			
			String pts = "SELECT id FROM user WHERE username=?"; 
					
			// Generate the SQL query. 
			statement = MySQLConnect.conn.prepareStatement(pts);
			statement.setString(1, newUsername);
			
			sqlResult = statement.executeQuery(); 
			int rowCount = sqlResult.last() ? sqlResult.getRow() : 0;
	
			
			if (rowCount > 0) {
				
				// found the id
				sqlResult.first();
				
				return sqlResult.getInt("id");
				
			} else {
				
				// not found the id
				return -1;
				
			}
	
			
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			// Close the connection. 
			try {
				sqlResult.close(); 
				statement.close(); 
				MySQLConnect.conn.close();
			} catch (Exception ec) {
				ec.printStackTrace();
			}
		}
	
		// undefined error
		return -999;
		
	}
	
	/**
	 * Insert user data into the database
	 * @param userData fullname, username, email, etc
	 */
	private int insertNewUser(ArrayList<String> userData) {
		
		PreparedStatement statement = null;
		int rowAffected = 0;
		
		MySQLConnect.createConnection();
		
		try {
			
			// Generate the SQL query. 
			statement = MySQLConnect.conn.prepareStatement("INSERT into user (full_name, username, email, password, full_address, postal_code, phone_number) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, userData.get(0));
			statement.setString(2, userData.get(1));
			statement.setString(3, userData.get(2));
			statement.setString(4, userData.get(3));
			statement.setString(5, userData.get(4));
			statement.setString(6, userData.get(5));
			statement.setString(7, userData.get(6));
			
			
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
				MySQLConnect.conn.close();
			} catch (Exception ec) {
				ec.printStackTrace();
			}
		}
		
		// undefined error
		return -1;
	}
	
	
	/**
	 * CHECKS if the generated token is unique or not
	 * @param token the generated token
	 * @return status token
	 */
	private int checkUniqueToken(String token) {
		
		PreparedStatement statement = null;
		int rowAffected = 0;
		ResultSet sqlResult = null;
		
		MySQLConnect.createConnection();
		
		try {
			
			// Generate the SQL query. 
			statement = MySQLConnect.conn.prepareStatement("SELECT * FROM token_ua_expire WHERE access_token = ? AND is_expired = 0");
			statement.setString(1, token);
			
			sqlResult = statement.executeQuery(); 
			
			int rowCount = sqlResult.last() ? sqlResult.getRow() : 0;
			
			if (rowCount > 0) {
				// invalid generated token
				return 0;
			} else {
				// valid generated token
				return 1;
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			// Close the connection. 
			try {
				sqlResult.close(); 
				statement.close(); 
				MySQLConnect.conn.close();
			} catch (Exception ec) {
				ec.printStackTrace();
			}
		}
		
		// undefined error
		return -1;
			
	}
	
	
	/**
	* Parses the browser name from the corresponding user agent
	*/
	private String getUABrowser(String uagent) {
		
		String userAgent = uagent;
		String user = userAgent.toLowerCase();

		String browser = "";

		// ==========================Browser===========================
		if (user.contains("msie")) {
		
			String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
			browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
		
		} else if (user.contains("safari") && user.contains("version")) {
			
			browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
		
		} else if ( user.contains("opr") || user.contains("opera")) {
			
			if(user.contains("opera"))
				browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
			else if(user.contains("opr"))
				browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
		
		} else if (user.contains("chrome")) {
			
			browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) ) {
			
			//browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
			browser = "Netscape-?";

		} else if (user.contains("firefox")) {
			
			browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		
		} else if(user.contains("rv")) {
			
			browser="IE";
		
		} else {
			
			browser = "UnKnown, More-Info: "+userAgent;
		
		}
		
		return browser;
		
	}
	

	
	/**
	* SET LOGGED IN USERS
	* Stores the access_token and browser in the logged_in_users table
	* Used for resources access validation by MarketPlace Web Service
	* @param acc_token means access token
	* @param cl_browser means client browser
	*/
	private void setLoggedInUsers(String acc_token, String cl_browser) {
		
		PreparedStatement statement = null;
		int rowAffected = 0;
		
		MySQLConnect.createConnection();
		
		try {
			
			// generates the SQL query. 
			statement = MySQLConnect.conn.prepareStatement("INSERT into logged_in_users (access_token, browser) "
					+ "VALUES(?, ?)");
			statement.setString(1, acc_token);
			statement.setString(2, cl_browser);
			
			rowAffected = statement.executeUpdate(); 
			
			
			if (rowAffected > 0) {
				// successful query execution
				
				System.out.println("[setLoggedInUsers] - rowAffected > 0");
		
			} else {
				
				System.out.println("[setLoggedInUsers] - rowAffected = 0");
					
			}
			
		}
		catch (Exception e) {
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
		
	}
	
		
	
	/**
	 * Generates access token and inserts it into the database
	 * @return status code
	 */
	private String generateTokenUAExpiryTime(int userid, String username, String user_agent, String ipaddress) {
		
		// generate JSON
		/*
		JSONObject json = new JSONObject();
		JSONArray register_responses = new JSONArray();
		JSONObject reg_res = null;
		*/
		
		String register_responses;
		
		/*
		* Parses the browser
		*/
		String ua_browser = getUABrowser(user_agent);
		
		
		/*
		 * Generates access token
		 */
		int isUnique = 0;
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		
		Random rnd = new Random();
		
		while (isUnique == 0) {
			
			salt.setLength(0);
			
			while (salt.length() < 18) {
			    int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			    salt.append(SALTCHARS.charAt(index));
			}
		
			// check whether the generated token is already in the database
			isUnique = checkUniqueToken(salt.toString());
			
		}
		
		String saltStr = salt.toString();
		
		// combine the saltStr with user agent and IP address: saltStr#useragent#ipaddr
		saltStr = saltStr + "#" + user_agent + "#" + ipaddress;
			
		// show the token	
		System.out.println("Generated token: " + saltStr);
		
		
		/*
		 * Sets the expiry time
		 */
		long date = System.currentTimeMillis();		// current time
		int sec = 60;								// one minute
		
		Timestamp original = new Timestamp(date);	// current timestamp
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(original.getTime());
		cal.add(Calendar.SECOND, sec);
		
		Timestamp later = new Timestamp(cal.getTime().getTime());	// later timestamp
		
		System.out.println("Original timestamp: " + original);
		System.out.println("Later timestamp: " + later);
		
		
		
		/*
		 * Inserts access token
		 */
		PreparedStatement statement = null;
		int rowAffected = 0;
		
		MySQLConnect.createConnection();
		
		try {
			
			// generates the SQL query. 
			statement = MySQLConnect.conn.prepareStatement("INSERT into token_ua_expire (username, access_token, browser, ipaddress, expire_time, is_expired) "
					+ "VALUES(?, ?, ?, ?, ?, ?)");
			statement.setString(1, username);
			statement.setString(2, saltStr);
			statement.setString(3, ua_browser);
			statement.setString(4, ipaddress);
			statement.setTimestamp(5, later);
			statement.setInt(6, 0);
			
			rowAffected = statement.executeUpdate(); 
			
			
			if (rowAffected > 0) {
				// successful query execution
				
				System.out.println("rowAffected > 0");
				
				register_responses = "{ \"status\": \"OK\" " +
									", \"userid\": \"" + String.valueOf(userid) + "\" " + 
									", \"username\": \"" + username + "\" " + 
									", \"access_token\": \"" + saltStr + "\" " + 
									", \"ua_browser\": \"" + ua_browser + "\" " +
									", \"ipaddress\": \"" + ipaddress + "\" " +	
									", \"expiry_time\": \"" + later + "\"}";
						
			} else {
				
				System.out.println("rowAffected = 0");
				

				register_responses = "{ \"status\": \"ERROR\" " +
									", \"userid\": \"" + String.valueOf(userid) + "\" " + 
									", \"username\": \"" + username + "\" " + 
									", \"access_token\": \"" + saltStr + "\" " + 
									", \"ua_browser\": \"" + ua_browser + "\" " +
									", \"ipaddress\": \"" + ipaddress + "\" " +	
									", \"expiry_time\": \"" + later + "\"}";
					
			}
			
			// store the access_token and browser in the logged_in_users table
			setLoggedInUsers(saltStr, ua_browser);
				
			return register_responses;
			
		}
		catch (Exception e) {
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
		
		// undefined error
		
		register_responses = "{ \"status\": \"UNDEFINED ERROR\" " +
							", \"userid\": \"" + String.valueOf(userid) + "\" " + 
							", \"username\": \"" + username + "\" " + 
							", \"access_token\": \"" + saltStr + "\" " + 
							", \"ua_browser\": \"" + ua_browser + "\" " +
							", \"ipaddress\": \"" + ipaddress + "\" " +			
							", \"expiry_time\": \"" + later + "\"}";
			
		
		return register_responses;
	
	}
	
	
	
    public ServletIdentity_register() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// set MIME type as application/json
		response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		
		ArrayList<String> userData = new ArrayList<String>();
		
		String username = request.getParameter("register_username");
		String email = request.getParameter("register_email");
		
		userData.add(request.getParameter("register_fullname"));
		userData.add(request.getParameter("register_username"));
		userData.add(request.getParameter("register_email"));
		userData.add(request.getParameter("register_password"));
		userData.add(request.getParameter("register_fulladdress"));
		userData.add(request.getParameter("register_postalcode"));
		userData.add(request.getParameter("register_phonenumber"));
		
		
		
		boolean isUnique = false;
		int newUser = -1;
		String token_and_expiry_Creation = null;
		String ipaddress = null;
		String user_agent = null;
		
		
		
		// cek username dan email sudah terdaftar sebelumnya atau belum
		//isUnique = validateUnique(username, email);
	
		
		/** TAHAP INI MENUNGGU VERISKY **/
		/** ASUMSIKAN SAJA TAHAP INI DILEWATI DULU **/
		/*
		if (isUnique) {
			
			//response.sendRedirect("/your/new/location.jsp");
			
			response.getWriter().append("VALID");
			
		} else {
			
			response.getWriter().append("INVALID");
		
		}
		*/
		
		
		/**
		 * NEXT ACTION if the username and email are VALID
		 * Memasukkan user baru ke dalam database
		 */
		newUser = insertNewUser(userData);
		
		if (newUser == 0) {
			
			/**
			 * NEXT ACTION if the user data was inserted successfully
			 * GET the user ID
			 * Mendapatkan IP address client
			 * Mendapatkan user agent client
			 * Mengenerate access token and expire time
			 */
			
			int newUserID = getNewUserID(username);
			
			if (newUserID > 0) {
			
				// get the IP address
				ipaddress = request.getHeader("X-FORWARDED-FOR");
				if (ipaddress == null) {
					   ipaddress = request.getRemoteAddr();
				}
				
				System.out.println("[REG] IP address: " + ipaddress);
				
				// get the user agent
				user_agent = request.getHeader("User-Agent");
				
				System.out.println("[REG] User-Agent: " + user_agent);
				
			
				token_and_expiry_Creation = generateTokenUAExpiryTime(newUserID, username, user_agent, ipaddress);
				
				
				// parse the JSON
				System.out.println("JSON response from ServletIdentity_register:");
				System.out.println(token_and_expiry_Creation);
				
				
				out.println(token_and_expiry_Creation);
			
				out.flush();
			
			} else {
				
				// new user ID not found
				response.setContentType("text/html");
				response.getWriter().append("<h1><b>Error finding new user ID</b></h1>");
			
			}
			
		} else if (newUser == 1) {
				
			// error in inserting user data
			response.setContentType("text/html");
			response.getWriter().append("<h1><b>Error inserting user data</b></h1>");
			
		} else {
			
			// undefined error
			response.setContentType("text/html");
			response.getWriter().append("<h1><b>Undefined error</b></h1>");
			
		}
		
	}

}

package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MySQLConnect;
import database.MySQLConnect_yangonline;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class ServletIdentity
 */
@WebServlet("/ServletIdentity_login")
public class ServletIdentity_login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	
	 public ServletIdentity_login() {
		 super();
	 }

	 public void tambahonline(String uname){
	 	PreparedStatement statement3 = null;
	 	MySQLConnect_yangonline.createConnection();

		 	try{

		 		String pts3 = "INSERT INTO tabelyangonline(username) VALUES (?)";
		 		statement3 = MySQLConnect_yangonline.conn.prepareStatement(pts3);
		 		statement3.setString(1, uname);
		 		statement3.executeUpdate();
		 	} 
		 	catch (Exception e) {
					e.printStackTrace();
			} 
			finally {
			
					// Close the connection. 
					try { 
						statement3.close();   
						MySQLConnect_yangonline.conn.close();
					} catch (Exception ec) {
						ec.printStackTrace();
					}
			}

	 }
	 
	 protected int validateMatch(String query, String pass) {
		 
		 	String xml2 = "";
			
			// fetch the user buying this product
			PreparedStatement statement2 = null;
			ResultSet sqlResult2 = null;
			
			MySQLConnect.createConnection();
			
			try {
				
				String pts = "SELECT id, username, email, password FROM user WHERE (username=? OR email=?) AND password=?";
				
				
				// search the selected product
				statement2 = MySQLConnect.conn.prepareStatement(pts);
				statement2.setString(1, query);
				statement2.setString(2, query);
				statement2.setString(3, pass);
				
				
				// Get the query results and display them. 
				sqlResult2 = statement2.executeQuery();
				
				
				// insert the results into an array
				int rowCount = sqlResult2.last() ? sqlResult2.getRow() : 0;
				
				
				System.out.println("ROW LOGIN USER: " + rowCount);
				
				
				if (rowCount > 0) {
					// success login
					//return 0;	
						
					//sqlResult2.next();
					tambahonline(query);
					return sqlResult2.getInt("id");
				}
				
				// fail login
				return -1;
				
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
			return -2;
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
			int sec = 300;								// five minutes
			
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

		
		// doGet method
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			doPost(request, response);
			
		}

		// doPost method
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			System.out.println("WELCOME TO doPost for LOGIN");
			
			// set MIME type as application/json
			response.setContentType("application/json");
			
			PrintWriter out = response.getWriter();
			
			ArrayList<String> userData = new ArrayList<String>();
			
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			
			boolean isUnique = false;
			int validStatus = -1;
			String token_and_expiry_Creation = null;
			String user_agent = null;
			String ipaddress = null;
			
			
			/**
			 * NEXT ACTION if the username and email are VALID
			 * Memasukkan user baru ke dalam database
			 */
			validStatus = validateMatch(email, pass);
			
			if (validStatus > 0) {
				
				/**
				 * NEXT ACTION if the user data was validated successfully
				 * Mengambil IP address client dari HTTP Request
				 * Mengambil User Agent dari HTTP Request
				 * Mengenerate access token and expire time
				 */
				 
				// get the IP address
				ipaddress = request.getHeader("X-FORWARDED-FOR");
				if (ipaddress == null) {
					   ipaddress = request.getRemoteAddr();
				}
				
				System.out.println("IP address: " + ipaddress);
				
				// get the user agent
				user_agent = request.getHeader("User-Agent");
				
				System.out.println("User-Agent: " + user_agent);
				
				// generate the access token and expiry time
				token_and_expiry_Creation = generateTokenUAExpiryTime(validStatus, email, user_agent, ipaddress);

				
				// parse the JSON
				System.out.println("JSON response from ServletIdentity_login:");
				System.out.println(token_and_expiry_Creation);
				
				
				out.println(token_and_expiry_Creation);
				out.flush();
				
			} else if (validStatus == -1) {
					
				// error in inserting user data
				response.setContentType("text/html");
				response.getWriter().append("<h1><b>Login failed</b></h1>");
				
			} else {
				
				// undefined error
				response.setContentType("text/html");
				response.getWriter().append("<h1><b>Undefined error</b></h1>");
				
			}


		}
	
	
}
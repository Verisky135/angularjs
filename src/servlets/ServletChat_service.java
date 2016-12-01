package servlets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//import org.json.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
//import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletChat_service
 */
@WebServlet("/ServletChat_service")
public class ServletChat_service extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private static Map<String, String> client_fcm_username = new HashMap<String, String>();

	
    public ServletChat_service() {
        super();
    }

    private int sendToFCM(String sender_username, String receiver_fcm, String message) {
    	
    	// Send HTTP POST request to the FCM server
    	final String USER_AGENT = "Mozilla/5.0";
		
		try {
			
			// FCM server
			String url = "https://fcm.googleapis.com/fcm/send";
			
			URL obj = new URL(url);
			
			System.out.println();
			System.out.println("\nSending message to FCM server");
			System.out.println("-----------------------------");
			System.out.println("Receiver token: " + receiver_fcm);
			System.out.println("Message: " + message);
			System.out.println("-----------------------------");
			
			//HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			
			// add request header
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			
			// Authorization key is FCM token (see your project settings GAN!)
			con.setRequestProperty("Authorization", "key=AAAAGOl_WYM:APA91bF--_MzgrpFmwcILfWDW7BV_E-EwFAEDIn080ae_jWFJGYmzUVkgoIP9v0DRXTp7gPLJB89kgGrGekJnP4P8FB4RB7ZG8SHQTZ6spaZoyldE_CKuijJG8DSEvhPMCdrubJj7Kf5VcoS4W4ZfQQcbj3XBH_9Ww");
			//con.setRequestProperty("User-Agent", USER_AGENT);
			
		
			String urlParameters = "{ \"data\": {" +
			    
												// the message to send
												"\"sender_username\": \"" + sender_username + "\"," +
												"\"message\": \"" + message + "\"" + 
												
			  								 "}," +
			  						 "\"to\" : \"" + receiver_fcm + "\"" +
			  					   "}";
			
			
			// send post request
			con.setDoOutput(true);
			
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			
			int responseCode = con.getResponseCode();
			
			//System.out.println("\nSending POST request to URL: " + url);
			System.out.println("Post parameters: " + urlParameters);
			System.out.println("Response code: " + responseCode);
			System.out.println("-----------------------------");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();
			
			
			//System.out.println("THIS IS THE RESPONSE");
			//System.out.println(response.toString());

			return responseCode; 
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return -1;
    	
    }
    
    private void storeToChatService(String client_fcm, String client_username) {
		
    	// store fcm and username into an ArrayList
    	// (username, token)
		client_fcm_username.put(client_username, client_fcm);;

    }
    
    private String findTokenWithIdentity(String paired_username) {
    	
		for (Map.Entry<String, String> entry : client_fcm_username.entrySet())
		{
		    if (entry.getKey().equals(paired_username)) {
		    	
		    	// return the corresponding token
		    	return entry.getValue();
		    
		    }
		}

		// failed to search for the username that matches with rcvr_username
		return "null";
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String client_purpose = request.getParameter("client_purpose");
		
		if (client_purpose.equals("save")) {
		
			String client_fcm = request.getParameter("client_fcm");
			String client_username = request.getParameter("client_username");
	
			// store the information
			storeToChatService(client_fcm, client_username);
			
			// show all elements within the map
			System.out.println("Map client_fcm_username:");
			System.out.print("\t" + client_fcm_username);
			
			response.getWriter().append("200");
		
		} else if (client_purpose.equals("send")) {
			
			/*
			 * Receives:
			 * - Identitas pengirim
			 * - Identitas tujuan
			 * - Isi pesan
			 */
			
			/* EXPERIMENT CODE */
			
			JSONParser parser = new JSONParser();
			int check_code = 0;
			String sender_username = "";
			String receiver_username = "";
			String message = "";
			
			try {
				
				StringBuilder sb = new StringBuilder();
				BufferedReader br = request.getReader();
		        String str = null;
		        while ((str = br.readLine()) != null) {
		            sb.append(str);
		        }
		        
		        System.out.println("StringBuilder");
		        System.out.println(sb.toString());
		        
				Object obj = parser.parse(sb.toString());
				
				JSONObject jsonObject = (JSONObject) obj;

				sender_username = (String) jsonObject.get("sender_username");
				receiver_username = (String) jsonObject.get("receiver_username");
				message = (String) jsonObject.get("message");

				System.out.println("OK");
				System.out.println(sender_username);
				System.out.println(receiver_username);
				System.out.println(message);
				
				// ok
				check_code = 1;
		            
		        
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (check_code == 1) {
				//response.setContentType("text/plain");
		        //response.setCharacterEncoding("UTF-8");
		        //response.setStatus(HttpServletResponse.SC_OK);
				System.out.println("SUCCESS");
				
		        System.out.println("[CHAT SERVER - RESPONSE]");
		        System.out.println("------------------------");
		        System.out.println("Sender: " + sender_username);
		        System.out.println("Receiver: " + receiver_username);
		        System.out.println("Message: " + message);
		        System.out.println("------------------------");
			} else {
				System.out.println("Error response");

				//response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				System.out.println("[CHAT SERVER - RESPONSE]");
				System.out.println("------------------------");
				System.out.println("Error");
				System.out.println("------------------------");
			}
			
			/* END OF EXPERIMENT CODE */
			
			
			// CONNECT TO FCM
			if (check_code == 1) {
				// find the token with the corresponding receiver_username
				String receiver_fcm = findTokenWithIdentity(receiver_username);
				
				if (!receiver_fcm.equals("null")) {
				
					// send the message to FCM server
					int fcm_resp = sendToFCM(sender_username, receiver_fcm, message);
					
					response.setStatus(HttpServletResponse.SC_OK);
					response.getWriter().append(String.valueOf(fcm_resp));
					
				} else {
					
					// failed to send the message to FCM server
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					response.getWriter().append("-999");
					
				}
			}
			
		}
		
	}

}

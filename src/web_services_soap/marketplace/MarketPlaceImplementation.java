package web_services_soap.marketplace;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.net.HttpURLConnection;

import web_services_soap.functionalities.searchCatalogFunc;
import web_services_soap.functionalities.fetchConfPurchaseDataFunc;
import web_services_soap.functionalities.confPurchaseFunc;
import web_services_soap.functionalities.purchasesFunc;
import web_services_soap.functionalities.yourproductFunc;
import web_services_soap.functionalities.salesFunc;
import web_services_soap.functionalities.addProductFunc;
import web_services_soap.functionalities.deleteProductFunc;
import web_services_soap.functionalities.editProductFunc;
import web_services_soap.functionalities.editProductFixFunc;
import web_services_soap.functionalities.modifyLikeFunc;
import web_services_soap.functionalities.daftaronlinefunc;
import web_services_soap.functionalities.hapusonlinefunc;
//import javax.net.ssl.HttpsURLConnection;

//Service Implementation
@WebService(endpointInterface = "web_services_soap.marketplace.MarketPlaceInterface")
public class MarketPlaceImplementation implements MarketPlaceInterface {

	public int validateAccess(String uglob, String atglob, String etglob) {
		
		final String USER_AGENT = "Mozilla/5.0";
		
		try {
			
			// servlet url pattern
			String url = "http://localhost:8080/Limitless_SaleProjectWS/views/validate_access";
			
			URL obj = new URL(url);
			
			System.out.println("GOT HERE");
			
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			// add request header
			con.setRequestMethod("POST");
			//con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			
			String urlParameters = "uglob=" + uglob + "&" +
					"atglob=" + atglob + "&" +
					"etglob=" + etglob;
			
			
			// send post request
			con.setDoOutput(true);
			
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			
			int responseCode = con.getResponseCode();
			System.out.println("\nSending POST request to URL: " + url);
			System.out.println("Post parameters: " + urlParameters);
			System.out.println("Response code: " + responseCode);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();
			
			
			System.out.println("THIS IS THE RESPONSE");
			System.out.println(response.toString());

			return responseCode; 
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return -1;
	}
	
	@Override
	public String searchCatalog(int userid_active_prm, String query_prm, String category_prm) {
		
		int userid_active = userid_active_prm;
		String query = query_prm;
		String category = category_prm;
		String fetched_products;
		
		searchCatalogFunc scf = new searchCatalogFunc(userid_active, query, category);
		fetched_products = scf.search();
			
		return fetched_products;
	}
	
	@Override
	public String daftaronline() {
		
		String fetched_online;
		
		System.out.println("Sedang melalui Daftar Online di MPI");

		daftaronlinefunc scf = new daftaronlinefunc();
		fetched_online = scf.search();
			
		return fetched_online;
	}

	@Override
	public String hapusonline(String uname) {
		
		String fetched_hapus;
		
		System.out.println("Sedang melalui Hapus Online di MPI");
		hapusonlinefunc scf = new hapusonlinefunc(uname);
		fetched_hapus = scf.hapus();
		

		return "Semangat" + fetched_hapus;
	}

	@Override
	public String fetchConfPurchaseData(int id_prm, String uglob, String atglob, String etglob, String ieglob) {
		
		int id = id_prm;
		String fetched_data;
		int validAccess;
		
		// validate access token and expiry time before accessing the resources
		validAccess = validateAccess(uglob, atglob, etglob);
		
		
		if (validAccess == 200) {
			
			System.out.println("validAccess - fetchConfPurchData: " + validAccess);
			
			// if OK, user can access the resources
			fetchConfPurchaseDataFunc fcpdf = new fetchConfPurchaseDataFunc(id, uglob, atglob, etglob, ieglob);
			fetched_data = fcpdf.fetchData();
		
			return fetched_data;
		
		} 	
		
		// response error for invalid access
		
		String xml;
		
		xml = "";
		xml += "<?xml version='1.0' encoding='utf-8'?>\n";
		xml += "<confpurch xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
		xml += "<access-validity>\n";
		xml += String.valueOf(validAccess)+"\n";
		xml += "</access-validity>\n";
		xml += "<numOfSearch>\n";
		xml += "-1\n";
		xml += "</numOfSearch>\n";
		xml += "</confpurch>";
		
		return xml;
		
	}
	
	
	@Override
	public int confPurchase(String uglob, String atglob, String etglob, String ieglob, 
				String s0, String s1, String s2, String s3, String s4, String s5,
				String s6, String s7, String s8, String s9, String s10) {

		int validAccess;
		int statusConfPurch;
		
		// validate access token and expiry time before accessing the resources
		validAccess = validateAccess(uglob, atglob, etglob);
		
		
		if (validAccess == 200) {
			
			System.out.println("validAccess - confPurch: " + validAccess);
			
			// if OK, user can access the resources
			confPurchaseFunc cpf = new confPurchaseFunc(s0, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10);
			statusConfPurch = cpf.confPurchase();
		
			return statusConfPurch;
		
		}  	
		
		// response error for invalid access
		
		System.out.println("INVALID ACCESS - confPurch");
		
		return -999;
		
	}
	
	@Override
	public String purchases(String uglob, String atglob, String etglob, String ieglob) {
		
		int validAccess;
		String fetched_purchases;
		
		// validate access token and expiry time before accessing the resources
		validAccess = validateAccess(uglob, atglob, etglob);
		
		if (validAccess == 200) {
			
			System.out.println("validAccess - purchases: " + validAccess);
			
			// if OK, user can access the resources
			purchasesFunc pf = new purchasesFunc(uglob);
			fetched_purchases = pf.purchases();
		
			return fetched_purchases;
		
		}  	
		
		// response error for invalid access
		
		String xml;
		
		xml = "";
		xml += "<?xml version='1.0' encoding='utf-8'?>\n";
		xml += "<purchases xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
		xml += "<access-validity>\n";
		xml += String.valueOf(validAccess)+"\n";
		xml += "</access-validity>\n";
		xml += "<numOfSearch>\n";
		xml += "-1\n";
		xml += "</numOfSearch>\n";
		xml += "</purchases>";
		
		return xml;
		
	}
	
	@Override
	public String yourproduct(String uglob, String atglob, String etglob, String ieglob) {
		
		String fetched_products;
		int validAccess;
		
		// validate access token and expiry time before accessing the resources
		validAccess = validateAccess(uglob, atglob, etglob);
		
		
		if (validAccess == 200) {
			
			System.out.println("validAccess - yourproduct: " + validAccess);
			
			// if OK, user can access the resources			
			yourproductFunc scf = new yourproductFunc(uglob, atglob, etglob, ieglob);
			fetched_products = scf.yourprod();
				
			return fetched_products;
		
		} 	
		
		String xml;
		
		xml = "";
		xml += "<?xml version='1.0' encoding='utf-8'?>\n";
		xml += "<yourproduct xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
		xml += "<access-validity>\n";
		xml += String.valueOf(validAccess)+"\n";
		xml += "</access-validity>\n";
		xml += "<numOfSearch>\n";
		xml += "-1\n";
		xml += "</numOfSearch>\n";
		xml += "</yourproduct>";
		
		return xml; 
	}
	
	@Override
	public String sales(String uglob, String atglob, String etglob, String ieglob) {
		
		String fetched_sales;
		int validAccess;
		
		// validate access token and expiry time before accessing the resources
		validAccess = validateAccess(uglob, atglob, etglob);
		
		
		if (validAccess == 200) {
			
			System.out.println("validAccess - sales: " + validAccess);
			
			salesFunc scf = new salesFunc(uglob, atglob, etglob, ieglob);
			fetched_sales = scf.sales();
					
			return fetched_sales;
		
		}
		
		
		String xml;
		
		xml = "";
		xml += "<?xml version='1.0' encoding='utf-8'?>\n";
		xml += "<yourproduct xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
		xml += "<access-validity>\n";
		xml += String.valueOf(validAccess)+"\n";
		xml += "</access-validity>\n";
		xml += "<numOfSearch>\n";
		xml += "-1\n";
		xml += "</numOfSearch>\n";
		xml += "</yourproduct>";
		
		return xml; 
	
	}
	
	@Override
	public int addproduct(String uglob, String atglob, String etglob, String ieglob,
							String prod_name, String prod_desc, String prod_price,
							String prod_pic) {
	
			int fetched_addprod;
			int validAccess;
			
			// validate access token and expiry time before accessing the resources
			validAccess = validateAccess(uglob, atglob, etglob);
			
			
			if (validAccess == 200) {
				
				System.out.println("validAccess - addproduct: " + validAccess);
				
				addProductFunc apf = new addProductFunc(uglob, atglob, etglob, ieglob, 
														prod_name, prod_desc, prod_price,
														prod_pic);
				// ret 0 - success
				// ret 1 - cannot insert data into database
				// ret -1 - undefined error
				fetched_addprod = apf.add();
						
				return fetched_addprod;
				
			}
			
			// INVALID ACCESS
			return -999;
			
	}
	

	@Override
	public int deleteproduct(String uglob, String atglob, String etglob, String ieglob,
							String product_id) {
		
			int fetched_delprod;
			int validAccess;
			
			// validate access token and expiry time before accessing the resources
			validAccess = validateAccess(uglob, atglob, etglob);
			
			
			if (validAccess == 200) {
				
				System.out.println("validAccess - deleteproduct: " + validAccess);
				
				deleteProductFunc dpf = new deleteProductFunc(product_id);
				
				// ret 0 - success
				// ret 1 - cannot insert data into database
				// ret -1 - undefined error
				fetched_delprod = dpf.delete();
						
				return fetched_delprod;
				
			}
			
			// INVALID ACCESS
			return -999;
			
	}

	@Override
	public String editproduct(String uglob, String atglob, String etglob, String ieglob,
							String prod_id) {
	
			String fetched_editprod;
			int validAccess;
			
			// validate access token and expiry time before accessing the resources
			validAccess = validateAccess(uglob, atglob, etglob);
			
			
			if (validAccess == 200) {
				
				System.out.println("validAccess - editproduct: " + validAccess);
				
				editProductFunc epf = new editProductFunc(prod_id);
				
				// ret 0 - success
				// ret 1 - cannot insert data into database
				// ret -1 - undefined error
				fetched_editprod = epf.edit();
						
				return fetched_editprod;
				
			}
			
			// INVALID ACCESS
			String xml;
			
			xml = "";
			xml += "<?xml version='1.0' encoding='utf-8'?>\n";
			xml += "<editprod xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
			xml += "<access-validity>\n";
			xml += String.valueOf(validAccess)+"\n";
			xml += "</access-validity>\n";
			xml += "<numOfSearch>\n";
			xml += "-1\n";
			xml += "</numOfSearch>\n";
			xml += "</editprod>";
			
			return xml;
			
	}

	
	public int editproductfix(String uglob, String atglob, String etglob, String ieglob,
								String prod_id, String prod_name, String prod_desc,
								String prod_price, String prod_photo) {

		int fetched_editprodfix;
		int validAccess;
		
		// validate access token and expiry time before accessing the resources
		validAccess = validateAccess(uglob, atglob, etglob);
		
			
		if (validAccess == 200) {
			
			System.out.println("validAccess - editproductfix: " + validAccess);
			
			editProductFixFunc epff = new editProductFixFunc(prod_id, prod_name, prod_desc,
															prod_price, prod_photo);
			
			// ret 0 - success
			// ret 1 - cannot insert data into database
			// ret -1 - undefined error
			fetched_editprodfix = epff.editFix();
					
			return fetched_editprodfix;
			
		}
			
		// INVALID ACCESS
		return -999;	

	}
	
	
	public String modifyLike(String uiglob, String uglob, String atglob, String etglob, 
							int prod_id, String is_liked) {
		
		String fetched_modlike;
		int validAccess;
		
		// validate access token and expiry time before accessing the resources
		validAccess = validateAccess(uglob, atglob, etglob);
		
			
		if (validAccess == 200) {
			
			System.out.println("validAccess - modifyLike: " + validAccess);
			
			modifyLikeFunc mlf = new modifyLikeFunc(uiglob, prod_id, is_liked);
			
			
			fetched_modlike = mlf.modifyLike();
					
			return fetched_modlike;
			
		}
			
		// INVALID ACCESS
		String xml;
		
		xml = "";
		
		xml += "<?xml version='1.0' encoding='utf-8'?>\n";
		xml += "<modifylike xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://java.sun.com/xml/ns/javaee' xsi:schemaLocation='http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd'>\n";
		xml += "<access-validity>\n";
		xml += String.valueOf(validAccess)+"\n";
		xml += "</access-validity>\n";
		xml += "<dbtype>\n";
		xml += "undefined\n";
		xml += "</dbtype>\n";
		xml += "</modifylike>";
		
		return xml;
		
	}
	
}
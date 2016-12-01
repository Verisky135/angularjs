package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_services_soap.marketplace.MarketPlaceImplementationService;
import web_services_soap.marketplace.MarketPlaceInterface;

/**
 * Servlet implementation class ServletMarketPlace
 */
@WebServlet("/ServletMarketPlace")
public class ServletMarketPlace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletMarketPlace() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String service = request.getParameter("service");
		
		MarketPlaceImplementationService MPIService = new MarketPlaceImplementationService();
		MarketPlaceInterface mpservice = MPIService.getMarketPlaceImplementationPort();
	
		if (service.equals("catalog")) {
			
			System.out.println("CATALOG USER ID ACTIVE: " + request.getParameter("user_active"));
			
			int userid_active = Integer.parseInt(request.getParameter("user_active"));
			String query = request.getParameter("query");
			String category = request.getParameter("cat");
			
			// panggil service
			String ObjCat = mpservice.searchCatalog(userid_active, query, category);
			
			
			// print ke console
			System.out.println("ObjCat:");
			System.out.println("Sudah di komentar di ServerMarketPlace");
			System.out.println(ObjCat);
		
			
			// send to client
			PrintWriter out = response.getWriter();
			out.print(ObjCat);
			out.flush();
			
		}else if(service.equals("daftaronline")){


			// panggil service
			String ObjCat = mpservice.daftaronline();
			
			
			// print ke console
			System.out.println("ObjCat Daftar Online (dari SMP):");
			System.out.println(ObjCat);
		
			
			// send to client
			PrintWriter out = response.getWriter();
			out.print(ObjCat);
			out.flush();



		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String service = request.getParameter("service");
		
		MarketPlaceImplementationService MPIService = new MarketPlaceImplementationService();
		MarketPlaceInterface mpservice = MPIService.getMarketPlaceImplementationPort();
	
		
		if (service.equals("fetch_confirm_purchase")) {
			
			int id = Integer.parseInt(request.getParameter("product_id"));
			String username_global = request.getParameter("username_global");
			String access_token_global = request.getParameter("access_token_global");
			String expiry_time_global = request.getParameter("expiry_time_global");
			String is_expired_global = request.getParameter("is_expired_global");
			
			
			// panggil service
			String ObjConf = mpservice.fetchConfPurchaseData(id,
									username_global,
									access_token_global,
									expiry_time_global,
									is_expired_global);
			
			
			// print ke console
			System.out.println("ObjConf:");
			System.out.println("Sudah di komentar di ServerMarketPlace");
			//System.out.println(ObjConf);
		

			// send to client
			PrintWriter out = response.getWriter();
			out.print(ObjConf);
			out.flush();
			
		} else if (service.equals("confirm_purchase")) {
			
			
			System.out.println("Servlet for confirm purchase");
			
			
			String[] purchase_data = new String[15];
			
			purchase_data[0] = request.getParameter("product_boughtby");
			//purchase_data[1] = request.getParameter("product_boughtfrom");
			purchase_data[1] = request.getParameter("product_name");
			purchase_data[2] = request.getParameter("price");
			purchase_data[3] = request.getParameter("totalprice");
			purchase_data[4] = request.getParameter("photo");
			purchase_data[5] = request.getParameter("quantity");
			purchase_data[6] = request.getParameter("consignee");
			purchase_data[7] = request.getParameter("full_address");
			purchase_data[8] = request.getParameter("postal_code");
			purchase_data[9] = request.getParameter("phone_number");
			purchase_data[10] = request.getParameter("product_id");
						
			
			String username_global = request.getParameter("username_global");
			String access_token_global = request.getParameter("access_token_global");
			String expiry_time_global = request.getParameter("expiry_time_global");
			String is_expired_global = request.getParameter("is_expired_global");
			
			
			
			// panggil service
			int ObjConfPurch = mpservice.confPurchase(
									username_global,
									access_token_global,
									expiry_time_global,
									is_expired_global,
									purchase_data[0],
									purchase_data[1],
									purchase_data[2],
									purchase_data[3],
									purchase_data[4],
									purchase_data[5],
									purchase_data[6],
									purchase_data[7],
									purchase_data[8],
									purchase_data[9],
									purchase_data[10]
									);
			
			
			// print ke console
			System.out.println("ObjConfPurch:");
			System.out.println("Sudah di komentar di ServerMarketPlace");
			//System.out.println(ObjConfPurch);
		

			// send to client
			PrintWriter out = response.getWriter();
			out.print(ObjConfPurch);
			out.flush();

			
		} else if (service.equals("purchases")) {
			
			String username_global = request.getParameter("username_global");
			String access_token_global = request.getParameter("access_token_global");
			String expiry_time_global = request.getParameter("expiry_time_global");
			String is_expired_global = request.getParameter("is_expired_global");
			
			// panggil service
			String ObjPurch = mpservice.purchases(
									username_global,
									access_token_global,
									expiry_time_global,
									is_expired_global);
			
			
			// print ke console
			System.out.println("ObjPurch:");
			System.out.println("Sudah di komentar di ServerMarketPlace");
			//System.out.println(ObjPurch);
		

			// send to client
			PrintWriter out = response.getWriter();
			out.print(ObjPurch);
			out.flush();
			
		} else if (service.equals("yourproduct")) {
			
			//String userid_active = request.getParameter("userid_active");
			String uglob = request.getParameter("username_global");
			String atglob = request.getParameter("access_token_global");
			String etglob = request.getParameter("expiry_time_global");
			String ieglob = request.getParameter("is_expired_global");
		
			// panggil service
			String ObjYourProd = mpservice.yourproduct(uglob, atglob, etglob, ieglob);
			
			
			// print ke console
			System.out.println("ObjYourProd:");
			System.out.println("Sudah di komentar di ServerMarketPlace");
			//System.out.println(ObjYourProd);
		
			
			// send to client
			PrintWriter out = response.getWriter();
			out.print(ObjYourProd);
			out.flush();
			
		} else if (service.equals("sales")) {
			
			//String userid_active = request.getParameter("userid_active");
			String uglob = request.getParameter("username_global");
			String atglob = request.getParameter("access_token_global");
			String etglob = request.getParameter("expiry_time_global");
			String ieglob = request.getParameter("is_expired_global");
		
			// panggil service
			String ObjSales = mpservice.sales(uglob, atglob, etglob, ieglob);
			
			
			// print ke console
			System.out.println("ObjSales:");
			System.out.println("Sudah di komentar di ServerMarketPlace");
			//System.out.println(ObjSales);
		
			
			// send to client
			PrintWriter out = response.getWriter();
			out.print(ObjSales);
			out.flush();
			
		} else if (service.equals("addproduct")) {
			
			String[] addprod_data = new String[4];
			
			//String userid_active = request.getParameter("userid_active");
			String uglob = request.getParameter("username_global");
			String atglob = request.getParameter("access_token_global");
			String etglob = request.getParameter("expiry_time_global");
			String ieglob = request.getParameter("is_expired_global");
		
			addprod_data[0] = request.getParameter("product_name");
			addprod_data[1] = request.getParameter("product_desc");
			addprod_data[2] = request.getParameter("product_price");
			addprod_data[3] = request.getParameter("product_pic");
			
			
			// panggil service
			int ObjAddProd = mpservice.addproduct(uglob, atglob, etglob, ieglob,
											addprod_data[0],
											addprod_data[1],
											addprod_data[2],
											addprod_data[3]);
			
			
			// print ke console
			System.out.println("ObjAddProd:");
			System.out.println("Sudah di komentar di ServerMarketPlace");
			//System.out.println(ObjAddProd);
		
			
			// send to client
			PrintWriter out = response.getWriter();
			out.print(ObjAddProd);
			out.flush();
			
		} else if (service.equals("yourproduct_delete")) {
			
			String uglob = request.getParameter("username_global");
			String atglob = request.getParameter("access_token_global");
			String etglob = request.getParameter("expiry_time_global");
			String ieglob = request.getParameter("is_expired_global");
		
			String product_id = request.getParameter("product_id");
			
			// panggil service
			int ObjDeleteProd = mpservice.deleteproduct(uglob, atglob, etglob, ieglob, product_id);
			
			
			// print ke console
			System.out.println("ObjDeleteProd:");
			System.out.println("Sudah di komentar di ServerMarketPlace");
			//System.out.println(ObjDeleteProd);
		
			
			// send to client
			PrintWriter out = response.getWriter();
			out.print(ObjDeleteProd);
			out.flush();
		
		} else if (service.equals("yourproduct_edit")) {
			
			String uglob = request.getParameter("username_global");
			String atglob = request.getParameter("access_token_global");
			String etglob = request.getParameter("expiry_time_global");
			String ieglob = request.getParameter("is_expired_global");
		
			String product_id = request.getParameter("product_id");
			
			// panggil service
			String ObjEditProd = mpservice.editproduct(uglob, atglob, etglob, ieglob, product_id);
			
			
			// print ke console
			System.out.println("ObjEditProd:");
			System.out.println("Sudah di komentar di ServerMarketPlace");
			//System.out.println(ObjEditProd);
		
			
			// send to client
			PrintWriter out = response.getWriter();
			out.print(ObjEditProd);
			out.flush();
			
		} else if (service.equals("editproduct")) {
			
			String uglob = request.getParameter("username_global");
			String atglob = request.getParameter("access_token_global");
			String etglob = request.getParameter("expiry_time_global");
			String ieglob = request.getParameter("is_expired_global");
		
			String product_id = request.getParameter("product_id");
			String product_name = request.getParameter("product_name");
			String product_desc = request.getParameter("product_desc");
			String product_price = request.getParameter("product_price");
			String product_photo = request.getParameter("product_photo");
			
			
			// panggil service
			int ObjEditProdFix = mpservice.editproductfix(uglob, atglob, etglob, ieglob, 
														product_id,
														product_name,
														product_desc,
														product_price,
														product_photo);
			
			
			// print ke console
			System.out.println("ObjEditProdFix:");
			System.out.println("Sudah di komentar di ServerMarketPlace");
			//System.out.println(ObjEditProdFix);
		
			
			// send to client
			PrintWriter out = response.getWriter();
			out.print(ObjEditProdFix);
			out.flush();
			
		} else if (service.equals("modify_like")) {
			
			
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			String is_liked = request.getParameter("is_liked");
			String uiglob = request.getParameter("userid_global");
			String uglob = request.getParameter("username_global");
			String atglob = request.getParameter("access_token_global");
			String etglob = request.getParameter("expiry_time_global");
			
			
			// panggil service
			String ObjModLike = mpservice.modifyLike(uiglob, uglob, atglob, etglob, 
													product_id,
													is_liked);
			
			
			// print ke console
			System.out.println("ObjModLike:");
			System.out.println("Sudah di komentar di ServerMarketPlace");
			//System.out.println(ObjModLike);
		
			
			// send to client
			PrintWriter out = response.getWriter();
			out.print(ObjModLike);
			out.flush();
		
		}
	
	}

}



package web_services_soap.marketplace;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MarketPlaceInterface", targetNamespace = "http://marketplace.web_services_soap/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MarketPlaceInterface {


    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://marketplace.web_services_soap/MarketPlaceInterface/searchCatalogRequest", output = "http://marketplace.web_services_soap/MarketPlaceInterface/searchCatalogResponse")
    public String searchCatalog(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2);

    /**
     * 
     * @return
     *     returns String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://marketplace.web_services_soap/MarketPlaceInterface/daftaronlineRequest", output = "http://marketplace.web_services_soap/MarketPlaceInterface/daftaronlineResponse")
    public String daftaronline();

    /**
     * 
     * @param arg7
     * @param arg6
     * @param arg9
     * @param arg8
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg13
     * @param arg14
     * @param arg1
     * @param arg0
     * @param arg10
     * @param arg11
     * @param arg12
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://marketplace.web_services_soap/MarketPlaceInterface/confPurchaseRequest", output = "http://marketplace.web_services_soap/MarketPlaceInterface/confPurchaseResponse")
    public int confPurchase(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6,
        @WebParam(name = "arg7", partName = "arg7")
        String arg7,
        @WebParam(name = "arg8", partName = "arg8")
        String arg8,
        @WebParam(name = "arg9", partName = "arg9")
        String arg9,
        @WebParam(name = "arg10", partName = "arg10")
        String arg10,
        @WebParam(name = "arg11", partName = "arg11")
        String arg11,
        @WebParam(name = "arg12", partName = "arg12")
        String arg12,
        @WebParam(name = "arg13", partName = "arg13")
        String arg13,
        @WebParam(name = "arg14", partName = "arg14")
        String arg14);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://marketplace.web_services_soap/MarketPlaceInterface/purchasesRequest", output = "http://marketplace.web_services_soap/MarketPlaceInterface/purchasesResponse")
    public String purchases(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://marketplace.web_services_soap/MarketPlaceInterface/yourproductRequest", output = "http://marketplace.web_services_soap/MarketPlaceInterface/yourproductResponse")
    public String yourproduct(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @param arg7
     * @param arg6
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://marketplace.web_services_soap/MarketPlaceInterface/addproductRequest", output = "http://marketplace.web_services_soap/MarketPlaceInterface/addproductResponse")
    public int addproduct(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6,
        @WebParam(name = "arg7", partName = "arg7")
        String arg7);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg4
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://marketplace.web_services_soap/MarketPlaceInterface/deleteproductRequest", output = "http://marketplace.web_services_soap/MarketPlaceInterface/deleteproductResponse")
    public int deleteproduct(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg4
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://marketplace.web_services_soap/MarketPlaceInterface/editproductRequest", output = "http://marketplace.web_services_soap/MarketPlaceInterface/editproductResponse")
    public String editproduct(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @param arg7
     * @param arg6
     * @param arg8
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://marketplace.web_services_soap/MarketPlaceInterface/editproductfixRequest", output = "http://marketplace.web_services_soap/MarketPlaceInterface/editproductfixResponse")
    public int editproductfix(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6,
        @WebParam(name = "arg7", partName = "arg7")
        String arg7,
        @WebParam(name = "arg8", partName = "arg8")
        String arg8);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://marketplace.web_services_soap/MarketPlaceInterface/modifyLikeRequest", output = "http://marketplace.web_services_soap/MarketPlaceInterface/modifyLikeResponse")
    public String modifyLike(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        int arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://marketplace.web_services_soap/MarketPlaceInterface/salesRequest", output = "http://marketplace.web_services_soap/MarketPlaceInterface/salesResponse")
    public String sales(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg4
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://marketplace.web_services_soap/MarketPlaceInterface/fetchConfPurchaseDataRequest", output = "http://marketplace.web_services_soap/MarketPlaceInterface/fetchConfPurchaseDataResponse")
    public String fetchConfPurchaseData(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4);

}

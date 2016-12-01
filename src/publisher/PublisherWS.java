package publisher;

import javax.xml.ws.Endpoint;
import web_services_soap.marketplace.MarketPlaceImplementation;

//Endpoint publisher
public class PublisherWS {

	public static void main(String[] args) {
	   Endpoint.publish("http://localhost:9999/ws/marketplace", new MarketPlaceImplementation());
    }

}


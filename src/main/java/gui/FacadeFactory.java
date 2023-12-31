package gui;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;

public class FacadeFactory {
	
	public BLFacade createFacade(ConfigXML c) throws MalformedURLException {
		
		BLFacade appFacadeInterface;
		
		if (c.isBusinessLogicLocal()) {
			
			//In this option the DataAccess is created by FacadeImplementationWS
			//appFacadeInterface=new BLFacadeImplementation();

			//In this option, you can parameterize the DataAccess (e.g. a Mock DataAccess object)

			DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			
			appFacadeInterface=new BLFacadeImplementation(da);
			
		}
		
		else { //If remote
			
			 String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
			 
			//URL url = new URL("http://localhost:9999/ws/ruralHouses?wsdl");
			URL url = new URL(serviceName);
	 
	        //1st argument refers to wsdl document above
			//2nd argument is service name, refer to wsdl document above
//	        QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
	        QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
	 
	        Service service = Service.create(url, qname);

	         appFacadeInterface = service.getPort(BLFacade.class);
		} 
		
		return appFacadeInterface;
		
	}

}

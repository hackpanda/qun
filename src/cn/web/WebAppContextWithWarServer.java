package cn.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;


public class WebAppContextWithWarServer {
	 public static void main(String[] args) throws Exception {  
	        Server server = new Server(8080);  
	  
	  
	        WebAppContext webAppContext = new WebAppContext("WebContent","/web");  
	  
	        //webAppContext.setContextPath("/");  
	        webAppContext.setDescriptor("WebContent/WEB-INF/web.xml");  
	        webAppContext.setResourceBase("WebContent");  
	        webAppContext.setDisplayName("web");  
	        webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());  
	        webAppContext.setConfigurationDiscovered(true);  
	        webAppContext.setParentLoaderPriority(true);  
	        server.setHandler(webAppContext);  
	        System.out.println(webAppContext.getContextPath());  
	        System.out.println(webAppContext.getDescriptor());  
	        System.out.println(webAppContext.getResourceBase());  
	        System.out.println(webAppContext.getBaseResource());  
	  
	        try {  
	            server.start();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        System.out.println("server is  start");  
	    }  
}

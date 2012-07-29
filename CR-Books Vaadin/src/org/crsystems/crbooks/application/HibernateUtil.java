package org.crsystems.crbooks.application;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	 
	    private static SessionFactory sessionFactory;
	    private static ServiceRegistry serviceRegistry;
	    
	    static {
	        try {      
	        	Configuration configuration = new Configuration();
	            configuration.configure();
	            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
	            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	        } catch (Throwable e) {
	            System.err.println("Error in creating SessionFactory object." 
	                + e.getMessage());
	            throw new ExceptionInInitializerError(e);
	        }
	    }
	    
	    public static SessionFactory getSessionFactory() {
	    	if (sessionFactory == null) {
		        try {      
		        	Configuration configuration = new Configuration();
		            configuration.configure();
		            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
		            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		        } catch (Throwable e) {
		            System.err.println("Error in creating SessionFactory object." 
		                + e.getMessage());
		            throw new ExceptionInInitializerError(e);
		        }		
	    	}
	        return sessionFactory;
	    }	    
}

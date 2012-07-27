package org.crsystems.crbooks.application;
import org.crsystems.crbooks.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

@SuppressWarnings("deprecation")
public class HibernateUtil {
	 
	    private static SessionFactory sessionFactory;
	 
	    static {
	        
	    }
	 
	    public static SessionFactory getSessionFactory() {
	    	if (sessionFactory == null) {
	    		try {
		            sessionFactory = new AnnotationConfiguration()
		            	.configure()
		            	.addPackage("org.crsystems.crbooks.models")
		            	.addAnnotatedClass(User.class)
		            	.buildSessionFactory();
		            System.out.println("Sesion factory creada con éxito");
		        } catch (Throwable ex) {
		            System.err.println("Initial SessionFactory creation failed." + ex);
		            throw new ExceptionInInitializerError(ex);
		        }
	    	}
	        return sessionFactory;
	    }
}

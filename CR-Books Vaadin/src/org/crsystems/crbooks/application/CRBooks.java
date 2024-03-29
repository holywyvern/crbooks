package org.crsystems.crbooks.application;

import javax.servlet.ServletContext;

import org.crsystems.crbooks.application.listeners.LoginListener;
import org.crsystems.crbooks.models.User;
import org.crsystems.crbooks.sessions.Session;
import org.crsystems.crbooks.ui.layouts.ApplicationLayout;
import org.crsystems.crbooks.ui.windows.HomeWindow;
import org.crsystems.crbooks.ui.windows.LoginWindow;

import com.vaadin.Application;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Window.Notification;
import com.vaadin.terminal.gwt.server.WebApplicationContext;

public class CRBooks extends Application {
	
	private static final long serialVersionUID = -8766793733061854039L;

	private static Session currentSession;
	
	private static CRBooks instance;
	
	private static Component lastView;
	
	private ApplicationLayout layout;
	
	private ServletContext servletContext;
	
	@Override
	public void init() {
		CRBooks.currentSession = null;
		servletContext = ((WebApplicationContext)getContext()).getHttpSession().getServletContext();
		setLocale(((WebApplicationContext)getContext()).getBrowser().getLocale());
		this.setTheme("cr-books");
		Window mainWindow = new Window("CR-Books");
		layout = new ApplicationLayout();
		mainWindow.addComponent(layout);
		mainWindow.setSizeFull();
		CRBooks.setInstance(this);
		CRBooks.setView(new HomeWindow());
		setMainWindow(mainWindow);
		CRBooks.instance = this;
	}

	public ServletContext getServletContext() {
		return this.servletContext;
	}
	
	public static SystemMessages getSystemMessages() {
		CustomizedSystemMessages messages = new CustomizedSystemMessages();
		messages.setAuthenticationErrorCaption("Error de autentificación") ;
		messages.setAuthenticationErrorMessage("No tiene privilegios para acceder al sistema.") ;
		messages.setAuthenticationErrorURL("#") ;
		messages.setCommunicationErrorCaption("Error de comunicación") ;
		messages.setCommunicationErrorMessage("El sistema no ha podido comunicarse. Compruebe su conexión e intente denuevo") ;
		messages.setCommunicationErrorURL("#") ;
		messages.setCookiesDisabledCaption("Coockies desactivadas") ; 
		messages.setCookiesDisabledMessage("Las cookies del sistema estan deshabilitadas.") ; 
		messages.setCookiesDisabledURL("#") ;
		messages.setInternalErrorCaption("Error interno") ;
		messages.setInternalErrorMessage("Ha ocurrido un error interno.") ;
		messages.setInternalErrorURL("#") ;
		messages.setOutOfSyncCaption("Fuera de sincronía") ;
		messages.setOutOfSyncMessage("La sincronización con el servidor se ha roto. Click aqui para continuar.") ;
		messages.setOutOfSyncURL("#") ;
		messages.setSessionExpiredCaption("Sesión expirada") ;
		messages.setSessionExpiredMessage("Se ha espirado la conexión. Click aqui para continuar.");
		messages.setSessionExpiredURL("#");
		messages.setAuthenticationErrorNotificationEnabled(true);
		messages.setCommunicationErrorNotificationEnabled(true);
		messages.setCookiesDisabledNotificationEnabled(true);
		messages.setInternalErrorNotificationEnabled(true);
		messages.setOutOfSyncNotificationEnabled(true);
		messages.setSessionExpiredNotificationEnabled(true);
		return messages;
	}

	public static CRBooks getInstance() {
		return instance;
	}

	public static void setInstance(CRBooks instance) {
		CRBooks.instance = instance;
	}

	public static Window mainWindow() {
		return CRBooks.instance.getMainWindow();
	}
	
	public static void showWarning(String i) {
		mainWindow().showNotification(i, Notification.TYPE_WARNING_MESSAGE);
	}
	
	public static void showError(String caption) {
		mainWindow().showNotification(caption, Notification.TYPE_ERROR_MESSAGE);
	}
	
	public static void showMessage(String caption) {
		mainWindow().showNotification(caption, Notification.TYPE_HUMANIZED_MESSAGE);
	}
	
	public static void showTrayMessage(String caption) {
		mainWindow().showNotification(caption, Notification.TYPE_TRAY_NOTIFICATION);
	}	
	
	public static Session getCurrentSession() {
		return CRBooks.currentSession;
	}

	public static User getCurrentUser() {
		if (CRBooks.currentSession == null) return null;
		return CRBooks.currentSession.getUser();
	}	
	
	public static void setCurrentSession(Session session) {
		if (CRBooks.currentSession != null) CRBooks.currentSession.close();
		CRBooks.currentSession = session;
		if (CRBooks.currentSession != null) {
			CRBooks.currentSession.setSession(HibernateUtil.getSessionFactory().openSession());
		}
		if (CRBooks.instance != null) CRBooks.instance.makeMenuCommands();		
		CRBooks.setView(new HomeWindow());
	}	
	
	public static boolean authenticateUser(LoginListener onRegister) {
		if (CRBooks.userSignedIn()) return true;
		CRBooks.setView(new LoginWindow(onRegister));
		return false;
	}	
	
	public static boolean authenticateClient(LoginListener onRegister) {
		if (CRBooks.clientSignedIn()) return true;
		return false;
	}

	public static boolean authenticateManager(LoginListener onRegister) {
		if (CRBooks.managerSignedIn()) return true;
		return false;
	}	
	
	public static boolean authenticateAdmin(LoginListener onRegister) {
		if (CRBooks.adminSignedIn()) return true;
		return false;
	}	
	
	public static boolean userSignedIn() {
		if (CRBooks.currentSession == null || CRBooks.currentSession.getUser() == null) return false;
		return (CRBooks.currentSession != null);
	}
	
	public static boolean clientSignedIn() {
		
		return (CRBooks.userSignedIn() && CRBooks.currentSession.getUser().isClient());
	}
	
	public static boolean managerSignedIn() {
		return (CRBooks.userSignedIn() && CRBooks.currentSession.getUser().isManager());
	}
	
	public static boolean adminSignedIn() {
		return (CRBooks.userSignedIn() && CRBooks.currentSession.getUser().isAdmin());
	}
	
	public static void setView(Component view) {
		lastView = view;
		CRBooks.getInstance().layout.changeView(view);
	}
	
	public static void returnView() {
		setView(lastView);
		
	}

	public void makeMenuCommands() {
		layout.makeMenuCommands();
	}
	
}

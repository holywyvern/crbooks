package org.crsystems.crbooks.application;

import org.crsystems.crbooks.models.User;
import org.crsystems.crbooks.sessions.Session;
import org.crsystems.crbooks.ui.layouts.ApplicationLayout;

import com.vaadin.Application;
import com.vaadin.ui.*;

public class CRBooks extends Application {
	
	private static final long serialVersionUID = -8766793733061854039L;

	private static Session currentSession;
	
	@Override
	public void init() {
		CRBooks.currentSession = null;
		this.setTheme("chameleon");
		Window mainWindow = new Window("CR-Books");
		ApplicationLayout layout = new ApplicationLayout();
		mainWindow.addComponent(layout);
		mainWindow.setSizeFull();
		setMainWindow(mainWindow);
	}

	public static Session getCurrentUser() {
		return CRBooks.currentSession;
	}

	public static boolean authenticateUser() {
		if (CRBooks.userSignedIn()) return true;
		return false;
	}	
	
	public static boolean authenticateClient() {
		if (CRBooks.clientSignedIn()) return true;
		return false;
	}

	public static boolean authenticateManager() {
		if (CRBooks.managerSignedIn()) return true;
		return false;
	}	
	
	public static boolean authenticateAdmin() {
		if (CRBooks.adminSignedIn()) return true;
		return false;
	}	
	
	public static boolean userSignedIn() {
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
	
}

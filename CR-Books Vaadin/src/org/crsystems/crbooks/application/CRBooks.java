package org.crsystems.crbooks.application;

import org.crsystems.crbooks.models.User;
import org.crsystems.crbooks.ui.layouts.ApplicationLayout;

import com.vaadin.Application;
import com.vaadin.ui.*;

public class CRBooks extends Application {
	
	private static final long serialVersionUID = -8766793733061854039L;

	private static User currentUser;
	
	@Override
	public void init() {
		CRBooks.currentUser = null;
		this.setTheme("chameleon");
		Window mainWindow = new Window("CR-Books");
		ApplicationLayout layout = new ApplicationLayout();
		mainWindow.addComponent(layout);
		mainWindow.setSizeFull();
		setMainWindow(mainWindow);
	}

	public static User getCurrentUser() {
		return CRBooks.currentUser;
	}
	
	public static boolean authenticateUser() {
		if (CRBooks.currentUser != null && CRBooks.currentUser.isUser()) return true;
		return false;
	}

	public static boolean authenticateManager() {
		if (CRBooks.currentUser != null && CRBooks.currentUser.isManager()) return true;
		return false;
	}	
	
	public static boolean authenticateAdmin() {
		if (CRBooks.currentUser != null && CRBooks.currentUser.isAdmin()) return true;
		return false;
	}	
	
	
}

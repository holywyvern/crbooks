package org.crsystems.crbooks.application;

import org.crsystems.crbooks.application.listeners.LoginListener;
import org.crsystems.crbooks.sessions.Session;
import org.crsystems.crbooks.ui.layouts.ApplicationLayout;
import org.crsystems.crbooks.ui.windows.HomeWindow;
import org.crsystems.crbooks.ui.windows.LoginWindow;

import com.vaadin.Application;
import com.vaadin.ui.*;
import com.vaadin.ui.Window.Notification;

public class CRBooks extends Application {
	
	private static final long serialVersionUID = -8766793733061854039L;

	private static Session currentSession;
	
	private static CRBooks instance;
	
	private static Component lastView;
	
	private ApplicationLayout layout;
	
	@Override
	public void init() {
		CRBooks.currentSession = null;
		this.setTheme("cr-books");
		Window mainWindow = new Window("CR-Books");
		layout = new ApplicationLayout();
		mainWindow.addComponent(layout);
		mainWindow.setSizeFull();
		CRBooks.setInstance(this);
		CRBooks.setView(new HomeWindow());
		setMainWindow(mainWindow);
	}

	public static CRBooks getInstance() {
		return instance;
	}

	public static void setInstance(CRBooks instance) {
		CRBooks.instance = instance;
	}

	public static Window mainWindow() {
		return CRBooks.getInstance().getMainWindow();
	}
	
	public static void showWarning(String caption) {
		mainWindow().showNotification(caption, Notification.TYPE_WARNING_MESSAGE);
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
	
	public static Session getCurrentUser() {
		return CRBooks.currentSession;
	}

	public static boolean authenticateUser(LoginListener onRegister) {
		if (CRBooks.userSignedIn()) return true;
		CRBooks.setView(new LoginWindow());
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
	
}

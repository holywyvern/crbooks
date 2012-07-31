package org.crsystems.crbooks.ui.layouts;

import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;

import org.crsystems.crbooks.application.CRBooks;
import org.crsystems.crbooks.application.listeners.LoginListener;
import org.crsystems.crbooks.ui.windows.AdministratorWindow;
import org.crsystems.crbooks.ui.windows.CurrentOrderWindow;
import org.crsystems.crbooks.ui.windows.HomeWindow;
import org.crsystems.crbooks.ui.windows.RegisterWindow;
import org.crsystems.crbooks.ui.windows.SearchBooksWindow;
import org.crsystems.crbooks.ui.windows.ViewBooksWindow;
import org.crsystems.crbooks.ui.windows.ViewResultsSearchBooks;
import org.crsystems.crbooks.ui.windows.ViewUserOrders;
import org.vaadin.gravatar.GravatarResource;


import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class ApplicationLayout extends CustomComponent {

	private static final long serialVersionUID = 8661744972380461255L;
	
	
	private VerticalLayout mainLayout;
	private VerticalLayout panelMain;
	private GridLayout gridContent;
	private HorizontalLayout gridMenus;
	private MenuBar menuToolbar;
	private MenuBar menuLogo;
	private Component currentView;
	
	public ApplicationLayout() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		makeMenuCommands();
		makeLogo();
	}

	
	private void makeLogo() {
		menuLogo.addItem("CR-Books", createLogoCommand());
	}


	private MenuBar.Command createLogoCommand() {
		return new MenuBar.Command() {

			private static final long serialVersionUID = -3013627189933105983L;

			public void menuSelected(MenuItem selectedItem) {
				onLogoCommand();
			}


		};
	}


	protected void onLogoCommand() {
		CRBooks.showTrayMessage("Est�s usando CR-Books(prototipo) versi�n 1.0\n(pulsa aqui para borrar el mensaje)");
	}


	public void makeMenuCommands() {
		menuToolbar.removeItems();
		if (CRBooks.clientSignedIn()) {
			makeClientMenuCommands();
		} else if (CRBooks.managerSignedIn()) {
			makeManagerMenuCommands();
		} else if (CRBooks.adminSignedIn()) {
			makeAdminMenuCommands();
		} else {
			makeDefaultMenuCommands();
		}
	}

	private MenuBar.Command makeAllBooksCommand() {		
		return new MenuBar.Command() {
			private static final long serialVersionUID = -3013627189933105983L;
			public void menuSelected(MenuItem selectedItem) {
				onNewOrderCommand();
				
			}
			
		};
	}
	
	private MenuBar.Command makeOrderCommand() {		
		return new MenuBar.Command() {
			private static final long serialVersionUID = -3013627189933105983L;
			public void menuSelected(MenuItem selectedItem) {
				onViewCompleteList();
				
			}
			
		};
	}
	
	
	private void onViewCompleteList()
	{
		this.changeView(new ViewResultsSearchBooks());
	}
	
	private void onNewOrderCommand() 
	{
		this.changeView(new SearchBooksWindow());
		
	}
	
	private MenuBar.Command  makeHomeCommand() {
		return new MenuBar.Command() {

			private static final long serialVersionUID = -3013627189933105983L;

			public void menuSelected(MenuItem selectedItem) {
				onHomeMenuCommand();
			}

		};
	}
	
	protected void onHomeMenuCommand() {
		this.changeView(new HomeWindow());
	}	
	
	private MenuBar.Command createLoginMenuCommand() {
		return new MenuBar.Command() {

			private static final long serialVersionUID = -3013627189933105983L;

			public void menuSelected(MenuItem selectedItem) {
				onLoginMenuCommand();
			}


		};
	}	
	
	private MenuBar.Command createLogoutMenuCommand() {
		return new MenuBar.Command() {

			private static final long serialVersionUID = -3013627189933105983L;

			public void menuSelected(MenuItem selectedItem) {
				onLogoutMenuCommand();
			}


		};
	}		

	private void makeDefaultMenuCommands() {
		MenuItem item;
		
		menuToolbar.addItem("P�gina principal", makeHomeCommand());	
		
		item = menuToolbar.addItem("Iniciar sesi�n", this.createLoginMenuCommand());
		item = menuToolbar.addItem("Registrarse", this.createRegisterMenuCommand());
	}

	private void makeClientMenuCommands() {
		MenuItem item = menuToolbar.addItem(String.format("%s %s %s", CRBooks.getCurrentUser().getFirstName(),
											CRBooks.getCurrentUser().getLastName(), "" + '\u25bc'), null);
		try {
			item.setIcon(new GravatarResource(CRBooks.getCurrentUser().getEmail(), 16,
                    null, null));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		generateClientSubmenu(item);
		item = menuToolbar.addItem("Cat�logo" + '\u25bc', null);
		generateSubMenuBookList(item);
		item = menuToolbar.addItem("Mis pedidos" + '\u25bc', null);
		createOrderSubMenu(item);
		menuToolbar.addItem("P�gina principal", makeHomeCommand());
		
		
	}	

	private void createOrderSubMenu(MenuItem item) {
		item.addItem("Mi pedido actual", makeCurrentOrderMenuCommand());
		item.addItem("Mi listado de pedidos", makeViewOrderMenuCommand());
	}


	private Command makeViewOrderMenuCommand() {
		return new MenuBar.Command() {

			private static final long serialVersionUID = -3013627189933105983L;

			public void menuSelected(MenuItem selectedItem) {
				onViewOrderCommand();
			}

			
		};
	}

	private void onViewOrderCommand() {
		CRBooks.setView(new ViewUserOrders());
	}


	private Command makeCurrentOrderMenuCommand() {
		return new MenuBar.Command() {

			private static final long serialVersionUID = -3013627189933105983L;

			public void menuSelected(MenuItem selectedItem) {
				onCurrentOrderCommand();
			}

		};
	}

	protected void onCurrentOrderCommand() {
		CRBooks.setView(new CurrentOrderWindow());
	}	

	private void generateSubMenuBookList(MenuItem item) {
		item.addItem("Ver cat�logo completo", makeOrderCommand());
		item.addItem("Realizar b�squeda", makeAllBooksCommand());
	}

	private void generateClientSubmenu(MenuItem item) {
		item.addItem("Preferencias", null);
		item.addSeparator();
		item.addItem("Cerrar sesi�n", this.createLogoutMenuCommand());
	}

   

	private void makeManagerMenuCommands() {
		MenuItem item = menuToolbar.addItem(String.format("%s %s(Gerente)%s", CRBooks.getCurrentUser().getFirstName(),
											CRBooks.getCurrentUser().getLastName(), "" + '\u25bc'), null);
		try {
			item.setIcon(new GravatarResource(CRBooks.getCurrentUser().getEmail(), 16,
                    null, null));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		generateClientSubmenu(item);
		menuToolbar.addItem("Panel de administraci�n", makeManagerAdministrationCommand());
		menuToolbar.addItem("P�gina principal", makeHomeCommand());
	}

	private Command makeManagerAdministrationCommand() {
		return new MenuBar.Command() {

			private static final long serialVersionUID = -3013627189933105983L;

			public void menuSelected(MenuItem selectedItem) {
				onManagerAdminCommand();
			}

		};
	}


	
	
	private void makeAdminMenuCommands() {
		MenuItem item = menuToolbar.addItem(String.format("%s %s(Administrador)%s", CRBooks.getCurrentUser().getFirstName(),
											CRBooks.getCurrentUser().getLastName(), "" + '\u25bc'), null);
		try {
			item.setIcon(new GravatarResource(CRBooks.getCurrentUser().getEmail(), 16,
                    null, null));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		generateClientSubmenu(item);
		menuToolbar.addItem("Panel de administraci�n", makeAdminAdministrationCommand());
		menuToolbar.addItem("P�gina principal", makeHomeCommand());
	}

	
	
	private Command makeAdminAdministrationCommand() {
		return new MenuBar.Command() {

			private static final long serialVersionUID = -3013627189933105983L;

			public void menuSelected(MenuItem selectedItem) {
				onAdminAdminCommand();
			}

		};
	}
	
	private MenuBar.Command createRegisterMenuCommand() {
		return new MenuBar.Command() {

			private static final long serialVersionUID = -3013627189933105983L;

			public void menuSelected(MenuItem selectedItem) {
				onRegisterMenuCommand();
			}


		};
	}


	protected void onLoginMenuCommand() {
		LoginListener onLogin = new LoginListener() {

			public void onLoginSuccesfull() {
				onSuccessfullLogin();
			}

			public void onLoginFailed() {
				onFailedLogin();
			}
			
		};
		if (CRBooks.authenticateUser(onLogin)) {
			this.makeMenuCommands();
		}
	}	
	
	protected void onSuccessfullLogin() {
		CRBooks.setView(new HomeWindow());
	}


	protected void onFailedLogin() {
		// TODO Auto-generated method stub
		
	}


	protected void onRegisterMenuCommand() {
		CRBooks.setView(new RegisterWindow());
	}

	protected void onLogoutMenuCommand() {
		CRBooks.setCurrentSession(null);
		makeMenuCommands();
		CRBooks.showTrayMessage("�Gracias por utilizar el sistema CR-Books! Vuelva pronto.");
		CRBooks.setView(new HomeWindow());
	}	

	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(true);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// gridMenus
		gridMenus = buildGridMenus();
		mainLayout.addComponent(gridMenus);
		
		// panelMain
		panelMain = buildPanelMain();
		panelMain.setSizeFull();
		mainLayout.addComponent(panelMain);
		
		return mainLayout;
	}

	
	private HorizontalLayout buildGridMenus() {
		// common part: create layout
		gridMenus = new HorizontalLayout();
		gridMenus.setImmediate(true);
		gridMenus.setWidth("100.0%");
		gridMenus.setHeight("-1px");
		gridMenus.setMargin(false);
		
		// menuLogo
		menuLogo = new MenuBar();
		menuLogo.setImmediate(true);
		menuLogo.setWidth("100.0%");
		menuLogo.setHeight("-1px");
		menuLogo.addStyleName("cr-books-logo-bar");
		gridMenus.addComponent(menuLogo);
		gridMenus.setExpandRatio(menuLogo, 1.0f);
		
		// menuToolbar
		menuToolbar = new MenuBar();
		menuToolbar.setImmediate(true);
		menuToolbar.setWidth("-1px");
		menuToolbar.setHeight("2.3em");
		menuToolbar.addStyleName("cr-books-menu-commands");
		gridMenus.addComponent(menuToolbar);
		
		return gridMenus;
	}

	
	private VerticalLayout buildPanelMain() {
		// common part: create layout
		panelMain = new VerticalLayout();
		panelMain.setImmediate(true);
		panelMain.setMargin(true);
		panelMain.setWidth("100.0%");
		panelMain.setHeight("100.0%");
		
		return panelMain;
	}


	public void changeView(Component view) {
		if (currentView != null) panelMain.removeComponent(currentView);
		currentView = view;
		panelMain.addComponent(view);
		panelMain.setSizeFull();
		view.setSizeFull();
	}
	
	

	protected void onManagerAdminCommand() {
		CRBooks.setView(new ManagerLayout());
	}	
	
	protected void onAdminAdminCommand() {
	
		CRBooks.setView(new AdministratorWindow());		
	}	
	
}

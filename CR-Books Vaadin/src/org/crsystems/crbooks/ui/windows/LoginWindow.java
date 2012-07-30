package org.crsystems.crbooks.ui.windows;

import org.crsystems.crbooks.application.CRBooks;
import org.crsystems.crbooks.application.listeners.LoginListener;
import org.crsystems.crbooks.models.User;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.BaseTheme;

public class LoginWindow extends CustomComponent {

	private VerticalLayout mainLayout;
	private Panel panelLogin;
	private GridLayout gridLoginFields;
	private HorizontalLayout gridRegister;
	private Button buttonRegister;
	private Label labelRegisterText;
	private Button buttonLogin;
	private PasswordField textPassword;
	private Label labelPassword;
	private TextField textEmail;
	private Label labelEmail;
	private LoginListener onRegister;

	public LoginWindow(LoginListener onRegister) {
		this.buildMainLayout();
		this.setCompositionRoot(mainLayout);
		this.makeListeners();
		this.onRegister = onRegister;
	}

	
	private void makeListeners() {
		this.buttonLogin.addListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {
				onLoginButton();
			}
			
		});
		this.buttonRegister.addListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {
				onRegisterButton();
			}
			
		});
	}


	protected void onLoginButton() {
		User user = getLoggedUser();
		if (user != null) {
			CRBooks.setCurrentSession(user.createSession());
			this.onRegister.onLoginSuccesfull();
		} else {
			CRBooks.showError("La dirección de correo electrónico o la contraseña no es válida.");
		}
	}

	private User getLoggedUser() {
		User user = User.getByEmail(this.textEmail.getValue().toString());
		if (user == null) return null;
		if (!user.getPassword().equals(this.textPassword.getValue())) return null;
		return user;
	}


	protected void onRegisterButton() {
		CRBooks.setView(new RegisterWindow());
	}
	
	
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(true);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		this.setWidth("100%");
		this.setHeight("100%");
		
		// panelLogin
		panelLogin = buildPanelLogin();
		mainLayout.addComponent(panelLogin);
		
		mainLayout.setComponentAlignment(panelLogin, Alignment.MIDDLE_CENTER);
		
		return mainLayout;
	}

	
	private Panel buildPanelLogin() {
		// common part: create layout
		panelLogin = new Panel();
		panelLogin.setImmediate(true);
		panelLogin.setWidth("-1px");
		panelLogin.setHeight("-1px");
		
		// gridLoginFields
		gridLoginFields = buildGridLoginFields();
		panelLogin.setContent(gridLoginFields);
		
		return panelLogin;
	}

	
	private GridLayout buildGridLoginFields() {
		// common part: create layout
		gridLoginFields = new GridLayout();
		gridLoginFields.setImmediate(true);
		gridLoginFields.setWidth("-1px");
		gridLoginFields.setHeight("-1px");
		gridLoginFields.setMargin(true);
		gridLoginFields.setSpacing(true);
		gridLoginFields.setColumns(2);
		gridLoginFields.setRows(4);
		
		// labelEmail
		labelEmail = new Label();
		labelEmail.setImmediate(true);
		labelEmail.setWidth("-1px");
		labelEmail.setHeight("-1px");
		labelEmail.setValue("Correo electrónico:");
		gridLoginFields.addComponent(labelEmail, 0, 0);
		
		// textEmail
		textEmail = new TextField();
		textEmail.setImmediate(true);
		textEmail.setWidth("100.0%");
		textEmail.setHeight("-1px");
		gridLoginFields.addComponent(textEmail, 1, 0);
		
		// labelPassword
		labelPassword = new Label();
		labelPassword.setImmediate(true);
		labelPassword.setWidth("-1px");
		labelPassword.setHeight("-1px");
		labelPassword.setValue("Contraseña:");
		gridLoginFields.addComponent(labelPassword, 0, 1);
		
		// textPassword
		textPassword = new PasswordField();
		textPassword.setImmediate(true);
		textPassword.setWidth("100.0%");
		textPassword.setHeight("-1px");
		gridLoginFields.addComponent(textPassword, 1, 1);
		
		// buttonLogin
		buttonLogin = new Button();
		buttonLogin.setCaption("Iniciar sesión");
		buttonLogin.setImmediate(true);
		buttonLogin.setWidth("-1px");
		buttonLogin.setHeight("-1px");
		gridLoginFields.addComponent(buttonLogin, 1, 2);
		gridLoginFields.setComponentAlignment(buttonLogin, new Alignment(34));
		
		// gridRegister
		gridRegister = buildGridRegister();
		gridLoginFields.addComponent(gridRegister, 1, 3);
		
		return gridLoginFields;
	}

	
	private HorizontalLayout buildGridRegister() {
		// common part: create layout
		gridRegister = new HorizontalLayout();
		gridRegister.setImmediate(true);
		gridRegister.setWidth("-1px");
		gridRegister.setHeight("-1px");
		gridRegister.setMargin(false);
		gridRegister.setSpacing(true);
		
		// labelRegisterText
		labelRegisterText = new Label();
		labelRegisterText.setImmediate(true);
		labelRegisterText.setWidth("-1px");
		labelRegisterText.setHeight("-1px");
		labelRegisterText.setValue("¿No eres miembro?");
		gridRegister.addComponent(labelRegisterText);
		
		// linkRegister
		buttonRegister = new Button();
		buttonRegister.setStyleName(BaseTheme.BUTTON_LINK);
		buttonRegister.setCaption("Registrarse");
		buttonRegister.setImmediate(true);
		buttonRegister.setWidth("-1px");
		buttonRegister.setHeight("-1px");
		gridRegister.addComponent(buttonRegister);
		
		return gridRegister;
	}

}

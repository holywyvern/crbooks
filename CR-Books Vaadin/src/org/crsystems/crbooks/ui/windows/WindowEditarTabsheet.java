package org.crsystems.crbooks.ui.windows;

import java.util.Date;
import java.util.List;

import org.crsystems.crbooks.application.CRBooks;
import org.crsystems.crbooks.models.User;
import org.hibernate.mapping.Map;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class WindowEditarTabsheet extends CustomComponent {

	private VerticalLayout mainLayout;
	private Panel paneRegister;
	private VerticalLayout gridRegister;
	private GridLayout gridFormFields;
	private Button buttonRegister;
	private Button buttonCancel;
	private PasswordField textPasswordConfirmation;
	private Label labelPasswordConfirmation;
	private PasswordField textPassword;
	private Label labelPassword;
	private TextField textPhone;
	private Label labelPhone;
	private TextField textPostalCode;
	private Label labelPostalCode;
	private TextField textAddress;
	private Label labelAddress;
	private TextField textCity;
	private Label labelCity;
	private TextField textState;
	private Label labelState;
	private PopupDateField dateBirthday;
	private Label labelBirthday;
	private TextField textEmailConfirmation;
	private Label labelEmailConfirmation;
	private TextField textEmail;
	private Label labelEmail;
	private TextField textLastName;
	private Label labelLastName;
	private TextField textFirstName;
	private Label labelFistName;
	private VerticalLayout gridFormheader;
	private Label labelFormHeaderTitle;
	private Label labelFormHeader;
	
	
	public WindowEditarTabsheet () {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		makeListeners();
	}

	
	private void makeListeners() {
		this.buttonRegister.addListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {
				onRegisterButton();
			}
			
		});
	}


	protected void onRegisterButton() {
		
	}

	
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// paneRegister
		paneRegister = buildPaneRegister();
		mainLayout.addComponent(paneRegister);
		mainLayout.setComponentAlignment(paneRegister, Alignment.MIDDLE_CENTER);
		
		return mainLayout;
	}

	
	private Panel buildPaneRegister() {
		// common part: create layout
		paneRegister = new Panel();
		paneRegister.setImmediate(false);
		paneRegister.setWidth("-1px");
		paneRegister.setHeight("-1px");
		
		// gridRegister
		gridRegister = buildGridRegister();
		paneRegister.setContent(gridRegister);
		
		return paneRegister;
	}

	
	private VerticalLayout buildGridRegister() {
		// common part: create layout
		gridRegister = new VerticalLayout();
		gridRegister.setImmediate(false);
		gridRegister.setWidth("100.0%");
		gridRegister.setHeight("100.0%");
		gridRegister.setMargin(true);
		gridRegister.setSpacing(true);
		
		// gridFormheader
		gridFormheader = buildGridFormheader();
		gridRegister.addComponent(gridFormheader);
		
		// gridFormFields
		gridFormFields = buildGridFormFields();
		gridRegister.addComponent(gridFormFields);
		
		return gridRegister;
	}

	
	private VerticalLayout buildGridFormheader() {
		// common part: create layout
		gridFormheader = new VerticalLayout();
		gridFormheader.setImmediate(false);
		gridFormheader.setWidth("100.0%");
		gridFormheader.setHeight("-1px");
		gridFormheader.setMargin(true);
		
		// labelFormHeader
		labelFormHeader = new Label();
		labelFormHeader.setImmediate(false);
		labelFormHeader.setWidth("-1px");
		labelFormHeader.setHeight("-1px");
		labelFormHeader.setValue("<h2>Modificar Datos</h2>");
		labelFormHeader.setContentMode(3);
		gridFormheader.addComponent(labelFormHeader);
		
		// labelFormHeaderTitle
		labelFormHeaderTitle = new Label();
		labelFormHeaderTitle.setImmediate(false);
		labelFormHeaderTitle.setWidth("-1px");
		labelFormHeaderTitle.setHeight("-1px");
		labelFormHeaderTitle.setValue("Modifique sus datos y a continuaci�n presione "+"Guardar Cambios:");
		gridFormheader.addComponent(labelFormHeaderTitle);
		
		return gridFormheader;
	}

	
	private GridLayout buildGridFormFields() {
		// common part: create layout
		gridFormFields = new GridLayout();
		gridFormFields.setImmediate(false);
		gridFormFields.setWidth("100.0%");
		gridFormFields.setHeight("100.0%");
		gridFormFields.setMargin(false);
		gridFormFields.setSpacing(true);
		gridFormFields.setColumns(2);
		gridFormFields.setRows(13);
		
		// labelFistName
		labelFistName = new Label();
		labelFistName.setImmediate(false);
		labelFistName.setWidth("-1px");
		labelFistName.setHeight("-1px");
		labelFistName.setValue("Nombres:");
		gridFormFields.addComponent(labelFistName, 0, 0);
		
		// textFirstName
		textFirstName = new TextField();
		textFirstName.setImmediate(false);
		textFirstName.setWidth("-1px");
		textFirstName.setHeight("-1px");
		textFirstName.setSecret(false);
		textFirstName.setValue(CRBooks.getCurrentUser().getFirstName());
		gridFormFields.addComponent(textFirstName, 1, 0);
		//gridFormFields.addComponent(textFirstName, 1, 0);
		
		// labelLastName
		labelLastName = new Label();
		labelLastName.setImmediate(false);
		labelLastName.setWidth("-1px");
		labelLastName.setHeight("-1px");
		labelLastName.setValue("Apellidos:");
		gridFormFields.addComponent(labelLastName, 0, 1);
		
		// textLastName
		textLastName = new TextField();
		textLastName.setImmediate(false);
		textLastName.setWidth("-1px");
		textLastName.setHeight("-1px");
		textLastName.setSecret(false);
		textLastName.setValue(CRBooks.getCurrentUser().getLastName());
		gridFormFields.addComponent(textLastName, 1, 1);
		
		// labelEmail
		labelEmail = new Label();
		labelEmail.setImmediate(false);
		labelEmail.setWidth("-1px");
		labelEmail.setHeight("-1px");
		labelEmail.setValue("Direcci�n de correo electr�nico:");
		gridFormFields.addComponent(labelEmail, 0, 2);
		
		// textEmail
		textEmail = new TextField();
		textEmail.setImmediate(false);
		textEmail.setWidth("-1px");
		textEmail.setHeight("-1px");
		textEmail.setValue(CRBooks.getCurrentUser().getEmail());
		textEmail.setSecret(false);
		gridFormFields.addComponent(textEmail, 1, 2);
		
		// labelEmailConfirmation
		labelEmailConfirmation = new Label();
		labelEmailConfirmation.setImmediate(false);
		labelEmailConfirmation.setWidth("-1px");
		labelEmailConfirmation.setHeight("-1px");
		labelEmailConfirmation.setValue("Confirmar direcc��n de correo electrnico");
		gridFormFields.addComponent(labelEmailConfirmation, 0, 3);
		
		// textEmailConfirmation
		textEmailConfirmation = new TextField();
		textEmailConfirmation.setImmediate(false);
		textEmailConfirmation.setWidth("-1px");
		textEmailConfirmation.setHeight("-1px");
		textEmailConfirmation.setValue(CRBooks.getCurrentUser().getEmail());
		textEmailConfirmation.setSecret(false);
		gridFormFields.addComponent(textEmailConfirmation, 1, 3);
		
		// labelBirthday
		labelBirthday = new Label();
		labelBirthday.setImmediate(false);
		labelBirthday.setWidth("-1px");
		labelBirthday.setHeight("-1px");
		labelBirthday.setValue("Fecha de nacimiento:");
		gridFormFields.addComponent(labelBirthday, 0, 4);
		
		// dateBirthday
		dateBirthday = new PopupDateField();
		dateBirthday.setImmediate(false);
		dateBirthday.setWidth("100.0%");
		dateBirthday.setHeight("-1px");
		dateBirthday.setInvalidAllowed(false);
		dateBirthday.setResolution(4);
		dateBirthday.setValue(CRBooks.getCurrentUser().getBirthday());
		//dateBirthday.setValue(new Date());
		gridFormFields.addComponent(dateBirthday, 1, 4);
		
		// labelState
		labelState = new Label();
		labelState.setImmediate(false);
		labelState.setWidth("-1px");
		labelState.setHeight("-1px");
		labelState.setValue("Provincia:");
		gridFormFields.addComponent(labelState, 0, 5);
		
		// textState
		textState = new TextField();
		textState.setImmediate(false);
		textState.setWidth("-1px");
		textState.setHeight("-1px");
		textState.setValue(CRBooks.getCurrentUser().getState());
		textState.setSecret(false);
		gridFormFields.addComponent(textState, 1, 5);
		
		// labelCity
		labelCity = new Label();
		labelCity.setImmediate(false);
		labelCity.setWidth("-1px");
		labelCity.setHeight("-1px");
		labelCity.setValue("Ciudad:");
		gridFormFields.addComponent(labelCity, 0, 6);
		
		// textCity
		textCity = new TextField();
		textCity.setImmediate(false);
		textCity.setWidth("-1px");
		textCity.setHeight("-1px");
		textCity.setValue(CRBooks.getCurrentUser().getCity());
		textCity.setSecret(false);
		gridFormFields.addComponent(textCity, 1, 6);
		
		// labelAddress
		labelAddress = new Label();
		labelAddress.setImmediate(false);
		labelAddress.setWidth("-1px");
		labelAddress.setHeight("-1px");
		labelAddress.setValue("Direcci�n:");
		gridFormFields.addComponent(labelAddress, 0, 7);
		
		// textAddress
		textAddress = new TextField();
		textAddress.setImmediate(false);
		textAddress.setWidth("-1px");
		textAddress.setHeight("-1px");
		textAddress.setValue(CRBooks.getCurrentUser().getAddress());
		textAddress.setSecret(false);
		gridFormFields.addComponent(textAddress, 1, 7);
		
		// labelPostalCode
		labelPostalCode = new Label();
		labelPostalCode.setImmediate(false);
		labelPostalCode.setWidth("-1px");
		labelPostalCode.setHeight("-1px");
		labelPostalCode.setValue("C�digo Postal:");
		gridFormFields.addComponent(labelPostalCode, 0, 8);
		
		// textPostalCode
		textPostalCode = new TextField();
		textPostalCode.setImmediate(false);
		textPostalCode.setWidth("-1px");
		textPostalCode.setHeight("-1px");
		textPostalCode.setValue(CRBooks.getCurrentUser().getPostalCode());
		textPostalCode.setSecret(false);
		gridFormFields.addComponent(textPostalCode, 1, 8);
		
		// labelPhone
		labelPhone = new Label();
		labelPhone.setImmediate(false);
		labelPhone.setWidth("-1px");
		labelPhone.setHeight("-1px");
		labelPhone.setValue("Tel�fono:");
		gridFormFields.addComponent(labelPhone, 0, 9);
		
		// textPhone
		textPhone = new TextField();
		textPhone.setImmediate(false);
		textPhone.setWidth("-1px");
		textPhone.setHeight("-1px");
		textPhone.setValue(CRBooks.getCurrentUser().getPhone());
		textPhone.setSecret(false);
		gridFormFields.addComponent(textPhone, 1, 9);
		
		// labelPassword
		labelPassword = new Label();
		labelPassword.setImmediate(false);
		labelPassword.setWidth("-1px");
		labelPassword.setHeight("-1px");
		labelPassword.setValue("Contrase�a:");
		gridFormFields.addComponent(labelPassword, 0, 10);
		
		// textPassword
		textPassword = new PasswordField();
		textPassword.setImmediate(false);
		textPassword.setWidth("-1px");
		textPassword.setHeight("-1px");
		textPassword.setValue(CRBooks.getCurrentUser().getPassword());
		gridFormFields.addComponent(textPassword, 1, 10);
		
		// labelPasswordConfirmation
		labelPasswordConfirmation = new Label();
		labelPasswordConfirmation.setImmediate(false);
		labelPasswordConfirmation.setWidth("-1px");
		labelPasswordConfirmation.setHeight("-1px");
		labelPasswordConfirmation.setValue("Confirmar contrase�a:");
		gridFormFields.addComponent(labelPasswordConfirmation, 0, 11);
		
		// textPasswordConfirmation
		textPasswordConfirmation = new PasswordField();
		textPasswordConfirmation.setImmediate(false);
		textPasswordConfirmation.setWidth("-1px");
		textPasswordConfirmation.setHeight("-1px");
		textPasswordConfirmation.setValue(CRBooks.getCurrentUser().getPassword());
		gridFormFields.addComponent(textPasswordConfirmation, 1, 11);		
		
		
		
		// buttonRegister
		buttonRegister = new Button();
		buttonRegister.setCaption("Guardar Cambios");		
		buttonRegister.setImmediate(true);
		buttonRegister.addListener(new ClickListener(){

			public void buttonClick(ClickEvent event) {
				onSaveUser();
				
			}

			
			
		});
		buttonRegister.setWidth("-1px");
		buttonRegister.setHeight("-1px");
		gridFormFields.addComponent(buttonRegister, 1, 12);
		gridFormFields.setComponentAlignment(buttonRegister, new Alignment(6));
		
	
		return gridFormFields;
	}
	
	private void onSaveUser() {
		User user=CRBooks.getCurrentUser();
		user.setFirstName(textFirstName.getValue().toString());
		user.setLastName(textLastName.getValue().toString());
		user.setEmail(textEmail.getValue().toString());
		user.setBirthday((Date)dateBirthday.getValue());
		user.setState(textState.getValue().toString());
		user.setCity(textCity.getValue().toString());
		user.setAddress(textAddress.getValue().toString());
		user.setPostalCode(textPostalCode.getValue().toString());
		user.setPhone(textPhone.getValue().toString());
		user.setPassword(textPassword.getValue().toString());
		if (user.saveOrUpdate()){
			CRBooks.showTrayMessage("Sus cambios se han guardado con exito.");
			CRBooks.setView(new HomeWindow());
		}
	}

}

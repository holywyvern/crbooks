package org.crsystems.crbooks.ui.windows;

import org.crsystems.crbooks.models.BookCategory;
import org.crsystems.crbooks.models.Publisher;
import org.crsystems.crbooks.ui.layouts.ManagerLayout;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class NewPublisherWindow extends CustomComponent {

	
	private VerticalLayout mainLayout;
	private Panel panelForm;
	private VerticalLayout gridForm;
	private GridLayout gridFormParams;
	private Button buttonForm;
	private TextField textDescription;
	private Label labelDescription;
	private TextField textName;
	private Label labelName;	
	private Label labelHeader;

	public NewPublisherWindow() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		createListeners();
	}

	
	private void createListeners() {
		buttonForm.addListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {
				onFormButtonClick();
			}
			
		});
	}

	protected void onFormButtonClick() {
		Publisher publisher = new Publisher();
		publisher.setDescription(textDescription.getValue().toString());
		publisher.setName(textName.getValue().toString());
		if (publisher.save()) {
			ManagerLayout.getCurrent().changeView(new ViewPublishersWindow());
		}
	}	

	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// panelForm
		panelForm = buildPanelForm();
		mainLayout.addComponent(panelForm);
		mainLayout.setComponentAlignment(panelForm, new Alignment(20));
		
		return mainLayout;
	}

	
	private Panel buildPanelForm() {
		// common part: create layout
		panelForm = new Panel();
		panelForm.setImmediate(false);
		panelForm.setWidth("-1px");
		panelForm.setHeight("-1px");
		
		// gridForm
		gridForm = buildGridForm();
		panelForm.setContent(gridForm);
		
		return panelForm;
	}

	
	private VerticalLayout buildGridForm() {
		// common part: create layout
		gridForm = new VerticalLayout();
		gridForm.setImmediate(false);
		gridForm.setWidth("100.0%");
		gridForm.setHeight("100.0%");
		gridForm.setMargin(true);
		gridForm.setSpacing(true);
		
		// labelHeader
		labelHeader = new Label();
		labelHeader.setImmediate(false);
		labelHeader.setWidth("-1px");
		labelHeader.setHeight("-1px");
		labelHeader.setValue("<h2>Nueva Editorial</h2>");
		labelHeader.setContentMode(3);
		gridForm.addComponent(labelHeader);
		
		// gridFormParams
		gridFormParams = buildGridFormParams();
		gridForm.addComponent(gridFormParams);
		
		return gridForm;
	}

	
	private GridLayout buildGridFormParams() {
		// common part: create layout
		gridFormParams = new GridLayout();
		gridFormParams.setImmediate(false);
		gridFormParams.setWidth("-1px");
		gridFormParams.setHeight("-1px");
		gridFormParams.setMargin(false);
		gridFormParams.setSpacing(true);
		gridFormParams.setColumns(2);
		gridFormParams.setRows(3);
		
		// labelName
		labelName = new Label();
		labelName.setImmediate(false);
		labelName.setWidth("-1px");
		labelName.setHeight("-1px");
		labelName.setValue("Nombre:");
		gridFormParams.addComponent(labelName, 0, 0);
		
		// textName
		textName = new TextField();
		textName.setImmediate(false);
		textName.setWidth("-1px");
		textName.setHeight("-1px");
		textName.setSecret(false);
		gridFormParams.addComponent(textName, 1, 0);
		
		// labelDescription
		labelDescription = new Label();
		labelDescription.setImmediate(false);
		labelDescription.setWidth("-1px");
		labelDescription.setHeight("-1px");
		labelDescription.setValue("Descripción:");
		gridFormParams.addComponent(labelDescription, 0, 1);
		
		// textDescription
		textDescription = new TextField();
		textDescription.setImmediate(false);
		textDescription.setWidth("-1px");
		textDescription.setHeight("-1px");
		textDescription.setSecret(false);
		gridFormParams.addComponent(textDescription, 1, 1);
		
		// buttonForm
		buttonForm = new Button();
		buttonForm.setCaption("Crear editorial");
		buttonForm.setImmediate(false);
		buttonForm.setWidth("-1px");
		buttonForm.setHeight("-1px");
		gridFormParams.addComponent(buttonForm, 1, 2);
		gridFormParams.setComponentAlignment(buttonForm, new Alignment(6));
		
		return gridFormParams;
	}

}

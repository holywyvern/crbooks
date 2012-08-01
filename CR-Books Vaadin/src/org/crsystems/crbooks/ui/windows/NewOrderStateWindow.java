package org.crsystems.crbooks.ui.windows;

import org.crsystems.crbooks.models.OrderState;
import org.crsystems.crbooks.ui.layouts.ManagerLayout;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class NewOrderStateWindow extends CustomComponent{

	
	private VerticalLayout mainLayout;
	private Panel panelForm;
	private VerticalLayout gridFormPanel;
	private GridLayout gridFormContent;
	private Button buttonForm;
	private CheckBox checkFinal;
	private Label labelFinal;
	private TextField textDescription;
	private Label labelDescription;
	private TextField textName;
	private Label labelname;
	private Label labelFormHeader;


	public NewOrderStateWindow() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		makeListeners();
		buttonForm.addStyleName("default");
	}

	
	private void makeListeners() {
		this.buttonForm.addListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {
				onFormButtonClick();
			}
			
		});
	}


	protected void onFormButtonClick() {
		OrderState state = new OrderState();
		state.setName(this.textName.getValue().toString());
		state.setDescription(this.textDescription.getValue().toString());
		state.setFinal(this.checkFinal.booleanValue());
		if (state.saveOrUpdate()) {
			ManagerLayout.getCurrent().changeView(new ViewOrderStateWindow());
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
		
		// gridFormPanel
		gridFormPanel = buildGridFormPanel();
		panelForm.setContent(gridFormPanel);
		
		return panelForm;
	}

	
	private VerticalLayout buildGridFormPanel() {
		// common part: create layout
		gridFormPanel = new VerticalLayout();
		gridFormPanel.setImmediate(false);
		gridFormPanel.setWidth("100.0%");
		gridFormPanel.setHeight("100.0%");
		gridFormPanel.setMargin(true);
		gridFormPanel.setSpacing(true);
		
		// labelFormHeader
		labelFormHeader = new Label();
		labelFormHeader.setImmediate(false);
		labelFormHeader.setWidth("-1px");
		labelFormHeader.setHeight("-1px");
		labelFormHeader.setValue("<h2>Editar Estado</h2>");
		labelFormHeader.setContentMode(3);
		gridFormPanel.addComponent(labelFormHeader);
		
		// gridFormContent
		gridFormContent = buildGridFormContent();
		gridFormPanel.addComponent(gridFormContent);
		
		return gridFormPanel;
	}

	
	private GridLayout buildGridFormContent() {
		// common part: create layout
		gridFormContent = new GridLayout();
		gridFormContent.setImmediate(false);
		gridFormContent.setWidth("-1px");
		gridFormContent.setHeight("-1px");
		gridFormContent.setMargin(true);
		gridFormContent.setSpacing(true);
		gridFormContent.setColumns(2);
		gridFormContent.setRows(4);
		
		// labelname
		labelname = new Label();
		labelname.setImmediate(false);
		labelname.setWidth("-1px");
		labelname.setHeight("-1px");
		labelname.setValue("Nombre:");
		gridFormContent.addComponent(labelname, 0, 0);
		
		// textName
		textName = new TextField();
		textName.setImmediate(false);
		textName.setWidth("-1px");
		textName.setHeight("-1px");
		textName.setSecret(false);
		gridFormContent.addComponent(textName, 1, 0);
		
		// labelDescription
		labelDescription = new Label();
		labelDescription.setImmediate(false);
		labelDescription.setWidth("-1px");
		labelDescription.setHeight("-1px");
		labelDescription.setValue("Descripción:");
		gridFormContent.addComponent(labelDescription, 0, 1);
		
		// textDescription
		textDescription = new TextField();
		textDescription.setImmediate(false);
		textDescription.setWidth("-1px");
		textDescription.setHeight("-1px");
		textDescription.setSecret(false);
		gridFormContent.addComponent(textDescription, 1, 1);
		
		// labelFinal
		labelFinal = new Label();
		labelFinal.setImmediate(false);
		labelFinal.setWidth("-1px");
		labelFinal.setHeight("-1px");
		labelFinal.setValue("EstadoFinal:");
		gridFormContent.addComponent(labelFinal, 0, 2);
		
		// checkFinal
		checkFinal = new CheckBox();
		checkFinal.setImmediate(false);
		checkFinal.setWidth("-1px");
		checkFinal.setHeight("-1px");
		gridFormContent.addComponent(checkFinal, 1, 2);
		
		// buttonForm
		buttonForm = new Button();
		buttonForm.setCaption("Editar Estado");
		buttonForm.setImmediate(false);
		buttonForm.setWidth("-1px");
		buttonForm.setHeight("-1px");
		gridFormContent.addComponent(buttonForm, 1, 3);
		gridFormContent.setComponentAlignment(buttonForm, new Alignment(6));
		
		return gridFormContent;
	}




}

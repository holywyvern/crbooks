package org.crsystems.crbooks.ui.windows;

import org.crsystems.crbooks.models.BookCategory;
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

public class EditCategoryWindow extends CustomComponent {

	
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

	public EditCategoryWindow() {
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
		BookCategory category = new BookCategory();
		category.setDescription(textDescription.getValue().toString());
		category.setName(textName.getValue().toString());
		if (category.update()) {
			ManagerLayout.getCurrent().changeView(new ViewCategoriesWindow());
		}
	}	

	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(true);
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
		panelForm.setImmediate(true);
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
		gridForm.setImmediate(true);
		gridForm.setWidth("100.0%");
		gridForm.setHeight("100.0%");
		gridForm.setMargin(true);
		gridForm.setSpacing(true);
		
		// labelHeader
		labelHeader = new Label();
		labelHeader.setImmediate(true);
		labelHeader.setWidth("-1px");
		labelHeader.setHeight("-1px");
		labelHeader.setValue("<h2>Editar Categoria</h2>");
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
		gridFormParams.setImmediate(true);
		gridFormParams.setWidth("-1px");
		gridFormParams.setHeight("-1px");
		gridFormParams.setMargin(false);
		gridFormParams.setSpacing(true);
		gridFormParams.setColumns(2);
		gridFormParams.setRows(3);
		
		// labelName
		labelName = new Label();
		labelName.setImmediate(true);
		labelName.setWidth("-1px");
		labelName.setHeight("-1px");
		labelName.setValue("Nombre:");
		gridFormParams.addComponent(labelName, 0, 0);
		
		// textName
		textName = new TextField();
		textName.setImmediate(true);
		textName.setWidth("-1px");
		textName.setHeight("-1px");
		gridFormParams.addComponent(textName, 1, 0);
		
		// labelDescription
		labelDescription = new Label();
		labelDescription.setImmediate(true);
		labelDescription.setWidth("-1px");
		labelDescription.setHeight("-1px");
		labelDescription.setValue("Descripción:");
		gridFormParams.addComponent(labelDescription, 0, 1);
		
		// textDescription
		textDescription = new TextField();
		textDescription.setImmediate(true);
		textDescription.setWidth("-1px");
		textDescription.setHeight("-1px");
		gridFormParams.addComponent(textDescription, 1, 1);
		
		// buttonForm
		buttonForm = new Button();
		buttonForm.setCaption("Editar categoria");
		buttonForm.setImmediate(true);
		buttonForm.setWidth("-1px");
		buttonForm.setHeight("-1px");
		gridFormParams.addComponent(buttonForm, 1, 2);
		gridFormParams.setComponentAlignment(buttonForm, new Alignment(6));
		
		return gridFormParams;
	}

}

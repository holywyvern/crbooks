package org.crsystems.crbooks.ui.windows;

import java.io.Serializable;
import java.util.List;

import org.crsystems.crbooks.application.CRBooks;
import org.crsystems.crbooks.models.ModelBase;
import org.crsystems.crbooks.ui.layouts.ManagerLayout;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public abstract class ViewBaseWindow<T extends ModelBase<T, PK>, PK extends Serializable> extends CustomComponent {

	private abstract class ItemClickListener implements ClickListener {

		protected T itemEvt;
		
		public ItemClickListener(T item) {
			super();
			itemEvt = item;
		}
 
	}
	
	
	
	private VerticalLayout mainLayout;
	private Panel panelData;
	private VerticalLayout gridPanel;
	private Table tableData;
	private Label labelheader;
	private String header;


	public ViewBaseWindow() {
		this("");
	}
	
	public ViewBaseWindow(String header) {
		super();
		this.header = header;
		buildMainLayout();
		setCompositionRoot(mainLayout);
		createTableData();
	}

	
	private void createTableData() {
		createTableColumns(this.tableData, true);
		for (T item : getAllItems()) {
			tableData.addItem(itemToTable(item, true, true), item);
		}
	}

	protected abstract void createTableColumns(Table tableData, boolean showLinkBar);
	
	protected abstract List<T> getAllItems();
	
	protected abstract Object[] itemToTable(T item, boolean showEdit, boolean showDelete);
	
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
		
		// panelData
		panelData = buildPanelData();
		mainLayout.addComponent(panelData);
		mainLayout.setComponentAlignment(panelData, new Alignment(48));
		
		return mainLayout;
	}

	
	private Panel buildPanelData() {
		// common part: create layout
		panelData = new Panel();
		panelData.setImmediate(false);
		panelData.setWidth("-1px");
		panelData.setHeight("-1px");
		
		// gridPanel
		gridPanel = buildGridPanel();
		panelData.setContent(gridPanel);
		
		return panelData;
	}

	
	private VerticalLayout buildGridPanel() {
		// common part: create layout
		gridPanel = new VerticalLayout();
		gridPanel.setImmediate(false);
		gridPanel.setWidth("100.0%");
		gridPanel.setHeight("100.0%");
		gridPanel.setMargin(true);
		gridPanel.setSpacing(true);
		
		// labelheader
		labelheader = new Label();
		labelheader.setImmediate(false);
		labelheader.setWidth("-1px");
		labelheader.setHeight("-1px");
		labelheader.setValue(String.format("<h2>%s</h2>", this.header));
		labelheader.setContentMode(3);
		gridPanel.addComponent(labelheader);
		
		// tableData
		tableData = new Table();
		tableData.setImmediate(false);
		tableData.setWidth("100.0%");
		tableData.setHeight("100.0%");
		gridPanel.addComponent(tableData);
		gridPanel.setExpandRatio(tableData, 1.0f);
		
		return gridPanel;
	}

	protected abstract EditBaseWindow<T> createEditWindow();
	
	protected Button createEditButton(T item) {
		Button buttonEdit = new Button("Editar");
		buttonEdit.setStyleName(BaseTheme.BUTTON_LINK);
		buttonEdit.addListener(new ItemClickListener(item) {

			public void buttonClick(ClickEvent event) {
				onEditButtonClick(itemEvt);
			}
			
		});	
		return buttonEdit;
	}
	
	protected Button createDeleteButton(T item) {
		Button buttonDelete = new Button("Eliminar");
		buttonDelete.setStyleName(BaseTheme.BUTTON_LINK);
		buttonDelete.addListener(new ItemClickListener(item) {

			public void buttonClick(ClickEvent event) {
				onDeleteButtonClick(itemEvt);
			}
			
		});		
		return buttonDelete;
	}
	
	
	protected HorizontalLayout createActionLayout(T item, boolean showEdit, boolean showDelete) {
		HorizontalLayout l = new HorizontalLayout();
		
		if (showEdit) {
			l.addComponent(createEditButton(item));			
		}
		if (showDelete) {
			l.addComponent(createDeleteButton(item));
		}
		l.setSizeFull();
		l.setSpacing(true);		
		return l;
	}
	
	protected void onDeleteButtonClick(T item) {
		this.tableData.removeItem(item);
		if (item.delete()) {
			CRBooks.showMessage("Se ha borrado el elemento exitosamente.");
		}
	}
	
	protected void onEditButtonClick(T item) {
		
	}
	
}

package org.crsystems.crbooks.ui.windows;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class CurrentOrderWindow extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Panel panelOrderPanel;
	@AutoGenerated
	private VerticalLayout gridPanel;
	@AutoGenerated
	private Button buttonCloseOrder;
	@AutoGenerated
	private Table tableCurrentOrder;
	@AutoGenerated
	private Label labelHeader;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public CurrentOrderWindow() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	@AutoGenerated
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
		
		// panelOrderPanel
		panelOrderPanel = buildPanelOrderPanel();
		mainLayout.addComponent(panelOrderPanel);
		mainLayout.setComponentAlignment(panelOrderPanel, new Alignment(20));
		
		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPanelOrderPanel() {
		// common part: create layout
		panelOrderPanel = new Panel();
		panelOrderPanel.setImmediate(false);
		panelOrderPanel.setWidth("-1px");
		panelOrderPanel.setHeight("-1px");
		
		// gridPanel
		gridPanel = buildGridPanel();
		panelOrderPanel.setContent(gridPanel);
		
		return panelOrderPanel;
	}

	@AutoGenerated
	private VerticalLayout buildGridPanel() {
		// common part: create layout
		gridPanel = new VerticalLayout();
		gridPanel.setImmediate(false);
		gridPanel.setWidth("100.0%");
		gridPanel.setHeight("100.0%");
		gridPanel.setMargin(true);
		gridPanel.setSpacing(true);
		
		// labelHeader
		labelHeader = new Label();
		labelHeader.setImmediate(false);
		labelHeader.setWidth("-1px");
		labelHeader.setHeight("-1px");
		labelHeader.setValue("<h2>Mi pedido actual</h2>");
		labelHeader.setContentMode(3);
		gridPanel.addComponent(labelHeader);
		
		// tableCurrentOrder
		tableCurrentOrder = new Table();
		tableCurrentOrder.setImmediate(false);
		tableCurrentOrder.setWidth("100.0%");
		tableCurrentOrder.setHeight("100.0%");
		gridPanel.addComponent(tableCurrentOrder);
		gridPanel.setExpandRatio(tableCurrentOrder, 1.0f);
		
		// buttonCloseOrder
		buttonCloseOrder = new Button();
		buttonCloseOrder.setCaption("Cerrar Pedido");
		buttonCloseOrder.setImmediate(false);
		buttonCloseOrder.setWidth("-1px");
		buttonCloseOrder.setHeight("-1px");
		gridPanel.addComponent(buttonCloseOrder);
		gridPanel.setComponentAlignment(buttonCloseOrder, new Alignment(6));
		
		return gridPanel;
	}

}

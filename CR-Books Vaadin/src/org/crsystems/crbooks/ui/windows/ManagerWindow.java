package org.crsystems.crbooks.ui.windows;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class ManagerWindow extends CustomComponent {

	
	private HorizontalLayout mainLayout;
	private GridLayout gridPanelData;
	private Panel panelTree;
	private VerticalLayout gridTree;
	private Tree treeMenu;
	private Label labelMenu;

	public ManagerWindow() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	
	private HorizontalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// panelTree
		panelTree = buildPanelTree();
		mainLayout.addComponent(panelTree);
		
		// gridPanelData
		gridPanelData = new GridLayout();
		gridPanelData.setImmediate(false);
		gridPanelData.setWidth("100.0%");
		gridPanelData.setHeight("100.0%");
		gridPanelData.setMargin(false);
		mainLayout.addComponent(gridPanelData);
		mainLayout.setExpandRatio(gridPanelData, 1.0f);
		
		return mainLayout;
	}

	
	private Panel buildPanelTree() {
		// common part: create layout
		panelTree = new Panel();
		panelTree.setImmediate(false);
		panelTree.setWidth("-1px");
		panelTree.setHeight("100.0%");
		
		// gridTree
		gridTree = buildGridTree();
		panelTree.setContent(gridTree);
		
		return panelTree;
	}

	
	private VerticalLayout buildGridTree() {
		// common part: create layout
		gridTree = new VerticalLayout();
		gridTree.setImmediate(false);
		gridTree.setWidth("100.0%");
		gridTree.setHeight("100.0%");
		gridTree.setMargin(true);
		
		// labelMenu
		labelMenu = new Label();
		labelMenu.setImmediate(false);
		labelMenu.setWidth("-1px");
		labelMenu.setHeight("-1px");
		labelMenu.setValue("<h2>Menú principal</h2>");
		labelMenu.setContentMode(3);
		gridTree.addComponent(labelMenu);
		
		// treeMenu
		treeMenu = new Tree();
		treeMenu.setImmediate(false);
		treeMenu.setWidth("-1px");
		treeMenu.setHeight("100.0%");
		gridTree.addComponent(treeMenu);
		gridTree.setExpandRatio(treeMenu, 1.0f);
		
		return gridTree;
	}

}

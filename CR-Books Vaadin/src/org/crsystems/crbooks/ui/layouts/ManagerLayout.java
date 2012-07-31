package org.crsystems.crbooks.ui.layouts;

import org.crsystems.crbooks.ui.windows.ConsultaGerente;
import org.crsystems.crbooks.ui.windows.ManagerHomeWindow;
import org.crsystems.crbooks.ui.windows.NewAuthorWindow;
import org.crsystems.crbooks.ui.windows.NewBookWindow;
import org.crsystems.crbooks.ui.windows.NewCategoryWindow;
import org.crsystems.crbooks.ui.windows.NewOrderStateWindow;
import org.crsystems.crbooks.ui.windows.NewPublisherWindow;
import org.crsystems.crbooks.ui.windows.ViewAuthorsWindow;
import org.crsystems.crbooks.ui.windows.ViewBooksWindow;
import org.crsystems.crbooks.ui.windows.ViewCategoriesWindow;
import org.crsystems.crbooks.ui.windows.ViewOrderStateWindow;
import org.crsystems.crbooks.ui.windows.ViewPublishersWindow;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class ManagerLayout extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3109984203161042744L;

	private static ManagerLayout current;
	
	private static final String[][] menues = {
		{"Escritorio"},
		{"Consultas", "Buscar pedidos"},
		{"Categorias", "Ver categorias", "Agregar categoria" },
		{"Editoriales", "Ver editoriales", "Agregar editorial"},
		{"Autores", "Ver autores", "Agregar autor"},
		{"Libros", "Ver libros", "Agregar libro"},
		{"Estados de pedidos", "Ver estados de pedido", "Agregar estados de pedido"},
		{"Clientes", "Ver clientes", "Suspender cliente", "Bloquear cliente"}
		
	};	
	
	private HorizontalLayout mainLayout;
	private GridLayout gridPanelData;
	private Panel panelTree;
	private VerticalLayout gridTree;
	private Tree treeMenu;
	private Label labelMenu;

	private Component lastView;

	private Component currentView;
	private String lastValue;
	
	public ManagerLayout() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		createTreeMenues();
		addListeners();
		ManagerLayout.current = this;
		this.changeView(new ManagerHomeWindow());
	}

	
	public static ManagerLayout getCurrent() {
		return ManagerLayout.current;
	}
	
	private void addListeners() {
		for (String[] m : ManagerLayout.menues) {
			int i = 0;
			for (String p : m) {
				treeMenu.addItem(m[i]);
				if (i != 0) {
					treeMenu.setParent(p, m[0]);
					treeMenu.setChildrenAllowed(p, false);
				}
				i++;
			}
			
			treeMenu.expandItemsRecursively(m[0]);
		}
		treeMenu.setChildrenAllowed("Escritorio", false);
	}; 


	private void createTreeMenues() {
		treeMenu.addListener(new ValueChangeListener() {

			public void valueChange(ValueChangeEvent event) {
				onSelectItem((treeMenu.getValue() == null) ? "" : treeMenu.getValue().toString());
			}
			
		});
	}

	protected void onSelectItem(String value) {
		if (value == lastValue) return;
		lastValue = value;
		if (value == "Escritorio") {
			this.changeView(new ManagerHomeWindow());
		} else if (value == "Buscar pedidos") {
			this.changeView(new ConsultaGerente());
		} else if (value == "Ver categorias") {
			this.changeView(new ViewCategoriesWindow());
		} else if (value == "Agregar categoria") {
			this.changeView(new NewCategoryWindow());
		} else if (value == "Ver libros") {
			this.changeView(new ViewBooksWindow());
		} else if (value == "Agregar libro") {
			this.changeView(new NewBookWindow());
		} else if (value == "Agregar Stock") {
			
		} else if (value == "Ver clientes") {
			
		} else if (value == "Suspender cliente") {
			
		} else if (value == "Bloquear cliente") {
			
		} else if (value == "Ver editoriales") {
			this.changeView(new ViewPublishersWindow());
		} else if (value == "Agregar editorial") {
			this.changeView(new NewPublisherWindow());
		} else if (value == "Ver estados de pedido") {
			this.changeView(new ViewOrderStateWindow());
		} else if (value == "Agregar estados de pedido") {
			this.changeView(new NewOrderStateWindow());
		} else if (value == "Ver autores") {
			this.changeView(new ViewAuthorsWindow());
		} else if (value == "Agregar autor") {
			this.changeView(new NewAuthorWindow());
		} 
	}	
	

	private HorizontalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(true);
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
		gridPanelData.setImmediate(true);
		gridPanelData.setWidth("100.0%");
		gridPanelData.setHeight("100.0%");
		gridPanelData.setMargin(false);
		gridPanelData.setRows(1);
		gridPanelData.setColumns(1);
		mainLayout.addComponent(gridPanelData);
		mainLayout.setExpandRatio(gridPanelData, 1.0f);
		
		return mainLayout;
	}

	
	private Panel buildPanelTree() {
		// common part: create layout
		panelTree = new Panel();
		panelTree.setImmediate(true);
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
		gridTree.setImmediate(true);
		gridTree.setWidth("100.0%");
		gridTree.setHeight("100.0%");
		gridTree.setMargin(true);
		
		// labelMenu
		labelMenu = new Label();
		labelMenu.setImmediate(true);
		labelMenu.setWidth("-1px");
		labelMenu.setHeight("-1px");
		labelMenu.setValue("<h2>Menú principal</h2>");
		labelMenu.setContentMode(3);
		gridTree.addComponent(labelMenu);
		
		// treeMenu
		treeMenu = new Tree();
		treeMenu.setImmediate(true);
		treeMenu.setWidth("-1px");
		treeMenu.setHeight("100.0%");
		gridTree.addComponent(treeMenu);
		gridTree.setExpandRatio(treeMenu, 1.0f);
		
		return gridTree;
	}

	public void changeView(Component view) {
		lastView = currentView;
		currentView = view;
		gridPanelData.removeAllComponents();
		gridPanelData.addComponent(view);
	}
	
}

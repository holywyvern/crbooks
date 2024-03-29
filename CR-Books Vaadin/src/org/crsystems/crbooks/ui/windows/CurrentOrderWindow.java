package org.crsystems.crbooks.ui.windows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.crsystems.crbooks.application.CRBooks;
import org.crsystems.crbooks.application.HibernateUtil;
import org.crsystems.crbooks.models.Order;
import org.crsystems.crbooks.models.OrderItem;
import org.crsystems.crbooks.models.OrderState;
import org.crsystems.crbooks.models.User;
import org.hibernate.Transaction;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class CurrentOrderWindow extends CustomComponent {

	private abstract class ItemTextChangeListener implements TextChangeListener {

		protected OrderItem itemDV; 
		protected TextField fieldDV;
		
		public ItemTextChangeListener(OrderItem item, TextField field) {
			itemDV = item;
			fieldDV = field;
		}
		
	}
	
	private abstract class ItemClickListener implements ClickListener {
		
		protected OrderItem itemDV;
		
		public	ItemClickListener(OrderItem item) {
			itemDV = item;
		}
	}
	
	private VerticalLayout mainLayout;	
	private Panel panelOrderPanel;
	private VerticalLayout gridPanel;
	private Button buttonCloseOrder;
	private Table tableCurrentOrder;
	private Label labelHeader;
	private Order order;

	public CurrentOrderWindow() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		createTableColumns();
		createTableData();
		makeListeners();
	}

	
	private void createTableColumns() {
		tableCurrentOrder.addContainerProperty("Libro", String.class, null);
		tableCurrentOrder.addContainerProperty("Autor", String.class, null);
		tableCurrentOrder.addContainerProperty("Cantidad", TextField.class, null);
		tableCurrentOrder.addContainerProperty("Costo unitario", String.class, null);
		tableCurrentOrder.addContainerProperty("Subtotal",  String.class, null);
		tableCurrentOrder.addContainerProperty("Acciones", HorizontalLayout.class, null);
		tableCurrentOrder.setFooterVisible(true);
		tableCurrentOrder.setColumnFooter("Libro", "");
		tableCurrentOrder.setColumnFooter("Cantidad", "");
		tableCurrentOrder.setColumnFooter("Costo unitario", "Total");
		tableCurrentOrder.setColumnFooter("Subtotal", getTotalPriceCaption());
	}


	private String getTotalPriceCaption() {
		return String.format("$%.2f", getTotalPrice());
	}


	private Double getTotalPrice() {
		Double tp = 0.0;
		for (OrderItem item : CRBooks.getCurrentSession().getCurrentItems()) {
			tp = tp + item.getAmount() * item.getBook().getPrice();
		}
		return tp;
	}


	private void createTableData() {
		List<OrderItem> list = CRBooks.getCurrentSession().getCurrentItems();
		if (list == null) list = OrderItem.getByOrder(order);
		if (list == null) list = CRBooks.getCurrentSession().getOrderItems();
		if (list == null) list = new ArrayList<OrderItem>();
		for (OrderItem item : list) {
			this.tableCurrentOrder.addItem(itemToTable(item), item);
		}
	}


	private Object[] itemToTable(OrderItem item) {
		return new Object[] {
				item.getBook().getTitle(),
				item.getBook().getAuthor().getName(),
				makeItemtextField(item),
				String.format("$%.2f", item.getBook().getPrice()),
				String.format("$%.2f", item.getAmount() * item.getBook().getPrice()),
				createActionLayout(item)
		};
	}

	private HorizontalLayout createActionLayout(OrderItem item) {
		HorizontalLayout l = new HorizontalLayout();
		l.setSizeFull();
		l.setSpacing(true);
		l.addComponent(createEditButton(item));
		return l;
	}


	private Component createEditButton(OrderItem item) {
		Button button = new Button("Eliminar");
		button.setStyleName(BaseTheme.BUTTON_LINK);
		button.addListener(new ItemClickListener(item) {
			public void buttonClick(ClickEvent event) {
				onDeleteItemClick(itemDV);
			}
			
		});
		return button;
	}


	protected void onDeleteItemClick(OrderItem item) {
		CRBooks.getCurrentSession().getCurrentItems().remove(item);
		CRBooks.getCurrentSession().getOrderItems().remove(item);
		CRBooks.getCurrentSession().removeItem(item);
		CRBooks.setView(new CurrentOrderWindow());
	}


	private TextField makeItemtextField(OrderItem item) {
		TextField field = new TextField();
		field.setValue(item.getAmount());
		field.setImmediate(true);
		field.addListener(new ItemTextChangeListener(item, field) {

			public void textChange(TextChangeEvent event) {
				onAmountItemChange(itemDV, event.getText());
			}
			
		});
		return field;
	}
	
	
	protected void onAmountItemChange(OrderItem item, String txt) {
		Integer amount = 0;
		try {
			amount = Integer.parseInt(txt);
		} catch (Exception e) {
			amount = 1;
		}
		if (amount < 1) amount = 1;
		item.setAmount(amount);
		CRBooks.setView(new CurrentOrderWindow());
	}


	private void makeListeners() {
		this.buttonCloseOrder.addListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {
				onButtonCloseOrder();
			}
			
		});
	}


	protected void onButtonCloseOrder() {
		Order order = null;
		if (CRBooks.getCurrentSession().getOrder() != null) Order.getByID(CRBooks.getCurrentSession().getOrder().getOrderID());
		if (order == null) order = new Order();
		order.setCreatedAt(new Date());
		order.setState(OrderState.getAll().get(0));
		order.setUser(User.getByID(CRBooks.getCurrentUser().getUserID()));
		CRBooks.getCurrentUser().update();
		order.setOrderID(CRBooks.getCurrentSession().getOrderID());
		if ((!CRBooks.getCurrentSession().isEdititing()) ? order.save() : order.update()) {
			for (OrderItem item : CRBooks.getCurrentSession().getOrderItems()) {
				OrderItem i = new OrderItem();
				i.setOrderItemID(item.getOrderItemID());
				i.setAmount(item.getAmount());
				i.setBook(item.getBook());
				i.setOrder(order);
				i.saveOrUpdate();
			}
			CRBooks.getCurrentSession().getCurrentItems().clear();
			CRBooks.getCurrentSession().closeOrder();
			CRBooks.setView(new ViewUserOrders());
			CRBooks.showTrayMessage("Se ha realizado su pedido exitosamente.");
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
		
		// panelOrderPanel
		panelOrderPanel = buildPanelOrderPanel();
		mainLayout.addComponent(panelOrderPanel);
		mainLayout.setComponentAlignment(panelOrderPanel, new Alignment(20));
		
		return mainLayout;
	}

	
	private Panel buildPanelOrderPanel() {
		// common part: create layout
		panelOrderPanel = new Panel();
		panelOrderPanel.setImmediate(true);
		panelOrderPanel.setWidth("-1px");
		panelOrderPanel.setHeight("100%");
		
		// gridPanel
		gridPanel = buildGridPanel();
		panelOrderPanel.setContent(gridPanel);
		
		return panelOrderPanel;
	}

	
	private VerticalLayout buildGridPanel() {
		// common part: create layout
		gridPanel = new VerticalLayout();
		gridPanel.setImmediate(true);
		gridPanel.setWidth("100.0%");
		gridPanel.setHeight("100.0%");
		gridPanel.setMargin(true);
		gridPanel.setSpacing(true);
		
		// labelHeader
		labelHeader = new Label();
		labelHeader.setImmediate(true);
		labelHeader.setWidth("-1px");
		labelHeader.setHeight("-1px");
		labelHeader.setValue("<h2>Mi pedido actual</h2>");
		labelHeader.setContentMode(3);
		gridPanel.addComponent(labelHeader);
		
		// tableCurrentOrder
		tableCurrentOrder = new Table();
		tableCurrentOrder.setImmediate(false);
		tableCurrentOrder.setWidth("100.0%");
		tableCurrentOrder.setHeight("-1px");
		gridPanel.addComponent(tableCurrentOrder);
		gridPanel.setExpandRatio(tableCurrentOrder, 1.0f);
		
		// buttonCloseOrder
		buttonCloseOrder = new Button();
		buttonCloseOrder.setCaption("Cerrar Pedido");
		buttonCloseOrder.setImmediate(true);
		buttonCloseOrder.setWidth("-1px");
		buttonCloseOrder.setHeight("-1px");
		gridPanel.addComponent(buttonCloseOrder);
		gridPanel.setComponentAlignment(buttonCloseOrder, new Alignment(6));
		
		return gridPanel;
	}

}

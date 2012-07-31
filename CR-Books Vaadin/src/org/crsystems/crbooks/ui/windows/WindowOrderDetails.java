package org.crsystems.crbooks.ui.windows;

import org.crsystems.crbooks.application.CRBooks;
import org.crsystems.crbooks.models.Order;
import org.crsystems.crbooks.models.OrderItem;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class WindowOrderDetails extends Window {

	
	private VerticalLayout mainLayout;
	private Button buttonClose;
	private Table tableItems;
	private Label labelHeader;

	public WindowOrderDetails(Order order) {
		super();
		buildMainLayout();
		this.addComponent(mainLayout);
		makeTableColumns(order);
		addTableItems(order);
		createListeners();
		this.setModal(true);
	}

	
	private void createListeners() {
		this.buttonClose.addListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {
				onWindowCloseClick();
			}
			
		});
		this.addListener(new CloseListener() {

			public void windowClose(CloseEvent e) {
				onWindowCloseClick();
			}
			
		});
	}


	protected void onWindowCloseClick() {
		this.setVisible(false);
		CRBooks.getInstance().getMainWindow().removeWindow(this);
	}


	private void addTableItems(Order order) {
		for (OrderItem item : order.getItems()) {
			tableItems.addItem(itemToTable(item), item);
		}
	}

	private Object[] itemToTable(OrderItem item) {
		return new Object[] {
				item.getBook().getTitle(),
				item.getBook().getAuthor().getName(),
				item.getAmount().toString(),
				String.format("$%.2f", item.getBook().getPrice()),
				String.format("$%.2f", item.getAmount() * item.getBook().getPrice())
		};
	}
	
	private void makeTableColumns(Order order) {
		tableItems.addContainerProperty("Libro", String.class, null);
		tableItems.addContainerProperty("Autor", String.class, null);
		tableItems.addContainerProperty("Cantidad", String.class, null);
		tableItems.addContainerProperty("Costo unitario", String.class, null);
		tableItems.addContainerProperty("Subtotal",  String.class, null);
		tableItems.setFooterVisible(true);
		tableItems.setColumnFooter("Libro", "");
		tableItems.setColumnFooter("Cantidad", "");
		tableItems.setColumnFooter("Costo unitario", "Total");
		tableItems.setColumnFooter("Subtotal", String.format("$%.2f", order.getTotalPrice()));
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
		setWidth("640px");
		setHeight("-1px");
		
		// labelHeader
		labelHeader = new Label();
		labelHeader.setImmediate(false);
		labelHeader.setWidth("-1px");
		labelHeader.setHeight("-1px");
		labelHeader.setValue("<h3>Detalles del pedido</h3>");
		labelHeader.setContentMode(3);
		mainLayout.addComponent(labelHeader);
		
		// tableItems
		tableItems = new Table();
		tableItems.setImmediate(false);
		tableItems.setWidth("100.0%");
		tableItems.setHeight("100%");
		mainLayout.addComponent(tableItems);
		mainLayout.setExpandRatio(tableItems, 1.0f);
		
		// buttonClose
		buttonClose = new Button();
		buttonClose.setCaption("Cerrar");
		buttonClose.setImmediate(false);
		buttonClose.setWidth("-1px");
		buttonClose.setHeight("-1px");
		mainLayout.addComponent(buttonClose);
		mainLayout.setComponentAlignment(buttonClose, new Alignment(48));
		
		return mainLayout;
	}

}
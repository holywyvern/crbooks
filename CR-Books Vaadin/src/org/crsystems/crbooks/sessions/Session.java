package org.crsystems.crbooks.sessions;

import java.util.ArrayList;
import java.util.List;

import org.crsystems.crbooks.application.CRBooks;
import org.crsystems.crbooks.models.Order;
import org.crsystems.crbooks.models.OrderItem;
import org.crsystems.crbooks.models.User;


public abstract class Session {

	private User user;
	
	private org.hibernate.Session session;
	
	private Order currentOrder;
	
	private List<OrderItem> items;
	
	
	public Session(User user) {
		if (user == null) throw new IllegalArgumentException("Session must have an user.");
		this.user = user;
		this.currentOrder = new Order();
		this.currentOrder.setUser(user);
		user.addOrder(this.currentOrder);
	}
	
	public User getUser() {
		return user;
	}

	public org.hibernate.Session getSession() {
		return session;
	}

	public void setSession(org.hibernate.Session session) {
		if (this.session != null) {
			this.session.close();
		}
		this.session = session;
	}
	
	public void close() {
		this.session.close();
		this.session = null;
	}

	public Order getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
		this.currentOrder.setUser(user);
		user.addOrder(this.currentOrder);
		this.items = new ArrayList<OrderItem>();
	}

	public void addItem(OrderItem item) {
		if (this.currentOrder == null) this.currentOrder = new Order();
		if (this.currentOrder.getItems() == null) this.currentOrder.setItems(new ArrayList<OrderItem>());
		this.currentOrder.addItem(item);
		showAddedOrder(item);
		if (this.items == null) this.items = new ArrayList<OrderItem>();
		boolean isAdded = false;
		for (OrderItem i : this.items) {
			if (i.getBook().getBookID() == item.getBook().getBookID()) {
				i.setAmount(i.getAmount() + item.getAmount());
				isAdded = true;
			}
		}
		if (!isAdded) {
			this.items.add(item);
		}
	}

	private void showAddedOrder(OrderItem item) {
		String amountText = item.getAmount() > 1 ? String.format("%d unidades", item.getAmount()) : "una unidad";
		CRBooks.showTrayMessage(String.format("Se han añadido %s de %s al pedido actual.", amountText, item.getBook().getTitle()));
	}

	public List<OrderItem> getOrderItems() {
		if (this.items == null) this.items = new ArrayList<OrderItem>();
		return this.items;
	}
	
	
}

package org.crsystems.crbooks.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Orders")
public class Order extends ModelBase<Order, Integer> {

	@Id
	@GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
	private Integer orderID;
	
	@OneToMany(mappedBy="orderItemID")
	private List<OrderItem> items;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private Order order;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private OrderState state;
	
	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}	
	
	@Override
	public String getTableName() {
		return "Orders";
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, String> getErrorFields() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}	
	
	public Double getTotalPrice() {
		List<OrderItem> items = getItems();
		Double totalPrice = 0.0;
		if (items == null) return 0.0;
		for (OrderItem item : items) {
			totalPrice = totalPrice + item.price();
		}
		return totalPrice;
	}
	public void addItem(OrderItem item) {
		List<OrderItem> list = this.getItems();
		if (list == null) {
			list = new ArrayList<OrderItem>();
			this.setItems(list);
		}
		boolean addedToOther = false;
		for (OrderItem i : list) {
			if (i.getBook().getBookID() == item.getBook().getBookID()) {
				addedToOther = true;
				i.setAmount(item.getAmount() + i.getAmount());
			}
		}
		if (!addedToOther) {
			list.add(item);
		}
	}
	public static List<Order> getAll() {
		return ModelBase.getAll(Order.class, Integer.class, "Order");
	}	
	
	
}

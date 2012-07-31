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
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

@Entity
@Table(name="Orders")
public class Order extends ModelBase<Order, Integer> {

	@Id
	@GenericGenerator(name="generatorOrders", strategy="increment")
    @GeneratedValue(generator="generatorOrders")
	private Integer orderID;

	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	@OneToMany(mappedBy="orderItemID")
	private List<OrderItem> orderItems;	
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private User user;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private OrderState orderState;
	
	
	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	@Override
	public String getTableName() {
		return "Orders";
	}

	@Override
	public boolean isValid() {
		if (this.orderItems == null) return false;
		if (this.orderItems.size() < 1) return false;
		return true;
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
		return orderState;
	}

	public void setState(OrderState state) {
		this.orderState = state;
	}

	public List<OrderItem> getItems() {
		return orderItems;
	}

	public void setItems(List<OrderItem> items) {
		this.orderItems = items;
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
			item.setOrder(this);
		}
	}
	public static List<Order> getAll() {
		return ModelBase.getAll(Order.class, Integer.class, "Order");
	}	
	
	
	public static List<Order> getByUser(User user) {
		Criterion c = Restrictions.eq("userID", user.getUserID());
		List<Order> list = ModelBase.getByCriterion(Order.class, c);
		if (list == null) list = new ArrayList<Order>();
		return list;
	}
	
	public static List<Order> getByUser(List<User> users) {
		List<Order> list = new ArrayList<Order>();
		for (User user : users) {
			list.addAll(Order.getByUser(user));
		}
		return list;
	}
	
	public static List<Order> getBetweenDates(Date startDate, Date endDate) {
		Criterion c = Restrictions.and(Restrictions.le("createdAt", endDate),
									   Restrictions.ge("createdAt", startDate));
		List<Order> list = ModelBase.getByCriterion(Order.class, c);
		if (list == null) list = new ArrayList<Order>();
		return list;
	}
	
	public static List<Order> getBetweenDatesAndUser(Date startDate, Date endDate, User user) {
		Criterion c = Restrictions.and(Restrictions.le("createdAt", endDate),
				                       Restrictions.ge("createdAt", startDate),
				                       Restrictions.eq("userID", user.getUserID()));
		List<Order> list = ModelBase.getByCriterion(Order.class, c);
		if (list == null) list = new ArrayList<Order>();
		return list;
	}
	
	public static List<Order> getBetweenDatesAndUserName(Date startDate, Date endDate, String username) {
		List<User> userList = User.getByFullName(username);
		List<Order> orders = new ArrayList<Order>();
		for (User user : userList) {
			Criterion c = Restrictions.and(Restrictions.le("createdAt", endDate),
					                       Restrictions.ge("createdAt", startDate),
				                           Restrictions.eq("userID", user.getUserID()));
			List<Order> list = ModelBase.getByCriterion(Order.class, c);
			if (list == null) list = new ArrayList<Order>();
			orders.addAll(list);
		}
		return orders;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}

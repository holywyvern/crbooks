package org.crsystems.crbooks.models;

import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="OrderItems")
public class OrderItem extends ModelBase<OrderItem, Integer> {

	@Id
	@GeneratedValue	
	private Integer orderItemID;


	@OneToOne(optional=false)
	private Book book;
	
	@Basic(optional=false)
	private Integer amount;
	
	@OneToOne(optional=false)
	private Order order;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false )
	@JoinColumn(name = "User", referencedColumnName = "email", nullable = false, insertable = false, updatable = false)
	private User user;
	
	public Integer getOrderItemID() {
		return orderItemID;
	}

	public void setOrderItemID(Integer orderItemID) {
		this.orderItemID = orderItemID;
	}

	@Override
	public String getTableName() {
		return "OrderItems";
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Double price() {
		return this.getAmount() * this.getBook().getPrice();
	}	
	
}

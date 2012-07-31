package org.crsystems.crbooks.models;

import java.util.ArrayList;
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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

@Entity
@Table(name="OrderItems")
public class OrderItem extends ModelBase<OrderItem, Integer> {

	@Id
	@GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
	private Integer orderItemID;


	@OneToOne(optional=false)
	private Book book;
	
	@Basic(optional=false)
	private Integer amount;
	
	@OneToOne(optional=false)
	private Order order;
	
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
		return true;
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

	public static List<OrderItem> getByOrder(Order order) {
		Criterion c = Restrictions.eq("orderID", order.getOrderID());
		List<OrderItem> list = null;
		list = ModelBase.getByCriterion(OrderItem.class, c);
		if (list == null) list = new ArrayList<OrderItem>();
		return list;
	}	
	
}

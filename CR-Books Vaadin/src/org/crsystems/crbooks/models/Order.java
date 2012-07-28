package org.crsystems.crbooks.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Orders")
public class Order extends ModelBase<Order, Integer> {

	@Id
	@GeneratedValue	
	private Integer orderID;
	
	@OneToMany(mappedBy="OrderItems")
	private List<OrderItem> items;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private Order order;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
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
	
}

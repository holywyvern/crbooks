package org.crsystems.crbooks.models;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="OrderItems")
public class OrderItem extends ModelBase<OrderItem, Integer> {

	@Id
	@GeneratedValue	
	private Integer orderItemID;


	@OneToOne
	private Book book;
	
	@Basic
	private Integer amount;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
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
	
}

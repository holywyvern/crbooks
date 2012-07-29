package org.crsystems.crbooks.models;

import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="OrderStates")
public class OrderState extends ModelBase<OrderState, Integer> {

	@Id
	@GeneratedValue		
	private Integer orderStateID;
	
	@OneToMany(mappedBy="orderID")
	private List<Order> orders;	
	
	@Basic
	private String name;
	
	@Basic
	private String description;
	
	@Basic
	private String iconName;
	
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Integer getOrderStateID() {
		return orderStateID;
	}

	public void setOrderStateID(Integer orderStateID) {
		this.orderStateID = orderStateID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

}

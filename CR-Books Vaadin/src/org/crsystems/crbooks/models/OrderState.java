package org.crsystems.crbooks.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="OrderStates")
public class OrderState extends ModelBase<OrderState, Integer> {

	@Id
	@GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")	
	private Integer orderStateID;
	
	@Basic
	private String name;
	
	@Basic
	private String description;
	
	@Basic
	private Boolean isFinal;
	
	@Basic
	private String iconName;
	
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "OrderStates";
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

	public Boolean isFinal() {
		return isFinal;
	}

	public void setFinal(Boolean isFinal) {
		this.isFinal = isFinal;
	}

	public static List<OrderState> getAll() {
		List<OrderState> list = ModelBase.getAll(OrderState.class, Integer.class, "OrderState");
		if (list == null) list = new ArrayList<OrderState>();
		return list;
	}

	public static OrderState getByID(Integer key) {
		return ModelBase.getByID(OrderState.class, Integer.class, key);
	}

}

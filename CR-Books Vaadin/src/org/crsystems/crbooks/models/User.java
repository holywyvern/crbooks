package org.crsystems.crbooks.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class User extends ModelBase<User, String> {
	
	private enum Role {
		USER,
		MANAGER,
		ADMIN;
	}
	
	public static final Role USER_ROLE = Role.USER;
	public static final Role MANAGER_ROLE = Role.MANAGER;
	public static final Role ADMIN_ROLE = Role.ADMIN;
	
	@Id
	private String email;
	
	@Basic
	private String password;
	@Basic
	private String firstName;
	@Basic
	private String lastName;
	@Basic
	private Date birthday;
	@Basic
	private String city;
	@Basic
	private String address;
	@Basic
	private String state;
	@Basic
	private String postalCode;
	@Basic
	private String phone;
	@Basic
	private Role role;
	
	@OneToMany(mappedBy="Orders")
	private List<Order> orders;
	
	public User() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Role getRole() {
		return this.role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public boolean isUser() {
		return (this.role == Role.USER);
	}
	
	public boolean isManager() {
		return (this.role == Role.MANAGER);
	}	
	
	public boolean isAdmin() {
		return (this.role == Role.ADMIN);
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public List<Order> getOrders() {
		return this.orders;
	}
	
	public boolean addOrder(Order order) {
		return false;
	}
	
	@Override
	public String getTableName() {
		return "Users";
	}
	
}

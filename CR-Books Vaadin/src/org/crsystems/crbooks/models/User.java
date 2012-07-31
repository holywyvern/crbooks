package org.crsystems.crbooks.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.crsystems.crbooks.sessions.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

@Entity
@Table(name="Users")
public class User extends ModelBase<User, Integer> {
	
	private enum Role {
		CLIENT,
		MANAGER,
		ADMIN;
	}
	
	public static final Role CLIENT_ROLE = Role.CLIENT;
	public static final Role MANAGER_ROLE = Role.MANAGER;
	public static final Role ADMIN_ROLE = Role.ADMIN;
	
	@Id
	@GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
	private Integer userID;
	
	@Basic
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
	
	@OneToMany(mappedBy="orderID")
	private List<Order> orders;
	
	@OneToMany(mappedBy="bookCommentID")
	private List<BookComment> comments;
	
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
	
	public boolean isClient() {
		return (this.role == Role.CLIENT);
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

	
	public boolean isValid(boolean updating) {
		if (this.getEmail() == null) return false;
		
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(this.getEmail());
		
		if (!m.matches()) return false;			
		if (!updating) {
			if (User.emailExists(this.getEmail())) return false;
		}
		if (this.getPassword() != null && this.getPassword().length() < 6) return false;
	
		return true;
	}

	private static boolean emailExists(String email) {
		return (User.getByEmail(email) != null);
	}

	@Override
	public boolean isValid() {
		return isValid(true);
	}
	
	public Map<String, String> getErrorFields(boolean updating) {
		Map<String, String> map = new HashMap<String, String>();
		
		if (this.getEmail() == null) { 
			map.put("email", "La dirección de correo electronico no puede estar en blanco.");
		} else {
			Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
			Matcher m = p.matcher(this.getEmail());			
			if (!m.matches()) 
				map.put("email", "La dirección de correo electronico válida.");			
		}
		if (!updating) {
			if (User.emailExists(this.getEmail())) 
				map.put("email", "Ya existe una cuenta asociada a esa dirección de correo electrónico.");	
		}
		if (this.getPassword() != null && this.getPassword().length() < 6)
			map.put("password", "La contraseña debe de tener al menos 6 caracteres.");	
		return map;
	}

	@Override
	public Map<String, String> getErrorFields() {
		return getErrorFields(true);
	}

	public Session createSession() {
		Session session = null;
		switch (this.role) {
		case CLIENT:
			session = new ClientSession(this);
			break;
		case MANAGER:
			session = new ManagerSession(this);
			break;
		case ADMIN:
			session = new AdminSession(this);
			break;
		}
		return session;
	}

	public static User getByID(Integer key) {
		User u = ModelBase.getByID(User.class, Integer.class, key);
		return u;
	}

	public static User getByEmail(String email) {
		Criterion c = Restrictions.eq("email", email);
		List<User> list = ModelBase.getByCriterion(User.class, c);
		return (list == null) ? null : ((list.size() > 0) ? list.get(0) : null) ;
	}
	
	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public static List<User> getByFullName(String name) {
		Criterion c = Restrictions.or(Restrictions.like("firstName", name),
				                    Restrictions.like("lastName", name));
		List<User> list = ModelBase.getByCriterion(User.class, c);
		if (list == null) list = new ArrayList<User>();
		return list;
	}

	public Object getFormatedName() {
		return String.format("%s %s", this.firstName, this.lastName);
	}

	public List<BookComment> getComments() {
		return comments;
	}

	public void setComments(List<BookComment> comments) {
		this.comments = comments;
	}
	
}

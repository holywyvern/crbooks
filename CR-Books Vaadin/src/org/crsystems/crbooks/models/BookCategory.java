package org.crsystems.crbooks.models;

import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.vaadin.annotations.AutoGenerated;

@Entity
@Table(name="BookCategories")
public class BookCategory extends ModelBase<BookCategory, Integer>  {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	private Integer bookCategoryID;

	@Basic
	private String name;
	
	@Basic
	private String description;
	
	public Integer getBookCategoryID() {
		return bookCategoryID;
	}

	public void setBookCategoryID(Integer bookCategoryID) {
		this.bookCategoryID = bookCategoryID;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "BookCategories";
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static List<BookCategory> getAll() {
		return ModelBase.getAll(BookCategory.class, Integer.class, "BookCategory");
	}	
	
	public boolean equals(Object other) {
		if (other != null && other instanceof BookCategory) {
			return this.bookCategoryID == ((BookCategory)other).getBookCategoryID();
		}
		return super.equals(other);
	}

	public static BookCategory getByID(Integer key) {
		return ModelBase.getByID(BookCategory.class, Integer.class, key);
	}
	
}

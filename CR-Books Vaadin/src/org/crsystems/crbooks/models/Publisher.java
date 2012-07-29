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
@Table(name="Publishers")
public class Publisher extends ModelBase<Publisher, String> {

	@Id
	private String name;
	
	@Basic
	private String description;
	
	@OneToMany(mappedBy="bookID")
	private List<Book> books;
	
	@Override
	public String getTableName() {
		return "Publishers";
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

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public static List<Publisher> getAll() {
		return ModelBase.getAll(Publisher.class, String.class, "Publisher");
	}

}

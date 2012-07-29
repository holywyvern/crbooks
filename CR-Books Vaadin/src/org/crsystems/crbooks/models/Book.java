package org.crsystems.crbooks.models;

import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Books")
public class Book extends ModelBase<Book, Integer> {

	@Id
	@GeneratedValue	
	private Integer bookID;

	@Basic
	private String title;
	
	@ManyToOne
	private Author author;
	
	
	@ManyToMany
	private List<BookCategory> categories;
	
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "Books";
	}

	public Integer getBookID() {
		return bookID;
	}

	public void setBookID(Integer bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
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

	public static Book getByID(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	
}

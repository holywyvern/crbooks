package org.crsystems.crbooks.models;

import java.util.ArrayList;
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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

@Entity
@Table(name="Books")
public class Book extends ModelBase<Book, Integer> {

	@Id
	@GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
	private Integer bookID;

	@Basic
	private String title;
	
	@ManyToOne
	private Author author;
	
	@ManyToOne
	private Publisher publisher;	
	
	@Basic
	private Integer stock;
	
	@Basic
	private String description;
	
	@Basic
	private Integer edition;
	
	@Basic
	private Double price;
	
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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getEdition() {
		return edition;
	}

	public void setEdition(Integer edition) {
		this.edition = edition;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<BookCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<BookCategory> categories) {
		this.categories = categories;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public static List<Book> getAll() {
		return ModelBase.getAll(Book.class, Integer.class, "Book");
	}
	
	
	
	

	public void addCategory(BookCategory c) {
		if (this.categories == null) {
			this.categories = new ArrayList<BookCategory>();
		}
		for (BookCategory category : this.categories) {
			if (category.getBookCategoryID() == c.getBookCategoryID()) return;
		}
		categories.add(c);
	}	
	
	
}

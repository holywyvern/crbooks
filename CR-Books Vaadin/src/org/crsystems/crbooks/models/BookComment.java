package org.crsystems.crbooks.models;

import java.util.Date;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="BookComments")
public class BookComment extends ModelBase<BookComment, Integer>{

	@Id
	@GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
	private Integer bookCommentID;

	@Temporal(TemporalType.DATE)
	private Date createdAt;	

	@Lob
	private String text;	
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private User user;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private Book book;
	

	
	public Integer getBookCommentID() {
		return bookCommentID;
	}

	public void setBookCommentID(Integer bookCommentID) {
		this.bookCommentID = bookCommentID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "BookComments";
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
	
}

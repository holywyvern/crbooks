package org.crsystems.crbooks.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BookCategories")
public class BookCategory extends ModelBase<BookCategory, Integer>  {

	@Id
	@GeneratedValue	
	private Integer bookCategoryID;

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
	
}

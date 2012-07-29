package org.crsystems.crbooks.models;

import java.util.List;
import java.util.Map;

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

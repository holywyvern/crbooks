package org.crsystems.crbooks.models;

import java.io.Serializable;
import java.util.List;

public interface IDatabaseModel<T, PK extends Serializable> {

	public boolean save();
	public boolean update();
	public boolean delete();
	public boolean saveOrUpdate();
	
	public List<T> getAll();
	
}

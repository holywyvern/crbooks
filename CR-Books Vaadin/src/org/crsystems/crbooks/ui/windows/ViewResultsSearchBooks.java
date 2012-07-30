package org.crsystems.crbooks.ui.windows;

import java.util.List;

import org.crsystems.crbooks.models.Book;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

public class ViewResultsSearchBooks extends ViewBaseWindow<Book, Integer> {

	public SearchBooksWindow titulo;
	public ViewResultsSearchBooks() {
		super("Resultados de la Busqueda");
	}

	@Override
	protected void createTableColumns(Table tableData, boolean showLinkBar) {
		tableData.addContainerProperty("Nombre", String.class, null);
		tableData.addContainerProperty("Autor", String.class, null);
		tableData.addContainerProperty("Editorial", String.class, null);
		tableData.addContainerProperty("Edici�n", String.class, null);
		tableData.addContainerProperty("Precio", String.class, null);
		tableData.addContainerProperty("Acciones", HorizontalLayout.class, null);
		
	}

	
	@Override
	protected List<Book> getAllItems() {
		// TODO Auto-generated method stub
		return Book.getAll();
	}

	
	@Override
	protected Object[] itemToTable(Book item, boolean showOrder, boolean showDelete) {
		
		return new Object[] {
				item.getTitle(),
				item.getAuthor().getName(),
				item.getPublisher().getName(),
				item.getEdition(),
				String.format("%.2f", item.getPrice()),
				createActionLayout11(item, showOrder)
		};
	}
	@Override
	protected EditBaseWindow<Book> createEditWindow() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
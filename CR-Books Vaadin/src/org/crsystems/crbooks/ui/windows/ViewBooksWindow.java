package org.crsystems.crbooks.ui.windows;

import java.util.List;

import org.crsystems.crbooks.models.Book;
import org.crsystems.crbooks.models.BookCategory;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.BaseTheme;

public class ViewBooksWindow extends ViewBaseWindow<Book, Integer> {

	public ViewBooksWindow() {
		super("Listado de Libros");
	}

	@Override
	protected EditBaseWindow<Book> createEditWindow() {
		return new EditBookWindow();
	}

	@Override
	protected void createTableColumns(Table tableData, boolean showLinkBar) {
		tableData.addContainerProperty("Nombre", String.class, null);
		tableData.addContainerProperty("Autor", String.class, null);
		tableData.addContainerProperty("Editorial", String.class, null);
		tableData.addContainerProperty("Edición", String.class, null);
		tableData.addContainerProperty("Precio", String.class, null);
		tableData.addContainerProperty("Cantidad disponible", Label.class, null);
		tableData.addContainerProperty("Acciones", HorizontalLayout.class, null);
	}

	@Override
	protected List<Book> getAllItems() {
		return Book.getAll();
	}

	@Override
	protected Object[] itemToTable(Book item, boolean showEdit,
			boolean showDelete) {
		String klass = (item.getStock() > 0) ? "cr-books-price-label-upper-zero" : "cr-books-price-label-lower-zero";
		Label label = new Label(String.format("<span class=\"%s\">%s</span>", klass, item.getStock().toString()));
		label.setContentMode(3);
		label.setStyleName(klass);	
		return new Object[] {
				item.getTitle(),
				(item.getAuthor() != null) ? item.getAuthor().getName() : "",
				item.getPublisher().getName(),
				item.getEdition(),
				String.format("%.2f", item.getPrice()),
				label,
				createActionLayout(item, showEdit, showDelete)
		};
	}
	
}

package org.crsystems.crbooks.ui.windows;

import java.util.List;

import org.crsystems.crbooks.models.Author;
import org.crsystems.crbooks.models.BookCategory;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.BaseTheme;

public class ViewAuthorsWindow extends ViewBaseWindow<Author, Integer> {

	public ViewAuthorsWindow() {
		super("Listado de Autores");
	}

	@Override
	protected EditBaseWindow<Author> createEditWindow() {
		return new EditAuthorWindow();
	}

	@Override
	protected void createTableColumns(Table tableData, boolean showLinkBar) {
		tableData.addContainerProperty("Nombre", String.class, null);
		tableData.addContainerProperty("Descripción", String.class, null);
		tableData.addContainerProperty("Acciones", HorizontalLayout.class, null);
	}

	@Override
	protected List<Author> getAllItems() {
		return Author.getAll();
	}

	@Override
	protected Object[] itemToTable(Author item, boolean showEdit,
			boolean showDelete) {
		return new Object[] {
				item.getName(), 
				item.getDescription(),
				createActionLayout(item, showEdit, showDelete)
		};
	}
	
}

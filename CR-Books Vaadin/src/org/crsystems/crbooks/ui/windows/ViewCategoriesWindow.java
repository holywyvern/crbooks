package org.crsystems.crbooks.ui.windows;

import java.util.List;

import org.crsystems.crbooks.models.BookCategory;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.BaseTheme;

public class ViewCategoriesWindow extends ViewBaseWindow<BookCategory, Integer> {

	public ViewCategoriesWindow() {
		super("Listado de Categorias");
	}

	@Override
	protected EditBaseWindow<BookCategory> createEditWindow() {
		return new EditCategoryWindow();
	}

	@Override
	protected void createTableColumns(Table tableData, boolean showLinkBar) {
		tableData.addContainerProperty("Nombre", String.class, null);
		tableData.addContainerProperty("Descripción", String.class, null);
		tableData.addContainerProperty("Acciones", HorizontalLayout.class, null);
	}

	@Override
	protected List<BookCategory> getAllItems() {
		return BookCategory.getAll();
	}

	@Override
	protected Object[] itemToTable(BookCategory item, boolean showEdit,
			boolean showDelete) {
		return new Object[] {
				item.getName(), 
				item.getDescription(),
				createActionLayout(item, showEdit, showDelete)
		};
	}
	
}

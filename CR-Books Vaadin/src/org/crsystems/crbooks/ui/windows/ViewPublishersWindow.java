package org.crsystems.crbooks.ui.windows;

import java.util.List;

import org.crsystems.crbooks.models.Publisher;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.BaseTheme;

public class ViewPublishersWindow extends ViewBaseWindow<Publisher, Integer> {

	public ViewPublishersWindow() {
		super("Listado de Editoriales");
	}
	
	@Override
	protected void createTableColumns(Table tableData, boolean showLinkBar) {
		tableData.addContainerProperty("Nombre", String.class, null);
		tableData.addContainerProperty("Descripción", String.class, null);
		tableData.addContainerProperty("Acciones", HorizontalLayout.class, null);
	}

	@Override
	protected List<Publisher> getAllItems() {
		return Publisher.getAll();
	}

	@Override
	protected Object[] itemToTable(Publisher item, boolean showEdit,
			boolean showDelete) {
		return new Object[] {
				item.getName(), 
				item.getDescription(),
				createActionLayout(item, showEdit, showDelete)
		};
	}

	@Override
	protected EditBaseWindow<Publisher> createEditWindow() {
		return new EditPublisherWindow();
	}

}

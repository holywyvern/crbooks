package org.crsystems.crbooks.ui.windows;

import java.util.List;

import org.crsystems.crbooks.models.Publisher;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.BaseTheme;

public class ViewPublishersWindow extends ViewBaseWindow<Publisher, String> {

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
		HorizontalLayout l = new HorizontalLayout();
		
		if (showEdit) {
			Button buttonEdit = new Button("Editar");
			buttonEdit.setStyleName(BaseTheme.BUTTON_LINK);
			l.addComponent(buttonEdit);
						
		}
		if (showDelete) {
			Button buttonDelete = new Button("Eliminar");
			buttonDelete.setStyleName(BaseTheme.BUTTON_LINK);
			l.addComponent(buttonDelete);
		}
		l.setSizeFull();
		l.setSpacing(true);
		return new Object[] {
				item.getName(), 
				item.getDescription(),
				l
		};
	}

	@Override
	protected EditBaseWindow<Publisher> createEditWindow() {
		// TODO Auto-generated method stub
		return null;
	}

}

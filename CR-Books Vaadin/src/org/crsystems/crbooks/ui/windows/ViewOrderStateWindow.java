package org.crsystems.crbooks.ui.windows;

import java.util.List;

import org.crsystems.crbooks.models.OrderState;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;

public class ViewOrderStateWindow extends ViewBaseWindow<OrderState, Integer> {

	@Override
	protected void createTableColumns(Table tableData, boolean showLinkBar) {
		tableData.addContainerProperty("Nombre", String.class, null);
		tableData.addContainerProperty("Descripción", String.class, null);
		tableData.addContainerProperty("¿Es final?", String.class, null);
		tableData.addContainerProperty("Acciones", HorizontalLayout.class, null);
	}

	@Override
	protected List<OrderState> getAllItems() {
		return OrderState.getAll();
	}

	@Override
	protected Object[] itemToTable(OrderState item, boolean showEdit,
			boolean showDelete) {
		return new Object[] {
				item.getName(),
				item.getDescription(),
				item.isFinal() ? "Si" : "No",
				createActionLayout(item, showEdit, showDelete)
		};
	}

	@Override
	protected EditBaseWindow<OrderState> createEditWindow() {
		return new EditOrderStateWindow();
	}

}

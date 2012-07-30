package org.crsystems.crbooks.ui.windows;

import com.vaadin.ui.Component;

public interface EditBaseWindow<T> extends Component {
	public void edit(T item);
}

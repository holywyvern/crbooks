package org.crsystems.crbooks.ui.windows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import org.crsystems.crbooks.models.Author;
import org.crsystems.crbooks.models.Book;
import org.crsystems.crbooks.models.BookCategory;
import org.crsystems.crbooks.models.Publisher;
import org.crsystems.crbooks.ui.layouts.ManagerLayout;

public class NewBookWindow extends CustomComponent {

	
	private VerticalLayout mainLayout;
	private Panel panelForm;
	private VerticalLayout gridForm;
	private GridLayout gridFormParams;
	private Button buttonForm;
	private TextField textStock;
	private Label labelStock;
	private OptionGroup optionCategories;
	private Label labelCategories;
	private TextField textPrice;
	private Label labelPrice;
	private TextArea textDescription;
	private Label labelDescription;
	private TextField textEdition;
	private Label labelEdition;
	private ComboBox comboPublisher;
	private Label labelPublisher;
	private ComboBox comboAuthor;
	private Label labelAuthor;
	private TextField textTitle;
	private Label labelTitle;
	private Label labelFormHeader;

	public NewBookWindow() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		putCategories();
		putAuthors();
		putPublishers();
		createListeners();
	}

	
	private void putAuthors() {
		this.comboAuthor.removeAllItems();
		for (Author author : Author.getAll()) {
			this.comboAuthor.addItem(author);
			this.comboAuthor.setItemCaption(author, author.getName());
		}
	}

	private void putPublishers() {
		this.comboPublisher.removeAllItems();
		for (Publisher publisher : Publisher.getAll()) {
			this.comboPublisher.addItem(publisher);
			this.comboPublisher.setItemCaption(publisher, publisher.getName());
		}
	}	

	private void createListeners() {
		this.buttonForm.addListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {
				onButtonCreate();
			}
			
		});
	}

	protected void onButtonCreate() {
		Book book = createBook();
		if (book.save()) {
			ManagerLayout.getCurrent().changeView(new ViewBooksWindow());
		}
	}

	private Book createBook() {
		Book book = new Book();
		book.setAuthor((Author)this.comboAuthor.getValue());
		for (BookCategory c : getSelectedCategories()) {
			System.out.println(c.getName());
			book.addCategory(c);
		}
		book.setDescription(this.textDescription.getValue().toString());
		try {
			book.setEdition(Integer.parseInt(textEdition.getValue().toString()));
		} catch (Exception e) {
			book.setEdition(1);
		}
		try {
			book.setPrice(Double.parseDouble(this.textPrice.getValue().toString()));
		} catch (Exception e) {
			book.setPrice(new Double(0));
		}
		book.setPublisher((Publisher)this.comboPublisher.getValue());
		try {
			book.setStock(Integer.parseInt(this.textStock.getValue().toString()));
		} catch (Exception e) {
			book.setStock(0);
		}
		book.setTitle(this.textTitle.getValue().toString());
		return book;
	}

	private List<BookCategory> getSelectedCategories() {
		Object value = this.optionCategories.getValue();
		Collection<?> l = null;
		if (value instanceof Collection<?>) {
			l = (Collection<?>)value;
		} else {
			l = new ArrayList<Object>();
		}
		List<BookCategory> list = new ArrayList<BookCategory>();
		for (Object item : l) {
			if (item instanceof BookCategory) {
				list.add((BookCategory)item);
			}
		}
		return list;
	}
	
	private void putCategories() {
		this.optionCategories.setMultiSelect(true);
		for (BookCategory category : BookCategory.getAll()) {
			this.optionCategories.addItem(category);
			this.optionCategories.setItemCaption(category, category.getName());
		}
	}


	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(true);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");
		
		// panelForm
		panelForm = buildPanelForm();
		mainLayout.addComponent(panelForm);
		mainLayout.setComponentAlignment(panelForm, new Alignment(48));
		
		return mainLayout;
	}

	
	private Panel buildPanelForm() {
		// common part: create layout
		panelForm = new Panel();
		panelForm.setImmediate(true);
		panelForm.setWidth("-1px");
		panelForm.setHeight("-1px");
		
		// gridForm
		gridForm = buildGridForm();
		panelForm.setContent(gridForm);
		
		return panelForm;
	}

	
	private VerticalLayout buildGridForm() {
		// common part: create layout
		gridForm = new VerticalLayout();
		gridForm.setImmediate(true);
		gridForm.setWidth("100.0%");
		gridForm.setHeight("100.0%");
		gridForm.setMargin(true);
		gridForm.setSpacing(true);
		
		// labelFormHeader
		labelFormHeader = new Label();
		labelFormHeader.setImmediate(true);
		labelFormHeader.setWidth("-1px");
		labelFormHeader.setHeight("-1px");
		labelFormHeader.setValue("<h2>Agregar un nuevo libro</h2>");
		labelFormHeader.setContentMode(3);
		gridForm.addComponent(labelFormHeader);
		
		// gridFormParams
		gridFormParams = buildGridFormParams();
		gridForm.addComponent(gridFormParams);
		
		return gridForm;
	}

	
	private GridLayout buildGridFormParams() {
		// common part: create layout
		gridFormParams = new GridLayout();
		gridFormParams.setImmediate(true);
		gridFormParams.setWidth("-1px");
		gridFormParams.setHeight("100.0%");
		gridFormParams.setMargin(false);
		gridFormParams.setSpacing(true);
		gridFormParams.setColumns(2);
		gridFormParams.setRows(9);
		
		// labelTitle
		labelTitle = new Label();
		labelTitle.setImmediate(true);
		labelTitle.setWidth("-1px");
		labelTitle.setHeight("-1px");
		labelTitle.setValue("Título:");
		gridFormParams.addComponent(labelTitle, 0, 0);
		
		// textTitle
		textTitle = new TextField();
		textTitle.setImmediate(true);
		textTitle.setWidth("100.0%");
		textTitle.setHeight("-1px");
		textTitle.setSecret(false);
		gridFormParams.addComponent(textTitle, 1, 0);
		
		// labelAuthor
		labelAuthor = new Label();
		labelAuthor.setImmediate(true);
		labelAuthor.setWidth("-1px");
		labelAuthor.setHeight("-1px");
		labelAuthor.setValue("Autor:");
		gridFormParams.addComponent(labelAuthor, 0, 1);
		
		// comboAuthor
		comboAuthor = new ComboBox();
		comboAuthor.setImmediate(true);
		comboAuthor.setWidth("100%");
		comboAuthor.setHeight("-1px");
		comboAuthor.setInvalidAllowed(false);
		comboAuthor.setWriteThrough(false);
		gridFormParams.addComponent(comboAuthor, 1, 1);
		
		// labelPublisher
		labelPublisher = new Label();
		labelPublisher.setImmediate(true);
		labelPublisher.setWidth("-1px");
		labelPublisher.setHeight("-1px");
		labelPublisher.setValue("Editorial:");
		gridFormParams.addComponent(labelPublisher, 0, 2);
		
		// comboPublisher
		comboPublisher = new ComboBox();
		comboPublisher.setImmediate(true);
		comboPublisher.setWidth("100%");
		comboPublisher.setHeight("-1px");
		comboPublisher.setInvalidAllowed(false);
		comboPublisher.setWriteThrough(false);
		gridFormParams.addComponent(comboPublisher, 1, 2);
		
		// labelEdition
		labelEdition = new Label();
		labelEdition.setImmediate(true);
		labelEdition.setWidth("-1px");
		labelEdition.setHeight("-1px");
		labelEdition.setValue("Edición:");
		gridFormParams.addComponent(labelEdition, 0, 3);
		
		// textEdition
		textEdition = new TextField();
		textEdition.setImmediate(true);
		textEdition.setWidth("100.0%");
		textEdition.setHeight("-1px");
		gridFormParams.addComponent(textEdition, 1, 3);
		
		// labelDescription
		labelDescription = new Label();
		labelDescription.setImmediate(true);
		labelDescription.setWidth("-1px");
		labelDescription.setHeight("-1px");
		labelDescription.setValue("Descripción:");
		gridFormParams.addComponent(labelDescription, 0, 4);
		
		// textDescription
		textDescription = new TextArea();
		textDescription.setImmediate(true);
		textDescription.setWidth("320px");
		textDescription.setHeight("-1px");
		textDescription.setRows(5);
		gridFormParams.addComponent(textDescription, 1, 4);
		
		// labelPrice
		labelPrice = new Label();
		labelPrice.setImmediate(true);
		labelPrice.setWidth("-1px");
		labelPrice.setHeight("-1px");
		labelPrice.setValue("Precio:");
		gridFormParams.addComponent(labelPrice, 0, 5);
		
		// textPrice
		textPrice = new TextField();
		textPrice.setImmediate(true);
		textPrice.setWidth("100.0%");
		textPrice.setHeight("-1px");
		gridFormParams.addComponent(textPrice, 1, 5);
		
		// labelCategories
		labelCategories = new Label();
		labelCategories.setImmediate(true);
		labelCategories.setWidth("-1px");
		labelCategories.setHeight("-1px");
		labelCategories.setValue("Categorias:");
		gridFormParams.addComponent(labelCategories, 0, 6);
		
		// optionCategories
		optionCategories = new OptionGroup();
		optionCategories.setImmediate(true);
		optionCategories.setWidth("100.0%");
		optionCategories.setHeight("-1px");
		gridFormParams.addComponent(optionCategories, 1, 6);
		
		// labelStock
		labelStock = new Label();
		labelStock.setImmediate(true);
		labelStock.setWidth("-1px");
		labelStock.setHeight("-1px");
		labelStock.setValue("Cantidad en stock:");
		gridFormParams.addComponent(labelStock, 0, 7);
		
		// textStock
		textStock = new TextField();
		textStock.setImmediate(true);
		textStock.setWidth("100.0%");
		textStock.setHeight("-1px");
		gridFormParams.addComponent(textStock, 1, 7);
		
		// buttonForm
		buttonForm = new Button();
		buttonForm.setCaption("Crear Libro");
		buttonForm.setImmediate(true);
		buttonForm.setWidth("-1px");
		buttonForm.setHeight("-1px");
		gridFormParams.addComponent(buttonForm, 1, 8);
		gridFormParams.setComponentAlignment(buttonForm, new Alignment(6));
		
		return gridFormParams;
	}

}

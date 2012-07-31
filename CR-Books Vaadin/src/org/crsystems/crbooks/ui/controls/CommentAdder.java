package org.crsystems.crbooks.ui.controls;

import java.util.Date;

import org.crsystems.crbooks.application.CRBooks;
import org.crsystems.crbooks.models.Book;
import org.crsystems.crbooks.models.BookComment;
import org.crsystems.crbooks.models.User;
import org.crsystems.crbooks.ui.windows.BookDetailsWindow;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class CommentAdder extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Button buttonAddComent;
	@AutoGenerated
	private TextArea textCommentData;
	@AutoGenerated
	private Label labelAddComent;

	private Integer bookID;
	private Integer userID;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public CommentAdder() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		setListeners();
	}

	
	private void setListeners() {
		this.buttonAddComent.addListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {
				onCommentButton();
			}
			
		});
	}


	protected void onCommentButton() {
		BookComment comment = new BookComment();
		Book book = Book.getByID(this.bookID);
		comment.setBook(book);
		comment.setUser(User.getByID(userID));
		comment.setText(this.textCommentData.getValue().toString());
		comment.setCreatedAt(new Date());
		if (comment.save()) {
			CRBooks.setView(new BookDetailsWindow(book));
		}
	}


	public CommentAdder(Book book, User user) {
		this();
		this.bookID = book.getBookID();
		this.userID = user.getUserID();
	}
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// labelAddComent
		labelAddComent = new Label();
		labelAddComent.setImmediate(false);
		labelAddComent.setWidth("-1px");
		labelAddComent.setHeight("-1px");
		labelAddComent.setValue("<b>Agregar un comentario:</b>");
		labelAddComent.setContentMode(3);
		mainLayout.addComponent(labelAddComent);
		
		// textCommentData
		textCommentData = new TextArea();
		textCommentData.setImmediate(false);
		textCommentData.setWidth("320px");
		textCommentData.setHeight("-1px");
		mainLayout.addComponent(textCommentData);
		
		// buttonAddComent
		buttonAddComent = new Button();
		buttonAddComent.setCaption("Agregar comentario");
		buttonAddComent.setImmediate(false);
		buttonAddComent.setWidth("-1px");
		buttonAddComent.setHeight("-1px");
		mainLayout.addComponent(buttonAddComent);
		mainLayout.setComponentAlignment(buttonAddComent, new Alignment(6));
		
		return mainLayout;
	}

}

package org.crsystems.crbooks.ui.controls;

import java.util.Date;

import org.crsystems.crbooks.models.BookComment;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class CommentControl extends CustomComponent {

	
	private HorizontalLayout mainLayout;
	private VerticalLayout gridData;
	private TextArea textComment;
	private Label labelAuthor;
	private Panel panelPhoto;
	private VerticalLayout gridPhoto;

	public CommentControl() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	public CommentControl(BookComment comment) {
		this();
		this.labelAuthor.setValue(String.format("%s (%s) dijo:", comment.getUser().getFormatedName(),
				makeFormattedTime(comment.getCreatedAt())));
		this.textComment.setValue(comment.getText());
	}
	
	private String makeFormattedTime(Date createdAt) {
		Date today = new Date();
		if (createdAt.after(today)) {
			return String.format("el ", createdAt.toString());
		}
		long hours = (today.getTime()-createdAt.getTime())/(60*60*10000);
		if (hours > 0) {
			String hf = hours > 1 ? String.format("%d horas", hours) : "una hora";
			return String.format("Hace aproximadamente %s", hf);
		}
		long mins= (today.getTime()-createdAt.getTime())/(60*10000);
		if (mins > 0) {
			String mf = mins > 0 ? String.format("%d minutos", mins) : "un minuto";
			return String.format("Hace aproximadamente %s", mf);
		}
		return "Hace un momento";
	}

	private HorizontalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// panelPhoto
		panelPhoto = buildPanelPhoto();
		mainLayout.addComponent(panelPhoto);
		mainLayout.setComponentAlignment(panelPhoto, new Alignment(48));
		
		// gridData
		gridData = buildGridData();
		mainLayout.addComponent(gridData);
		
		return mainLayout;
	}

	
	private Panel buildPanelPhoto() {
		// common part: create layout
		panelPhoto = new Panel();
		panelPhoto.setImmediate(false);
		panelPhoto.setWidth("64px");
		panelPhoto.setHeight("64px");
		
		// gridPhoto
		gridPhoto = new VerticalLayout();
		gridPhoto.setImmediate(false);
		gridPhoto.setWidth("100.0%");
		gridPhoto.setHeight("100.0%");
		gridPhoto.setMargin(false);
		panelPhoto.setContent(gridPhoto);
		
		return panelPhoto;
	}

	
	private VerticalLayout buildGridData() {
		// common part: create layout
		gridData = new VerticalLayout();
		gridData.setImmediate(false);
		gridData.setWidth("100.0%");
		gridData.setHeight("-1px");
		gridData.setMargin(true);
		gridData.setSpacing(true);
		
		// labelAuthor
		labelAuthor = new Label();
		labelAuthor.setImmediate(false);
		labelAuthor.setWidth("-1px");
		labelAuthor.setHeight("-1px");
		labelAuthor.setValue("Autor del comentario (Fecha) dijo:");
		gridData.addComponent(labelAuthor);
		
		// textComment
		textComment = new TextArea();
		textComment.setImmediate(false);
		textComment.setWidth("320px");
		textComment.setHeight("-1px");
		textComment.setReadThrough(false);
		textComment.setWriteThrough(false);
		textComment.setEnabled(false);
		gridData.addComponent(textComment);
		
		return gridData;
	}

}
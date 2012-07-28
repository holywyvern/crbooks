package org.crsystems.crbooks.sessions;

import org.crsystems.crbooks.application.HibernateUtil;
import org.crsystems.crbooks.models.User;


public abstract class Session {

	private User user;
	
	public Session(User user) {
		if (user == null) throw new IllegalArgumentException("Session must have an user.");
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
}

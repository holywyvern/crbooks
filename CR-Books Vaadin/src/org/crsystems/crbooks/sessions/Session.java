package org.crsystems.crbooks.sessions;

import org.crsystems.crbooks.application.HibernateUtil;
import org.crsystems.crbooks.models.User;


public abstract class Session {

	private User user;
	
	private org.hibernate.Session session;
	
	public Session(User user) {
		if (user == null) throw new IllegalArgumentException("Session must have an user.");
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	public org.hibernate.Session getSession() {
		return session;
	}

	public void setSession(org.hibernate.Session session) {
		if (this.session != null) {
			this.session.close();
		}
		this.session = session;
	}
	
	public void close() {
		this.session.close();
		this.session = null;
	}
	
}

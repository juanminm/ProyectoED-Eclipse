package org.institutoserpis.ed.juanminm;

import java.io.Serializable;

public class Conversation implements Serializable {

	private static final long serialVersionUID = 1L;

	private final long ID;
	private final Account sender;
	private final Account recipient;
	private final String subject;

	public Conversation(long id, Account sender, Account recipient,
			String subject) {
		this.ID = id;
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
	}

	public long getID() {
		return ID;
	}

	public Account getSender() {
		return sender;
	}

	public Account getRecipient() {
		return recipient;
	}

	public String getSubject() {
		return subject;
	}
}
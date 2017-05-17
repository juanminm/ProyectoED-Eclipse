package org.institutoserpis.ed.juanminm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Conversation implements Serializable {

	private static final long serialVersionUID = 1L;

	private final long ID;
	private final Account sender;
	private final Account recipient;
	private final String subject;
	private Calendar lastUpdated;
	private ArrayList<Message> messageList = new ArrayList<Message>();

	public Conversation(long id, Account sender, Account recipient,
			String subject) {
		this.ID = id;
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
		this.lastUpdated = Calendar.getInstance();
	}

	public long getID() {
		return ID;
	}

	public Account getSender() {
		return sender;
	}

	public Account getRecipient() {
		return recipient;
	public Calendar getLastUpdated() {
		return lastUpdated;
	}

	public String getSubject() {
		return subject;
	}

	public void addMessage(Message message) {
		messageList.add(message);
		lastUpdated = message.getDate();
	}
}

package org.institutoserpis.ed.juanminm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Conversation implements Serializable {

	private static final long serialVersionUID = 1L;

	private final long ID;
	private final Account[] correspondants = new Account[2];
	private Calendar lastUpdated;
	private ArrayList<Message> messageList = new ArrayList<Message>();

	public Conversation(long id, Account a1, Account a2) {
		this.ID = id;
		this.correspondants[0] = a1;
		this.correspondants[1] = a2;
		this.lastUpdated = Calendar.getInstance();
	}

	public long getID() {
		return ID;
	}

	public Account getCorrespondant(int i) {
		return correspondants[i];
	}

	public Calendar getLastUpdated() {
		return lastUpdated;
	}

	public boolean isCorrespondant(String username) {
		return username.equals(correspondants[0].getUsername())
				|| username.equals(correspondants[1].getUsername());
	}

	public void addMessage(Message message) {
		messageList.add(message);
		lastUpdated = message.getDate();
	}
}

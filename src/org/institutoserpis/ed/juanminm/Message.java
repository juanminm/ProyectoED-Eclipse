package org.institutoserpis.ed.juanminm;

import java.io.Serializable;
import java.util.Calendar;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private final long messageID;
	private final String messageBody;
	private final Calendar date;

	public Message (long messageID, String messageBody) {
		this.messageID = messageID;
		this.messageBody = messageBody;
		this.date = Calendar.getInstance();
	}

	public long getMessageID() {
		return messageID;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public Calendar getDate() {
		return date;
	}
}

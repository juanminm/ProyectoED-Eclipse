package org.institutoserpis.ed.juanminm;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private final long messageID;
	private final String messageBody;

	public Message (long messageID, String messageBody) {
		this.messageID = messageID;
		this.messageBody = messageBody;
	}

	public long getMessageID() {
		return messageID;
	}

	public String getMessageBody() {
		return messageBody;
	}
}

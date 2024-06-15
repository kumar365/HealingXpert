package com.healthcare.HealingXpert.model;

import java.io.Serializable;

/**
 * @author KUMAR
 */
public class MessageResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	public MessageResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

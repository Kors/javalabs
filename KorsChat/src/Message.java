//package ru.spbstu.telematics.javatechnologies.lection12;

import java.io.Serializable;

public class Message implements Serializable {
	private String user;	// имя отправителя.
	private int num;		// тип сообщения
	private String message;	// само сообщение
	
	public Message(String user, int num, String message) {
		super();
		this.user = user;
		this.num = num;
		this.message = message;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		//num + " " + 
		return user + ": " + message;
	}
	
	
}
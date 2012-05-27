package my;


import java.io.Serializable;

public class MyMessage implements Serializable {
	private String userId;
	private int id;
	private String message;
	
	public MyMessage(String userId, int id, String message) {
		super();
		this.userId = userId;
		this.id = id;
		this.message = message;
	}
	public String getUser() {
		return userId;
	}
	public void setUser(String user) {
		this.userId = user;
	}
	public int getNum() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return id + " " + userId + ": " + message;
	}
	
	
}

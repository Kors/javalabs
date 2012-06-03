import java.io.*;
import java.net.*;

public class ClientAccount {

	public Socket socket = null;
	public String userName = null;
	private ObjectOutputStream oos = null;
	public ObjectInputStream ois = null;

	public ClientAccount(String userName, Socket socket ) {
		this.socket = socket;
		this.userName = userName;
		try {
			oos = new ObjectOutputStream(this.socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ois = new ObjectInputStream(this.socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void send(Message m) {
		//throws IOException 
		try {
			oos.writeObject(m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	public String getInputString() {
		return socket.getInetAddress().toString();
	}


}


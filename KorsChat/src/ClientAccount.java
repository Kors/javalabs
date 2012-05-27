import java.io.*;
import java.net.*;

public class ClientAccount {

	public Socket _socket = null;
	public String _userName = null;
	private ObjectOutputStream _oos = null;
	public ObjectInputStream _ois = null;
	
	public ClientAccount(String userName, Socket socket ) {
		this._socket = socket;
		this._userName = userName;
		//this._oos = oos;
		//System.out.println("хрень");
		//this._ois = ois;
		try {
			_oos = new ObjectOutputStream(this._socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			_ois = new ObjectInputStream(this._socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void send(Message m) throws IOException {
		_oos.writeObject(m);
	}
	
	public String getInputString() {
		return _socket.getInetAddress().toString();
	}
	

}


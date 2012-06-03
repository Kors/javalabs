import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
// это "нормальный" клиент для работы чата в окне.

public class AppClient {

	static private int port = 7000;	
	static private String servIP = "localhost";
	static Socket socket ;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;

	public static Thread listener;

	public AppClient(int sPort, String ip) {
		port = sPort;	
		servIP = ip;
		socket = new Socket();
		try {
			socket.connect(new InetSocketAddress(servIP, port));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			setOIS(new ObjectInputStream(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected static void Listen(ObjectInputStream ois) {
		Message localMessage = null;		
		while(true){			
			try {
				localMessage = (Message) ois.readObject();
			} catch (IOException e) {
				//e.printStackTrace();
				try {
					ois.close();
					break;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				//e.printStackTrace();
				try {
					ois.close();
					break;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			System.out.println(localMessage);			
		}		
	}

	
	public boolean sendMessage(String text){
		Message m = new Message("", 2, text);
		try {
			oos.writeObject(m);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean changeName(String name){
		Message m = new Message(name, 1, "");
		try {
			oos.writeObject(m);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void setOIS(ObjectInputStream ois) {
		AppClient.ois = ois;
	}

	public static ObjectInputStream getOIS() {
		return ois;
	}
}


//package ru.spbstu.telematics.javatechnologies.lection12;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Client {
	
	static private int port = 7000;	
	static private String servIP = "localhost";

	public static Thread listener;
	
	public static void main(String[] args) throws Exception {
/////////////////////////////// установка соединения и потоков ввода-вывода.		
		Socket socket = new Socket();
		socket.connect(new InetSocketAddress(servIP, port));	
		
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		
		String user = "Vova";
		int type = 1;
		String m = "test";
		Message message = new Message(user, type, m);
		
		for(int i=0; i<5 ; i++){
			
			oos.writeObject(message);
			
		}	
		
	}

	protected static void Listen(ObjectInputStream ois) {
		Message localMessage = null;		
		while(true){			
			try {
				localMessage = (Message) ois.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(localMessage);			
		}		
	}
	
}


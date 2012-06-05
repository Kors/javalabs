
//package ru.spbstu.telematics.javatechnologies.lection12;

// тестовый клиент без интерактива. 


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Client {


	static private int port = 7000;	
	static private String servIP = "localhost";

	public static Thread listener;
	
	public Client(int sPort, String ip) {
		port = sPort;	
		servIP = ip;
	}

	public static void main(String[] args) throws Exception {
		/////////////////////////////// установка соединения и потоков ввода-вывода.		
		Socket socket = new Socket();
		socket.connect(new InetSocketAddress(servIP, port));	

		final ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		final ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

		listener = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Listen( ois );
					//socket.recieveMessa;ges(textArea);
				} catch (Exception e) {
					//e.printStackTrace();
					System.exit(1);
				}
			}
		});
		listener.start();

		String user = "Vova";
		int type = 1;
		String m = "test";
		Message message = new Message(user, type, m);

		oos.writeObject(message);
		for(long j =  0; j<500000000 ; j++){}

		
		type = 2;
		message = new Message(user, type, m);
		for(int i=0; i<50 ; i++){

			oos.writeObject(message);
			for(long j =  0; j<500000000 ; j++){}

		}	
	}

	protected static void Listen(ObjectInputStream ois) {
		Message localMessage = null;		
		while(true){			
			try {
				localMessage = (Message) ois.readObject();
			} catch (Exception e) {
				//e.printStackTrace();
				System.exit(1);
			}
			System.out.println(localMessage);			
		}		
	}

}


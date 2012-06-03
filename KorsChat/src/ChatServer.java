//package ru.spbstu.telematics.javatechnologies.lection12;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//старт сервера
public class ChatServer {

	static private int port = 7000;	
	static ExecutorService pool = Executors.newFixedThreadPool(10);

	public static ArrayList<ClientAccount> clients;

	////////////////////////////////////// ожидание клиентов	
	public static void main(String[] args) throws Exception {
		System.out.println("Server start. port № " + port);
		ServerSocket servSocket = new ServerSocket(port);
		clients = new ArrayList<ClientAccount>(); 

		while(true) {
			final Socket client = servSocket.accept();
			System.out.println("Connected: client port=" + client.getPort());

			pool.submit(new Runnable() {
				@Override
				public void run() {
					try {
						serve(client);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}



	////////////////////////////////////// обработка каждого клиента отдельно 
	private static void serve(Socket socket) throws Exception {
		// создадим "безымянного" клиента по умолчанию.
		ClientAccount clientAcc = new ClientAccount("somebody_"+socket.getPort(), socket );	
		//добавим в список:
		clients.add(clientAcc);
		LogIn(clientAcc);
		
		while (true){
			try{
				// пытаемся принять сообщение от клиента
				Message m = (Message) clientAcc.ois.readObject();
				
				// разделим сообщения по типу
				switch ( m.getNum() ){
				//тип "логин" ( сменить имя )
				case	1:
					//System.out.println( clientAcc.userName +" сменил имя на "+ m.getUser() );
					Message m1 = new Message("server", 0, clientAcc.userName +" сменил имя на "+ m.getUser() );
					Send(m1);
					clientAcc.userName = m.getUser();	
					break;
				//тип "сообщение"
				case	2:	
					m.setUser(clientAcc.userName);
					Send(m);
					break;
				default:
					System.out.println("Type of message unknown!");
					break;
				}
			} catch (Exception e) {
				//e.printStackTrace();
				//remove from list
				RemoveClient(clientAcc);
				Message m1 = new Message("server", 0, clientAcc.userName +" отключился от чата. " );
				Send(m1);
				clientAcc.ois.close();		
				break;
			}

		}
	}

	private static void LogIn(ClientAccount client) throws IOException, java.net.SocketException {
		Message m1 = new Message("server", 0, "К чату присоединился новенький.\n  Ему присвоено временное имя " + client.userName );
		Send(m1);

	}
	

	private static void Send(Message m) throws IOException, java.net.SocketException {
		// здесь надо пробегать по списку и рассылать всем.
		System.out.println(m);

		for (int i = 0; i < clients.size(); i++) {
			clients.get(i).send(m);
		}


	}
	
	private static void RemoveClient(ClientAccount client) throws IOException {
		// здесь надо пробегать по списку и удалять. 
		//System.out.println("удаляем клиента");
		clients.remove(client);

	}	
	
}
//package ru.spbstu.telematics.javatechnologies.lection12;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.locks.*;

//старт сервера
public class ChatServer {

	static private Lock clientsListLock = new ReentrantLock();
	static private Lock messageLock = new ReentrantLock();
	static private int port = 7000;	
	//static ExecutorService pool = Executors.newFixedThreadPool(10);

	public static ArrayList<ClientAccount> clients;

	////////////////////////////////////// ожидание клиентов	
	public static void main(String[] args) throws Exception {
		System.out.println("Server start. port № " + port);
		ServerSocket servSocket = new ServerSocket(port);
		clients = new ArrayList<ClientAccount>(); 

		while(true) {
			final Socket client = servSocket.accept();
			System.out.println("Connected: client port=" + client.getPort());

			//pool.submit(new Runnable() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						serve(client);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				//});
			}).start();
		}
	}



	////////////////////////////////////// обработка каждого клиента отдельно 
	private static void serve(Socket socket) throws Exception {
		// создадим "безымянного" клиента по умолчанию.
		ClientAccount clientAcc = new ClientAccount("somebody_"+socket.getPort(), socket );	
		//добавим в список:
		clientsListLock.lock();
		try{
			clients.add(clientAcc);
		}finally{ 
			clientsListLock.unlock();
		}

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
				break;
			}

		}
	}

	private static void LogIn(ClientAccount client) {
		Message m1 = new Message("server", 0, "К чату присоединился новенький.\n  Ему присвоено временное имя " + client.userName );
		Send(m1);
	}


	private static void Send(Message m)  {
		// здесь надо пробегать по списку и рассылать всем.  throws IOException  java.net.SocketException
		messageLock.lock();
		clientsListLock.lock();
		try {
			System.out.println(m);
			for (int i = 0; i < clients.size(); i++) {
				try {
					clients.get(i).send(m);
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
		}finally{
			clientsListLock.unlock();
			messageLock.unlock();
		}
	}

	private static void RemoveClient(ClientAccount client){
		// здесь надо пробегать по списку и удалять. 
		//System.out.println("удаляем клиента");
		clientsListLock.lock();
		try{
			clients.remove(client);
		}finally{
			clientsListLock.unlock();
		}
	}	

}
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

	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	
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
		//добавим в список:
		
		//clients.add(new ClientAccount("testName", socket));
		//// установка потоков ввода-вывода.			
		//input stream
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//Object
		//ClientAccount cl = new ClientAccount("name", socket );		
		
			while (true){
					try{
						System.out.println("***");
						Message m = (Message) ois.readObject();
						System.out.println("***2");
						Send(m);
					} catch (Exception e) {
						e.printStackTrace();
						ois.close();
						break;
					} finally{
						//remove from list
					}

			}
	}



	private static void Send(Message m) {
		// здесь надо пробегать по списку и рассылать всем.
		System.out.println(m);

		//for (int i = 0; i < clients.size(); i++) {
		//	clients.get(i).send(m);
		//}


	}
}
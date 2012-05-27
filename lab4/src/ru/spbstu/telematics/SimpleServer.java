package ru.spbstu.telematics;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleServer {
	
	static ExecutorService pool = Executors.newFixedThreadPool(10);
	
	public static void main(String[] args) throws Exception {
		ServerSocket s = new ServerSocket(10000);
		System.out.print("\nStart!\n");
		while(true) {
			final Socket client = s.accept();
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

	private static void serve(Socket client) throws Exception {
		InputStream inputStream = client.getInputStream();
		DataInputStream dis = new DataInputStream(inputStream);
		double d = dis.readDouble();
		System.out.println(d);
		
		DataOutputStream dos =  new DataOutputStream(client.getOutputStream());
		dos.writeDouble(Math.sqrt(d));
		
		ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
		Message m = (Message) ois.readObject();
		System.out.println(m);
//		client.shutdownInput();
	}
}

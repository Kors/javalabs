package ru.spbstu.telematics;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class SimpleClient {
	public static void main(String[] args) throws Exception {
		Socket s = new Socket();
		s.connect(new InetSocketAddress("localhost", 10000));
		
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		DataInputStream dis = new DataInputStream(s.getInputStream());
		dos.writeDouble(Math.abs(new Random().nextDouble()));
		double d = dis.readDouble();
		System.out.println(d);
		
		Message m = new Message("john", 2, "hello");
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(m);
	}
}

package my;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class MyClient {


	public static void main(String[] args) throws Exception {
		Socket s = new Socket();
		s.connect(new InetSocketAddress("localhost", 10000));
		
		
		
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		DataInputStream dis = new DataInputStream(s.getInputStream());
		//dos.writeDouble(Math.abs(new Random().nextDouble()));
		dos.writeDouble(4);
		double d = dis.readDouble();
		System.out.println(d);
		
//		double i = 1;
//		while (true){
//			Thread.sleep(3000L);
//			dos.writeDouble(i++);
//			d = dis.readDouble();
//			System.out.println(d);
//			System.out.print("\nclient here\n");
//		}
		
		MyMessage m = new MyMessage("john", 2, "hello");
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(m);
		
	}

}

﻿package files;
import files.*;

import java.util.Random;

public class field_of_berries {

	//private final static Object lock = new Object();
	Random rand = new Random();
	int _berries = 5;

	public field_of_berries(){

	}

	int get_berries(int neighbourNumber){
		//synchronized(lock) {
		System.out.print("\nсосед " + neighbourNumber + " поднял флаг и вышел в поле");
		try {
			Thread.sleep(1000 + rand.nextInt(2000) );
		} catch (InterruptedException ex) {
		}
		//System.out.print("\nсосед " + neighbourNumber + " опустил флаг выходя");


		//		lock.notify();
		//	}
		return _berries + rand.nextInt(10);
	}
}

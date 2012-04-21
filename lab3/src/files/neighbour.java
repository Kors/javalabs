package files;

import files.*;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.Random;


public class neighbour implements Runnable {

	Random rand = new Random();
	//private ReadWriteLock _flag;
	//private Lock _flag;
	private int _neighbourNumber = 0 ;
	private field_of_berries _field;
	private neighbour _myNeighbour;
	public boolean _flag = false;
	private int _countOfGoingToField = 0 ;
	private int _countOfBerries = 0 ;

	private int _tmp = 0 ;


	public neighbour(int neighbourNumber , field_of_berries field) {
		this._neighbourNumber = neighbourNumber;
		this._field = field ;
		//this._flag.lock();
	}

	public void setNeighbour(neighbour myNeighbour){
		this._myNeighbour = myNeighbour;
	}

	public void run() {
		System.out.println("\nNeighbour "+ _neighbourNumber + " START");
		while (true){
			System.out.print("\n����� " +_neighbourNumber + " ���������� ������� ����");

			if ( tryGoToField() ){
				_tmp = _field.get_berries(_neighbourNumber);
				System.out.print("\n����� " + _neighbourNumber + 
						" �������� � ����, ������� " + 
						 _tmp + "�� ����. =)" +
						"\n � ������� ����.");
				_countOfGoingToField += 1 ;
				_countOfBerries += _tmp;
				this._flag = false; 
			}else{
				System.out.print("\n����� " +_neighbourNumber + " ������ ���� � ���� =(");
			}

			try {
				Thread.sleep(100 + rand.nextInt(2000));
			} catch (InterruptedException ex) {
			}

		}

	}

	private boolean tryGoToField(){
		_flag = true;
		if ( ! _myNeighbour._flag ){
			return true;
		}
		_flag = false;
		return false;
	}



	public int get_countOfBerries() {
		return _countOfBerries;
	}

	public int get_countOfGoingToField() {
		return _countOfGoingToField;
	}

}

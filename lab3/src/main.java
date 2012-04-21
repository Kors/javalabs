
import files.*;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class main {

	/**
	 * @param args
	 * ����� � ����.
	 * ��� ���������� ������ ��������� ����� � �������.
	 * ��� ��������� ���� ����� �������� �� ���� � �������� �����,
	 * �� �� ����� ���� ��������� ��� ������ ���� �� ��� ���������
	 * �� ���� � ������ ������ �������.
	 * ����� ������ ������ ��� ������� ��������� �������� ��������:
	 * ����� ���� �� ������� ����� ����� �� ����, �� ��������� ����.
	 * ���� �� ����� ���� ������ ������,
	 * �� �� ������� �� ���� � �������� ���� ���� � ������� �����.
	 * ���� �� �� ����� ����� ������,
	 * �� ������ �� ���� � �������� �����.
	 * �� �������� ���� ���� ����� ��� ������ � ����.
	 * ������������� ��� ��������.
	 * ������� ��� ������, N1 � N2.
	 * ���������� ���������� ���������� ������ � ���� � ������������
	 * � ����������.
	 * �������� �������� �Progress� ��� ������� (���������� ����),
	 * ����� �������������� �������������� ���������.
	 */


	public static void main(String[] args) {
		System.out.print("\nMain begin!\n");
		//////************///////
		Random rand = new Random();
		final int neighbourCount = 2;
		Thread[] threads = new Thread[neighbourCount];
		field_of_berries field = new field_of_berries();
		//////************///////
		neighbour neighbour[] = new neighbour[neighbourCount];
		for (int i = 0; i < neighbourCount; i++) {
			neighbour[i] = new neighbour(i+1, field);
			threads[i] = new Thread(neighbour[i]);
		}
		//������������� ���������
		neighbour[0].setNeighbour( neighbour[1] );
		neighbour[1].setNeighbour( neighbour[0] );

		//��������� �������
		for (int i=0; i<neighbourCount; i++)
			threads[i].start();

		//������� ����������
		while(true){
			System.out.print("\n\n ����� 1 ������� �� ���� " +
					neighbour[0].get_countOfGoingToField() + " ���. � ������ " +
					neighbour[0].get_countOfBerries() + "�� ����." +
					"\n ����� 2 ������� �� ���� " +
					neighbour[1].get_countOfGoingToField() + " ���. � ������ " +
					neighbour[1].get_countOfBerries() + "�� ����.\n" );
			try {
				Thread.sleep(5000);
			} catch (InterruptedException ex) {
			}
		}

	}
}

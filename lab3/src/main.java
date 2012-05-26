﻿
import files.*;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class main {

	/**
	 * @param args
	 * Ягоды в саду.
	 * Два враждующих соседа разделены полем с ягодами.
	 * Они разрешили друг другу выходить на поле и собирать ягоды,
	 * но им нужно быть уверенным что только один из них находится
	 * на поле в каждый момент времени.
	 * После долгих споров они приняли следующий протокол действий:
	 * Когда один из соседей хочет пойти на поле, он поднимает флаг.
	 * Если он видит флаг своего соседа,
	 * он не заходит на поле и спускает свой флаг и пробует снова.
	 * Если он не видит флага соседа,
	 * он входит на поле и собирает ягоды.
	 * Он опускает свой флаг сразу как выйдет с поля.
	 * Смоделировать эту ситуацию.
	 * Имеется два соседа, N1 и N2.
	 * Необходимо обеспечить раздельный доступ к полю в соответствии
	 * с протоколом.
	 * Добавить свойство “Progress” для соседей (количество ягод),
	 * чтобы контролировать справедливость протокола.
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
		//устанавливаем соседство
		neighbour[0].setNeighbour( neighbour[1] );
		neighbour[1].setNeighbour( neighbour[0] );

		//запускаем соседей
		for (int i=0; i<neighbourCount; i++)
			threads[i].start();

		//выводим статистику
		while(true){
			System.out.print("\n\n сосед 1 выходил на поле " +
					neighbour[0].get_countOfGoingToField() + " раз. и принес " +
					neighbour[0].get_countOfBerries() + "кг ягод." +
					"\n сосед 2 выходил на поле " +
					neighbour[1].get_countOfGoingToField() + " раз. и принес " +
					neighbour[1].get_countOfBerries() + "кг ягод.\n" );
			try {
				Thread.sleep(5000);
			} catch (InterruptedException ex) {
			}
		}

	}
}

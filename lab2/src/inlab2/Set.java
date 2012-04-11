package inlab2;

import inlab2.ISet;
import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Set<T> implements ISet<T>, Iterable<T> {
	
	private T[] _array;
	private int _size = 0;
	
	public Set()
	{
		_array = (T[]) new Object[1];
	}
	
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Object o){
		//
	System.out.print("\n\n try to add " + o);
		//
		boolean tmp = false;
		for ( int i = 0 ; (i < _size) && (! tmp); i++ ){
			if ( o.equals(_array[i]) )
				tmp = true;
		}
		
		if (! tmp){
			if (_size==_array.length)
				enlarge();
			_array[_size] = (T) o;
			_size++;
			//
			System.out.print("\n  "+ o + " added successfully ");
			//
		}else
			System.out.print("\n  "+ o + " is already in ");
	}

	private void enlarge() {
		System.out.print("\n   enlarge to " + _array.length*2 + " cells");
		T tmp[] = (T[]) new Object[_array.length*2];
		System.arraycopy(_array, 0, tmp, 0, _array.length);
		_array = tmp;
	}


	@Override
	public boolean remove(Object o) {
		System.out.print("\n\n try to remove " + o);
		
		int tmp = -1;
		for ( int i = 0 ; (i < _size) && (tmp == -1); i++ ){
			if ( o.equals(_array[i]) )
				tmp = i;
		}
		
		if (tmp != -1){
			_size--;
			System.arraycopy(_array, tmp+1, _array, tmp , _size);
			System.out.print("\n  "+ o + " deleted successfully ");
			return true;
		}

		System.out.print("\n Object " + o + " is not in our set ");
		return false;
	}


	@Override
	public boolean contains(Object o) {
		System.out.print("\n\n try to contains " + o);
		
		for( int i =0 ; i < _size ; i++ ){
			if ( o.equals(_array[i]) ){
				return true;
			}
		}
			return false;
	}
	
	public void isin(Object o) {
		if ( this.contains( o ) ){
			System.out.print("\n Object " + o + " is in our set ");
		}else {
			System.out.print("\n Object " + o + " is not in our set ");
		}
	}
	
}

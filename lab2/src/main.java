
//import inlab2.ISet;
import inlab2.Set;
import java.io.*;
//import java.util.Iterator;

import javax.swing.text.html.HTMLDocument.Iterator;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.print("\nLab 2 begin!\n");
		
		Set<String> set = new Set<String>();
		
		set.add("k");
		set.add("k");
		set.add("k");
		set.add("d");
		set.add("s");
		set.add("2");
		set.add("9");
		
		set.remove("q");
		set.remove("k");
		set.remove("k");
		
		set.isin("k");
		set.isin("e");
		set.isin("2");
		set.isin("s");
		set.isin("7");
		set.isin("d");
		set.isin("9");
		
		set.add("k");
		
		
		
		// что будет если будем добавлять не соответствующие данные
		//set.add(2);
		//set.add("2");
		//set.add(2);
		
		//set.add((int)65878);
		//set.add(true);
		
		/////////////////

		set.show();
		
		////////////// при помощи iterator
		
		// чтение
		//System.out.println("\n\nShow with iterator");
		//for ( java.util.Iterator i = set.iterator(); i.hasNext(); ){
		//	System.out.println(i.next());
		//}
		
		System.out.println("\n\nShow with iterator");
		for (java.util.Iterator<String> i = set.iterator(); i.hasNext(); ){
			System.out.println(i.next());
		}
		
		// удаление
		//set.remove("2");
		boolean tmp=true;
		for ( java.util.Iterator j = set.iterator(); j.hasNext() && tmp; ){
			if("2".equals(j.next())) {
				j.remove();
				System.out.println("\n\n 2 removed ");
				tmp=false;
			}
		}
		if (tmp){
			System.out.println("\n Object 2 is absent ");
		}
		
		tmp=true;
		for ( java.util.Iterator j = set.iterator(); j.hasNext() && tmp; ){
			if("7".equals(j.next())) {
				j.remove();
				System.out.println("\n\n 7 removed ");
				tmp=false;
			}
		}
		if (tmp){
			System.out.println("\n Object 7 is absent ");
		}
		set.show();		
		
	}
	
}


import inlab2.ISet;
import inlab2.Set;
import java.io.*;

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
		
		
		///////////////////////////
		
		set.add(2);
		set.add((int)65878);
		set.add(true);
		
		/////////////////
		
		Set<Boolean> set1 = new Set<Boolean>();
		set1.add(true);
		set1.isin(true);
		set1.isin(false);
		
		
	}

}

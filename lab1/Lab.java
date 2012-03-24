/*
Korsikov Ilya
Lab  1
*/

//package lab1;
import java.io.*;

public class Lab {

	public static void main (String[] args) {
		System.out.println("Введите строку: ");
		int number = 0 ;
		String s1 ="";
		  
		boolean temp = true;
		   
		StringBuffer sb = new StringBuffer(30);
		
		while ( temp ){
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				s1 = in.readLine();
				number = s1.length() ;
				System.out.print("\nEntered -> " + s1 + " length:" + number);
			}
			catch(IOException e) {
				System.out.println("IOException has been caught");
			}

			for (int i = 0; i<number ; i++){
			
				if ( '~' == s1.charAt(i)  ){
					temp = false;
					System.out.println(" Yes!!! ");
					break;
				}
				else{
					//sb.append(s1.charAt(i));
					sb.insert(0 , s1.charAt(i));
				}
			}
			System.out.println("\ncontinue ( until ~ ) : ");
		}
		

		int number2 = sb.length() ;	
			
		System.out.println("\nв итоге : " + sb + " length: " + number2 );


	}

}

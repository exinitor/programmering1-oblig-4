import java.util.Scanner;

public class SnuTekst {

	
	public static void baklengs(String tekst, int n){
		int teller = 1;
		System.out.print(tekst.charAt(tekst.length()-1));
		
		if(tekst.length() > 1)
		{
			baklengs(tekst.substring(0,tekst.length()-1));	
			teller ++;
		}
		System.out.print(teller);
	}

}
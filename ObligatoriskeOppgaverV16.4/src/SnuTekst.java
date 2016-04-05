import java.util.Scanner;

public class SnuTekst {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		System.out.print("Oppgi en tekst: ");
		String tekst = input.nextLine();
		
		System.out.print("\nTeksten baklengs er: " );
		baklengs(tekst);
	}
	
	public static void baklengs(String tekst){
		System.out.print(tekst.charAt(tekst.length()-1));
		if(tekst.length() > 1)
			baklengs(tekst.substring(0,tekst.length()-1));
	}

}
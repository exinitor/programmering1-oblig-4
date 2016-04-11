import java.util.Scanner;

public class testSnuTekst {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		System.out.print("Oppgi en tekst: ");
		String tekst = input.nextLine();
		
		System.out.print("\nTeksten baklengs er: " );
		SnuTekst.baklengs(tekst, 1);
	}
}
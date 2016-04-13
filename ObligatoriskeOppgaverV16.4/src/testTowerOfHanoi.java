	import java.util.Scanner;
public class testTowerOfHanoi {
	
	public static void main(String[] args) {
			
		Scanner input = new Scanner (System.in);
		System.out.print("Oppgi antall disker: ");
		int n = input.nextInt();
		towerOfHanoi test1 = new towerOfHanoi();
		test1.moveDisks(n, 'A', 'B', 'C');
		}	
	}

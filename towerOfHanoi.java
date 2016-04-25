
public class towerOfHanoi {
	int antallTrekk = 0;
	int antallKall = 0;
	
	public towerOfHanoi(){
	}
	
	public void moveDisks(int n, char fromTower, char toTower, char auxTower){
		if(n==1)
		{	
			print("Flytt disk " + n + " fra " + fromTower + " til " + toTower);
		}
			
		else
		{	
			moveDisks(n-1, fromTower, auxTower, toTower);
			print("Flytt disk " + n + " fra " + fromTower + " til " + toTower);	
			moveDisks(n-1, auxTower, toTower, fromTower);
		}
	}
	public void print(String s){
		antallTrekk++;
		antallKall++;
		System.out.println(s);
		System.out.println("Antall kall er: " + antallKall);
	}

}

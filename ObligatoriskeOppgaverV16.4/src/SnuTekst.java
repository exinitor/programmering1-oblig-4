
public class SnuTekst {

	String tekst;
	public static int antallForandringer = 1;
 
 	public SnuTekst(String tekst){
	 this.tekst = tekst;
 	}
 
	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public static void baklengs(String tekst){
		System.out.print(tekst.charAt(tekst.length()-1));
		
		if(tekst.length() > 1)
		{
				baklengs(tekst.substring(0,tekst.length()-1));
				antallForandringer++;
		}
	}
}
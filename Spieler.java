

import java.util.*;

public abstract class Spieler {

	private int[] wuerfel = new int[5];
	private Spielblock Spielblock1;
	public Spieler(Spielblock Spielblock1){
		this.Spielblock1=Spielblock1;
	}
	Random r = new Random();
	Scanner sc = new Scanner(System.in);

	public void zugAusfuehren() {
		boolean[] behalten = new boolean[6];
		//System.out.println();
		for (int j = 0; j < 3; j++) {

			for (int i = 0; i < wuerfel.length; i++) {
				if (!behalten[i])
					wuerfel[i] = (int) (6* r.nextDouble() + 1);
			}
			//System.out.println();
			if (j < 2)
				entscheideBehalten(wuerfel, behalten);
		}
		entscheideFeld(wuerfel);
		
	}
	
	public Spielblock getBlock(){
		return Spielblock1;
	}
	public abstract void entscheideFeld(int[] w5);

	public abstract void entscheideBehalten(int[] w5, boolean[] behalten);
}

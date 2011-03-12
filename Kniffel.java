

public class Kniffel {
	private static Spieler[] theSpieler;

	private static final int SPALTEN = 3;
	private static final int ZEILEN = 14;
	private static final int SPIELER = 10000;

	public static Spieler[] getTheSpieler() {
		return theSpieler;
	}

	public static void setTheSpieler(Spieler[] Spieler) {
		theSpieler = Spieler;
	}

	public static void main(String[] args) {

		theSpieler = new SpielerSchlau[SPIELER];
		Spielblock[] block = new Spielblock[SPIELER];
		for (int j = 0; j < theSpieler.length; j++) {
			block[j] = new Spielblock(SPALTEN, ZEILEN);
			theSpieler[j] = new SpielerSchlau(block[j]);
		}

		for (int i = 0; i < 39; i++) {
			for (int j = 0; j < theSpieler.length; j++) {
				theSpieler[j].zugAusfuehren();
			}
		}
		for (int j = 0; j < theSpieler.length; j++) {
			theSpieler[j].getBlock().ausgeben();
		}
		// Durchschnitt berechnen und ausgeben
		double durchschnitt = 0;
		double gesamt = 0;
		double meister=0;
		double loser=100000;
		for (int j = 0; j < theSpieler.length; j++) {
			gesamt = gesamt + theSpieler[j].getBlock().gesamtPunktzahl;
			if(theSpieler[j].getBlock().gesamtPunktzahl>meister)
				meister =theSpieler[j].getBlock().gesamtPunktzahl;
			if(theSpieler[j].getBlock().gesamtPunktzahl<loser)
				loser =theSpieler[j].getBlock().gesamtPunktzahl;
		}
		durchschnitt = gesamt / theSpieler.length;
		
		System.out.println("Gesamtpunkte:  " + gesamt);
		System.out.println("Loser:  " + loser);
		System.out.println("Meister:  " + meister);
		System.out.println("Durchschnitt:  " + durchschnitt);
	}
}

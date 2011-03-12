

public class Spielblock {
	private int punkte[][];
	
	public int gesamtPunktzahl;

	public Spielblock(int spalten, int zeilen) {
		punkte = new int[spalten][zeilen];
		for (int i = 0; i < punkte.length; i++) {
			for (int j = 0; j < punkte[i].length; j++) {
				if (j != 6)
					punkte[i][j] = -1;
			}
		}
	}
	
	public void setWert(int spalte, int zeile, int[] w5) {
		int summe = 0;

		// Bonuspunkte 
		switch (zeile) {
		case 0: 
		case 1:
		case 2://3,3,4,3,1 Zeile=2
		case 3:
		case 4:
		case 5:
			int anzZahl = 0;
			for (int i = 0; i < w5.length; i++) {
				if (w5[i] == zeile + 1) {
					anzZahl++;
				}
			}
			summe = anzZahl * (zeile + 1);
			break;
		case 6:
			break;
		case 7:
			if (istDreierpasch(w5))
				summe = Gesamtwert(w5);
			break;
		case 8:
			if (istViererpasch(w5))
				summe = Gesamtwert(w5);

			break;
		case 9:
			if (istFullHouse(w5))
				summe = 25;
			break;
		case 10:
			if (istKleineStrasse(w5))
				summe = 30;
			break;
		case 11:
			if (istGrosseStrasse(w5))
				summe = 40;
			break;
		case 12:
			if (istKniffel(w5))
				summe = 50;
			break;
		case 13:
			summe = Gesamtwert(w5);
			break;
		default:
			System.out.println("ERROR!!");
		}

		if (punkte[spalte][zeile] == -1) {
			punkte[spalte][zeile] = summe;
		} else {
			System.out.println("Kein Wert eingetragen");
		}
	}

	// get und set Methoden
	public int getWert(int spalte, int zeile) {
		return punkte[spalte][zeile];
	}

	public int getSpalten() {
		return punkte.length;
	}

	public int getZeilen() {
		return punkte[0].length;
	}


	// andere Methoden
	private boolean istKniffel(int[] w5) {
		int testzahl = w5[0];
		for (int j = 0; j < w5.length; j++) {
			if (testzahl != w5[j])
				return false;
		}
		return true;
	}

	private boolean istGrosseStrasse(int[] w5) {
		for (int i = 1; i <= 2; i++) {
			if (anzZahl(i, w5) > 0 & anzZahl(i + 1, w5) > 0
					&& anzZahl(i + 2, w5) > 0 && anzZahl(i + 3, w5) > 0
					&& anzZahl(i + 4, w5) > 0)
				return true;
		}
		return false;
	}

	private boolean istKleineStrasse(int[] w5) {
		for (int i = 1; i <= 3; i++) {
			if (anzZahl(i, w5) > 0 & anzZahl(i + 1, w5) > 0
					& anzZahl(i + 2, w5) > 0 & anzZahl(i + 3, w5) > 0)
				return true;
		}
		return false;
	}

	private boolean istViererpasch(int[] w5) {
		int anzZahl = 0;
		int testzahl;
		for (int i = 0; i < w5.length; i++) {
			testzahl = w5[i];
			for (int j = 0; j < w5.length; j++) {
				if (testzahl == w5[j])
					anzZahl++;
				if (anzZahl == 4) {
					return true;
				}
			}
			anzZahl = 0;
		}
		return false;
	}

	private boolean istDreierpasch(int[] w5) {
		int anzZahl = 0;
		int testzahl;
		for (int i = 0; i < w5.length; i++) {
			testzahl = w5[i];
			for (int j = 0; j < w5.length; j++) {
				if (testzahl == w5[j])
					anzZahl++;
				if (anzZahl == 3) {
					return true;
				}
			}
			anzZahl = 0;
		}
		return false;
	}

	public void ausgeben() {
//		System.out.println();
//		System.out.println("Spielblock");
		gesamtPunktzahl = 0;
		addBonus();
		for (int i = 0; i < punkte.length; i++) {

			for (int j = 0; j < punkte[i].length; j++) {
//				System.out.print(punkte[i][j] + "\t");
				gesamtPunktzahl += punkte[i][j];
			}
//			System.out.println();
			
		}
//		System.out.println("Gesamtpunktzahl:  " + gesamtPunktzahl);
	}

	private boolean istFullHouse(int[] w5) {
		int testzahl = 0;
		int anz = 0;
		boolean zwei = false, drei = false;
		for (int i = 1; i < 7; i++) {
			testzahl = i;
			for (int j = 0; j < w5.length; j++) {
				if (w5[j] == testzahl)
					anz++;
			}
			if (anz == 3)
				drei = true;
			else if (anz == 2)
				zwei = true;
			anz = 0;
		}

		if (zwei && drei)
			return true;
		else
			return false;

	}

	private int Gesamtwert(int[] w5) {
		int punkte = 0;
		for (int i = 0; i < w5.length; i++) {
			punkte = punkte + w5[i];
		}
		return punkte;

	}

	private void addBonus() {
		int obenPunkte = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 6; j++) {
				obenPunkte = obenPunkte + punkte[i][j];
			}
			if (obenPunkte >= 63)
				punkte[i][6] = 35;
			obenPunkte = 0;
		}

	}

	

	private int anzZahl(int zahl, int[] w5) {// gibt die Anzahl einer Zahl der
											// WŸrfel  zurŸck
		int anzZahl = 0;
		for (int j = 0; j < w5.length; j++) {
			if (zahl == w5[j])
				anzZahl++;
		}
		return anzZahl;
	}
}

public class SpielerSchlau extends Spieler {

	public SpielerSchlau(Spielblock Spielblock1) {
		super(Spielblock1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void entscheideBehalten(int[] wuerfel, boolean[] behalten) {
		boolean entschieden = false;

		for (int i = 0; i < wuerfel.length; i++)
			behalten[i] = false;

		// Bei Kniffel alle behalten
		if (!entschieden && istKniffel(wuerfel) && !istZeileVoll(12)) {
			for (int i = 0; i < wuerfel.length; i++)
				behalten[i] = true;
			entschieden = true;
			// Bei GrosseStrasse alle behalten
		} else if (!entschieden && istGrosseStrasse(wuerfel)
				&& !istZeileVoll(11)) {
			for (int i = 0; i < wuerfel.length; i++)
				behalten[i] = true;
			entschieden = true;
		} else {
			// fals eine Zahl Àofter als 3 mal vorkommt behalten
			for (int i = 1; i <= 6; i++)
				if (anzZahl(i, wuerfel) >= 3)
					if (!entschieden
							&& ((istFullHouse(wuerfel) && i > 3 && !istZeileVoll(9)) || (!istFullHouse(wuerfel)))) {// 0=708;2-4=713;5-6=710
						zahlBehalten(i, behalten, wuerfel);
						entschieden = true;
					} else if (!entschieden && istFullHouse(wuerfel)
							&& !istZeileVoll(9)) {
						for (int j = 0; j < wuerfel.length; j++)
							behalten[j] = true;
						entschieden = true;
					}
			// wenn die kleine Strasse 2 3 4 5 ist behalten, weil man gute
			// chance auf grosse hat

			if (!entschieden
					&& (anzZahl(2, wuerfel) > 0 && anzZahl(3, wuerfel) > 0
							&& anzZahl(4, wuerfel) > 0 && anzZahl(5, wuerfel) > 0)
					&& (!istZeileVoll(10) || !istZeileVoll(11))) {
				for (int j = 2; j <= 5; j++)
					for (int i = 0; i < wuerfel.length; i++)
						if (wuerfel[i] == j) {
							behalten[i] = true;
							break;
						}
				entschieden = true;
			} else if (!entschieden
					&& (anzZahl(1, wuerfel) > 0 && anzZahl(2, wuerfel) > 0
							&& anzZahl(3, wuerfel) > 0 && anzZahl(4, wuerfel) > 0)
					&& (!istZeileVoll(10) || !istZeileVoll(11))) {
				for (int j = 1; j <= 4; j++)
					for (int i = 0; i < wuerfel.length; i++)
						if (wuerfel[i] == j) {
							behalten[i] = true;
							break;
						}
				entschieden = true;
			} else if (!entschieden
					&& (anzZahl(3, wuerfel) > 0 && anzZahl(4, wuerfel) > 0
							&& anzZahl(5, wuerfel) > 0 && anzZahl(6, wuerfel) > 0)
					&& (!istZeileVoll(10) || !istZeileVoll(11))) {
				for (int j = 3; j <= 6; j++)
					for (int i = 0; i < wuerfel.length; i++)
						if (wuerfel[i] == j) {
							behalten[i] = true;
							break;
						}
				entschieden = true;
			} else {// wenn oben voll ist und strassen noch offen nimm 234 345
				if (!entschieden && istObenVoll()
						&& (!istZeileVoll(10) || !istZeileVoll(11))) {
					if (!entschieden
							&& (anzZahl(2, wuerfel) > 0
									&& anzZahl(3, wuerfel) > 0 && anzZahl(4,
									wuerfel) > 0)) {
						for (int j = 2; j <= 4; j++)
							for (int i = 0; i < wuerfel.length; i++)
								if (wuerfel[i] == j) {
									behalten[i] = true;
									break;
								}
						entschieden = true;
					}
					if (!entschieden
							&& (anzZahl(3, wuerfel) > 0
									&& anzZahl(4, wuerfel) > 0 && anzZahl(5,
									wuerfel) > 0)) {
						for (int j = 3; j <= 5; j++)
							for (int i = 0; i < wuerfel.length; i++)
								if (wuerfel[i] == j) {
									behalten[i] = true;
									break;
								}
						entschieden = true;
					}
				}
				for (int i = 6; i >= 1; i--)
					if (!entschieden && anzZahl(i, wuerfel) >= 2
							&& (!istZeileVoll(i - 1) || istObenVoll())) {
						zahlBehalten(i, behalten, wuerfel);
						entschieden = true;
						break;
					}
				for (int i = 6; i >= 1; i--)
					if (!entschieden && anzZahl(i, wuerfel) >= 1
							&& !istZeileVoll(i - 1)) {
						zahlBehalten(i, behalten, wuerfel);
						entschieden = true;
						break;
					}
			}

		}

//		 ausgabe
//		 for (int i = 0; i < wuerfel.length; i++) {
//		 System.out.print(wuerfel[i] + " ");
//		 }
//		 System.out.println();
	}

	// entscheidung welches Feld!
	@Override
	public void entscheideFeld(int[] wuerfel) {
//		 for (int i = 0; i < wuerfel.length; i++) {
//		 System.out.print(wuerfel[i] + " ");
//		 }
//		 System.out.println();
		// getPunkte();
		boolean eingetragen = false;
		// Kniffel wird eingetragen

		if (istKniffel(wuerfel) && !istZeileVoll(12) && !eingetragen) {
			wertEintragenEinfach(12, wuerfel);
			eingetragen = true;
		} else {
			// 4Gleiche werden oben eingetragen
			for (int i = 0; i < 6; i++) {
				if (anzZahl(i + 1, wuerfel) >= 4 && !istZeileVoll(i)
						&& !eingetragen) {

					wertEintragenOben(i, wuerfel);
					eingetragen = true;
				}
			}
			if (istViererpasch(wuerfel) && !istZeileVoll(8) && !eingetragen) {
				wertEintragenEinfach(8, wuerfel);
				eingetragen = true;
			} else if (istGrosseStrasse(wuerfel) && !istZeileVoll(11)
					&& !eingetragen) {
				wertEintragenEinfach(11, wuerfel);
				eingetragen = true;
			} else if (istKleineStrasse(wuerfel) && !istZeileVoll(10)
					&& !eingetragen) {
				wertEintragenEinfach(10, wuerfel);
				eingetragen = true;
			} else if (istFullHouse(wuerfel) && !istZeileVoll(9)
					&& !eingetragen) {
				wertEintragenEinfach(9, wuerfel);
				eingetragen = true;
			}
			for (int i = 6; i >= 1; i--) {
				if (anzZahl(i, wuerfel) == 3 && !istZeileVoll(i - 1)
						&& !eingetragen) {
					wertEintragenOben(i - 1, wuerfel);
					eingetragen = true;
				}
			}
			for (int i = 1; i <= 3; i++) {
				if (anzZahl(i, wuerfel) == 2 && !istZeileVoll(i - 1)
						&& !eingetragen) {
					wertEintragenOben(i - 1, wuerfel);
					eingetragen = true;
				}
			}
			if (istDreierpasch(wuerfel) && !istZeileVoll(7) && !eingetragen) {
				wertEintragenEinfach(7, wuerfel);
				eingetragen = true;
			} else if (Gesamtwert(wuerfel) > 13 // 13 747 21 718
					&& !istZeileVoll(13) && !eingetragen) {
				wertEintragenEinfach(13, wuerfel);
				eingetragen = true;
			} else if (!istZeileVoll(0) && !eingetragen) {
				wertEintragenEinfach(0, wuerfel);
				eingetragen = true;
			} else if (!istZeileVoll(8) && !eingetragen) {
				wertEintragenEinfach(8, wuerfel);
				eingetragen = true;
			} else if (!istZeileVoll(12) && !eingetragen) {
				wertEintragenEinfach(12, wuerfel);
				eingetragen = true;
			}

			for (int i = 6; i >= 4; i--) {
				if (anzZahl(i, wuerfel) == 2 && !istZeileVoll(i - 1)
						&& !eingetragen) {
					wertEintragenOben(i - 1, wuerfel);
					eingetragen = true;
				}
			}
			if (!istZeileVoll(11) && !eingetragen) {
				wertEintragenEinfach(11, wuerfel);
				eingetragen = true;
			}

			for (int i = 0; i < 14; i++) {
				if (!istZeileVoll(i) && !eingetragen) {
					wertEintragenEinfach(i, wuerfel);
					eingetragen = true;
				}
			}
		}

		//getBlock().ausgeben();
	}

	public boolean[] zahlBehalten(int zahl, boolean[] behalten, int[] wuerfel) {
		for (int i = 0; i < wuerfel.length; i++)
			if (wuerfel[i] == zahl)
				behalten[i] = true;
		return behalten;
	}

	public void wertEintragenEinfach(int b, int[] w5) {

		for (int i = 0; i < 3; i++) {
			if (getBlock().getWert(i, b) == -1) {
				getBlock().setWert(i, b, w5);
				break;
			}
		}
	}

	public void wertEintragenOben(int b, int[] w5) {
		int[] oben = new int[3];
		
		
		boolean entschieden = false;
		getPunkte(oben);
		if (anzZahl(b + 1, w5) >= 4)
			for (int i = 0; i <= 2; i++) {
				if (!entschieden && oben[i] < 63
						&& getBlock().getWert(i, b) == -1) {
					getBlock().setWert(i, b, w5);
					entschieden = true;
					break;
				}
			}

		if (anzZahl(b + 1, w5) <= 3)
			for (int i = 0; i <= 2; i++) {
				if (!entschieden && oben[i] >= 63
						&& getBlock().getWert(i, b) == -1) {
					getBlock().setWert(i, b, w5);
					entschieden = true;
					break;
				}
			}
		for (int i = 0; i <= 2; i++)
			if (!entschieden && getBlock().getWert(i, b) == -1) {
				getBlock().setWert(i, b, w5);
				entschieden = true;
				break;
			}

	}

	public int[] getPunkte(int[] oben) {

		for (int i = 0; i < oben.length; i++) {
			for (int j = 0; j < 6; j++) {
				if (getBlock().getWert(i, j) == -1)
					oben[i] += (j + 1) * 3;
				else
					oben[i] += getBlock().getWert(i, j);
			}

		}
		return oben;
	}

	public void wertEintragenEinfachVonHinten(int b, int[] w5) {
		int a = 2;
		while (getBlock().getWert(a, b) != -1 && a > 0) {
			a--;
		}
		getBlock().setWert(a, b, w5);
	}

	public boolean istZeileVoll(int b) {
		int a = 0;
		for (int i = 0; i < getBlock().getSpalten(); i++)
			if (getBlock().getWert(i, b) == -1)
				a++;
		if (a != 0)
			return false;
		else
			return true;
	}

	public boolean istKniffel(int[] w5) {
		int testzahl = w5[0];
		for (int j = 0; j < w5.length; j++) {
			if (testzahl != w5[j])
				return false;
		}
		return true;
	}

	public boolean istGrosseStrasse(int[] w5) {
		for (int i = 1; i <= 2; i++) {
			if (anzZahl(i, w5) > 0 & anzZahl(i + 1, w5) > 0
					&& anzZahl(i + 2, w5) > 0 && anzZahl(i + 3, w5) > 0
					&& anzZahl(i + 4, w5) > 0)
				return true;
		}
		return false;
	}

	public boolean istKleineStrasse(int[] w5) {
		for (int i = 1; i <= 3; i++) {
			if (anzZahl(i, w5) > 0 & anzZahl(i + 1, w5) > 0
					& anzZahl(i + 2, w5) > 0 & anzZahl(i + 3, w5) > 0)
				return true;
		}
		return false;
	}

	public boolean istViererpasch(int[] w5) {
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

	public boolean istDreierpasch(int[] w5) {
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

	public boolean istFullHouse(int[] w5) {
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

	public int Gesamtwert(int[] w5) {
		int punkte = 0;
		for (int i = 0; i < w5.length; i++) {
			punkte = punkte + w5[i];
		}
		return punkte;

	}

	public int anzZahl(int zahl, int[] w5) {// gibt die Anzahl einer Zahl der
		// WŸrfel zurŸck
		int anzZahl = 0;
		for (int j = 0; j < w5.length; j++) {
			if (zahl == w5[j])
				anzZahl++;
		}
		return anzZahl;
	}

	public boolean istObenVoll() {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 6; j++) {
				if (getBlock().getWert(i, j) == -1)
					return false;
			}

		}
		return true;
	}

}

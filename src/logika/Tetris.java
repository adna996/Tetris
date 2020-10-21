package logika;

import java.util.Random;

public class Tetris {
	public static final int DIREKCIJA_LIJEVO = 0;
	public static final int DIREKCIJA_DESNO = 1;
	public static final int DIREKCIJA_DOLE = 2;
	int[][] lonac;
	Figura[] figure;
	Figura[] sljedecaUNizu;
	Random rnd;
	int trenutnaPozicijaX, trenutnaPozicijaY;
	String tip;

	public Tetris() {
		this("classic", 10, 25, 1);
	}

	public Tetris(String tip, int sirina, int visina, int brojSljedecih) {
		// TREBA DORADITI
		this.tip = tip;
		rnd = new Random();
		lonac = new int[visina][sirina];
		figure = new Figura[3];
		for (int i = 0; i < figure.length; i++) {
			figure[i] = new Figura(tip, "" + (i + 1));
//			System.out.println("Figura " + i + "\n" + figure[i]);
		}
		sljedecaUNizu = new Figura[brojSljedecih];
		for (int i = 0; i < brojSljedecih; i++) {
			sljedecaUNizu[i] = new Figura(tip, "" + (1 + rnd.nextInt(figure.length)));
//			System.out.println("sljedecaUNizu " + i + "\n" + sljedecaUNizu[i]);
		}
		trenutnaPozicijaX = lonac.length;
		trenutnaPozicijaY = lonac[0].length / 2 - 1;
	}

	public String getTip() {
		return tip;
	}

	public int getSirina() {
		return lonac[0].length;
	}

	public int getVisina() {
		return lonac.length;
	}

	public int getBrojSljedecih() {
		return sljedecaUNizu.length;
	}

	public boolean kraj() {
		// TODO Auto-generated method stub
		return false;
	}

	public int[][] stanjeTabele() {
		int[][] tabela = new int[lonac.length][lonac[0].length];
		for (int i = 0; i < tabela.length; i++) {
			for (int j = 0; j < tabela[i].length; j++) {
				tabela[i][j] = lonac[i][j];
			}
		}
		for (int i = 0; i < sljedecaUNizu[0].getFigura()[0].length; i++) {
			for (int j = 0; j < sljedecaUNizu[0].getFigura().length; j++) {
//				System.out.println("Ovdje je trenutnaPozicijaX->" + trenutnaPozicijaX + ", trenutnaPozicijaY->" +
//						trenutnaPozicijaY + " i->" + i + " j->" + j + " tabela.length->" + tabela.length +
//						" tabela[0].length->" + tabela[0].length);
				if ((trenutnaPozicijaX + i >= 0) && (trenutnaPozicijaX + i < tabela.length) &&
						(trenutnaPozicijaY + j >= 0) && (trenutnaPozicijaY + j < tabela[0].length)) {
					tabela[trenutnaPozicijaX + i][trenutnaPozicijaY + j] += sljedecaUNizu[0].getFigura()[i][j];
				}
			}
		}
		return tabela;
	}

	public void pomjeriFiguru(int direkcija) {
		if (direkcija == DIREKCIJA_LIJEVO) {
			if (mozePomjeriti(direkcija)) {
				trenutnaPozicijaY -= 1;
			}
		} else {
			if (direkcija == DIREKCIJA_DESNO) {
				if (mozePomjeriti(direkcija)) {
					trenutnaPozicijaY += 1;
				}
			} else {
				if (mozePomjeriti(direkcija)) {
					trenutnaPozicijaX -= 1;
				}
			}
		}
	}

	private boolean mozePomjeriti(int direkcija) {
		int noviX = trenutnaPozicijaX, noviY = trenutnaPozicijaY;
		if (direkcija == DIREKCIJA_LIJEVO) {
			noviY--;
		} else {
			if (direkcija == DIREKCIJA_DESNO) {
				noviY++;
			} else {
				noviX--;
			}
		}
		for (int i = 0; i < sljedecaUNizu[0].getFigura()[0].length; i++) {
			for (int j = 0; j < sljedecaUNizu[0].getFigura().length; j++) {
				if ((noviX + i >= 0) && (noviX + i < lonac.length) &&
						(noviY + j >= 0) && (noviY + j < lonac[0].length)) {
//System.out.println("Ovdje je\n\tnoviX = " + noviX + "\n\tnoviY = " + noviY + "\n\ti =" + i + "\n\tj =" + j + "\n\tsljedecaUNizu =" + sljedecaUNizu[0].getFigura()[i][j] + "\n\tlonac =" + lonac[noviX + i][noviY + j]);
					if ((sljedecaUNizu[0].getFigura()[i][j] != 0) && (lonac[noviX + i][noviY + j] != 0)) {
						if (direkcija == DIREKCIJA_DOLE) {
							spasiStanje();
						}
						return false;
					}
				} else {
//System.out.println("Ovdje je\n\tnoviX = " + noviX + "\n\tnoviY = " + noviY + "\n\ti =" + i + "\n\tj =" + j + "\n\tsljedecaUNizu =" + sljedecaUNizu[0].getFigura()[i][j]);
					if (noviX + i < lonac.length) {
						if (sljedecaUNizu[0].getFigura()[i][j] != 0) {
							if (direkcija == DIREKCIJA_DOLE) {
								spasiStanje();
							}
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void spasiStanje() {
		for (int i = 0; i < sljedecaUNizu[0].getFigura()[0].length; i++) {
			for (int j = 0; j < sljedecaUNizu[0].getFigura().length; j++) {
				if ((trenutnaPozicijaX + i >= 0) && (trenutnaPozicijaX + i < lonac.length) &&
						(trenutnaPozicijaY + j >= 0) && (trenutnaPozicijaY + j < lonac[0].length)) {
					lonac[trenutnaPozicijaX + i][trenutnaPozicijaY + j] += sljedecaUNizu[0].getFigura()[i][j];
				}
			}
		}
		for (int i = 0; i < sljedecaUNizu.length - 1; i++) {
			sljedecaUNizu[i] = sljedecaUNizu[i + 1];
		}
		sljedecaUNizu[sljedecaUNizu.length - 1] = new Figura(tip, "" + (1 + rnd.nextInt(figure.length))); 
		trenutnaPozicijaX = lonac.length;
		trenutnaPozicijaY = lonac[0].length / 2 - 1;
	}
}

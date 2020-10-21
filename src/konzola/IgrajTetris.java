package konzola;

import logika.Figura;
import logika.Tetris;

public class IgrajTetris {

	public static void main(String[] args) {
		Tetris tet = new Tetris("classic", 10, 20, 1);

		


		for (int i = 0; i < 15; i++) {
			prikaziTabelu(tet);
			tet.pomjeriFiguru(Tetris.DIREKCIJA_DOLE);
			System.out.println("\n\n");
		}

		for (int i = 0; i < 5; i++) {
			prikaziTabelu(tet);
			tet.pomjeriFiguru(Tetris.DIREKCIJA_LIJEVO);
			System.out.println("\n\n");
		}

		for (int i = 0; i < 6; i++) {
			prikaziTabelu(tet);
			tet.pomjeriFiguru(Tetris.DIREKCIJA_DOLE);
			System.out.println("\n\n");
		}

		for (int i = 0; i < 7; i++) {
			prikaziTabelu(tet);
			tet.pomjeriFiguru(Tetris.DIREKCIJA_DESNO);
			System.out.println("\n\n");
		}

		for (int i = 0; i < 60; i++) {
			prikaziTabelu(tet);
			tet.pomjeriFiguru(Tetris.DIREKCIJA_DOLE);
			System.out.println("\n\n");
		}

		prikaziTabelu(tet);

/*
		while (!tet.kraj()) {
			
		}
*/
	}

	public static void prikaziTabelu(Tetris tet) {
		int[][] tabela = tet.stanjeTabele();
		for (int i = tabela.length - 1; i >= 0; i--) {
			for (int j = 0; j < tabela[0].length; j++) {
				System.out.print(tabela[i][j] == 0 ? "." : "" + tabela[i][j]);
			}
			System.out.println();
		}
	}
}

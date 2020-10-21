package logika;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Figura {
	private int[][] tabela;

	public int getDimension() {
		return tabela.length;
	}

	public int[][] getFigura() {
		return tabela;
	}

	public Figura() {
		super();
	}

	public Figura(String tip, String naziv) {
		super();
		try {
			Scanner sc = new Scanner(new File("tezina\\" + tip + "\\" + naziv + ".in"));
			int tmp = sc.nextInt();
			this.tabela = new int[tmp][tmp];
			for (int i = 0; i < tmp; i++) {
				for (int j = 0; j < tmp; j++) {
					this.tabela[i][j] = sc.nextInt();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Figura(int n) {
		super();
		this.tabela = new int[n][n];
	}

	public Figura rotiraj() {
		Figura fig = new Figura(this.tabela.length);
		for (int i = 0; i < this.tabela.length; i++) {
			for (int j = 0; j < this.tabela.length; j++) {
				fig.tabela[j][this.tabela.length - i - 1] = this.tabela[i][j];
			}
		}
		return fig;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "";
		for (int i = 0; i < this.tabela.length; i++) {
			for (int j = 0; j < this.tabela[i].length; j++) {
				str += this.tabela[i][j];
			}
			str += "\n";
		}
		return str;
	}
}

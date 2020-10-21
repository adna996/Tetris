package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import konzola.IgrajTetris;
import logika.Tetris;

public class TetrisPane extends JPanel {
	Tetris tet;
	final static char TETRIS_PANE_LIJEVO = '7';
	final static char TETRIS_PANE_DESNO = '9';
	final static char TETRIS_PANE_ROTIRAJ_LIJEVO = '8';
	final static char TETRIS_PANE_ROTIRAJ_DESNO = '5';
	final static char TETRIS_PANE_SPUSTI_JEDNU = '6';
	final static char TETRIS_PANE_SPUSTI_SKROZ = '4';
	
	public TetrisPane() {
		this(new Tetris());
	}

	public TetrisPane(Tetris tetris) {
		this.tet = tetris;
		String tip = tetris.getTip();
		int sirina = tetris.getSirina();
		int visina = tetris.getVisina();
		int brojSljedecih = tetris.getBrojSljedecih();
		this.setLayout(new GridLayout(visina, sirina));
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				JButton jb = new JButton();
				jb.setPreferredSize(new Dimension(25, 25));
				jb.addKeyListener(new MojKeyListener());
				jb.setBackground(mojaBoja(tet.stanjeTabele()[visina - 1 - i][j]));
				System.out.println("" + i + ", " + j + "\t" + tet.stanjeTabele()[visina - 1 - i][j]);
				this.add(jb);
			}
		}
	}

	private Color mojaBoja(int i) {
		if (i == 0)
			return Color.WHITE;
		if (i == 1)
			return Color.RED;
		if (i == 2)
			return Color.CYAN;
		if (i == 3)
			return Color.GREEN;
		return Color.BLACK;
	}

	/**
	 * Funkcija ponovo postavlja vrijednosti na polja za ispis stanja igre.
	 */
	public void osvjeziEkran() {
		for (int i = 0; i < tet.getVisina(); i++) {
			for (int j = 0; j < tet.getSirina(); j++) {
				((JButton)getComponent(i * tet.getSirina() + j)).setBackground(mojaBoja(tet.stanjeTabele()[tet.getVisina() - 1 - i][j]));
//				System.out.println("" + i + ", " + j + "\t" + tet.stanjeTabele()[i][j]);
			}
		}
	}

	class MojKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("Tipka pritisnuta");
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("Tipka otpustena");
		}

		@Override
		public void keyTyped(KeyEvent e) {
			System.out.println("Tipka otkucana");
			System.out.println(e.getKeyChar() + "\t" + e.getKeyCode());
			if (e.getKeyChar() == TETRIS_PANE_LIJEVO) {
				tet.pomjeriFiguru(Tetris.DIREKCIJA_LIJEVO);
			} else {
				if(e.getKeyChar() == TETRIS_PANE_DESNO) {
					tet.pomjeriFiguru(Tetris.DIREKCIJA_DESNO);
				} else {
					if (e.getKeyChar() == TETRIS_PANE_SPUSTI_SKROZ) {
						tet.pomjeriFiguru(Tetris.DIREKCIJA_DOLE);
					}
				}
			}
			System.out.println("prije osvjezi ekran");
			konzola.IgrajTetris.prikaziTabelu(tet);
			osvjeziEkran();
			System.out.println("poslije osvjezi ekran");
			konzola.IgrajTetris.prikaziTabelu(tet);
		}
		
	}
}

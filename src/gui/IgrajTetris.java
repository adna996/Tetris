package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import logika.Tetris;

public class IgrajTetris extends JFrame{

	public static void main(String[] args) {
		IgrajTetris it = new IgrajTetris();
		it.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		it.getContentPane().setLayout(new BorderLayout());
		Tetris tetris = new Tetris();
		it.getContentPane().add(new TetrisPane(tetris), BorderLayout.CENTER);
		it.pack();
		it.setVisible(true);
	}

}

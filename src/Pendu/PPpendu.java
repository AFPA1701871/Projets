package Pendu;

import java.awt.*; // Frame
import Potence.*; // class Pendu, FermerFenetre

class PPPendu extends Frame {
	// Programme Principal Pendu
	private static final long serialVersionUID = 1L;
	PPPendu() {
		setTitle("Jeu du pendu");
		setBounds(10, 10, 340, 450);
		addWindowListener(new FermerFenetre()); 
		add(new Pendu(), "Center");
		setVisible(true);
	}

	public static void main(String[] args) {
		new PPPendu();
	}
} // PPPendu

package Potence;

//PPDessinPotence.java
//Programme Principal de Dessin
// des états de la Potence

import java.awt.*; // Frame

//Programme Principal de Dessin des états de la Potence

class PPDessinPotence extends Frame { // hérite de Frame
	private static final long serialVersionUID = 1L;
	PPDessinPotence() {
		// les caractéristiques de la fenêtre principale
		setTitle("Les " + Potence.maxEtat + " états de la potence");
		setBackground(Color.lightGray); // défaut : blanc
		setBounds(10, 10, 400, 550); // défaut : petite taille
		// mise en page par colonnes de 3; 10 pixels entre composants
		setLayout(new GridLayout(0, 3, 10, 10));
		// un tableau d’objets Potence
		Potence[] dessin = new Potence[Potence.maxEtat]; // de 0 à 8
		for (int i = 0; i < dessin.length; i++) {

			dessin[i] = new Potence();
			dessin[i].setBackground(Color.cyan);
			dessin[i].setForeground(Color.red);
			dessin[i].setEtat(i);
			add(dessin[i]);
		}
		addWindowListener(new FermerFenetre()); // voir page 221
		setVisible(true); // défaut : invisible
	} // constructeur PPDessinPotence

	/*public static void main(String[] arg) {
		new PPDessinPotence();
	} */// main
} // class PPDessinPotence
package Potence;

import java.awt.*;

public class Potence extends Component {
	private static final long serialVersionUID = 1L;
	public static final int maxEtat = 9; // de 0 à 8 inclus
	private int etat = 0; // état de la construction
	private boolean trouve = false; // afficher le message trouvé
	Dimension di; // pour les méthodes l() et h()

	public Potence() {
		etat = 0;
		trouve = false;
	}

	// réinitaliser l’état de la Potence
	public void init() {
		etat = 0;
		trouve = false;
		repaint();
	}

	// positionner l’état de la Potence
	public void setEtat(int etat) {
		if (etat >= 0 && etat < maxEtat)
			this.etat = etat;
		repaint();
	}

	// donner l’état de la Potence
	public int getEtat() {
		return etat;
	}

	// positionner trouve de la Potence à vrai
	public void setTrouve() {
		this.trouve = true;
		repaint();
	}

	// incrémenter l’état de la Potence
	public void incEtat() {
		if (etat + 1 < maxEtat) {
			etat++;
			repaint();
		}
	}

	// utile pour FlowLayout et BorderLayout
	public Dimension getPreferredSize() {
		return new Dimension(140, 160); // voir page 149
	}

	// mise à l’échelle en largeur de v
	int l(int v) {
		double k = Math.min(di.width / 140., di.height / 160.);
		return (int) (v * k);
		// return v*di.width/140; // non proportionnelle
	}

	// mise à l’échelle en hauteur de v
	int h(int v) {
		double k = Math.min(di.width / 140., di.height / 160.);
		return (int) (v * k);
		// return v*di.height/160; // non proportionnelle
	}

	// le dessin de la Potence
	public void paint(Graphics g) {
		// le dessin s’adapte à l’espace attribué
		di = getSize(); // de Component
		g.clearRect(0, 0, di.width - 1, di.height - 1); // effacer
		g.drawRect(0, 0, di.width - 1, di.height - 1); // tracer le cadre
		// s’adapte à l’espace du composant
		int taille = 12 * (di.width / 120);
		if (taille < 8)
			taille = 8;
		g.setFont(new Font("TimesRoman", Font.PLAIN, taille));
		if (etat >= 1)
			g.drawLine(l(30), h(120), l(90), h(120));
		if (etat >= 2)
			g.drawLine(l(30), h(120), l(30), h(40));
		if (etat >= 3)
			g.drawLine(l(60), h(120), l(30), h(90));
		if (etat >= 4)
			g.drawLine(l(30), h(40), l(80), h(40));
		if (etat >= 5)
			g.drawLine(l(30), h(60), l(50), h(40));
		if (etat >= 6)
			g.drawLine(l(70), h(40), l(70), h(60));
		if (etat >= 7)
			g.drawOval(l(65), h(60), l(10), h(10)); // tête
		if (etat >= 8) {
			g.drawLine(l(70), h(70), l(70), h(85)); // le corps
			g.drawLine(l(70), h(70), l(65), h(75));
			g.drawLine(l(70), h(70), l(75), h(75));
			g.drawLine(l(70), h(85), l(65), h(95));
			g.drawLine(l(70), h(85), l(75), h(95));
		}
		if (trouve) {
			g.drawString("Bravo, vous avez trouvé", l(10), h(150));
		} else if (etat == 8) {
			g.drawString("Vous êtes pendu !!! ", l(10), h(150));
		} else if (etat == 7) {
			g.drawString("Reste 1 coup à jouer", l(10), h(150));
		} else { // etat >= 0 et < 7
			g.drawString("Reste " + (8 - etat) + " coups à jouer", l(10), h(150));
		}
	} // paint
} // Potence

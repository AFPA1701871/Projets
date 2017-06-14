package Pendu;

import java.awt.Button;
import java.awt.*;
import java.awt.event.*;
import java.awt.TextField;

import Potence.Potence;
import Utile.Utile;

public class Pendu extends Panel { // composant Pendu
	private static final long serialVersionUID = 1L;
	// initialisation directe du tableau tabMots
	private String[] tabMots = { "beaucoup", "baudruche", "colibri", "coing", "couenne", "dodeliner", "dompter",
			"doigt", "fat", "foyer", "gencive", "gel", "grandiloquent", "hameçon", "immonde", "insulaire", "obséquieux",
			"leader", "maestro", "minaret", "morille", "navet", "ouistiti", "parjure", "pharynx", "poinçon", "presbyte",
			"quintuple", "ravitaillement", "reptile", "république", "somptueux", "surexcité", "trémolo", "vouvoyer",
			"anticonstitutionnellement" };
	int nbMots = tabMots.length;
	String mot; // mot courant à découvrir
	StringBuffer motEnCours; // les caractères trouvés du mot en cours
	TextField tF1 = new TextField(""); // mot en cours
	TextField tF2 = new TextField(""); // le caractère proposé
	Potence potence = new Potence();
	Button bRejouer = new Button("Rejouer");

	// cbPendu : color back Pendu, color back Potence et front Potence
	public Pendu(Color cbPendu, Color cbPotence, Color cfPotence) {
		setLayout(new BorderLayout()); // defaut : FlowLayout
		setBackground(cbPendu);
		Panel pnord = new Panel();
		pnord.setLayout(new GridLayout(2, 2));
		add(pnord, "North");
		Label l1 = new Label("Mot à trouver ? ");
		pnord.add(l1);
		tF1.setBackground(cbPotence);
		tF1.setEditable(false);
		tF1.setFocusable(false);
		pnord.add(tF1);
		Label l2 = new Label("Caractère proposé ? ");
		pnord.add(l2);
		tF2.setBackground(cbPotence);
		pnord.add(tF2);
		potence.setBackground(cbPotence);
		potence.setForeground(cfPotence);
		add(potence, "Center");
		add(bRejouer, "South");
		Font f = new Font("TimesRoman", Font.BOLD, 16);
		tF1.setFont(f);
		tF2.setFont(f);
		setVisible(true);
		initJouer(); // initialisation et choix du premier mot
		traitement();
	} // constructeur Pendu

	public Pendu() {
		this(Color.blue, Color.pink, Color.red);
	}

	// chargement du tableau des mots à découvrir
	public void setTableMots(String[] tabMots) {
		if (tabMots != null) {
			this.tabMots = tabMots;
			initJouer();
		}
	}

	// À faire à chaque jeu : (ré)initialiser les données
	private void initJouer() {
		potence.init();
		int nAleat = Utile.aleat(tabMots.length);

		mot = tabMots[nAleat];
		motEnCours = new StringBuffer("");
		for (int i = 0; i < mot.length(); i++)
			motEnCours.append("*");
		tF1.setText(new String(motEnCours));
		tF2.setEditable(true); // mis à faux si pendu
		tF2.setText("");
		tF2.requestFocus(); // curseur dans tF2
	} // initJouer
		
	
	// TRAITEMENT DES EVENEMENTS
		// on déclare les composants à prendre en compte (écouter)
	void traitement() {
		tF2.addActionListener(new CaracterePropose());
		bRejouer.addActionListener(new ActionRejouer());
	}

	// Classes internes à la classe Pendu

	// un caractère proposé
	class CaracterePropose implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			String s = tF2.getText();
			if (s.length() == 0)
				return; // sinon exception
			char car = s.charAt(0);
			// car existe-t-il dans le mot à trouver ?
			boolean trouve = false;
			for (int i = 0; i < mot.length(); i++) {
				if (mot.charAt(i) == car) {
					motEnCours.setCharAt(i, car);
					trouve = true;
				}
			}
			if (!trouve) { // pas trouvé car
				potence.incEtat();
				if (potence.getEtat() == Potence.maxEtat - 1)
					tF2.setEditable(false);
			} else if (mot.equals(new String(motEnCours))) {
				potence.setTrouve();
				tF2.setEditable(false);
			}
			tF1.setText(new String(motEnCours));
			tF2.setText("");
			tF2.requestFocus();
		}
	} // caracterePropose
		
	// pour le bouton Rejouer
	class ActionRejouer implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			initJouer();
		}
	}
} // class Pendu

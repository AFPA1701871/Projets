package Potence;

import java.awt.event.*; // WindowAdapter
// pour fermer la fenêtre ou l’application

public class FermerFenetre extends WindowAdapter {
	
	public void windowClosing(WindowEvent evt) {
		// System.out.println (evt.getWindow().getName());
		if (evt.getWindow().getName().equals("frame0")) {
			System.exit(0); // fermer l’application
		} else {
			evt.getWindow().dispose(); // fermer la fenêtre
		}
	}

}

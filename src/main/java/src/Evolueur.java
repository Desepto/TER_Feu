package src;

import java.util.ArrayList;

/**
 * Classe principale du bouzin, contient la méthode main, crée tous les trucs et
 * s'occupe de faire avancer le temps. Elle sera p'tet découpée en 2 (une partie
 * main, une partie evolueur)
 * 
 * @author Thomas
 * 
 */

public class Evolueur {

	protected Carte c;
	protected Gestionnaire g;
	protected int tic = 0;

	public Evolueur(int tailleCarte) {

		this.c = new Carte(tailleCarte);
		this.c = Lecteur.carteEnDur(); // Création temporaire
		this.g = new Gestionnaire();

		// Les acteurs sont déjà mis dans la carte ou bien c'est à moi de les
		// rajouter depuis le gestionnaire ? La première solution a l'air plus
		// intelligente

		// Ecrivain.ecritureTete(tailleCarte); OU QUELQUE CHOSE DU GENRE
	}

	public void deroulement() {

		// ICI IL FAUDRA CHANGER, METTRE UN GETTER QUI RENVOIE JUSTE LE
		// FEU/PLUIE/POMPIER/CANADAIR
		while (tic < 15 || this.presenceFeu()) {
			for (Acteur a : this.c.getSesActeurs())
				if (a instanceof Feu)
					a.agi(this.c);

			// RAJOUTER UNE METHODE POUR RECUPERER TOUTE L'ARRAYLIST

			// for (Acteur a : this.g.getUnEvent(tic))) {
			// this.c.ajoutActeur(a);
			// }
			// Ecrivain.ecrireJeSaisPasQuoi();
		}
		// Ecrivain.ecritureFinale
	}

	// Il serait intelligent de mettre cette méthode dans Carte
	public boolean presenceFeu() {
		ArrayList<Acteur> a = c.getSesActeurs();
		for (int i = 0; i < (c.getSesActeurs()).size(); i++) {
			if (a.get(i) instanceof Feu) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {

		int tailleTemporaire = 5;
		Evolueur e = new Evolueur(tailleTemporaire);
		e.deroulement();

	}

}

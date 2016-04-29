package main;

import acteurs.Acteur;
import acteurs.Feu;
import entreesSorties.Ecrivain;
import entreesSorties.Lecteur;

/**
 * Classe principale du bouzin, contient la m�thode main, cr�e tous les trucs et
 * s'occupe de faire avancer le temps. Elle sera p'tet d�coup�e en 2 (une partie
 * main, une partie evolueur)
 * 
 * @author Thomas
 * 
 */

public class Evolueur {

	protected Carte c;
	protected Gestionnaire g;
	protected Ecrivain e;
	protected int tic = 0;

	public Evolueur(int tailleCarte, String nomFichier) {

		this.c = new Carte(tailleCarte);
		this.c = Lecteur.carteEnDur(); // Cr�ation temporaire
		this.g = new Gestionnaire();
		this.e = new Ecrivain(nomFichier);
		e.initFichier(this.c);
		// Ecrivain.ecritureTete(tailleCarte); OU QUELQUE CHOSE DU GENRE
	}

	public void deroulement(String nomFichier) {

		// ICI IL FAUDRA CHANGER, METTRE UN GETTER QUI RENVOIE JUSTE LE
		// FEU/PLUIE/POMPIER/CANADAIR
		while (this.tic < 15 || this.presenceFeu()) {
			// vider la liste des modifi�s
			for (Acteur a : this.c.getFeu())
				a.agi(this.c);

			// RAJOUTER UNE METHODE POUR RECUPERER TOUTE L'ARRAYLIST

			for (Acteur a : this.g.getUnEvent(this.tic).getActeurs()) {
				this.c.ajoutActeur(a);
			}
			this.e.printChangement(this.c, this.tic);
			tic++;
		}
		this.e.printFin();
	}

	// Il serait intelligent de mettre cette m�thode dans Carte
	public boolean presenceFeu() {
		for (Acteur a : this.c.getSesActeurs())
			if (a instanceof Feu)
				return true;
		return false;
	}

	public static void main(String[] args) {

		int tailleTemporaire = 5;
		Evolueur e = new Evolueur(tailleTemporaire, args[0]);
		e.deroulement(args[0]);

	}
}

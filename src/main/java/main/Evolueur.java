package main;

import java.util.ArrayList;

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

	public Evolueur(int tailleCarte, String fichierEntree, String fichierSortie) {

		this.c = new Carte(tailleCarte);
		this.c = Lecteur.carteEnDur(); // Cr�ation temporaire
		this.g = new Gestionnaire();
		this.e = new Ecrivain(fichierSortie);
		e.initFichier(this.c);

		// test tmtc
		ArrayList<Acteur> list = new ArrayList<Acteur>();
		// list.add(new Feu(3, 3));
		list.add(new Feu(0, 0));
		// list.add(new Feu(6, 6));
		// list.add(new Feu(9, 9));
		Evenement test = new Evenement(list);
		g.ajoutEvenement(test);
	}

	public void deroulement(String nomFichier) {

		// ICI IL FAUDRA CHANGER, METTRE UN GETTER QUI RENVOIE JUSTE LE
		// FEU/PLUIE/POMPIER/CANADAIR
		while (this.tic < 15 || this.presenceFeu()) {
			this.c.nettoieModifications();

			for (Acteur a : this.c.getFeu())
				a.agi(this.c);

			try {
				for (Acteur a : this.g.getUnEvent(this.tic).getActeurs()) {
					this.c.ajoutActeur(a);
				}
			} catch (IndexOutOfBoundsException e) {
			}

			this.e.printChangement(this.c, this.tic);
			tic++;
		}
		this.e.printFin(this.c);
	}

	// Il serait intelligent de mettre cette m�thode dans Carte
	public boolean presenceFeu() {
		for (Acteur a : this.c.getSesActeurs())
			if (a instanceof Feu) {
				System.out.println(this.c.getSesActeurs().size() + " x :"
						+ this.c.getSesActeurs().get(0).getX() + " y :"
						+ this.c.getSesActeurs().get(0).getY());
				return true;
			}

		// System.out.println(this.c.getSesActeurs().toString());
		System.out.println(" TA MAMAN : " + this.c.getSesActeurs().toString());
		return false;
	}

	public static void main(String[] args) {

		int tailleTemporaire = 5;
		Evolueur e = new Evolueur(tailleTemporaire, args[0], args[1]);
		e.deroulement(args[0]);

	}
}

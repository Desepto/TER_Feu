package main;

import java.awt.Point;

import pathfinding.PlusCourtChemin;
import acteurs.Acteur;
import acteurs.Canadair;
import acteurs.Feu;
import entreesSorties.Ecrivain;
import entreesSorties.Lecteur;

/**
 * Classe principale du programme, contient la méthode main, crée tous les trucs
 * et s'occupe de faire avancer le temps.
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

		this.g = new Gestionnaire();
		this.c = Lecteur.creemapFichier("Aveclesrochers", g);
		this.e = new Ecrivain(fichierSortie);
		e.initFichier(this.c);

	}

	public void deroulement(String nomFichier) {

		// Boucle générale du programme
		while (this.tic < 15 || this.presenceFeu()) {
			// Remise à zéro de la liste des cases modifiées
			this.c.nettoieModifications();

			// On fait agir tous les acteurs
			for (Acteur a : this.c.getFeu())
				a.agi(this.c);

			for (Acteur a : this.c.getPompier()) {
				if (this.c.presenceFeu(a.getX(), a.getY()))
					a.agi(this.c);
				else
					a.setActeur(PlusCourtChemin.deplacement(this.c, a), this.c);
			}
			for (Acteur a : this.c.getCanadair()) {
				// Si le canadair est plein et sur une zone de feu ou s'il est
				// vide et sur un lac, il agit
				if (((Canadair) a).agi(this.c, 0))
					// si le canadair n'a pas agi il se déplace
					a.setActeur(PlusCourtChemin.deplacement(this.c, a), this.c);
			}
			for (Acteur a : this.c.getPluie())
				a.agi(this.c);

			try {
				// Gestion des evennements (arrivées des nouveaux acteurs)
				Evenement e = new Evenement();
				e = g.getUnEvent(tic);
				if (e != null) {
					for (Acteur a : e.getActeurs()) {
						this.c.ajoutActeur(a);
						Point p = new Point(a.getX(), a.getY());
						this.c.getModifications().add(p);
					}
				}

			} catch (IndexOutOfBoundsException e) {
			}
			this.e.printChangement(this.c, this.tic);
			tic++;
		}
		this.e.printFin(this.c);
	}

	/**
	 * Méthode indiquant s'il y a du feu dans la carte. Devrait être dans carte
	 * mais est gardée ici pour des soucis de rétro-compatibilité
	 * 
	 * @return
	 */
	public boolean presenceFeu() {
		for (Acteur a : this.c.getSesActeurs())
			if (a instanceof Feu)
				return true;
		return false;
	}

	/**
	 * Fonction main, crée l'évolueur à partir du fichier d'entrée et envoit les
	 * résultats dans le fichier de sortie, respectivement args[0] et args[1]
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int tailleTemporaire = 5;
		Evolueur e = new Evolueur(tailleTemporaire, args[0], args[1]);
		e.deroulement(args[0]);

	}
}

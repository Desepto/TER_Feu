package main;

import java.awt.Point;

import acteurs.Acteur;
import acteurs.Canadair;
import acteurs.Feu;
import entreesSorties.Ecrivain;
import entreesSorties.Lecteur;
import pathfinding.PlusCourtChemin;

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

		this.g = new Gestionnaire();
		this.c = Lecteur.creemapFichier("Aveclesrochers", g);
		// Ecrivain e2 = new Ecrivain("Scenario1");
		// e2.ecrireMap(c);
		this.e = new Ecrivain(fichierSortie);
		e.initFichier(this.c);

	}

	public void deroulement(String nomFichier) {

		// ICI IL FAUDRA CHANGER, METTRE UN GETTER QUI RENVOIE JUSTE LE
		// FEU/PLUIE/POMPIER/CANADAIR
		while (this.tic < 15 || this.presenceFeu()) {
			this.c.nettoieModifications();

			for (Acteur a : this.c.getFeu())
				a.agi(this.c);
			// System.out.println(this.c.getModifications().size());

			for (Acteur a : this.c.getPompier()) {
				if (this.c.presenceFeu(a.getX(), a.getY()))
					a.agi(this.c);
				else
					a.setActeur(PlusCourtChemin.deplacement(this.c, a), this.c);
			}
			for (Acteur a : this.c.getCanadair()) {
				// Si le canadair est plein et sur une zone de feu ou s'il est
				// vide et sur un lac, il agit
				// if ((this.c.presenceFeu(a.getX(), a.getY()) && ((Canadair) a)
				// .isEstCharge())
				// || (this.c.getTabHexagones(a.getX(), a.getY()) instanceof Lac
				// && !((Canadair) a)
				// .isEstCharge()))
				if (((Canadair) a).agi(this.c, 0)) // si le canadair n'a pas
													// agit il se
					// déplace
					a.setActeur(PlusCourtChemin.deplacement(this.c, a), this.c);
			}
			for (Acteur a : this.c.getPluie())
				a.agi(this.c);

			try {
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

	// Il serait intelligent de mettre cette méthode dans Carte
	public boolean presenceFeu() {
		for (Acteur a : this.c.getSesActeurs())
			if (a instanceof Feu) {
				/*
				 *
				 * System.out.println(this.c.getSesActeurs().size() + " x :" +
				 * this.c.getSesActeurs().get(0).getX() + " y :" +
				 * this.c.getSesActeurs().get(0).getY());
				 */
				return true;
			}

		// System.out.println(this.c.getSesActeurs().toString());
		// System.out.println(" TA MAMAN : " +
		// this.c.getSesActeurs().toString());
		return false;
	}

	public static void main(String[] args) {

		int tailleTemporaire = 5;
		Evolueur e = new Evolueur(tailleTemporaire, args[0], args[1]);
		e.deroulement(args[0]);

	}
}

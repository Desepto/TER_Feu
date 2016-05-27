package main;

import java.util.ArrayList;

import acteurs.Acteur;

/**
 * Un ensemble d'acteurs qui vont apparaitre sur la carte avec leurs coordonnées
 * respectives.
 * 
 * @author Nicolas
 * 
 */
public class Evenement {

	private ArrayList<Acteur> acteurs;

	/**
	 * Déclencher un évènement, c'est ajouter tous les acteurs de cet évènement
	 * à ceux de la Carte.
	 * 
	 * @param maCarte
	 * @return maCarte
	 */
	public Carte declenche(Carte maCarte) {

		for (Acteur acteurCourant : acteurs) {
			maCarte.ajoutActeur(acteurCourant);
		}
		return maCarte;
	}

	/**
	 * 
	 * @param acteurs
	 */

	public Evenement() {
		this.acteurs = new ArrayList<Acteur>();
	}

	public Evenement(ArrayList<Acteur> acteurs) {
		this.acteurs = acteurs;
	}

	public void ajouterActeur(Acteur a) {
		acteurs.add(a);
	}

	public ArrayList<Acteur> getActeurs() {
		return acteurs;
	}

}

package src;

import java.util.ArrayList;

/**
 * Classe mère de tous les types de terrains
 *
 * @author Thomas
 *
 */

public abstract class Terrain {

	protected String nom; // Complètement inutile. Permet de faire marcher mes
							// tests. Vous
	// pouvez vous en servir si besoin.

	protected ArrayList<Acteur> sesActeurs;

	public ArrayList<Acteur> getSesActeurs() {
		return sesActeurs;
	}

	public Terrain() {
		sesActeurs = new ArrayList<Acteur>();
	}

	public void ajoutActeur(Acteur nouveau) {
		if (!sesActeurs.contains(nouveau))
			sesActeurs.add(nouveau);
	}

	public void supprActeur(Acteur ancien) {
		sesActeurs.remove(ancien);
	}

	public String getNom() {
		return nom;
	}
}

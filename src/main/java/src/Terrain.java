package src;

import java.util.ArrayList;

/**
 * Classe mère de tous les types de terrains
 *
 * @author Nicolas
 *
 */

public abstract class Terrain {

	protected final boolean inflammable;
	protected NiveauDensite densite;
	protected ArrayList<Acteur> sesActeurs;

	// El Constrouctor
	public Terrain(boolean inflammable) {
		this.inflammable = inflammable;
		sesActeurs = new ArrayList<Acteur>();
	}

	public NiveauDensite getDensite() {
		return densite;
	}

	public final boolean isInflammable() {
		return inflammable;
	}

	public void setDensite(NiveauDensite densite) {
		this.densite = densite;
	}

	public ArrayList<Acteur> getSesActeurs() {
		return sesActeurs;
	}

	public void ajoutActeur(Acteur nouveau) {
		if (!sesActeurs.contains(nouveau))
			sesActeurs.add(nouveau);
	}

	public void supprActeur(Acteur ancien) {
		sesActeurs.remove(ancien);
	}

}

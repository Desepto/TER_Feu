package src;

/**
 * Classe mère de tous les types de terrains
 *
 * @author Thomas
 *
 */

public abstract class Terrain {

	protected int identifiant;
	// Identifiant du terrain. Ils seront numérotés de 1 à 930 selon leur
	// placement.

	public int getIdentifiant() {
		return identifiant;
	}

	String nature;

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

}

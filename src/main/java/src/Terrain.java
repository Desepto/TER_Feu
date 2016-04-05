package src;

/**
 * Classe mère de tous les types de terrains.
 *
 * @author Nicolas
 *
 */

public abstract class Terrain {

	protected final boolean inflammable;
	protected NiveauDensite densite;
	protected int humidite = 0;

	// El Constrouctor
	public Terrain(boolean inflammable) {
		this.inflammable = inflammable;
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

}

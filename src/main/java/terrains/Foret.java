package terrains;

import enumerations.NiveauDensite;

/**
 * Une forêt !
 *
 * @author Nicolas
 *
 */
public class Foret extends Terrain {

	private final int duree = 6;

	/**
	 * Constructeur par défaut à modifier.
	 */
	public Foret() {
		super(true); // On rend la foret inflammable.
		this.densite = NiveauDensite.un;
		this.setTrans(5);
		this.PV = duree;
	}

	/**
	 * El Constrouctor !
	 *
	 * @param maDensite
	 */
	public Foret(NiveauDensite maDensite) {
		super(true);
		this.densite = maDensite;
		this.setTrans(5);
		if (densite == NiveauDensite.un)
			this.PV = duree;
		else
			this.PV = duree * 2;
	}
}

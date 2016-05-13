package terrains;

import enumerations.NiveauDensite;

/**
 * Une plaine !
 * 
 * @author Nicolas
 * 
 */
public class Plaine extends Terrain {

	private final int duree = 5;

	/**
	 * Constructeur par défaut à modifier.
	 */
	public Plaine() {
		super(true); // On rend la plaine non inflammable.
		this.densite = NiveauDensite.un;
		this.PV = duree;
		this.setTrans(5);
		this.coutDeplacement = 10;
	}

	/**
	 * El Constrouctor !
	 * 
	 * @param maDuree
	 */
	public Plaine(NiveauDensite maDensite) {
		super(true);
		this.densite = maDensite;
		this.setTrans(5);
		if (densite == NiveauDensite.un)
			this.PV = duree;
		else
			this.PV = duree * 2;
		this.coutDeplacement = 10;
	}
}

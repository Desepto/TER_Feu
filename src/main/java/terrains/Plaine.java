package terrains;

import enumerations.NiveauDensite;

/**
 * Une plaine !
 * 
 * @author Nicolas
 * 
 */
public class Plaine extends Terrain {

	private final int duree = 10;

	/**
	 * Constructeur par défaut
	 */
	public Plaine() {
		super(true); // On rend la plaine non inflammable.
		this.densite = NiveauDensite.un;
		this.PV = duree;
		this.setTrans(3);
		this.coutDeplacement = 10;
	}

	/**
	 * Constructeur prenant en compte la densité
	 * 
	 * @param maDuree
	 */
	public Plaine(NiveauDensite maDensite) {
		super(true);
		this.densite = maDensite;
		this.setTrans(3);
		if (densite == NiveauDensite.un)
			this.PV = duree;
		else
			this.PV = duree * 2;
		this.coutDeplacement = 10;
	}
}

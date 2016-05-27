package terrains;

import enumerations.NiveauDensite;

public class Prairie extends Terrain {

	private final int duree = 8;

	/**
	 * Constructeur par défaut
	 */
	public Prairie() {
		super(true); // On rend la prairie non inflammable.
		this.densite = NiveauDensite.un;
		this.setTrans(8);
		this.PV = duree;
		this.coutDeplacement = 10;

	}

	/**
	 * Constructeur prenant en compte la densité
	 * 
	 * @param maDuree
	 */
	public Prairie(NiveauDensite maDensite) {
		super(true);
		this.setTrans(8);
		this.densite = maDensite;
		if (densite == NiveauDensite.un)
			this.PV = duree;
		else
			this.PV = duree * 2;
		this.coutDeplacement = 10;
	}
}

package terrains;

import enumerations.NiveauDensite;

/**
 * Une maison !
 *
 * @author Nicolas
 *
 */
public class Maison extends Terrain {

	private final int duree = 10;

	/**
	 * Constructeur par défaut à modifier.
	 */
	public Maison() {
		super(true); // On rend la maison inflammable.
		densite = NiveauDensite.un;
		this.setTrans(8);
		this.PV = duree;
		this.coutDeplacement = 10;
	}

}

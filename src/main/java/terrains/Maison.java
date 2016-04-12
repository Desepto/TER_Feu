package terrains;

import enumerations.NiveauDensite;

/**
 * Une maison !
 *
 * @author Nicolas
 *
 */
public class Maison extends Terrain {

	private final int duree = 5;

	/**
	 * Constructeur par défaut à modifier.
	 */
	public Maison() {
		super(true); // On rend la maison inflammable.
		densite = NiveauDensite.un;
		this.setTrans(5);
		this.PV = duree;
	}

}

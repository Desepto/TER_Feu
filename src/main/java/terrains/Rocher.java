package terrains;

import enumerations.NiveauDensite;

/**
 * Un caillou !
 *
 * @author Nicolas
 *
 */
public class Rocher extends Terrain {

	/**
	 * Constructeur par défaut à modifier.
	 */
	public Rocher() {
		super(false); // On rend le rocher non inflammable.
		this.densite = NiveauDensite.un;
	}

}

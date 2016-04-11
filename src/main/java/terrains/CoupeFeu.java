package terrains;

import enumerations.NiveauDensite;

/**
 * Un coupe feu !
 *
 * @author Nicolas
 *
 */
public class CoupeFeu extends Terrain {
	/**
	 * Constructeur par défaut à modifier.
	 */
	public CoupeFeu() {
		// Coupe feu non inflammable, pratique pour un coupe Feu, HERR GENERAL.
		super(false);
		densite = NiveauDensite.un;
	}

}

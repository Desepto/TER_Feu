package terrains;

import enumerations.NiveauDensite;

/**
 * Un coupe-feu !
 * 
 * @author Nicolas
 * 
 */
public class CoupeFeu extends Terrain {
	/**
	 * Constructeur par défaut
	 */
	public CoupeFeu() {
		// Coupe feu non inflammable, pratique pour un coupe Feu !
		super(false);
		densite = NiveauDensite.un;
		this.coutDeplacement = 12;
	}

}

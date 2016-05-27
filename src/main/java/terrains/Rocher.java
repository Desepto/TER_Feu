package terrains;

import enumerations.NiveauDensite;

/**
 * Un rocher !
 * 
 * @author Nicolas
 * 
 */
public class Rocher extends Terrain {

	/**
	 * Constructeur par défaut
	 */
	public Rocher() {
		super(false); // On rend le rocher non inflammable.
		this.densite = NiveauDensite.un;
		this.traversable = false;
		this.coutDeplacement = -1;
	}

}

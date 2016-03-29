package src;

/**
 * Un lac !
 *
 * @author Nicolas
 *
 */
public class Lac extends Terrain {

	/**
	 * Constructeur par défaut à modifier.
	 */
	public Lac() {
		super(false); // On rend le lac non inflammable.
		densite = NiveauDensite.un;
	}

}

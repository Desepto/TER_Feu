package src;

/**
 * Une route !
 *
 * @author Nicolas
 *
 */
public class Route extends Terrain {

	/**
	 * Constructeur par défaut à modifier.
	 */
	public Route() {
		super(false); // On rend la route non inflammable.
		this.densite = NiveauDensite.un;
	}

}

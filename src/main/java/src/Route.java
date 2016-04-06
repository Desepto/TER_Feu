package src;

/**
 * Une route !
 *
 * @author Nicolas
 *
 */
public class Route extends Terrain {

	private final int duree = 4;

	/**
	 * Constructeur par défaut à modifier.
	 */
	public Route() {
		super(true); // On rend la route inflammable.
		this.densite = NiveauDensite.un;
		PV = duree;

	}

}

package src;

/**
 * Une maison !
 *
 * @author Nicolas
 *
 */
public class Maison extends Terrain {

	protected Duree duree;
	protected Transmission trans;

	/**
	 * Constructeur par défaut à modifier.
	 */
	public Maison() {
		super(true); // On rend la maison inflammable.
		duree = Duree.moyenne;
		trans = Transmission.moyenne;
		densite = NiveauDensite.un;
	}

}

package src;

/**
 * Une forêt !
 *
 * @author Nicolas
 *
 */
public class Foret extends Terrain {

	protected Duree duree;
	protected Transmission trans;

	/**
	 * Constructeur par défaut à modifier.
	 */
	public Foret() {
		super(true); // On rend la foret inflammable.
		duree = Duree.haute;
		trans = Transmission.moyenne;
		this.densite = NiveauDensite.un;
	}

	/**
	 * El Constrouctor !
	 *
	 * @param maDensite
	 */
	public Foret(NiveauDensite maDensite) {
		super(true);
		duree = Duree.haute;
		trans = Transmission.moyenne;
		this.densite = maDensite;
	}
}

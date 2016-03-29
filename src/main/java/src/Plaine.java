package src;

/**
 * Une plaine !
 *
 * @author Nicolas
 *
 */
public class Plaine extends Terrain {

	protected Duree duree;
	protected Transmission trans;

	/**
	 * Constructeur par défaut à modifier.
	 */
	public Plaine() {
		super(true); // On rend la plaine non inflammable.
		duree = Duree.moyenne;
		trans = Transmission.moyenne;
		this.densite = NiveauDensite.un;
	}

	/**
	 * El Constrouctor !
	 *
	 * @param maDuree
	 */
	public Plaine(NiveauDensite maDensite) {
		super(true);
		duree = Duree.moyenne;
		trans = Transmission.moyenne;
		this.densite = maDensite;
	}
}

package src;

public class Prairie extends Terrain {

	protected Duree duree;
	protected Transmission trans;

	/**
	 * Constructeur par défaut à modifier.
	 */
	public Prairie() {
		super(true); // On rend la prairie non inflammable.
		duree = Duree.basse;
		trans = Transmission.haute;
		this.densite = NiveauDensite.un;
	}

	/**
	 * El Constrouctor !
	 *
	 * @param maDuree
	 */
	public Prairie(NiveauDensite maDensite) {
		super(true);
		duree = Duree.basse;
		trans = Transmission.haute;
		this.densite = maDensite;
	}
}

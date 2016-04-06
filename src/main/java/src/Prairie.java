package src;

public class Prairie extends Terrain {

	private final int duree = 4;

	/**
	 * Constructeur par défaut à modifier.
	 */
	public Prairie() {
		super(true); // On rend la prairie non inflammable.
		this.densite = NiveauDensite.un;
		this.trans = 5;
		this.PV = duree;

	}

	/**
	 * El Constrouctor !
	 *
	 * @param maDuree
	 */
	public Prairie(NiveauDensite maDensite) {
		super(true);
		this.trans = 5;
		this.densite = maDensite;
		if (densite == NiveauDensite.un)
			this.PV = duree;
		else
			this.PV = duree * 2;
	}
}

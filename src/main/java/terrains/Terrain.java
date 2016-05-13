package terrains;

import enumerations.NiveauDensite;

/**
 * Classe mère de tous les types de terrains.
 * 
 * @author Nicolas
 * 
 */

public abstract class Terrain {

	protected final boolean inflammable;
	protected boolean traversable = true;
	protected NiveauDensite densite;
	private int humidite = 10; // Humidité par défaut.
	protected int PV = 1;
	protected int coutDeplacement;

	// Seuls les terrains inflammables verront leur PV calculés dans leurs
	// constructeurs respectifs.
	private int trans; // Capacité d'un terrain à propager son feu.

	// El Constrouctor
	public Terrain(boolean inflammable) {
		this.inflammable = inflammable;
	}

	public NiveauDensite getDensite() {
		return densite;
	}

	public final boolean isInflammable() {
		return inflammable;
	}

	public void setDensite(NiveauDensite densite) {
		this.densite = densite;
	}

	public int getCoutDeplacement() {
		return coutDeplacement;
	}

	public boolean isTraversable() {
		return traversable;
	}

	// Setter personnalisé : Ajoute l'humidite d'un acteur à celle du terrain.
	public void arrose(int monHumidite) {
		this.setHumidite(this.getHumidite() + monHumidite);
	}

	// Setter personnalisé : Enlève l'humidite d'un acteur à celle du terrain.
	public void asseche(int monHumidite) {
		this.setHumidite(this.getHumidite() - monHumidite);
	}

	public int getPV() {
		return PV;
	}

	public void setPV(int pV) {
		PV = pV;
	}

	// Setter personnalisé : Diminue les PV du terrain d'un montant égal à
	// degats.
	public void brule(int degats) {
		PV -= degats;
	}

	public void setHumidite(int humidite) {
		this.humidite = humidite;
	}

	/**
	 * @return the trans
	 */
	public int getTrans() {
		return trans;
	}

	/**
	 * @param trans
	 *            the trans to set
	 */
	public void setTrans(int trans) {
		this.trans = trans;
	}

	/**
	 * @return the humidite
	 */
	public int getHumidite() {
		return humidite;
	}
}

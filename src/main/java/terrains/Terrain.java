package terrains;

import java.awt.Point;

import main.Carte;
import acteurs.Acteur;
import enumerations.NiveauDensite;

/**
 * Classe mère de tous les types de terrains.
 * 
 * @author Nicolas
 * 
 */

public abstract class Terrain {

	protected final boolean inflammable;
	protected boolean inonde = false;
	protected boolean traversable = true;
	protected NiveauDensite densite;
	private int humidite = 10; // Humidité par défaut.
	protected int PV = 1;
	protected int coutDeplacement;

	// Seuls les terrains inflammables verront leur PV calculés dans leurs
	// constructeurs respectifs.
	private int trans; // Capacité d'un terrain à propager son feu.

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

	/**
	 * Setter personnalisé : Ajoute l'humidite d'un acteur à celle du terrain.
	 * 
	 * Il peut très bien ne pas y avoir de Feu sur une case que l'on arrose : La
	 * pluie.
	 * 
	 * @param monHumidite
	 *            De combien j'arrose.
	 * @param X
	 *            Sur quoi j'arrose. Pour savoir si le feu à cet endroit doit
	 *            être supprimé si l'arrose est conséquent.
	 * @param Y
	 *            idem
	 * 
	 * @param maCarte
	 *            La carte pour pouvoir apeller presenceFeu() et supprimer le
	 *            Feu si besoin.
	 */
	public void arrose(int monHumidite, int X, int Y, Carte maCarte) {
		this.setHumidite(this.getHumidite() + monHumidite);

		if (this.humidite >= 100) {
			this.inonde = true; // Le terrain passe à l'état inondé.
			if (maCarte.presenceFeu(X, Y)) {
				for (Acteur courant : maCarte.getFeu()) {
					if (courant.getX() == X && courant.getY() == Y) {
						// On supprime le Feu. Pas besoin de la technique acteur
						// Anouar, car ici on supprime le Feu depuis un terrain
						// par la méthode arrose (qui ne peut être appellée
						// depuis un Feu), donc pas de soucis technique pour la
						// suppression.
						maCarte.getSesActeurs().remove(courant);
						// On enregistre la modification de la case dans la
						// liste historique.
						maCarte.getModifications().add(new Point(X, Y));
						return;
					}
				}
			}
		}
	}

	/**
	 * Enlève l'humidite d'un acteur à celle du terrain.
	 * 
	 * @param monHumidite
	 * @param X
	 * @param Y
	 * @param maCarte
	 */
	public void asseche(int monHumidite, int X, int Y, Carte maCarte) {
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

	public int getTrans() {
		return trans;
	}

	public void setTrans(int trans) {
		this.trans = trans;
	}

	/**
	 * @return the humidite
	 */
	public int getHumidite() {
		return humidite;
	}

	/**
	 * @return true si le terrain est inondé, false sinon.
	 */
	public boolean isInonde() {
		return this.inonde;
	}

	/**
	 * Change l'état du terrain (true pour inondé, false pour normal).
	 * 
	 * @param innondation
	 */
	public void setInonde(boolean innondation) {
		this.inonde = innondation;
	}
}

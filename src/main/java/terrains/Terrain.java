package terrains;

import java.awt.Point;

import acteurs.Acteur;
import enumerations.NiveauDensite;
import main.Carte;

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
				int i = 0;
				for (Acteur courant : maCarte.getFeu()) {
					if (courant.getX() == X && courant.getY() == Y) {
						// On supprime le Feu. Pas besoin de la technique acteur
						// Anouar, car ici on supprime le Feu depuis un terrain
						// par la méthode arrose (qui ne peut être appellée
						// depuis un Feu), donc pas de soucis technique pour la
						// suppression.
						maCarte.getSesActeurs().remove(i);

						// On enregistre la modification de la case dans la
						// liste historique.
						maCarte.getModifications().add(new Point(X, Y));
						return;
					}
					i++;
				}
			}
		}
	}

	/**
	 * Enlève l'humidite d'un acteur à celle du terrain. Si la case redevient
	 * suffisament sèche, (humidité < 100) elle n'est plus inondée et le feu
	 * pourra de nouveau s'y propager.
	 *
	 * @param monHumidite
	 * @param X
	 * @param Y
	 * @param maCarte
	 */
	public void asseche(int monHumidite, int X, int Y, Carte maCarte) {
		this.setHumidite(this.getHumidite() - monHumidite);

		// Si l'humidité repasse sous la barre des 100, le terrain n'est plus
		// inondé.
		if (this.humidite < 100) {
			this.inonde = false;
			maCarte.getModifications().add(new Point(X, Y));
		}
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

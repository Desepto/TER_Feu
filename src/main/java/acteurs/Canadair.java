package acteurs;

import main.Carte;
import terrains.Lac;

/**
 * La classe Canadair décrit les avions larguant de l'eau sur les cases
 * enflammées. Ils sont plus efficaces qu'un groupe de pompiers mais doivent
 * aller se recharger entre deux largages.
 * 
 * @author Nicolas
 * 
 */
public class Canadair extends Acteur {

	// Correspond à l'efficacite du Canadair, c'est à dire à l'augmentation de
	// l'humidite de la case sur laquelle il largue son eau.
	private final int efficaciteCanadaire = 100;
	private boolean estCharge;

	/*
	 * (non-Javadoc)
	 * 
	 * @see acteurs.Acteur#agi(main.Carte) N'est pas utilisée pour des raisons
	 * d'un besoin de renvoi d'infos
	 */
	@Override
	public void agi(Carte maCarte) {

	}

	/**
	 * Décharge la réserve d'eau du canadair, augmentant l'humidité d'un taux
	 * égal à efficaciteCanadair. Vide son reservoire d'eau. Ne peut pas larguer
	 * son eau sur une case non enflammée.
	 * 
	 * Fonction surchargée avec un attribut en plus car on a besoin d'une valeur
	 * de retour
	 * 
	 * @param maCarte
	 * @param autre
	 *            Paramètre inutile
	 * @return Renvoit vrai si l'acteur a agi
	 */
	public boolean agi(Carte maCarte, int autre) {

		// Si on est sur une case feu
		for (Acteur courant : maCarte.getSesActeurs()) {
			if (courant.X == this.X && courant.Y == this.Y
					&& courant instanceof Feu) {
				if (estCharge) {
					maCarte.getTabHexagones(this.X, this.Y).arrose(
							efficaciteCanadaire, X, Y, maCarte);
					// On arrose.
					this.estCharge = false; // On décharge.
					return false;
				}
			}
		}
		if (maCarte.getTabHexagones(this.X, this.Y) instanceof Lac
				&& this.estCharge == false) {
			this.estCharge = true;
			return false;
		}
		return true;
	}

	/**
	 * Un Canadair appraît chargé (rempli d'eau).
	 * 
	 * @param X
	 * @param Y
	 */
	public Canadair(int X, int Y) {
		super(X, Y);
		this.estCharge = true;
	}

	public int getEfficaciteCanadaire() {
		return efficaciteCanadaire;
	}

	public boolean isEstCharge() {
		return estCharge;
	}
}

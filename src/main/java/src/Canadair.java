package src;

/**
 * Pilotés par nos meilleurs employés Talibans ! Vous ne courrez aucun risques !
 *
 * @author Nicolas
 *
 */
public class Canadair extends Acteur {

	// Correspond à l'efficacite du Canadair, c'est à dire à l'augmentation de
	// l'humidite de la case sur laquelle il largue son eau.
	private final int efficaciteCanadaire = 50;
	private boolean estCharge;

	/**
	 * Décharge la réserve d'eau du canadair, augmentant l'humidité d'un taux
	 * égal à efficaciteCanadair. Vide son reservoire d'eau. Ne peut pas larguer
	 * son eau sur une case non enflammée.
	 */
	@Override
	public void agi(Carte maCarte) {
		for (Acteur courant : maCarte.getSesActeurs()) {
			if (courant.X == this.X && courant.Y == this.Y && courant instanceof Feu) {
				if (estCharge) {
					maCarte.getTerrain(this.X, this.Y).arrose(efficaciteCanadaire);
					this.estCharge = false;
				}
			}
		}
	}

	/**
	 * El Constrouctoritatoress, si maque ! Un Canadair appraît chargé (rempli
	 * d'eau).
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
}

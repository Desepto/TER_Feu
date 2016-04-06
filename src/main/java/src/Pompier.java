package src;

import java.util.Random;

public class Pompier extends Acteur {

	// Probabilité de décès d'un pompier sur une case en feu, en pourcentage.
	private final int probaMort = 2;
	private final int efficacitePompier = 20;
	// Taux d'augmentation de l'humidité du terrain arrosé par le pompier.
	// Correspond en moyenne à 3-5 tocs d'horloge pour éteindre une case en feu.

	/**
	 * Travail du pompier sur une case en feu.
	 */
	@Override
	public void agi(Carte maCarte) {
		// Le pompier décède.
		if (!StillAlive()) {
			int indice = 0;
			for (Acteur courant : maCarte.getSesActeurs()) {
				if (courant.X == this.X && courant.Y == this.Y && courant instanceof Pompier) {
					maCarte.supprActeur(indice);
					// On supprime le pompier de la carte.
					return;
					// On sort de la fonction après décès.
				}
				indice++;
			}
		}
		// Le pompier est toujours en vie.
		maCarte.getTerrain(X, Y).arrose(efficacitePompier);
	}

	/**
	 * El Counstrouctativoush
	 *
	 * @param X
	 * @param Y
	 */
	public Pompier(int X, int Y) {
		super(X, Y);

	}

	/**
	 * Lance un dé pour savoir si le pompier sur une case en feu décède ou s'il
	 * sagit d'un SURVIVOR.
	 *
	 * @return true s'il est encore vivant, false s'il est mort.
	 */
	public boolean StillAlive() {
		Random rand = new Random();
		int mort = rand.nextInt(100); // Nombre aléatoire entre 0 et 99.
		if (mort <= this.probaMort)
			return false;
		return true;
	}

}

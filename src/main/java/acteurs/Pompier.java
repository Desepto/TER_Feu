package acteurs;

import java.awt.Point;
import java.util.Random;

import main.Carte;

public class Pompier extends Acteur {

	/**
	 * Probabilité de décès d'un pompier sur une case en feu, en pourcentage.
	 *
	 * Cet attribut ne peut pas être final car on a besoin de rendre un pompier
	 * immortel ou débile durant les tests.
	 */
	private int probaMort = 2;
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
			maCarte.setnBpompiersMorts(maCarte.getnBpompiersMorts() + 1);
			// On enregistre les stats des pompiers crevés.
			for (int i = 0; i < maCarte.getSesActeurs().size(); i++) {
				if (maCarte.getSesActeurs().get(i) instanceof Pompier) {
					if (maCarte.getSesActeurs().get(i).X == X && maCarte.getSesActeurs().get(i).Y == Y) {
						// Suppression du pompier.
						maCarte.getSesActeurs().add(i, new Anouar(X, Y));
						maCarte.getSesActeurs().remove(i + 1);
						maCarte.purifieActeurs();
						maCarte.getModifications().add(new Point(this.X, this.Y));
						// On enregistre les coordonnées de la case modifiée
						// (pompier qui disparait).
						return;
					}
				}
			}
		}

		// Le pompier est toujours en vie.
		maCarte.getTerrain(X, Y).arrose(efficacitePompier, X, Y, maCarte);
		// Il balance sa flotte.
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

	public void setProbaMort(int probaMort) {
		this.probaMort = probaMort;
	}

	public int getEfficacitePompier() {
		return efficacitePompier;
	}
}

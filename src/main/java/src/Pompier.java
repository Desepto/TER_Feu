package src;

import java.util.Random;

public class Pompier extends Acteur {

	// Probabilité de décès d'un pompier sur une case en feu, en pourcentage.
	private final int probaMort = 5;

	@Override
	public void agi(Carte maCarte) {

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

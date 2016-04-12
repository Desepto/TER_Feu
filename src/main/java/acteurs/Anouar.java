/**
 *
 */
package acteurs;

import main.Carte;

/**
 * @author Nicolas
 *
 *         "Je n'ai pas fait l'implémentation, mais j'ai participé aux recherches"
 *
 *         Quand un acteur est supprimé, il est remplacé par un acteur Anouar.
 *         Règle une infinité de problèmes.
 */
public class Anouar extends Acteur {

	/**
	 * @param X
	 * @param Y
	 */
	public Anouar(int X, int Y) {
		super(X, Y);
		recherches();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see src.Acteur#agi(src.Carte)
	 */
	@Override
	public void agi(Carte maCarte) {
		recherches();
	}

	/**
	 * DaaAaar TD2 TD2 TD2
	 */
	private void recherches() {
		return;
	}
}

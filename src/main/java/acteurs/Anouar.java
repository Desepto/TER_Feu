/**
 *
 */
package acteurs;

import main.Carte;

/**
 * @author Nicolas
 * 
 *         Il s'agit d'une classe utilisée lors de la suppression d'un acteur
 *         depuis lui-même. Hack peut-être un peu sale mais fonctionnel..
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

	/*
	 * Fonction gardée pour des raisons de rétro-compatibilité
	 */
	private static void recherches() {
		return;
	}
}

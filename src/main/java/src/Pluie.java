/**
 *
 */
package src;

/**
 * @author Nicolas I'm siiiiiiiiiiiinging in the raiiiiiiin, just siiiiiiiiiging
 *         in the rain ! One moooore, then I'm haaaaappy again !
 */
public class Pluie extends Acteur {

	// Correspond à l'augmentation de l'humidité sur la case où il pleut et ses
	// voisines (moitié moins pour les voisines).
	private final int intensitePluie = 6;

	private int duree = 4; // Nombre de tocs pendant lesquels il pleut.

	private boolean apparition = true;
	// Pour savoir si c'est la première fois que cet acteur agi sur sa case.

	/**
	 * Fait pleuvoir : augmentation de l'humidite de la case ainsi que celles de
	 * ses voisines d'un taux équivalent à intensitePluie pendant une duree.
	 */
	@Override
	public void agi(Carte maCarte) {
		if (duree != 0) {

			if (apparition) {
				maCarte.getTerrain(X, Y).arrose(intensitePluie * 2);
				// 12% d'humidité en plus au début sur la case centrale.
				apparition = false;
			} else {
				maCarte.getTerrain(X, Y).arrose(intensitePluie);
				// 6% d'humidité en plus ensuite sur la case centrale.
			}
			for (Terrain courant : maCarte.superVoisinage(X, Y)) {
				courant.arrose(intensitePluie / 2);
				// +3% d'humidité au début et ensuite sur les cases voisines.
			}
			this.duree--;
		}
	}

	/**
	 * El Constrouctivatooooorrress, SI !
	 *
	 * @param X
	 * @param Y
	 */
	public Pluie(int X, int Y) {
		super(X, Y);

	}

}

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
	// voisines.
	private final int intensitePluie = 15;
	private int duree = 20;

	/**
	 * Fait pleuvoir (augmentation de l'humidite de la case ainsi que celle de
	 * ses voisines d'un taux équivalent à intensitePluie. Décrémente la durée
	 * pendant laquelle il pleut.
	 */
	@Override
	public void agi(Carte maCarte) {
		for (Terrain courant : maCarte.superVoisinage(X, Y)) {
			courant.humidite += intensitePluie;
		}
		this.duree--;
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

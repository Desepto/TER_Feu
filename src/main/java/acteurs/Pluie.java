/**
 *
 */
package acteurs;

import java.awt.Point;
import java.util.ArrayList;

import main.Carte;
import terrains.Terrain;
import terrains.TerrainVide;

/**
 * @author Nicolas I'm siiiiiiiiiiiinging in the raiiiiiiin, just siiiiiiiiiging
 *         in the rain ! One moooore, then I'm haaaaappy again !
 */
public class Pluie extends Acteur {

	// Correspond à l'augmentation de l'humidité sur la case où il pleut et ses
	// voisines (moitié moins pour les voisines).
	private final int intensitePluie = 8;

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
				/**
				 * Apparition de la pluie, on doit enregistrer les modifications
				 * de la case dans la Carte.
				 */
				maCarte.getModifications().add(new Point(this.X, this.Y));
				maCarte.getTabHexagones(X, Y).arrose(intensitePluie * 2, X, Y, maCarte);
				// 16% d'humidité en plus au début sur la case centrale.
				apparition = false;
			} else {
				maCarte.getTabHexagones(X, Y).arrose(intensitePluie, X, Y, maCarte);
				// 8% d'humidité en plus ensuite sur la case centrale.
			}
			for (Terrain courant : maCarte.superVoisinage(X, Y)) {
				if (!(courant instanceof TerrainVide))
					// On arrose pas n'importe quoi.
					courant.arrose(intensitePluie, X, Y, maCarte);
				// +8% d'humidité au début et ensuite sur les cases voisines.
			}

			/**
			 * On ajoute les cases modifiées (les voisins arrosés donc) dans la
			 * Carte, uniquement s'il n'y a pas déjà de la pluie dessus.
			 */
			ArrayList<Point> mesCoordsVoisins = maCarte.superVoisinageCoord(X, Y);
			for (Point monPoint : mesCoordsVoisins) {
				if (monPoint.x >= 0 && maCarte.presencePluie(monPoint.x, monPoint.y))
					maCarte.getModifications().add(monPoint);
			}

			this.duree--;
			if (this.duree == 0) {
				for (int i = 0; i < maCarte.getSesActeurs().size(); i++) {
					if (maCarte.getSesActeurs().get(i) instanceof Pluie) {
						if (maCarte.getSesActeurs().get(i).X == X && maCarte.getSesActeurs().get(i).Y == Y) {
							// Suppression de la pluie.
							maCarte.getSesActeurs().add(i, new Anouar(X, Y));
							maCarte.getSesActeurs().remove(i + 1);
							maCarte.purifieActeurs();
							// On enregistre la disparitation de la pluie.
							maCarte.getModifications().add(new Point(X, Y));
							return;
						}
					}
				}
			}

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

	public int getDuree() {
		return duree;
	}

	public int getIntensitePluie() {
		return intensitePluie;
	}

}

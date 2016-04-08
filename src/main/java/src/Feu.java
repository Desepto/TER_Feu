/**
 *
 */
package src;

import java.awt.Point;
import java.util.ArrayList;

/**
 * @author Nicolas Allumeeeeeeeeeeeeeeez, le feu ! Allumeeeeeeeeeeeeeez le
 *         feuuuu !
 *
 */
public class Feu extends Acteur {

	// Lorsque le feu se déclenche sur une case, il fait immédiatement baisser
	// l'humidité de la case d'un taux égal à assechement.
	private final int assechement = 20;
	private boolean apparition = true;
	// Pour savoir si c'est la première fois que cet acteur agi sur sa case.

	private final int intensiteFeu = 5;
	// La force du feu : Il assechera l'humidité des cases de 5% par toc sauf au
	// début.

	private final int propaFeu = 1;
	// La fameuse variable à équilibrer. Décrit la capacité du feu à se propager
	// sur ses voisins.

	/**
	 * Déclenche l'apocalypse de l'armageddon sur la terre.
	 *
	 * Grosse perte d'humidité sur la case centrale au premier toc.
	 *
	 * Perte de PV et humidité sur case centrale à tous les tocs.
	 */
	@Override
	public void agi(Carte maCarte) {

		// On essaie pas de mettre le feu n'importe où...
		// On est entre personnes civilisées.
		if (!maCarte.getTerrain(X, Y).isInflammable()) {
			System.out.println("Euh, Herr General, vous voulez vraiment mettre le feu au lac ??");
			return;
		}

		if (maCarte.getTerrain(X, Y).getPV() == 0) {
			for (int i = 0; i < maCarte.getSesActeurs().size(); i++) {
				if (maCarte.getSesActeurs().get(i) instanceof Feu) {
					if (maCarte.getSesActeurs().get(i).X == X && maCarte.getSesActeurs().get(i).Y == Y) {
						// Suppression du feu.
						maCarte.getSesActeurs().add(i, new Anouar(X, Y));
						maCarte.getSesActeurs().remove(i + 1);
						maCarte.purifieActeurs();
						return;
					}
				}
			}
		}
		if (apparition) {
			maCarte.getTerrain(X, Y).asseche(assechement);
			// On baisse l'humidité d'un montant exceptionnel au début.
			if (maCarte.getTerrain(X, Y).getPV() != 0)
				maCarte.getTerrain(X, Y).brule(1);
			// On décrémente les PV du terrain de 1.
			apparition = false;
		} else {
			maCarte.getTerrain(X, Y).asseche(intensiteFeu);
			// On baisse l'humidite d'un montant classique.
			if (maCarte.getTerrain(X, Y).getPV() != 0)
				maCarte.getTerrain(X, Y).brule(1);
			// On décrémente les PV du terrain de 1.
			propage(maCarte);
			// On lance un dé pour tenter de propager le feu à ses voisins.
		}

	}

	/**
	 * @param X
	 * @param Y
	 */
	public Feu(int X, int Y) {
		super(X, Y);

	}

	public int getIntensiteFeu() {
		return intensiteFeu;
	}

	public int getAssechement() {
		return assechement;
	}

	/**
	 * La fameuse fonction à équilibrer. Une carte est nécessaire pour récupérer
	 * force et direction du vent.
	 *
	 * @return vrai si le Terrain propageur met le feu au Terrain propage.
	 */

	private void propage(Carte maCarte) {
		double transmission = maCarte.getTerrain(X, Y).trans;
		// La transmission du terrain sur lequel y a le feu.
		double humidite;
		ArrayList<Point> mesVoisinsPoint = maCarte.superVoisinageCoord(X, Y);
		// Les coordonnées des voisins.
		ArrayList<Terrain> mesVoisins = maCarte.superVoisinage(X, Y);
		// Les voisins, pour avoir leur transmissions.
		ArrayList<Boolean> resultat = new ArrayList<Boolean>();
		// La liste finale, vrai ou faux pour chaque voisin s'il crame ou pas.
		ArrayList<Double> probas = new ArrayList<Double>();
		// Un pourcentage de réussite, on va lancer un dé et comparer avec ce
		// pourcentage, pour voir si ça crame.
		// On fera ça pour les 6 voisins.

		for (int i = 0; i < 6; i++) {
			if (mesVoisinsPoint.get(i).x >= 0) { // Si on est bien dans la map.
				if (mesVoisins.get(i).isInflammable()) {
					humidite = mesVoisins.get(i).humidite;
					if (humidite >= 100) {
						resultat.add(false);
						// Humidité trop élevée, no way que ça crame.
					}
					// la formule sans vent.
					probas.add(1 / humidite * transmission);
					// 100 - humidité + transmission;
					// On glisse le résultat dans une liste probas.
					resultat.add(true);
					// Par défaut on remplit la liste finale.
				} else {
					resultat.add(false);
					// Pas inflammable, on met directement un false au bon
					// endroit dans resultat.

				}
			}
			for (double courant : probas) {

			}

		}
	}

}

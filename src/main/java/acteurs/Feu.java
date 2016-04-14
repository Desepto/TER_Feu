/**
 *
 */
package acteurs;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import main.Carte;
import terrains.Terrain;

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
	// La force du feu : Il assechera l'humidité des cases de intensiteFeu% par
	// toc sauf au début.

	/**
	 * Déclenche l'apocalypse de l'armageddon sur la terre.
	 *
	 * Grosse perte d'humidité et 1 PV sur la case centrale au premier toc.
	 *
	 * Perte de 1 PV et humidité sur case centrale à tous les tocs.
	 */
	@Override
	public void agi(Carte maCarte) {

		// On essaie pas de mettre le feu n'importe où...
		// On est entre personnes civilisées.
		if (!maCarte.getTerrain(X, Y).isInflammable()) {
			System.out.println("Euh, Herr General, vous voulez vraiment mettre le feu au lac ??");
			return;
		}

		/**
		 * Que le feu ait tout brûlé (disparition donc) on non, il modifie sa
		 * case dans tous les cas. On le note donc dans l'historique des modifs
		 * de la carte.
		 */
		maCarte.getModifications().add(new Point(this.X, this.Y));

		/**
		 * On gère le cas où le feu a tout brûlé : On le supprime des acteurs.
		 */
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
		/**
		 * Première fois que le feu agit : Il fait baisser les PV, l'humidité de
		 * beaucoup et ne peut pas se propager ! (Cellules de 1 borne de large,
		 * laisser 1 tic au feu pour se propager à ses voisins.)
		 */
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

			// On s'occupe maintenant de la propagation.

			ArrayList<Boolean> feuVoisins = propage(maCarte);
			// On lance 6 dés pour tenter de propager le feu à ses voisins.
			ArrayList<Point> mesVoisinsCoord = maCarte.superVoisinageCoord(X, Y);

			boolean allumeeeeeerLeFeuuuu = true; // Lire la suite pour
													// comprendre.

			/**
			 * On parcourt la liste de Bouléens feuVoisins qui contient les
			 * résultats du tirage et mesVoisinsCoord qui contient les
			 * coordonnées des 6 voisins de this. (indice de parcours z).
			 *
			 * Il faut que mon z courant de feuVoisins soit true pour mettre le
			 * feu au voisin z. Si c'est le cas, il faut tester qu'il n'y ait
			 * pas déjà un feu dessus. C'est à ça que sert allumeeeeeerLeFeuuuu.
			 */
			for (int z = 0; z < 6; z++) {
				if (feuVoisins.get(z) == true) {
					for (Acteur courant : maCarte.getSesActeurs(mesVoisinsCoord.get(z).x, mesVoisinsCoord.get(z).y)) {
						if (courant instanceof Feu) {
							allumeeeeeerLeFeuuuu = false;
						}
					}
					if (allumeeeeeerLeFeuuuu) {
						Point yMettreLeFeu = mesVoisinsCoord.get(z);
						// On fout le feu au voisin courant.
						Feu monFeu = new Feu(yMettreLeFeu.x, yMettreLeFeu.y);
						maCarte.ajoutActeur(monFeu);
						/**
						 * On ajoute les coordonnées du voisin modifié dans la
						 * Carte.
						 */
						maCarte.getModifications().add(yMettreLeFeu);
					}
				}
			}
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
	 * @return Une liste de bouléens. Vrai ou faux pour chaque voisin, s'il
	 *         commence à brûler ou non.
	 */

	private ArrayList<Boolean> propage(Carte maCarte) {
		double transmission = maCarte.getTerrain(X, Y).getTrans();
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
					humidite = mesVoisins.get(i).getHumidite();
					if (humidite >= 100) {
						resultat.add(false);
						// Humidité trop élevée, no way que ça crame.
					}
					// la formule sans vent.
					probas.add(100 - humidite + transmission);
					// 100 - humidité + transmission;
					// On glisse le résultat dans une liste probas.
					resultat.add(true);
					// Par défaut on remplit la liste finale.
				} else {
					probas.add(-666.666);
					resultat.add(false);
					// Pas inflammable, on met directement un false au bon
					// endroit dans resultat.

				}
			} else {
				probas.add(-666.666);
				resultat.add(false);
			}
		}

		for (int z = 0; z < 6; z++) {
			if (resultat.get(z) == true) {
				if (probaAlea() <= probas.get(z))
					resultat.add(true);
				else
					resultat.add(false);
			}
		}
		return resultat;

	}

	/**
	 * Petite fonction interne pour faire un tirage de 0 à 99.
	 *
	 * @return le nombre tiré.
	 */
	private int probaAlea() {
		Random rand = new Random();
		int tirage = rand.nextInt(100);
		return tirage;
	}

}

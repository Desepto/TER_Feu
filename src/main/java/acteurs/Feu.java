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
		if (!maCarte.getTabHexagones(X, Y).isInflammable()) {

			System.out.println("Euh, Herr General, vous voulez vraiment mettre le feu au lac ??");
			return;
		}

		/**
		 * Première fois que le feu agit : Il fait baisser les PV, l'humidité de
		 * beaucoup et ne peut pas se propager ! (Cellules de 1 borne de large,
		 * laisser 1 tic au feu pour se propager à ses voisins.)
		 */
		if (apparition) {

			/**
			 * On enregistre l'apparition du feu dans l'historique. Mais
			 * uniquement s'il n'y a pas déjà un feu à cet endroit (ce qui
			 * arrive, car lorsque le feu se propage, on met les voisins dans
			 * l'historique. Ce qui créé des doubles modifications dans
			 * l'historique une fois que les voisins "apparaitrons" dans leur
			 * agi respectifs).
			 */
			maCarte.getModifications().add(new Point(X, Y));

			maCarte.getTabHexagones(X, Y).asseche(assechement, X, Y, maCarte);
			// On baisse l'humidité d'un montant exceptionnel au début.
			if (maCarte.getTabHexagones(X, Y).getPV() > 0)
				maCarte.getTabHexagones(X, Y).brule(1);
			// On décrémente les PV du terrain de 1.
			this.apparition = false;
		} else {
			// maCarte.getTabHexagones(X, Y).asseche(intensiteFeu, X, Y,
			// maCarte);

			/** ATTENION A REMETTRE */

			// On baisse les pv d'un montant classique.
			if (maCarte.getTabHexagones(X, Y).getPV() > 0)
				maCarte.getTabHexagones(X, Y).brule(1);
			// On décrémente les PV du terrain de 1.

			// On s'occupe maintenant de la propagation.

			ArrayList<Boolean> feuVoisins = propage(maCarte);
			// On lance 6 dés pour tenter de propager le feu à ses voisins.
			ArrayList<Point> mesVoisinsCoord = maCarte.voisinageCoord(X, Y);

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
					if (allumeeeeeerLeFeuuuu) {
						Point yMettreLeFeu = mesVoisinsCoord.get(z);
						// On fout le feu au voisin courant.
						if (yMettreLeFeu.x >= 0 && yMettreLeFeu.y >= 0
								&& !maCarte.presenceFeu(yMettreLeFeu.x, yMettreLeFeu.y)) {
							/**
							 * Le feu ne peut se déclencher si la case est
							 * inondée.
							 */
							if (!maCarte.getTabHexagones(yMettreLeFeu.x, yMettreLeFeu.y).isInonde()) {
								System.out.println("TA MAMAN EN STRING");
								Feu monFeu = new Feu(yMettreLeFeu.x, yMettreLeFeu.y);
								if (maCarte.getTabHexagones(yMettreLeFeu.x, yMettreLeFeu.y).isInflammable()
										&& maCarte.getTabHexagones(yMettreLeFeu.x, yMettreLeFeu.y).getPV() > 0) {
									maCarte.ajoutActeur(monFeu);
								}
							} else
								System.out.println("Ton pere en slip kangourou");
						}

					}
				}
			}
		}
		/**
		 * On gère le cas où le feu a tout brûlé : On le supprime des acteurs.
		 */
		if (maCarte.getTabHexagones(X, Y).getPV() <= 0) {
			for (int i = 0; i < maCarte.getSesActeurs().size(); i++) {
				if (maCarte.getSesActeurs().get(i) instanceof Feu) {
					if (maCarte.getSesActeurs().get(i).X == X && maCarte.getSesActeurs().get(i).Y == Y) {
						// Suppression du feu.
						maCarte.getSesActeurs().add(i, new Anouar(X, Y));
						maCarte.getSesActeurs().remove(i + 1);
						maCarte.purifieActeurs();
						// On enregistre la modification de la case dans la
						// liste historique.
						maCarte.getModifications().add(new Point(X, Y));
						return;
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
		double transmission = maCarte.getTabHexagones(X, Y).getTrans();
		// La transmission du terrain sur lequel y a le feu.
		double humidite;
		ArrayList<Point> mesVoisinsPoint = maCarte.voisinageCoord(X, Y);
		// Les coordonnées des voisins.
		ArrayList<Terrain> mesVoisins = maCarte.voisinage(X, Y);
		// Les voisins, pour avoir leur transmissions.
		ArrayList<Boolean> resultat = new ArrayList<Boolean>();
		// La liste finale, vrai ou faux pour chaque voisin s'il crame ou pas.
		ArrayList<Double> probas = new ArrayList<Double>();
		// Un pourcentage de réussite, on va lancer un dé et comparer avec ce
		// pourcentage, pour voir si ça crame.
		// On fera ça pour les 6 voisins.
		ArrayList<Double> vent = ventProba(maCarte);
		// Contient les probas des 6 voisins inhérentes au vent.

		for (int j = 0; j < 6; j++) {
			resultat.add(true);
			// Par défaut on remplit la liste finale.
		}

		for (int i = 0; i < 6; i++) {
			if (mesVoisinsPoint.get(i).x >= 0) { // Si on est bien dans la map.
				if (mesVoisins.get(i).isInflammable()) {
					humidite = mesVoisins.get(i).getHumidite();
					if (humidite >= 100) {
						resultat.set(i, false);
						// Humidité trop élevée, no way que ça crame.
					}

					double proba = probaOU(0.2 * (1.0 / humidite), (transmission / 20));
					if (proba < 0.0) {
						proba = 0.0;
					}

					probas.add(proba);
					/**
					 * LA PROBAS ICI
					 */

				} else {
					probas.add(-666.666);
					resultat.set(i, false);
					// Pas inflammable, on met directement un false au bon
					// endroit dans resultat.

				}
			} else {
				probas.add(-666.666);
				resultat.set(i, false);
			}
		}

		for (int z = 0; z < 6; z++) {
			if (resultat.get(z) == true) {
				if (probaAlea() <= probas.get(z))
					resultat.set(z, true);
				else
					resultat.set(z, false);
			}
		}

		return resultat;
	}

	/**
	 * Petite fonction interne pour faire un tirage de 0 à 99.
	 *
	 * @return le nombre tiré.
	 */
	private static double probaAlea() {
		Random rand = new Random();

		return rand.nextDouble();
		// Return 1 ici pour forcer le feu à se propager.
	}

	/**
	 * Gère la force et la direction du vent, et renvoie une probas associée
	 * pour chaque voisin.
	 *
	 * @return La liste de probas venant du vent à considérer pour la
	 *         propagation.
	 */
	private static ArrayList<Double> ventProba(Carte maCarte) {

		double vent = maCarte.getForceVent().ordinal() + 1;
		// La force du vent. 1-2-3

		double ventDirection = maCarte.getDirectionVent().ordinal() + 1;
		// La direction du vent global de la carte conveti en entier.
		ArrayList<Double> probasVent = new ArrayList<Double>();
		// La proba finale de chaque voisin qui sera renvoyée.
		for (int z = 1; z < 7; z++) {
			if (ventDirection == z) {
				// Directions égales. Favorable.
				probasVent.add(vent + 6);
				continue; // Inutile d'aller plus loin dans la boucle, les cas
							// sont disjoints.
			}

			if (Math.abs(ventDirection - z) == 3) {
				// Directions opposées. Défavorable.
				probasVent.add(4 - vent);
				continue; // Inutile d'aller plus loin dans la boucle, les cas
				// sont disjoints.
			}

			if (Math.abs(ventDirection - z) == 1) {
				// Directions presque opposées. Légèrement défavorable.
				probasVent.add(5.5 - vent);
				// System.out.println(z);
				continue; // Inutile d'aller plus loin dans la boucle, les cas
				// sont disjoints.
			}

			if (Math.abs(ventDirection - z) == 2) {
				// Directions presque égales. Légèrement favorable.
				probasVent.add(4.5 + vent);
				// System.out.println(z);
			}
		}
		// System.out.println(probasVent.size() + "JE SUCE TA SOEUR SALE GAY");
		return probasVent;
	}

	public static double probaOU(double a, double b) {
		return (a + b) - (a * b);
	}

}

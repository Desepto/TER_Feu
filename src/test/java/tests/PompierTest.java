/**
 *
 */
package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import acteurs.Pompier;
import entreesSorties.Lecteur;
import main.Carte;

/**
 * @author Nicolas
 *
 */
public class PompierTest {

	/**
	 * On teste la classe pompier. Tout est détaillé.
	 *
	 * Test method for {@link src.Pompier#agi(main.Carte)}.
	 */
	@Test
	public void testAgi() {
		Carte maCarte = Lecteur.carteEnDur();
		Pompier monPompier = new Pompier(12, 12);
		monPompier.setProbaMort(100);
		// On rend le pompier mourant à tous les coups.
		maCarte.ajoutActeur(monPompier);

		assertTrue(maCarte.getSesActeurs().size() == 1);
		// On checke que le pompier est là.

		// On checke qu'il n'y ait pas de modification pour l'instant sur la
		// carte.
		assertTrue(maCarte.getModifications().size() == 0);

		maCarte.getSesActeurs().get(0).agi(maCarte);
		// Il balance sa flotte. Mais meurt.

		// Une case devrait être modifiée.
		assertTrue(maCarte.getModifications().size() == 1);

		assertTrue(maCarte.getSesActeurs().size() == 0);
		// On checke qu'il est bien au paradis et pas dans la carte.
		monPompier.setProbaMort(-1);
		// On le rend immortel.
		assertTrue(maCarte.getTerrain(12, 12).getHumidite() == maCarte.getTerrain(19, 19).getHumidite());
		// On chercke que l'humidité du terrain n'a pas bougé.
		maCarte.getSesActeurs().add(0, monPompier);
		// On remet le pompier sur la carte.
		maCarte.getSesActeurs().get(0).agi(maCarte);
		// Il balance sa flotte et ne meurt pas.
		/*
		 * // 2 cases devraient être modifiées maintenant. (deux fois la même).
		 * assertTrue(maCarte.getModifications().size() == 2);
		 */
		assertTrue(maCarte.getTerrain(12, 12).getHumidite() == maCarte.getTerrain(19, 19).getHumidite()
				+ monPompier.getEfficacitePompier());
		// On checke qu'il a bien arrosé son terrain.

	}

}

/**
 *
 */
package src;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Nicolas
 *
 */
public class PompierTest {

	/**
	 * On teste la classe pompier. Tout est détaillé.
	 *
	 * Test method for {@link src.Pompier#agi(src.Carte)}.
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

		maCarte.getSesActeurs().get(0).agi(maCarte);
		// Il balance sa flotte. Mais meurt.
		assertTrue(maCarte.getSesActeurs().size() == 0);
		// On checke qu'il est bien au paradis et pas dans la carte.
		monPompier.setProbaMort(-1);
		// On le rend immortel.
		assertTrue(maCarte.getTerrain(12, 12).humidite == maCarte.getTerrain(19, 19).humidite);
		// On chercke que l'humidité du terrain n'a pas bougé.
		maCarte.getSesActeurs().add(0, monPompier);
		// On remet le pompier sur la carte.
		maCarte.getSesActeurs().get(0).agi(maCarte);
		// Il balance sa flotte et ne meurt pas.
		assertTrue(maCarte.getTerrain(12, 12).humidite == maCarte.getTerrain(19, 19).humidite
				+ monPompier.getEfficacitePompier());
		// On checke qu'il a bien arrosé son terrain.

	}

}

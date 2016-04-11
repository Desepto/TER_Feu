/**
 *
 */
package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import acteurs.Feu;
import entreesSorties.Lecteur;
import main.Carte;
import terrains.Prairie;

/**
 * @author Nicolas
 *
 */
public class FeuTest {

	/**
	 * Test method for {@link src.Feu#agi(main.Carte)}.
	 */
	@Test
	public void testAgi() {
		Carte maCarte = Lecteur.carteEnDur();
		maCarte.transformeTerrain(12, 12, new Prairie());
		Feu monFeu = new Feu(12, 12);
		maCarte.ajoutActeur(monFeu);
		maCarte.getTerrain(15, 15).setPV(maCarte.getTerrain(12, 12).getPV());
		maCarte.getTerrain(15, 15).setHumidite(maCarte.getTerrain(12, 12).getHumidite());

		assertTrue(maCarte.getSesActeurs().size() == 1);
		maCarte.getSesActeurs().get(0).agi(maCarte);

		assertTrue(maCarte.getTerrain(12, 12).getHumidite() == maCarte.getTerrain(15, 15).getHumidite()
				- monFeu.getAssechement());

		assertTrue(maCarte.getTerrain(12, 12).getPV() == maCarte.getTerrain(15, 15).getPV() - 1);

		for (int i = maCarte.getTerrain(12, 12).getPV(); i > -1; i--)
			maCarte.getSesActeurs().get(0).agi(maCarte);

		assertTrue(maCarte.getSesActeurs().size() == 0);
		assertTrue(maCarte.getTerrain(12, 12).getPV() == 0);

		int humidite = maCarte.getTerrain(15, 15).getHumidite() - monFeu.getAssechement();
		humidite -= monFeu.getIntensiteFeu() * (maCarte.getTerrain(15, 15).getPV() - 1);

		assertTrue(maCarte.getTerrain(12, 12).getHumidite() == humidite);
	}
}

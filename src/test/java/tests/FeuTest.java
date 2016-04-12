/**
 *
 */
package tests;

import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;

import acteurs.Acteur;
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

		// assertTrue(maCarte.getSesActeurs().size() == 0);

		// L'instruction en haut n'est plus faisable, car le feu peu maintenant
		// se propager, et donc rajouter de nouveaux acteurs Feu sur la carte.
		assertTrue(maCarte.getTerrain(12, 12).getPV() == 0);

		int humidite = maCarte.getTerrain(15, 15).getHumidite() - monFeu.getAssechement();
		humidite -= monFeu.getIntensiteFeu() * (maCarte.getTerrain(15, 15).getPV() - 1);

		assertTrue(maCarte.getTerrain(12, 12).getHumidite() == humidite);
	}

	/**
	 * Quelques tests sur la fonction de propagation. On se contente d'afficher
	 * les acteurs de la carte, pour voir si de nouveaux Feu sont apparus aux
	 * bons endroits.
	 */
	@Test
	public void testPropage() {
		Carte maCarte = Lecteur.carteEnDur();

		maCarte.transformeTerrain(12, 12, new Prairie());
		maCarte.ajoutActeur(new Feu(12, 12));

		for (Point monPoint : maCarte.superVoisinageCoord(12, 12)) {
			maCarte.transformeTerrain(monPoint.x, monPoint.y, new Prairie());
		}

		assertTrue(maCarte.getSesActeurs().size() == 1);
		maCarte.getSesActeurs().get(0).agi(maCarte);

		System.out.println("Nb ActeursFeu : " + maCarte.getFeu().size());
		maCarte.getSesActeurs().get(0).agi(maCarte);
		System.out.println("Nb ActeursFeu : " + maCarte.getFeu().size());

		for (Acteur courant : maCarte.getSesActeurs()) {
			System.out.println(courant.getX() + " ; " + courant.getY());
		}

	}
}

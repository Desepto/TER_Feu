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
public class CanadairTest {

	/**
	 * On créer un canadair, on l'ajoute, on checke qu'il est bien dans la map,
	 * on le fait larguer sa flotte, on checke que l'humidité du terrain à
	 * correctement augmenté.
	 *
	 * Test method for {@link src.Canadair#agi(src.Carte)}.
	 */
	@Test
	public void testAgi() {
		Carte maCarte = Lecteur.carteEnDur();
		Canadair monCanadair = new Canadair(12, 12);
		maCarte.ajoutActeur(monCanadair);

		Feu monFeu = new Feu(12, 12);
		maCarte.ajoutActeur(monFeu);

		assertTrue(maCarte.getTerrain(12, 12).humidite == maCarte.getTerrain(15, 15).humidite);
		assertTrue(maCarte.getSesActeurs().size() == 2);
		maCarte.getSesActeurs().get(0).agi(maCarte);
		assertTrue(maCarte.getTerrain(12, 12).humidite == maCarte.getTerrain(15, 15).humidite
				+ monCanadair.getEfficaciteCanadaire());
	}

}

/**
 *
 */
package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import acteurs.Canadair;
import acteurs.Feu;
import entreesSorties.Lecteur;
import main.Carte;

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
	 * Test method for {@link src.Canadair#agi(main.Carte)}.
	 */
	@Test
	public void testAgi() {
		Carte maCarte = Lecteur.carteEnDur();
		Canadair monCanadair = new Canadair(12, 12);
		maCarte.ajoutActeur(monCanadair);

		Feu monFeu = new Feu(12, 12);
		// Le canadair ne largue pas de flotte sans feu.
		maCarte.ajoutActeur(monFeu);

		// On checke qu'aucune modification n'ait été prise en compte pour
		// l'instant.
		assertTrue(maCarte.getModifications().size() == 0);

		assertTrue(maCarte.getTerrain(12, 12).getHumidite() == maCarte.getTerrain(15, 15).getHumidite());
		// On checke l'humidite de base.

		assertTrue(maCarte.getSesActeurs().size() == 2);
		// On checke le nombre d'acteurs.
		maCarte.getSesActeurs().get(0).agi(maCarte);

		assertTrue(maCarte.getTerrain(12, 12).getHumidite() == maCarte.getTerrain(15, 15).getHumidite()
				+ monCanadair.getEfficaciteCanadaire());
				// On checke que le largage de flotte à fonctionné.

		// On chercke que la case arossée ait bien été enregistrée dans la
		// Carte.
		assertTrue(maCarte.getModifications().size() == 1);
		assertTrue(maCarte.getModifications().get(0).x == 12);
		assertTrue(maCarte.getModifications().get(0).y == 12);
	}

}

/**
 *
 */
package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import acteurs.Acteur;
import acteurs.Pluie;
import entreesSorties.Lecteur;
import main.Carte;
import terrains.Rocher;
import terrains.Terrain;

/**
 * @author Nicolas On teste la pluie. On fait pleuvoir, on regarde si
 *         l'augmentation de l'humidité sur la case centrale et sur les cases
 *         adjacentes est bonne. On regarde aussi que le nombre d'acteur est
 *         bon. Puis les pluies disparaissent, et on vérifie que la liste
 *         d'acteur est à jour.
 *
 */
public class PluieTest {

	/**
	 * Test method for {@link src.Pluie#agi(main.Carte)}.
	 */
	@Test
	public void testAgi() {
		Carte maCarte = Lecteur.carteEnDur(); // On remplit la carte ici.
		Carte maCarte2 = Lecteur.carteEnDur();
		Pluie maPluie = new Pluie(12, 12);
		Pluie maPluie2 = new Pluie(19, 19);
		Pluie maPluie3 = new Pluie(25, 25);
		Pluie maPluie4 = new Pluie(10, 10);
		maCarte.ajoutActeur(maPluie);
		maCarte.ajoutActeur(maPluie2);

		assertTrue(maCarte.getSesActeurs().size() == 2);

		Terrain monTerrain = new Rocher();

		int humidite = monTerrain.getHumidite();

		int indice = 0;
		for (Acteur courant : maCarte.getSesActeurs()) {
			courant.agi(maCarte);
			indice++;
		}

		maPluie3.agi(maCarte2);

		monTerrain = maCarte.getTerrain(12, 12);
		Terrain monTerrainInutile = maCarte2.getTerrain(0, 0);

		humidite += 2 * maPluie.getIntensitePluie();

		assertTrue(monTerrain.getHumidite() == humidite);
		assertTrue(indice == 2);
		assertTrue(maPluie2.getDuree() == maPluie4.getDuree() - 1);
		assertTrue(maPluie.getDuree() == maPluie4.getDuree() - 1);

		for (Terrain courant : maCarte.superVoisinage(12, 12))
			assertTrue(courant.getHumidite() == (maPluie4.getIntensitePluie() / 2) + monTerrainInutile.getHumidite());

		while (maPluie3.getDuree() > 0) {
			for (int i = 0; i < maCarte.getSesActeurs().size(); i++) {
				maCarte.getSesActeurs().get(i).agi(maCarte);
			}
			maPluie3.agi(maCarte2);
		}

		assertTrue(maCarte.getSesActeurs().size() == 1);
		assertTrue(maCarte.getSesActeurs().get(0) instanceof Pluie);

	}
}
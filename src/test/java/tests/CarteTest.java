/**
 *
 */
package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import enumerations.Direction;
import main.Carte;
import terrains.Lac;
import terrains.Rocher;
import terrains.Terrain;
import terrains.TerrainVide;

/**
 * @author Nicolas
 *
 */
public class CarteTest {

	Carte maCarte;

	/**
	 * Test préliminaire. On remplit d'abord une Carte en 30 x 30 avec des
	 * Rochers, puis on regarde que tous les kloulitas sont au bons endroits, et
	 * que tout le reste est bien en Rocher.
	 */
	@Before
	public void rempliTableau() {
		maCarte = new Carte(30);
		// Remplissage.
		for (int Y = 0; Y < 30; Y++) {
			for (int X = 0; X < 31; X++) {

				if (Y % 2 == 0) {
					if (X == 30) {
						maCarte.getTabHexagones()[X][Y] = new TerrainVide();
						continue;
					}
				}

				if (Y % 2 != 0) {
					if (X == 0) {
						maCarte.getTabHexagones()[X][Y] = new TerrainVide();
						continue;
					}
				}

				maCarte.getTabHexagones()[X][Y] = new Rocher();
			}
		}
		// Et maintenant on checke que tout est à la bonne place :

		// test contient un affichage du tableau de la carte. Juste pour tester
		// et afficher le tableau dans la console.

		// On supprime le Warning dégueulasse généré par l'inutilisation de la
		// variable test servant à afficher le tableau si besoin.
		@SuppressWarnings("unused")
		String test = new String(""); // Décommenter le syso plus bas pour
										// afficher le tableau.
		for (int Y = 0; Y < 30; Y++) {
			for (int X = 0; X < 31; X++) {
				if (maCarte.getTabHexagones()[X][Y] instanceof Rocher)
					test += "Cailloux! ";
				else {
					test += "Vide ";
				}
				if (X == 30 && Y % 2 == 0) {
					if (!(maCarte.getTabHexagones()[X][Y] instanceof TerrainVide))
						fail("Pas de kloulita là où il en faut ! AU BUCHER !");
				}

				else if (X == 0 && Y % 2 != 0) {
					if (!(maCarte.getTabHexagones()[X][Y] instanceof TerrainVide))
						fail("Pas de kloulita là où il en faut ! AU BUCHER !");
				} else {
					if (maCarte.getTabHexagones()[X][Y] instanceof TerrainVide)
						fail("Un kloulita là où il en faut pas ! AU BUCHER !");
				}
				if (maCarte.getTabHexagones()[X][Y] == null)
					fail("Initialisation ratée, certaines cases vallent null, au bucheeeer !!!");
			}
			test += "\n";
		}
		// System.out.println(test); // A décommenter pour voir la tronche du
		// tableau.
	}

	/**
	 * Le petit test d'André, parce que bon transformeterrain() yavait des zones
	 * d'ombre.
	 */
	@Test
	public void testTransformeTerrain() {

		Carte mayk = new Carte(30);
		for (int X = 0; X < 30; X++) {
			for (int Y = 0; Y < 30; Y++) {
				mayk.transformeTerrain(X, Y, new Lac());
			}
		}
	}

	/**
	 * On se contente de modifier un Terrain de la carte, et de vérifier que la
	 * modif a bien été prise en compte.
	 *
	 * Test method for
	 * {@link main.Carte#transformeTerrain(src.Terrain, int, int)}.
	 */
	@Test
	public void testTransformeTerrain2() {

		assertTrue(maCarte.getTerrain(12, 12) instanceof Rocher);
		Lac flaqueDeau = new Lac();
		maCarte.transformeTerrain(12, 12, flaqueDeau);
		assertTrue(maCarte.getTerrain(12, 12) instanceof Lac);

		maCarte.transformeTerrain(0, 1, flaqueDeau);
		assertTrue(maCarte.getTerrain(0, 1) instanceof Lac);

		// maCarte.transformeTerrain(666, 2, flaqueDeau); Génère un syso de bug
		// normal.
	}

	/**
	 * Test method for {@link main.Carte#voisinage(int, int)}. ça rigole plus du
	 * tout ! On teste les fonctions voisinage et voisin sur 5 points
	 * stratégiques de la carte : Le tout premier, le tout dernier, et 3 autres
	 * à ligne paire et impaire. On teste aussi de voisiner des coordonnées à la
	 * con pour voir s'il y a bien une liste nulle.
	 *
	 * YAKSHIMASH ! Proffessionnel KAZAKH !
	 */
	@Test
	public void testVoisinage() {

		// On teste ensuite voisinage ! ça rigole zéro dans la street!

		ArrayList<Terrain> theorie = maCarte.voisinage(0, 0);
		if (theorie == null)
			fail("Ce Terrain ne figure pas dans la carte. Au Bucheeeeer !!!");

		assertTrue(theorie.get(0) instanceof TerrainVide);
		assertTrue(theorie.get(1) instanceof TerrainVide);
		assertTrue(theorie.get(2) instanceof Rocher);
		assertTrue(theorie.get(3) instanceof Rocher);
		assertTrue(theorie.get(4) instanceof TerrainVide);
		assertTrue(theorie.get(5) instanceof TerrainVide);

		theorie = maCarte.voisinage(30, 29);

		assertTrue(theorie.get(0) instanceof Rocher);
		assertTrue(theorie.get(1) instanceof TerrainVide);
		assertTrue(theorie.get(2) instanceof TerrainVide);
		assertTrue(theorie.get(3) instanceof TerrainVide);
		assertTrue(theorie.get(4) instanceof TerrainVide);
		assertTrue(theorie.get(5) instanceof Rocher);

		theorie = maCarte.voisinage(2, 2);

		assertTrue(theorie.get(0) instanceof Rocher);
		assertTrue(theorie.get(1) instanceof Rocher);
		assertTrue(theorie.get(2) instanceof Rocher);
		assertTrue(theorie.get(3) instanceof Rocher);
		assertTrue(theorie.get(4) instanceof Rocher);
		assertTrue(theorie.get(5) instanceof Rocher);

		theorie = maCarte.voisinage(30, 3);

		assertTrue(theorie.get(0) instanceof Rocher);
		assertTrue(theorie.get(1) instanceof TerrainVide);
		assertTrue(theorie.get(2) instanceof TerrainVide);
		assertTrue(theorie.get(3) instanceof TerrainVide);
		assertTrue(theorie.get(4) instanceof Rocher);
		assertTrue(theorie.get(5) instanceof Rocher);

		theorie = maCarte.voisinage(29, 3);

		assertTrue(theorie.get(0) instanceof Rocher);
		assertTrue(theorie.get(1) instanceof Rocher);
		assertTrue(theorie.get(2) instanceof Rocher);
		assertTrue(theorie.get(3) instanceof Rocher);
		assertTrue(theorie.get(4) instanceof Rocher);
		assertTrue(theorie.get(5) instanceof Rocher);

		theorie = maCarte.voisinage(666, 2903); // Genère un syso "Grosse cave"
												// tout à fait normal.

		assertTrue(theorie == null);

		theorie = maCarte.voisinage(0, 1); // Génère un syso bucher normal
											// aussi.

		assertTrue(theorie == null);

	}

	/**
	 * Test method for {@link main.Carte#voisin(int, int, src.Direction)}. On
	 * teste quelques directions. Cette fonction est basée sur voisinage et est
	 * assez simple, pas besoin de tests poussés.
	 */
	@Test
	public void testVoisin() {
		Terrain theorie = new TerrainVide();
		theorie = maCarte.voisin(0, 0, Direction.BD);
		assertTrue(theorie instanceof Rocher);

		theorie = maCarte.voisin(0, 0, Direction.G);
		assertTrue(theorie instanceof TerrainVide);
		System.out.println("C'est normal, 3 syso de bugs volontaires pour les tests.");

		theorie = maCarte.voisin(29, 2, Direction.BG);
		assertTrue(theorie instanceof Rocher);

	}

	/**
	 * On teste la véritble fonction de voisinage : superVoisinage. On la teste
	 * avec des coordonnées réelles "sensibles".
	 */
	@Test
	public void testSuperVoisinage() {

		ArrayList<Terrain> theorie = maCarte.superVoisinage(0, 0);
		assertTrue(theorie.get(0) instanceof TerrainVide);
		assertTrue(theorie.get(1) instanceof TerrainVide);
		assertTrue(theorie.get(2) instanceof Rocher);
		assertTrue(theorie.get(3) instanceof Rocher);
		assertTrue(theorie.get(4) instanceof TerrainVide);
		assertTrue(theorie.get(5) instanceof TerrainVide);

		theorie = maCarte.superVoisinage(0, 1);

		assertTrue(theorie.get(0) instanceof Rocher);
		assertTrue(theorie.get(1) instanceof Rocher);
		assertTrue(theorie.get(2) instanceof Rocher);
		assertTrue(theorie.get(3) instanceof Rocher);
		assertTrue(theorie.get(4) instanceof Rocher);
		assertTrue(theorie.get(5) instanceof TerrainVide);

		theorie = maCarte.superVoisinage(29, 5);

		assertTrue(theorie.get(0) instanceof Rocher);
		assertTrue(theorie.get(1) instanceof TerrainVide);
		assertTrue(theorie.get(2) instanceof TerrainVide);
		assertTrue(theorie.get(3) instanceof TerrainVide);
		assertTrue(theorie.get(4) instanceof Rocher);
		assertTrue(theorie.get(5) instanceof Rocher);
	}

	/**
	 * On teste superVoisin. Rien de bien spécial à raconter. Du coup j'aurais
	 * dû me taire et ne pas taper cette phrase de merde, mais de loin ça fait
	 * tellement pro que j'ai pas résisté.
	 */
	@Test
	public void superVoisin() {
		Terrain theorie = new TerrainVide();
		theorie = maCarte.superVoisin(0, 0, Direction.BD);
		assertTrue(theorie instanceof Rocher);

		theorie = maCarte.superVoisin(0, 1, Direction.D);
		assertTrue(theorie instanceof Rocher);

		theorie = maCarte.superVoisin(29, 7, Direction.HG);
		assertTrue(theorie instanceof Rocher);
	}
}

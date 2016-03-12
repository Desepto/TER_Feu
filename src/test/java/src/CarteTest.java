/**
 *
 */
package src;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

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
						maCarte.getTabHexagones()[X][Y] = new TerrainVide(2, "Kloulita");
						continue;
					}
				}

				if (Y % 2 != 0) {
					if (X == 0) {
						maCarte.getTabHexagones()[X][Y] = new TerrainVide(1, "Kloulita");
						continue;
					}
				}

				maCarte.getTabHexagones()[X][Y] = new Rocher("Cailloux!");
			}
		}
		// Et maintenant on checke que tout est à la bonne place :

		// test contient un affichage du tableau de la carte. Juste pour tester
		// et afficher le tableau dans la console.

		String test = new String(""); // Décommenter le syso plus bas pour
										// afficher le tableau.
		for (int Y = 0; Y < 30; Y++) {
			for (int X = 0; X < 31; X++) {
				test += maCarte.getTabHexagones()[X][Y].nature;
				if (X == 30 && Y % 2 == 0) {
					if (maCarte.getTabHexagones()[X][Y].nature != "Kloulita")
						fail("Pas de kloulita là où il en faut ! AU BUCHER !");
				}

				else if (X == 0 && Y % 2 != 0) {
					if (maCarte.getTabHexagones()[X][Y].nature != "Kloulita")
						fail("Pas de kloulita là où il en faut ! AU BUCHER !");
				} else {
					if (maCarte.getTabHexagones()[X][Y].nature == "Kloulita")
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
	 * Test method for
	 * {@link src.Carte#transformeTerrain(src.Terrain, int, int)}.
	 */
	@Test
	public void testTransformeTerrain() {

	}

	/**
	 * Test method for {@link src.Carte#voisinage(int, int)}. ça rigole plus du
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
	 * Test method for {@link src.Carte#voisin(int, int, src.Direction)}. On
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
		System.out.println("C'est normal, bug volontaire pour les tests.");

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

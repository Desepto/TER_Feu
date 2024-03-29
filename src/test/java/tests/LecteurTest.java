/**
 *
 */
package tests;

import org.junit.Test;

import entreesSorties.Lecteur;
import main.Carte;
import terrains.Rocher;

/**
 * @author Nicolas Classe de test pour le lecteur.
 */
public class LecteurTest {

	/**
	 * On teste ici la méthode CarteEnDur qui affiche une carte avec des
	 * terrains au pif sans lire le fichier.
	 *
	 * Test method for {@link entreesSorties.Lecteur#carteEnDur()}.
	 */
	@Test
	public void testCarteEnDur() {

		Carte maCarte = Lecteur.carteEnDur(); // On remplit la carte ici.

		// Puis on l'affiche :

		@SuppressWarnings("unused")
		String test = new String(""); // Décommenter le syso plus bas pour
		// afficher le tableau.
		for (int Y = 0; Y < 30; Y++) {
			for (int X = 0; X < 31; X++) {
				if (maCarte.getTabHexagones()[X][Y] instanceof Rocher)
					test += "Cailloux !";
				else {
					test += "Vide";
				}
			}
			test += "\n";

		}
		// System.out.println(test); // A décommenter pour afficher le contenu
		// du tableau.

	}

}
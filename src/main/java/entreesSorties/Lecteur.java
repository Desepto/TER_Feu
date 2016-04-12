package entreesSorties;

import java.util.Random;

import main.Carte;
import terrains.CoupeFeu;
import terrains.Foret;
import terrains.Lac;
import terrains.Maison;
import terrains.Plaine;
import terrains.Prairie;
import terrains.Rocher;
import terrains.Route;
import terrains.TerrainVide;

/**
 * Classe qui lit le fichier de base et le transforme en une map
 *
 * @author Nicolas
 *
 */

public class Lecteur {

	/**
	 * Méthode statique pour générer une carte "en dur" sans passer par la
	 * lecture d'un fichier. Utile pour faire une carte de base avec des
	 * terrains aléatoires.
	 *
	 * @return la carte remplie.
	 */
	public static Carte carteEnDur() {

		Carte maCarte = new Carte(30);
		int nombre; // Nombre aléatoire qui contiendra le type de terrain.
		Random rand;
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

				rand = new Random();
				nombre = rand.nextInt(8); // Entre 0 et 7 car il y a 8 terrains
											// différents.

				switch (nombre) {
				case 0:
					maCarte.getTabHexagones()[X][Y] = new Route();
					break;
				case 1:
					maCarte.getTabHexagones()[X][Y] = new Rocher();
					break;
				case 2:
					maCarte.getTabHexagones()[X][Y] = new Prairie();
					break;
				case 3:
					maCarte.getTabHexagones()[X][Y] = new Plaine();
					break;
				case 4:
					maCarte.getTabHexagones()[X][Y] = new Maison();
					break;
				case 5:
					maCarte.getTabHexagones()[X][Y] = new Lac();
					break;
				case 6:
					maCarte.getTabHexagones()[X][Y] = new Foret();
					break;
				case 7:
					maCarte.getTabHexagones()[X][Y] = new CoupeFeu();
					break;
				default:
					maCarte.getTabHexagones()[X][Y] = new TerrainVide();
					break;
				}

			}
		}
		return maCarte;
	}
}
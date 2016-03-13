package src;

/**
 * Décrit l'apparition d'un acteur sur la carte. A compléter. Un évènement c'est
 * l'apparition d'un Acteur sur une case donnée.
 * 
 * @author Nicolas
 *
 */
public class Evenement {

	private int X, Y; // Là où l'acteur va apparaitre sur la map.
	Acteur sonActeur; // L'acteur en question.

	public Evenement(int x, int y) {
		X = x;
		Y = y;
	}

	public void declenche(Carte maCarte) {
		maCarte.getTabHexagones(X, Y).ajoutActeur(sonActeur);
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

}

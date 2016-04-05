package src;

/**
 * Classe mère de tous les acteurs Un bon acteur doit bien connaître son texte
 * pour bien jouer la comédie !
 *
 * @author Nicolas
 *
 */

public abstract class Acteur {

	protected int X, Y; // Ses coordonnées sur la carte.0

	/**
	 * Méthode abstraite redéfinie pour tous les acteurs. Pour chaque acteur de
	 * la carte, l'évolueur devra appeler sa méthode agi Pour un pompier, c'est
	 * une tentative d'extinction de feu. Pour un canadaire, le largage de son
	 * réservoir d'eau. Pour de la pluie, l'humidification de la case et ses
	 * voisins. Pour du feu, la carbonisation totale.
	 *
	 * @param maCarte
	 */
	public abstract void agi(Carte maCarte);

	/**
	 * El Constrouctivoush
	 *
	 * @param X
	 * @param Y
	 */
	public Acteur(int X, int Y) {
		this.X = X;
		this.Y = Y;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public void setX(int x) {
		X = x;
	}

	public void setY(int y) {
		Y = y;
	}

}

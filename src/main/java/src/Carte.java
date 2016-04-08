package src;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Carte contenant le tableau des terrains.
 *
 * @author Nicolas
 *
 */
public class Carte {

	private Terrain[][] tabHexagones;
	private final int tailleCarte; // Taille carrée, comme on l'a dit.
	private ArrayList<Acteur> sesActeurs; // Fonctions de manip tout en bas.
	private final Force forceVent;
	private final Direction directionVent;

	// El Constrouctador ! Ne pas toucher, utile pour les tests.
	public Carte(int tailleCarte) {
		this.tailleCarte = tailleCarte;
		this.tabHexagones = new Terrain[tailleCarte + 1][tailleCarte];
		sesActeurs = new ArrayList<Acteur>();
		forceVent = Force.faible;
		directionVent = Direction.BD;
	}

	// El Constrouctador !
	public Carte(int tailleCarte, Force forceVent, Direction directionVent) {
		this.tailleCarte = tailleCarte;
		this.tabHexagones = new Terrain[tailleCarte + 1][tailleCarte];
		sesActeurs = new ArrayList<Acteur>();
		this.forceVent = forceVent;
		this.directionVent = directionVent;
	}

	/**
	 * NE PAS UTILISER, utiliser getTerrain() à la place. Ne travaille pas avec
	 * les coordonnées abstraites. ACHTUNG Seul HER GENERAL peut utiliser ceci.
	 *
	 * @param x
	 * @param y
	 * @return Terrain
	 */
	public Terrain getTabHexagones(int x, int y) {
		return tabHexagones[x][y];
	}

	/**
	 * Retourne le terrain figurant aux coordonnées passées en paramètre.
	 *
	 * @param X
	 * @param Y
	 * @return Le terrain associé à la position X,Y
	 */
	public Terrain getTerrain(int X, int Y) {

		X = ElConvertador(X, Y).x;
		Y = ElConvertador(X, Y).y;

		return this.tabHexagones[X][Y];
	}

	/**
	 * Accesseur peu utile.
	 *
	 * @return Terrain[][]
	 */
	public Terrain[][] getTabHexagones() {
		return tabHexagones;
	}

	public void setMaCarte(Terrain[][] maCarte) {
		this.tabHexagones = maCarte;
	}

	public int getTailleCarte() {
		return tailleCarte;
	}

	/**
	 * Transforme un terrain de la carte en un autre, passé en paramètre.
	 *
	 * @param nouveau
	 *            Le nouveau terrain
	 * @param X
	 *            Les coordonnées du terrain à modifer.
	 * @param Y
	 */
	public void transformeTerrain(int X, int Y, Terrain nouveau) {
		if (X > 29 || Y > 29 || X < 0 || Y < 0) {
			System.out.println("Ce Terrain n'est même pas sur la carte, pff, tu veux transformer quoi ? Anouar ?");
			return;
		}
		X = ElConvertador(X, Y).x;
		Y = ElConvertador(X, Y).y;

		if (getTerrain(X, Y) instanceof TerrainVide) {
			System.out.println("Ce Terrain n'est même pas sur la carte, pff, tu veux transformer quoi ? Anouar ?");
			return;
		}
		this.tabHexagones[X][Y] = nouveau;
	}

	/**
	 * vérifie que le voisin choisi existe bien dans le tableau. Si ce n'est pas
	 * le cas, renvoie un TerrainVide. Permet de ne pas sortir du tableau
	 * (OutOfBounds Exception).
	 *
	 * @param X
	 * @param Y
	 * @return
	 */
	private Terrain ajoutListeVoisins(int X, int Y) {
		if (X < tailleCarte + 1 && Y < tailleCarte && X >= 0 && Y >= 0)
			return this.tabHexagones[X][Y];
		return new TerrainVide();
	}

	/**
	 * Fonction outil à ne pas utiliser. nécessaire Pour SuperVoisinageCoord().
	 * vérifie que le voisin choisi existe bien dans le tableau. Si ce n'est pas
	 * le cas, renvoie un TerrainVide. Permet de ne pas sortir du tableau
	 * (OutOfBounds Exception).
	 *
	 * @param X
	 * @param Y
	 * @return
	 */
	private Point ajoutListeVoisinsCoord(int X, int Y) {
		if (X < tailleCarte + 1 && Y < tailleCarte && X >= 0 && Y >= 0)
			return new Point(X, Y);
		return new Point(-1, -1);
	}

	/**
	 * Renvoie une liste chaînée contenant les 6 voisins d'une case donnée. On
	 * fait appel à convertador pour convertir les coordonnées en réelles pour
	 * qu'elles soient exploitables dans le tableau.
	 *
	 * @param X
	 * @param Y
	 * @return la liste de voisins
	 */
	public ArrayList<Terrain> voisinage(int X, int Y) {

		if (X > tailleCarte + 1 || Y > tailleCarte) {
			System.out.println("Mais quelle grosse cave, t'es sorti du tableau avec tes valeurs à la con !");
			return null;
		}

		if (tabHexagones[X][Y] instanceof TerrainVide) {
			System.out.println("Ce Terrain ne figure pas dans la carte. Au Bucheeeeer !!!");
			return null;
		} // Impossible d'afficher les voisins d'une case inexistante.

		ArrayList<Terrain> mesVoisins = new ArrayList<Terrain>();

		if (Y % 2 == 0) // Y pair
		{
			// On vérifie que le voisin est dans le tableau, et on l'ajoute. On
			// ajoute un Terrain vide sinon. On répete l'opération pour les 6
			// voisins.
			mesVoisins.add(ajoutListeVoisins(X, Y - 1));
			mesVoisins.add(ajoutListeVoisins(X + 1, Y - 1));
			mesVoisins.add(ajoutListeVoisins(X + 1, Y));
			mesVoisins.add(ajoutListeVoisins(X + 1, Y + 1));
			mesVoisins.add(ajoutListeVoisins(X, Y + 1));
			mesVoisins.add(ajoutListeVoisins(X - 1, Y));
		}

		else { // Y impair, même combat.

			mesVoisins.add(ajoutListeVoisins(X - 1, Y - 1));
			mesVoisins.add(ajoutListeVoisins(X, Y - 1));
			mesVoisins.add(ajoutListeVoisins(X + 1, Y));
			mesVoisins.add(ajoutListeVoisins(X, Y + 1));
			mesVoisins.add(ajoutListeVoisins(X - 1, Y + 1));
			mesVoisins.add(ajoutListeVoisins(X - 1, Y));
		}
		return mesVoisins;
	}

	/**
	 * Renvoie l'unique voisin d'une case donnée, avec une direction donnée. Cf
	 * classe Enum Direction.
	 *
	 * @param X
	 * @param Y
	 * @param maDirection
	 * @return
	 */
	public Terrain voisin(int X, int Y, Direction maDirection) {
		Terrain monTerrain = new TerrainVide();
		switch (maDirection) {
		case HG:
			monTerrain = voisinage(X, Y).get(0);
			break;
		case HD:
			monTerrain = voisinage(X, Y).get(1);
			break;
		case D:
			monTerrain = voisinage(X, Y).get(2);
			break;
		case BD:
			monTerrain = voisinage(X, Y).get(3);
			break;
		case BG:
			monTerrain = voisinage(X, Y).get(4);
			break;
		case G:
			monTerrain = voisinage(X, Y).get(5);
			break;
		}

		if (monTerrain instanceof TerrainVide) {
			System.out.println("Ce Terrain ne figure pas dans la carte. Au Bucheeeeer !!!");
		}
		return monTerrain;
	}

	/**
	 * Convertit des coordonnées abstraites en coordonnées réelles. Cf google
	 * Doc.
	 *
	 * @param X
	 * @param Y
	 * @return Un point contenant les nouvelles coordonnées.
	 */
	public Point ElConvertador(int X, int Y) {

		if (Y % 2 != 0) {
			X++;
		}
		return new Point(X, Y);
	}

	/**
	 * La VRAIE fonction de voisinage. Qui travaille avec des coordonnées
	 * abstraites, celle avec laquelle on travaillera pour le reste du
	 * programme.
	 *
	 * @param X
	 * @param Y
	 * @return la liste de voisins.
	 */
	public ArrayList<Terrain> superVoisinage(int X, int Y) {

		X = ElConvertador(X, Y).x;
		Y = ElConvertador(X, Y).y;

		return voisinage(X, Y);
	}

	/**
	 * La VRAIE fonction pour avoir un voisin à partir de coordonnées et d'une
	 * direction. Travaille avec des coordonnées réelles.
	 *
	 * @param X
	 * @param Y
	 * @param maDirection
	 * @return Un terrain qui est le voisin recherché.
	 */
	public Terrain superVoisin(int X, int Y, Direction maDirection) {
		X = ElConvertador(X, Y).x;
		Y = ElConvertador(X, Y).y;

		return voisin(X, Y, maDirection);
	}

	public ArrayList<Acteur> getSesActeurs() {
		return sesActeurs;
	}

	public void ajoutActeur(Acteur monActeur) {
		this.sesActeurs.add(monActeur);
	}

	public void supprActeur(int indice) {
		this.sesActeurs.remove(indice);
	}

	public Direction getDirectionVent() {
		return directionVent;
	}

	public Force getForceVent() {
		return forceVent;
	}

	/**
	 * Pareil que SuperVoisinage mais renvoie des coordonnées au lieu de
	 * Terrain.
	 *
	 * @param X
	 * @param Y
	 * @return La liste des 6 points correspondant aux coordonnées des voisins.
	 */
	public ArrayList<Point> superVoisinageCoord(int X, int Y) {

		if (X > tailleCarte + 1 || Y > tailleCarte) {
			System.out.println("Mais quelle grosse cave, t'es sorti du tableau avec tes valeurs à la con !");
			return null;
		}

		if (tabHexagones[X][Y] instanceof TerrainVide) {
			System.out.println("Ce Terrain ne figure pas dans la carte. Au Bucheeeeer !!!");
			return null;
		} // Impossible d'afficher les voisins d'une case inexistante.

		ArrayList<Point> mesVoisins = new ArrayList<Point>();

		if (Y % 2 == 0) // Y pair
		{
			// On vérifie que le voisin est dans le tableau, et on l'ajoute. On
			// ajoute un Terrain vide sinon. On répete l'opération pour les 6
			// voisins.
			mesVoisins.add(ajoutListeVoisinsCoord(X, Y - 1));
			mesVoisins.add(ajoutListeVoisinsCoord(X + 1, Y - 1));
			mesVoisins.add(ajoutListeVoisinsCoord(X + 1, Y));
			mesVoisins.add(ajoutListeVoisinsCoord(X + 1, Y + 1));
			mesVoisins.add(ajoutListeVoisinsCoord(X, Y + 1));
			mesVoisins.add(ajoutListeVoisinsCoord(X - 1, Y));
		}

		else { // Y impair, même combat.

			mesVoisins.add(ajoutListeVoisinsCoord(X - 1, Y - 1));
			mesVoisins.add(ajoutListeVoisinsCoord(X, Y - 1));
			mesVoisins.add(ajoutListeVoisinsCoord(X + 1, Y));
			mesVoisins.add(ajoutListeVoisinsCoord(X, Y + 1));
			mesVoisins.add(ajoutListeVoisinsCoord(X - 1, Y + 1));
			mesVoisins.add(ajoutListeVoisinsCoord(X - 1, Y));
		}
		return mesVoisins;
	}

	/**
	 * Enlève les Anouar de la carte.
	 *
	 * Ils font des recherches mais enfin quand même...
	 */
	public void purifieActeurs() {
		for (int i = 0; i < this.getSesActeurs().size(); i++) {
			if (this.sesActeurs.get(i) instanceof Anouar) {
				this.sesActeurs.remove(i);
				i--;
			}
		}
	}
}

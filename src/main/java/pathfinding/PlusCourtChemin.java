package pathfinding;

import java.awt.Point;
import java.util.ArrayList;

import main.Carte;
import terrains.Lac;
import acteurs.Acteur;
import acteurs.Canadair;
import acteurs.Feu;
import acteurs.Pompier;

public class PlusCourtChemin {

	/**
	 * Détermine le feu vers lequel se déplacer et renvoi la case permettant de
	 * s'en rapprocher le plus
	 * 
	 * @param c
	 *            La carte
	 * @param depart
	 *            La case de départ de l'acteur
	 * @param coutMax
	 *            La distance maximale que l'acteur peut parcourir(20 pour les
	 *            pompiers, 10 pour l'avion en notant que les couts pour l'avion
	 *            sont toujours de 1)
	 * @param aPied
	 *            Si l'acteur vole ou marche
	 * @return La case sur laquelle arrive l'acteur
	 */
	public static Point deplacement(Carte c, Acteur a) {

		Point depart = new Point(a.getX(), a.getY());
		int coutMax;
		boolean aPied;
		if (a instanceof Pompier) {
			coutMax = 20;
			aPied = true;
		} else {
			coutMax = 10;
			aPied = false;
		}
		// ClosedList = sommets déjà TOTALEMENT traités ou intraversables
		ArrayList<Node> closedList = new ArrayList<Node>();
		// OpenList = sommets déjà partiellement traités
		ArrayList<Node> openList = new ArrayList<Node>();
		openList.add(new Node(depart.x, depart.y, 0));
		Node n;
		int compteNombreTraiteTotal = 0;
		while (!openList.isEmpty()) {// tant que tous les sommets ne sont pas
										// traités

			n = openList.remove(0);// On récupère le haut de la liste
			for (Point voisin : c.voisinageCoord(n.x, n.y)) {
				// Pour chaque voisin de n

				if (voisin.x >= 0 && voisin.y >= 0
						&& voisin.x < c.getTailleCarte()
						&& voisin.y < c.getTailleCarte()) {
					Node v = new Node(voisin.x, voisin.y, +determineCout(n,
							voisin, c, aPied), n);
					if (!c.getTabHexagones(voisin.x, voisin.y).isTraversable()
							&& aPied) {
						// Si c'est une case intraversable et qu'on est à pied
						// on
						// l'ajoute direct à la closedList
						closedList.add(v);

					} else {
						if (contient(closedList, v) == 0) {
							// Si le noeud n'est pas dans la closedList(n'a pas
							// déjà
							// été totalement traité)
							int traite = contient(openList, v);
							if (traite == 1) {
								// Si le noeud est déjà dans l'openList (a déjà
								// été
								// traité mais on a trouvé mieux, on le
								// remplace)
								openList.get(recuperePositionNoeud(openList, v))
										.remplacer(v);
							}
							if (traite == 0) {
								// si on doit le rajouter, on le rajoute
								openList.add(v);
							}
						}
					}
				}
			}
			compteNombreTraiteTotal++;
			// Tous les voisins du sommet sont traités
			closedList.add(n);
		}
		// On regarde quel est la case enflammée la moins couteuse en
		// déplacements
		Node destination;
		if (a instanceof Canadair && !((Canadair) a).isEstCharge())
			destination = determineEauPlusProche(closedList, c);
		else
			destination = determineFeuPlusProche(closedList, c);
		if (destination != null) {
			// System.out.println("destination :" + destination.toString());
		}
		if (destination == null)
			return depart;
		// On renvoit la case la plus proche du feu où l'on veut aller
		return getPositionPlusAvancee(destination, depart, coutMax);
	}

	/**
	 * Récupère la position du lac le plus proche de l'acteur
	 * 
	 * @param list
	 * @param c
	 * @return
	 */
	private static Node determineEauPlusProche(ArrayList<Node> list, Carte c) {

		int min = Integer.MAX_VALUE;
		int positionMin = -1;
		Node n;
		for (int i = 0; i < list.size(); i++) {
			n = list.get(i);
			if (n.cout < min) {
				if (c.getTabHexagones(n.x, n.y) instanceof Lac) {
					min = n.cout;
					positionMin = i;
				}
			}
		}
		if (positionMin == -1)
			return null;
		return list.get(positionMin);
	}

	/**
	 * Renvoit la case enflammée la moins couteuse en déplacement
	 * 
	 * @param list
	 * @param c
	 * @return
	 */
	private static Node determineFeuPlusProche(ArrayList<Node> list, Carte c) {

		/*
		 * On cherche parmi tous les noeuds qui possède un acteur feu lequel a
		 * le plus petit cout et on le renvoit
		 */

		int min = Integer.MAX_VALUE;
		int positionMin = -1;
		Node n;
		for (int i = 0; i < list.size(); i++) {
			n = list.get(i);
			if (n.cout < min) {
				for (Acteur a : c.getSesActeurs(n.x, n.y))
					if (a instanceof Feu) {// on regarde si la case est en feu
						min = n.cout;
						positionMin = i;
					}
			}
		}
		if (positionMin == -1)
			return null;
		return list.get(positionMin);
	}

	public static int determineCout(Node u, Point voisin, Carte c, boolean aPied) {

		if (!aPied)
			return u.cout + 1;
		else
			return u.cout
					+ c.getTabHexagones(voisin.x, voisin.y)
							.getCoutDeplacement();
	}

	/**
	 * Renvoit la position dans la liste du noeud possédant les meme coordonnées
	 * que celui passé en argument (pas possible d'utiliser la fonction
	 * contains, leur couts/heuristiques parents sont différents)
	 * 
	 * @param openList
	 * @param v
	 * @return
	 */
	private static int recuperePositionNoeud(ArrayList<Node> openList, Node v) {
		for (int i = 0; i < openList.size(); i++) {
			if (v.x == openList.get(i).x && v.y == openList.get(i).y)
				return i;
		}
		throw new IllegalArgumentException(
				"Le noeud demandé DOIT etre present dans la liste fournie !");
	}

	/**
	 * Renvoit les coordonées du noeud le plus éloigné en respectant le coutMax.
	 * Si rien ne correspond, on renvoit le point de depart
	 * 
	 * @param n
	 * @param depart
	 * @param coutMax
	 * @return
	 */
	public static Point getPositionPlusAvancee(Node n, Point depart, int coutMax) {

		int nbExec = 0;
		// System.out.println("depart : " + depart.toString());
		while (n.x != depart.x || n.y != depart.y) {
			// System.out.println(nbExec);
			nbExec++;
			// System.out.println(n.toString());
			if (n.cout <= coutMax) {
				// System.out.println("cout " + n.cout + " coutMax" + coutMax);
				return new Point(n.x, n.y);
			}

			n = n.parent;
		}

		return depart;
	}

	/**
	 * Renvoit :
	 * <ul>
	 * <li>-1 si n est dans la liste avec une valeur plus petite</li>
	 * <li>0 si n n'est pas dans la liste</li>
	 * <li>1 si n est dans la la liste avec une valeur plus grande</li>
	 * </ul>
	 * 
	 * @param list
	 * @param n
	 * @return
	 */
	public static int contient(ArrayList<Node> list, Node n) {

		for (Node n2 : list)
			if (n.x == n2.x && n.y == n2.y) {
				if (n2.cout <= n.cout)
					return -1;
				else if (n2.cout > n.cout)
					return 1;
			}
		return 0;
	}

	public static int calculDistance(Point p1, Point p2) {

		return 0;
	}
}

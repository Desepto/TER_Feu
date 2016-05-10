package pathfinding;

import java.awt.Point;
import java.util.ArrayList;

import main.Carte;

public class AStar {

	public static boolean comparerNoeud(Node n1, Node n2) {
		if (n1.heuristique <= n2.heuristique)
			return true;
		else
			return false;
	}

	public static Point deplacement(Carte c, Point destination, Point depart,
			int coutMax) {

		ArrayList<Node> closedList = new ArrayList<Node>();
		ArrayList<Node> openList = new ArrayList<Node>();
		openList.add(new Node(depart.x, depart.y, 0, 0));
		Node n;
		while (!openList.isEmpty()) {
			n = openList.remove(0);
			if (n.x == destination.x && n.y == destination.y) {
				return destination;
			} else {
				for (Point voisin : c.superVoisinageCoord(n.x, n.y)) {

					// Création du noeud qu'on utilise
					int cout = 0;
					// int cout = n.cout + c.getTerrain(voisin.x,
					// voisin.y).cout;
					Node v = new Node(voisin.x, voisin.y, cout, cout
							+ calculDistance(voisin, destination));

					// Si son cout est supérieur au coutMax, pas la peine de
					// l'ajouter, on pourra pas y aller donc direct closedList
					if (v.cout > coutMax)
						closedList.add(v);
					// Vérifie si on a déjà traité le noeud avec une plus petite
					// distance
					else if (!contientEnPlusPetit(closedList, v)
							&& !contientEnPlusPetit(openList, v)) {
						// Si ce n'est pas le cas on l'ajoute au bon endroit
						// dans la liste
						int i = 0;
						boolean continuer = true;
						while (i < openList.size() && continuer) {
							if (comparerNoeud(v, openList.get(i))) {
								openList.add(i, v);
								continuer = false;
							}
							i++;
						}
					}

				}
			}
			closedList.add(n);
		}
		Node max = closedList.get(0);
		for (Node parcours : closedList) {
			if (parcours.heuristique < max.heuristique)
				max = parcours;
		}
		return new Point(max.x, max.y);
	}

	public static boolean contientEnPlusPetit(ArrayList<Node> list, Node n) {

		for (Node n2 : list)
			if (n.x == n2.x && n.y == n2.y && n2.cout < n.cout)
				return true;
		return false;
	}

	public static int calculDistance(Point p1, Point p2) {

		return 0;
	}
}

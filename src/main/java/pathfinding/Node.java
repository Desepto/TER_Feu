package pathfinding;

/**
 * Classe utilisée pour représenter un noeud (qui correspond à une case de la
 * carte)
 * 
 * @author Thomas
 * 
 */

public class Node {

	public int x, y, cout;
	public Node parent;

	public Node() {
		this.x = 0;
		this.y = 0;
		this.cout = 0;
	}

	public Node(int x, int y, int c) {
		this.x = x;
		this.y = y;
		this.cout = c;
	}

	public Node(int x, int y, int c, Node parent) {
		this.x = x;
		this.y = y;
		this.cout = c;
		this.parent = parent;
	}

	public String toString() {
		return "x : " + this.x + " y : " + this.y + " cout : " + this.cout;
	}

	/**
	 * Foncntion utilisée pour remplacer le cout et les parents, il s'agit d'une
	 * fonction d'update
	 * 
	 * @param v
	 */
	public void remplacer(Node v) {
		this.cout = v.cout;
		this.parent = v.parent;
	}
}

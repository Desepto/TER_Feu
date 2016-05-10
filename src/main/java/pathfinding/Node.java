package pathfinding;

public class Node {

	public int x, y, cout, heuristique;

	public Node(int x, int y, int c, int h) {
		this.x = x;
		this.y = y;
		this.cout = c;
		this.heuristique = h;
	}

}

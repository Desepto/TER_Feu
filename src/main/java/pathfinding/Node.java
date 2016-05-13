package pathfinding;

public class Node {

	public int x, y, cout;
	public Node parent;

	public Node(int x, int y, int c, Node parent) {
		this.x = x;
		this.y = y;
		this.cout = c;
		this.parent = parent;
	}

	public void remplacer(Node v) {
		this.cout = v.cout;
		this.parent = v.parent;
	}
}

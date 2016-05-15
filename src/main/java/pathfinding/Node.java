package pathfinding;

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

	public void remplacer(Node v) {
		this.cout = v.cout;
		this.parent = v.parent;
	}
}

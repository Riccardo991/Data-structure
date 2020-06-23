package tree;

public class Node {
	private Elem num;
	private Node left, right;

	public Node(Elem n) {
		num = n;
		left = null;
		right = null;
	}

	public Node(Elem x, Node l, Node r) {
		num = x;
		left = l;
		right = r;
	}

	public Elem getNum() {
		return num;
	}

	public void setNum(Elem x) {
		num = x;
	}

	public void setLeftChild(Node l) {
		left = l;
	}

	public void setRightChild(Node r) {
		right = r;
	}

	public Node getLeftChild() {
		return left;
	}

	public Node getRightChild() {
		return right;
	}

}

package tree;

import java.util.*;

public class Tree {
	private Node root;

	public Tree() {
		root = null;
	}

	 public Node getRoot (){
		return root;
	}

	public void setRoot (Node  a){
	root = a;
	} 
	
	
	/*
	 * create a random tree n is the number of node if b is true, the function
	 * create a tree without doubles numbers
	 */

	public Tree(int n, boolean b) {
		Random rand = new Random();
		int t = rand.nextInt(14) + 5;
		Elem x = new Elem(t);
		Node m = new Node(x);
		if (b == true) {
			root = NodeubleRand(m, n - 1);
		} else {
			root = myRand(m, n - 1);
		}
	}

	public Node myRand(Node root, int n) {
		Random rand = new Random();
		int r = rand.nextInt(26);
		if (n > 0) {
			Elem x = new Elem(r);
			myInsert(root, x);
			myRand(root, n - 1);
		}
		return root;
	}

	public Node NodeubleRand(Node root, int n) {
		Random rand = new Random();
		int r = rand.nextInt(26);
		if (n > 0) {
			Elem x = new Elem(r);
			if (!myExist(root, x)) {
				myInsert(root, x);
				NodeubleRand(root, n - 1);
			} else {
				NodeubleRand(root, n);
			}
		}
		return root;
	}

	// print the tree in preorder way
	public void printTree() throws TreeException {
		if (root == null) {
			throw new TreeException(" empty tree ");
		} else {
			myPrint(root);
			System.out.print(" ");
		}
	}

	public void myPrint(Node root) {
		if (root != null) {
			System.out.print("(");
			System.out.print(root.getNum());
			myPrint(root.getLeftChild());
			myPrint(root.getRightChild());
			System.out.print(")");
		}
	}

	// insert a new element in the tree
	public void Insert(Elem x) {
		if (root == null) {
			root = new Node(x);
		} else {
			myInsert(root, x);
		}
	}

	public void myInsert(Node root, Elem x) {
		if (root == null) {
			root = new Node(x);
		}
		if (x.compareTo(root.getNum()) < 0) {
			if (root.getLeftChild() == null) {
				root.setLeftChild(new Node(x));
			} else {
				myInsert(root.getLeftChild(), x);
			}
		} else {
			if (root.getRightChild() == null) {
				root.setRightChild(new Node(x));
			} else {
				myInsert(root.getRightChild(), x);
			}
		}
	}

	// verify if a element is in the tree
	public boolean Exist(Elem x) {
		boolean b = myExist(root, x);
		return b;
	}

	public boolean myExist(Node root, Elem x) {
		if (root == null) {
			return false;
		}
		Elem e = root.getNum();
		if (x.getVal() == e.getVal()) {
			return true;
		} else {
			return myExist(root.getLeftChild(), x) || myExist(root.getRightChild(), x);
		}
	}

	// remove the element x from the tree
	public void delete(Elem x) throws TreeException {
		if (myExist(root, x) == false) {
			throw new TreeException("the node is not in the tree");
		}
		if (x.compareTo(root.getNum()) == 0) {
			insertTree(root.getLeftChild(), root.getRightChild());
		}
		if (x.compareTo(root.getNum()) < 0) {
			myDelete(x, root, root.getLeftChild());
		} else {
			myDelete(x, root, root.getRightChild());
		}
	}

	public void myDelete(Elem x, Node father, Node cur) {
		if (x.compareTo(cur.getNum()) < 0) {
			myDelete(x, cur, cur.getLeftChild());
		} else {
			if (x.compareTo(cur.getNum()) > 0) {
				myDelete(x, cur, cur.getRightChild());
			} else {
				Node temp;
				if (cur.getRightChild() == null) {
					temp = cur.getLeftChild();
				} else {
					temp = cur.getLeftChild();
					insertTree(cur.getRightChild(), cur.getLeftChild());
				}
				if (father.getLeftChild() == cur) {
					father.setLeftChild(temp);
				} else {
					father.setRightChild(temp);
				}
			}
		}
	}

	public void insertTree(Node tree, Node add) {
		Node parent = null;
		while (tree != null) {
			parent = tree;
			tree = tree.getLeftChild();
		}
		if (parent != null) {
			parent.setLeftChild(add);
		}
	}

	// do inorder visit and print elements
	public ArrayList<Elem> visitInOrder() throws TreeException {
		if (root == null) {
			throw new TreeException("empty tree ");
		} else {
			ArrayList<Elem> l = new ArrayList<Elem>();
			l.add(root.getNum());
			l = inOrder(root, l);
			l.remove(0);
			return l;
		}
	}

	public ArrayList<Elem> inOrder(Node root, ArrayList<Elem> l) {
		if (root != null) {
			inOrder(root.getLeftChild(), l);
			Elem x = root.getNum();
			l.add(x);
			inOrder(root.getRightChild(), l);
		}
		return l;
	}

	// do preorder visit and print elements
	public ArrayList<Elem> visitPreOrder() throws TreeException {
		if (root == null) {
			throw new TreeException("empty tree ");
		} else {
			ArrayList<Elem> l = new ArrayList<Elem>();
			l.add(root.getNum());
			l = preOrder(root, l);
			l.remove(0);
			return l;
		}
	}

	public ArrayList<Elem> preOrder(Node root, ArrayList<Elem> l) {
		if (root != null) {
			Elem x = root.getNum();
			l.add(x);
			preOrder(root.getLeftChild(), l);
			preOrder(root.getRightChild(), l);
		}
		return l;
	}

	// do postorder visit and print elements
	public ArrayList<Elem> visitPostOrder() throws TreeException {
		if (root == null) {
			throw new TreeException("empty tree");
		} else {
			ArrayList<Elem> l = new ArrayList<Elem>();
			l.add(root.getNum());
			l = postOrder(root, l);
			l.remove(0);
			return l;
		}
	}

	public ArrayList<Elem> postOrder(Node root, ArrayList<Elem> l) {
		if (root != null) {
			postOrder(root.getLeftChild(), l);
			postOrder(root.getRightChild(), l);
			Elem x = root.getNum();
			l.add(x);
		}
		return l;
	}

}

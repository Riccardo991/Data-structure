package tree;

import java.util.*;

public class TreeExercises {

	
	// countNodes get the number of nodes in the tree
	public static int countNodes(Tree a) throws TreeException {
		Node root = a.getRoot();
		if (root == null) {
			throw new TreeException("empty tree");
		} else {
			return myCount(root);
		}
	}

	public static int myCount(Node root) {
		int n = 0;
		if (root == null) {
			n += 0;
		} else {
			n = n + 1 + myCount(root.getLeftChild()) + myCount(root.getRightChild());
		}
		return n;
	}

	
	// maxLevel get the max height of tree
	public static int maxLevel(Tree a) {
		Node root = a.getRoot();
		if (root == null) {
			return 0;
		} else {
			return myMax(root);
		}
	}

	public static int myMax(Node root) {
		int r = 0;
		int l = 0;
		if (root == null) {
			l += 0;
			r += 0;
		} else {
			l = l + 1 + myMax(root.getLeftChild());
			r = r + 1 + myMax(root.getRightChild());
		}
		return r > l ? r : l;
	}

	
	// minLevel get the min height of tree */
	public static int minLevel(Tree a) {
		Node root = a.getRoot();
		if (root == null) {
			return 0;
		} else {
			return myMin(root);
		}
	}

	public static int myMin(Node root) {
		int r = 0;
		int l = 0;
		if (root == null) {
			l += 0;
			r += 0;
		} else {
			l = l + 1 + myMax(root.getLeftChild());
			r = r + 1 + myMax(root.getRightChild());
		}
		return r < l ? r : l;
	}

	
	// verify if it is a balance tree
	public static boolean isBalance(Tree a) {
		Node root = a.getRoot();
		if (root == null) {
			return true;
		} else {
			int l = myMin(root);
			int m = myMax(root);
			return (m == l || m == l + 1 || m + 1 == l);
		}
	}

	
	// levelNode get the number's level where is the node
	public static int levelNode(Tree a, Elem x) {
		if (a.Exist(x)) {
			Node root = a.getRoot();
			return myLevel(root, x);
		}
		return -1;
	}

	public static int myLevel(Node root, Elem x) {
		int n = 0;
		if (root != null) {
			if (root.getNum().compareTo(x) == 0) {
				return n;
			}
			if (root.getNum().compareTo(x) < 0) {
				n = 1 + myLevel(root.getRightChild(), x);
			} else {
				n = 1 + myLevel(root.getLeftChild(), x);
			}
		}
		return n;
	}

	
	// balanceTree transform the tree in input in a balance tree and give it in
	// output
	public static Tree balanceTree(Tree t) {
		if (!isBalance(t)) {
			ArrayList<Elem> l = new ArrayList<Elem>();
			try {
				l = t.visitInOrder();
			} catch (Exception e) {
			}
			int k = l.size();
			Elem[] v = new Elem[k];
			for (int i = 0; i < k; i++) {
				v[i] = l.get(i);
			}
			Tree a = new Tree();
			a = myBalance(a, v, 0, (k - 1));
			return a;
		} else {
			return t;
		}
	}

	public static Tree myBalance(Tree a, Elem[] v, int s, int f) {
		if (s < f) {
			int r = (s + f) / 2;
			Elem e = v[r];
			a.Insert(e);

			myBalance(a, v, s, r);
			myBalance(a, v, (r + 1), f);
		}
		return a;
	}


	// nodeInLevel give the list of the node that are in the level selected in input
	public static ArrayList<Elem> nodeInLevel(Tree a, int l) throws TreeException {
		int mx = maxLevel(a);
		if (mx < l) {
			throw new TreeException("the level doesn't exist");
		}
		Node root = a.getRoot();
		ArrayList<Elem> ln = new ArrayList<Elem>();
		ln.add(root.getNum());
		ln = listLevel(root, ln, l);
		ln.remove(0);
		return ln;
	}

	public static ArrayList<Elem> listLevel(Node root, ArrayList<Elem> ln, int l) {
		if (root != null) {
			if (l == 1) {
				Elem x = root.getNum();
				ln.add(x);
			}
			listLevel(root.getLeftChild(), ln, l - 1);
			listLevel(root.getRightChild(), ln, l - 1);
		}
		return ln;
	}

	
	// sumPaths in each leafs of tree add a new node, in which the number is the
	// sumof path
	public static void sumPath(Tree a) {
		Node root = a.getRoot();
		if (root != null) {
			int s = 0;
			mysum(root, s);
		}
	}

	public static void mysum(Node root, int s) {
		if (root != null) {
			Elem x = root.getNum();
			s = s + x.getVal();
			mysum(root.getLeftChild(), s);
			mysum(root.getRightChild(), s);
			if (root.getLeftChild() == null && root.getRightChild() == null) {
				Elem f = new Elem(s);
				Node y = new Node(f, null, null);
				root.setLeftChild(y);
			}
		}
		return;
	}

	
	// union merges the 2 input trees in a new tree
	public static Tree union(Tree a, Tree b) {
		Node u = b.getRoot();
		Tree c = new Tree();
		c = myMerge(a, u);
		return c;
	}

	public static Tree myMerge(Tree a, Node b) {
		if (b != null) {
			Elem x = b.getNum();
			a.Insert(x);
			myMerge(a, b.getLeftChild());
			myMerge(a, b.getRightChild());
		}
		return a;
	}

	
	// isSubTree verify if tree b is a subtree of a
	public static boolean isSubTree(Tree a, Tree b) {
		Node ar = a.getRoot();
		Node br = b.getRoot();
		Elem e = br.getNum();
		if (a.Exist(e)) {
			return mySub(ar, br);
		} else {
			return false;
		}
	}

	public static boolean mySub(Node a, Node b) {
		boolean y = false;
		if (a != null) {
			Elem xa = a.getNum();
			Elem xb = b.getNum();
			if (xa.getVal() == xb.getVal()) {
				y = verify(a, b);
			} else {
				mySub(a.getLeftChild(), b);
				mySub(a.getRightChild(), b);
			}
		}
		return y;
	}

	public static boolean verify(Node a, Node b) {
		if (b != null) {
			Elem xa = a.getNum();
			Elem xb = b.getNum();
			if (xa.getVal() != xb.getVal()) {
				return false;
			}
			verify(a.getLeftChild(), b.getLeftChild());
			verify(a.getRightChild(), b.getRightChild());
		}
		return true;
	}

	
	// Ancestor get the element that is the commun Ancestor between 2 elements in
	// input
	public static Elem Ancestor(Tree a, Elem e1, Elem e2) throws TreeException {
		if ((a.Exist(e1) && a.Exist(e2)) == false) {
			throw new TreeException("one of 2 elements  isn't in the tree");
		}
		Node root = a.getRoot();
		return myAnce(root, e1, e2);
	}

	public static Elem myAnce(Node root, Elem e1, Elem e2) {
		Elem r = root.getNum();
		if (root != null) {
			if ((e1.getVal() <= r.getVal() && e2.getVal() > r.getVal())
					|| (e1.getVal() > r.getVal() && e2.getVal() <= r.getVal())) {
				return r;
			}
			if (e1.getVal() <= r.getVal() && e2.getVal() <= r.getVal()) {
				myAnce(root.getLeftChild(), e1, e2);
			}
			if (e1.getVal() > r.getVal() && e2.getVal() > r.getVal()) {
				myAnce(root.getRightChild(), e1, e2);
			}
		}
		return r;
	}

	
	// Parents gives the list of node that link the root with the input node
	public static ArrayList<Elem> Parents(Tree a, Elem x) {
		ArrayList<Elem> l = new ArrayList<Elem>();
		if (a.Exist(x)) {
			Node root = a.getRoot();

			l = myParents(root, x, l);
			l = controlList(l, x);
			return l;
		} else {
			Elem u = new Elem(-1);
			l.add(u);
			return l;
		}
	}

	public static ArrayList<Elem> myParents(Node root, Elem x, ArrayList<Elem> l) {

		if (root != null) {
			if (root.getNum().compareTo(x) == 0) {
				l.add(x);
				return l;
			}
			Tree t = new Tree();
			Node y = root.getLeftChild();
			t.setRoot(y);
			if (t.Exist(x)) {
				l.add(root.getNum());
				myParents(root.getLeftChild(), x, l);
			} else {
				l.add(root.getNum());
				myParents(root.getRightChild(), x, l);
			}
		}
		return l;
	}

	public static ArrayList<Elem> controlList(ArrayList<Elem> l, Elem x) {

		int n = l.size() - 1;
		Elem t = l.get(n);
		if (t.getVal() != x.getVal()) {
			while (n > 0) {
				Elem u = l.get(l.size() - 1);
				if (u.getVal() != x.getVal()) {
					l.remove(l.size() - 1);
					n--;
				} else {
					break;
				}
			}
		}
		return l;
	}

	public static void main(String[] arg) {
		Tree a = new Tree(10, true);

		try {
			a.printTree();
		} catch (Exception e) {
		}
		System.out.print("\n became  \n ");
		sumPath(a);
		try {
			a.printTree();
		} catch (Exception e) {
		} 

	}

}

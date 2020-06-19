package graph;

import java.util.*;

public class graphEsercises {

	/* implement the Breadth first search visit of graph */
	public static ArrayList<node> BFSVisit(graph g, node v) {
		ArrayList<node> white = g.nodesOfGraph();
		myRemove(white, v);
		ArrayList<node> gray = new ArrayList<node>();
		gray.add(v);
		ArrayList<node> black = new ArrayList<node>();

		while (!gray.isEmpty()) {
			node a = gray.get(0);
			ArrayList<edge> neighbors = new ArrayList<edge>();
			neighbors = g.edgesOfNode(a);
			int j = 0;
			if (neighbors != null) {
				while (j < neighbors.size()) {
					edge y = neighbors.get(j);
					node b = y.getEnd();
					if (white.contains(b)) {
						gray.add(b);
						myRemove(white, b);
					}
					j++;
				}
			}
			black.add(a);
			myRemove(gray, a);
		}
		return black;
	}

	public static void myRemove(ArrayList<node> l, node v) {
		for (int i = 0; i < l.size(); i++) {
			node x = l.get(i);
			if (x.isEquals(v)) {
				l.remove(i);
				break;
			}
		}
	}

	/* implement the Deep first search visit of graph */
	public static ArrayList<node> DFSVisit(graph g, node s) {
		ArrayList<node> white = g.nodesOfGraph();
		Stack<node> gray = new Stack<node>();
		ArrayList<node> black = new ArrayList<node>();
		myRemove(white, s);
		gray.push(s);
		while (!gray.isEmpty()) {
			node v = gray.pop();
			ArrayList<edge> neighbors = g.edgesOfNode(v);
			int i = 0;
			while (i < neighbors.size()) {
				edge e = neighbors.get(i);
				node u = e.getEnd();
				if (white.contains(u)) {
					gray.push(u);
					myRemove(white, u);
				}
				i++;
			}
			black.add(v);
		}
		return black;
	}

	/* implement the minimum spanning tree of a graph ( Prim version) */
	public static ArrayList<edge> MSTPrim(graph g, node s) {
		ArrayList<edge> edgeMST = new ArrayList<edge>();
		ArrayList<edge> edgeList = new ArrayList<edge>();
		ArrayList<node> nodeMST = new ArrayList<node>();
		int j = g.numberNodes();
		int c = 1;
		nodeMST.add(s);
		while (nodeMST.size() < j) {
			if (c == nodeMST.size()) {
				node v = nodeMST.get(0);
				ArrayList<edge> neighbors = g.edgesOfNode(v);
				if (edgeList == null) {
					edgeList = neighbors;
				} else {
					mergeTo(edgeList, neighbors);
				}
			}
			edge min = minEdge(edgeList);
			node u = min.getEnd();
			if (!nodeMST.contains(u)) {
				edgeMST.add(min);
				nodeMST.add(0, u);
				eRemove(edgeList, min);
				c = nodeMST.size();
			} else {
				eRemove(edgeList, min);
				c++;
			}
		}
		return edgeMST;
	}

	public static void mergeTo(ArrayList<edge> l1, ArrayList<edge> l2) {
		for (int i = 0; i < l2.size(); i++) {
			edge e = l2.get(i);
			l1.add(e);
		}
	}

	public static edge minEdge(ArrayList<edge> l) {
		edge min = l.get(0);
		for (int i = 1; i < l.size(); i++) {
			edge e = l.get(i);
			if (e.getWeight() < min.getWeight()) {
				min = e;
			}
		}
		return min;
	}

	public static ArrayList<edge> eRemove(ArrayList<edge> l, edge e) {
		for (int i = 0; i < l.size(); i++) {
			edge x = l.get(i);
			if (x.equalsEdge(e)) {
				l.remove(i);
				break;
			}
		}
		return l;
	}

	/* implement the MST of a graph (Kruscall version) */
	public static ArrayList<edge> MSTKruscall(graph g) {
		ArrayList<edge> edgeList = sortEdge(g);
		ArrayList<edge> edgeMST = new ArrayList<edge>();
		ArrayList<node> nodeList = new ArrayList<node>();
		int i = 0;
		int n = edgeList.size();
		while (i < n) {
			edge min = edgeList.get(i);
			node s = min.getStart();
			node e = min.getEnd();
			if (nodeList.contains(s) && nodeList.contains(e)) {
				i++;
			} else {
				edgeMST.add(min);
				if (!nodeList.contains(s)) {
					nodeList.add(s);
				}
				if (!nodeList.contains(e)) {
					nodeList.add(e);
				}
				i++;
			}
		}
		return edgeMST;
	}

	public static ArrayList<edge> sortEdge(graph g) {
		ArrayList<edge> totEdge = new ArrayList<edge>();
		ArrayList<node> l = g.nodesOfGraph();
		for (int i = 0; i < l.size(); i++) {
			node x = l.get(i);
			ArrayList<edge> neighbors = g.edgesOfNode(x);
			if (totEdge == null) {
				totEdge = neighbors;
			} else {
				mergeTo(totEdge, neighbors);
			}
		}

		ArrayList<edge> sortList = new ArrayList<edge>();
		for (int i = 0; i < totEdge.size() - 1; i++) {
			edge min = totEdge.get(i);
			for (int j = i + 1; j < totEdge.size(); j++) {
				edge y = totEdge.get(j);
				if (min.getWeight() > y.getWeight()) {
					min = y;
				}
			}
			sortList.add(min);
		}
		return sortList;
	}

	/* implement Dijkstra algorithm: find the minimum sum path */
	public static ArrayList<node> Dijkstra(graph g, node s) {
		ArrayList<node> nodeList = graphDjStart(g, s);
		myRemove(nodeList, s);
		s.setFather("start");
		s.setSum(0);
		ArrayList<node> djNode = new ArrayList<node>();
		djNode.add(s);

		while (!nodeList.isEmpty()) {
			node min = minDjNode(nodeList);
			if (min.getSum() == 300) {
				break;
			}
			djNode.add(min);
			myRemove(nodeList, min);
			nodeList = upDate(g, nodeList, min);
		}
		return djNode;
	}

	/* implement Dijkstra algorithm: find the minimum path between 2 nodes */
	public static ArrayList<node> DijkstraShortPath(graph g, node s, node f) {
		ArrayList<node> nodeList = graphDjStart(g, s);
		myRemove(nodeList, s);
		s.setFather("start");
		s.setSum(0);
		ArrayList<node> djNode = new ArrayList<node>();
		djNode.add(s);

		while (!nodeList.isEmpty()) {
			node min = minDjNode(nodeList);
			if (min.getSum() == 300) {
				break;
			}
			if (min.isEquals(f)) {
				djNode.add(min);
				break;
			}
			djNode.add(min);
			myRemove(nodeList, min);
			nodeList = upDate(g, nodeList, min);
		}
		return djNode;
	}

	public static ArrayList<node> graphDjStart(graph g, node s) {
		ArrayList<node> l = g.nodesOfGraph();
		ArrayList<node> d = new ArrayList<node>();
		ArrayList<edge> neighbors = g.edgesOfNode(s);
		for (int i = 0; i < l.size(); i++) {
			node x = l.get(i);
			if (x.isEquals(s)) {
				d.add(x);
			} else if (myContains(neighbors, x)) {
				for (int j = 0; j < neighbors.size(); j++) {
					edge y = neighbors.get(j);
					node u = y.getEnd();
					if (x.isEquals(u)) {
						x.setFather(s.getName());
						x.setSum(y.getWeight());
						d.add(x);
					}
				}
			} else {
				x.setSum(300);
				d.add(x);
			}
		}
		return d;
	}

	public static boolean myContains(ArrayList<edge> l, node x) {
		for (int i = 0; i < l.size(); i++) {
			edge y = l.get(i);
			node u = y.getEnd();
			if (x.isEquals(u)) {
				return true;
			}
		}
		return false;
	}

	public static node minDjNode(ArrayList<node> l) {
		node min = l.get(0);
		for (int i = 1; i < l.size(); i++) {
			node x = l.get(i);
			if (min.getSum() > x.getSum()) {
				min = x;
			}
		}
		return min;
	}

	public static ArrayList<node> upDate(graph g, ArrayList<node> l, node u) {
		ArrayList<edge> neighbors = g.edgesOfNode(u);
		ArrayList<node> newList = new ArrayList<node>();
		for (int i = 0; i < l.size(); i++) {
			node x = l.get(i);
			if (myContains(neighbors, x)) {
				for (int j = 0; j < neighbors.size(); j++) {
					edge y = neighbors.get(j);
					node f = y.getEnd();
					if (x.isEquals(f)) {
						int s = u.getSum() + y.getWeight();
						if (s < x.getSum()) {
							x.setFather(u.getName());
							x.setSum(s);
							newList.add(x);
						} else {
							newList.add(x);
						}
					}
				}
			} else {
				newList.add(x);
			}
		}
		return newList;
	}

	/* functions to print the results */
	public static void printVisitList(ArrayList<node> l) {
		for (int i = 0; i < l.size(); i++) {
			node v = l.get(i);
			System.out.print(v.getName() + ", ");
		}
	}

	public static void printMSTList(ArrayList<edge> l) {
		for (int i = 0; i < l.size(); i++) {
			edge y = l.get(i);
			node s = y.getStart();
			node e = y.getEnd();
			System.out.print("(" + s.getName() + " " + y.getWeight() + " " + e.getName() + ") ");
		}
	}

	public static void printDijkstraList(ArrayList<node> l) {
		int c = 0;
		for (int i = 0; i < l.size(); i++) {
			node x = l.get(i);
			c = c + x.getSum();
			System.out.print("(" + x.getName() + " " + x.getSum() + " " + x.getFather() + "), ");
		}
		System.out.print("\n" + "sum tot " + c);
	}

	public static void main(String[] arg) {
		System.out.print("the graph  " + "\n");
		graph g = new graph(5, 12, true);
		g.printGraph();
		ArrayList<node> l = g.nodesOfGraph();
		node s = l.get(0);
		ArrayList<node> z = Dijkstra(g, s);
		System.out.print("\n" + "Dijkstra path " + "\n");
		printDijkstraList(z);

	}
}

package graph;

import java.util.*;

public class graph {
	private ArrayList<node> nodes;
	private HashMap<node, ArrayList<edge>> edges;
	private int numN;
	private int numE;

	/*
	 * This class builds the graph. The object graph is made by nodes, edges and 2
	 * integer numN and numE. Nodes is the list of the node of graph. Edges is a
	 * hash map that link a node with its edges. numN is the total number of node in
	 * the graph. numE is the total number of edges in the graph.
	 */

	public graph() {
		nodes = new ArrayList<node>();
		edges = new HashMap<node, ArrayList<edge>>();
		numN = 0;
		numE = 0;
	}

	/*
	 * this builder builds automatically a graph. you put in input the number of
	 * nodes n"n" the number of edges "e" and a boolean "o". if "o" is true we get a
	 * oriented weight graph, else we get a not oriented weight graph.
	 */
	public graph(int n, int e, boolean o) {
		Random rand = new Random();
		int z = 97;
		nodes = new ArrayList<node>();
		edges = new HashMap<node, ArrayList<edge>>();

		for (int i = 0; i < n; i++) {
			char c = (char) (char) (z + i);
			String a = String.valueOf(c);
			node v = new node(a);
			nodes.add(v);
			ArrayList<edge> list = new ArrayList<edge>();
			edges.put(v, list);
		}

		int j = 0;
		while (j < e) {
			int x = rand.nextInt(n);
			int y = rand.nextInt(n);
			int w = rand.nextInt(9) + 1;
			if (x != y) {
				node s = nodes.get(x);
				node f = nodes.get(y);
				edge u1 = new edge(s, w, f);
				ArrayList<edge> u1List = new ArrayList<edge>();
				u1List = edges.get(s);
				if (noSimilar(u1List, u1)) {
					if (o) {
						u1List.add(u1);
						edges.put(s, u1List);
						j++;
					} else {
						u1List.add(u1);
						edges.put(s, u1List);
						edge u2 = new edge(f, w, s);
						ArrayList<edge> u2List = new ArrayList<edge>();
						u2List = edges.get(f);
						u2List.add(u2);
						edges.put(f, u2List);
						j++;
					}
				}
			}
		}
		numN = n;
		numE = e;
	}

	public boolean noSimilar(ArrayList<edge> l, edge u) {
		for (int i = 0; i < l.size(); i++) {
			edge x = l.get(i);
			if (u.getEnd().isEquals(x.getEnd())) {
				return false;
			}
		}
		return true;
	}

	/* verify if node "v" is in the graph */
	public boolean existNode(node v) {
		if (nodes.contains(v)) {
			return true;
		} else {
			return false;
		}
	}

	/* add a new node "v" in the graph */
	public void addNode(node v) {
		if (!nodes.contains(v)) {
			nodes.add(v);
			ArrayList<edge> neighbors = new ArrayList<edge>();
			edges.put(v, neighbors);
			numN++;
		}
	}

	/* delete the node v to the graph */
	public void removeNode(node v) throws graphException {
		if (!nodes.contains(v)) {
			throw new graphException("node don't exist");
		} else {
			int i = 0;
			while (i < nodes.size()) {
				node x = nodes.get(i);
				if (x.isEquals(v)) {
					break;
				}
				i++;
			}
			nodes.remove(i);
			edges.remove(v);
			numN--;
		}
	}

	/* verify if the edge "e" is in the graph */
	public boolean existEdge(edge e) {
		if (edges.containsKey(e.getStart())) {
			ArrayList<edge> neighbors = new ArrayList<edge>();
			neighbors = edges.get(e.getStart());
			int i = 0;
			while (i < neighbors.size()) {
				edge x = neighbors.get(i);
				if (x.equalsEdge(e)) {
					return true;
				}
				i++;
			}
		}
		return false;
	}

	/*
	 * add a new edge in the graph. if the nodes' edge aren't in the graph, we add
	 * the nodes and insert the edge
	 */
	public void addEdge(edge e) {
		if (!existEdge(e)) {
			node v1 = e.getStart();
			node v2 = e.getEnd();
			if (!nodes.contains(v1)) {
				addNode(v1);
			}
			if (!nodes.contains(v2)) {
				addNode(v2);
			}
			ArrayList<edge> neighbors = new ArrayList<edge>();
			neighbors = edges.get(v1);
			neighbors.add(e);
			edges.put(v1, neighbors);
			numE++;
		}
	}

	/* add a not oriented edge in the graph */
	public void addDoubleEdge(node v1, int w, node v2) {
		edge e1 = new edge(v1, w, v2);
		addEdge(e1);
		edge e2 = new edge(v2, w, v1);
		addEdge(e2);
	}

	/* delete a oriented edge to the graph */
	public void removeEdge(edge e) throws graphException {
		if (!existEdge(e)) {
			throw new graphException("don't exist edge");
		} else {
			node v = e.getStart();
			ArrayList<edge> neighbors = new ArrayList<edge>();
			neighbors = edges.get(v);
			int i = 0;
			while (i < neighbors.size()) {
				edge x = neighbors.get(i);
				if (x.equalsEdge(e)) {
					break;
				}
				i++;
			}
			neighbors.remove(i);
			edges.put(v, neighbors);
			numE--;
		}
	}

	/* delete a not oriented edge to the graph */
	public void removeDoubleEdge(edge e) {
		try {
			removeEdge(e);
		} catch (graphException a) {
		}
		;
		edge e1 = new edge(e.getEnd(), e.getWeight(), e.getStart());
		try {
			removeEdge(e1);
		} catch (graphException a) {
		}
		;
	}

	/* get the list of graph's nodes */
	public ArrayList<node> nodesOfGraph() {
		return nodes;
	}

	/* get the list of edges that start in node v */
	public ArrayList<edge> edgesOfNode(node v) {
		ArrayList<edge> neighbors = new ArrayList<edge>();
		neighbors = edges.get(v);
		return neighbors;
	}

	/* if exist a edge between v1 and v2 gives it in output, else rreturn null */
	public edge getEdge(node v1, node v2) {
		ArrayList<edge> neighbors = new ArrayList<edge>();
		neighbors = edges.get(v1);
		for (int i = 0; i < neighbors.size(); i++) {
			edge x = neighbors.get(i);
			node xs = x.getStart();
			node xe = x.getEnd();
			if (v1.isEquals(xs) && v2.isEquals(xe)) {
				return x;
			}
		}
		return null;
	}

	/* get the number of nodes in the graph */
	public int numberNodes() {
		return numN;
	}

	/* get the total nubers of edge in the graph */
	public int numberEdges() {
		return numE;
	}

	/* print the list of nodesof graph */
	public void printNodes() {
		int i = 0;
		while (i < nodes.size()) {
			node x = nodes.get(i);
			System.out.print(x.getName() + ", ");
			i++;
		}
	}

	/* print the graph */
	public void printGraph() {
		int i = 0;
		while (i < nodes.size()) {
			node x = nodes.get(i);
			System.out.print("node " + x.getName() + " its edges are:" + "\n");
			ArrayList<edge> neighbors = new ArrayList<edge>();
			neighbors = edges.get(x);
			for (int j = 0; j < neighbors.size(); j++) {
				edge y = neighbors.get(j);
				node s = y.getStart();
				node e = y.getEnd();
				int w = y.getWeight();
				System.out.print("(" + s.getName() + " " + w + " " + e.getName() + "), ");
			}
			System.out.print("\n");
			i++;
		}
	}

}

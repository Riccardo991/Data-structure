package graph;

public class edge {
	private node start, end;
	private int weight;

	/*
	 * This is the class about the edges of graph. The object edge is made by 2
	 * nodes and a int number. Node start is the node where start the edge. Node end
	 * is the node where arrive the edge. the number indicate the weight of edge.
	 */

	public edge() {
		start = null;
		end = null;
		weight = 0;
	}

	public edge(node a, node b) {
		start = a;
		end = b;
		weight = 1;
	}

	public edge(node a, int w, node b) {
		start = a;
		end = b;
		weight = w;
	}

	public node getStart() {
		return start;
	}

	public node getEnd() {
		return end;
	}

	public int getWeight() {
		return weight;
	}

	public void setStart(node a) {
		start = a;
	}

	public void setEnd(node b) {
		end = b;
	}

	public void setWeight(int w) {
		weight = w;
	}

	public boolean equalsEdge(edge x) {
		int k = 0;
		node as = this.start;
		node bs = x.getStart();
		node ae = this.end;
		node be = x.getEnd();
		int aw = this.weight;
		int bw = x.getWeight();
		if (as.isEquals(bs)) {
			k++;
		}
		if (ae.isEquals(be)) {
			k++;
		}
		if (aw == bw) {
			k++;
		}
		if (k != 3) {
			return false;
		} else {
			return true;
		}
	}

}

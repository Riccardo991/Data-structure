package graph;

public class node {
	private String name;
	private String color;
	private String father;
	private int sum;

	/*
	 * This is the class about the vertices of graph. The object node is made by 4
	 * attributes: String name identify it; string color that we use for the visit
	 * of graph. String Father and number sum we use for Dijkstra algorithm.
	 */

	public node() {
		name = "";
		color = "";
		father = "";
		sum = 0;
	}

	public node(String a) {
		name = a;
		color = "";
		father = "";
		sum = 0;
	}

	public node(String a, String b) {
		name = a;
		color = b;
		father = "";
		sum = 0;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public String getFather() {
		return father;
	}

	public int getSum() {
		return sum;
	}

	public void setName(String b) {
		name = b;
	}

	public void setColor(String c) {
		color = c;
	}

	public void setFather(String a) {
		father = a;
	}

	public void setSum(int n) {
		sum = n;
	}

	public boolean isEquals(node b) {
		return this.name.equals(b.name);
	}

}

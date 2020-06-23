package tree;

public class Elem implements Comparable<Elem> {
	private int val;

	public Elem() {
		val = -1;
	}

	public Elem(int n) {
		this.val = n;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int n) {
		this.val = n;
	}

	@Override
	public int compareTo(Elem x) {
		int z;
		if (this.val == x.val) {
			z = 0;
		}
		if (this.val < x.val) {
			z = -1;
		} else {
			z = 1;
		}
		return z;
	}

	@Override
	public String toString() {
		int t = this.val;
		String a = Integer.toString(t);
		return a;
	}

}

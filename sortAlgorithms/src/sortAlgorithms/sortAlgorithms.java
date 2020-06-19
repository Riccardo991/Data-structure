package sortAlgorithms;

import java.util.*;

public class sortAlgorithms {

	public static int[] makeVector(int dim) {
		Random rand = new Random();
		int[] v = new int[dim];
		for (int i = 0; i < dim; i++) {
			int r = rand.nextInt(30);
			v[i] = r;
		}
		return v;
	}

	public static void printVector(int[] v) {
		for (int i = 0; i < v.length; i++) {
			int n = v[i];
			System.out.print(n + ", ");
		}
	}

	public static void boubleSort(int[] v) {
		int l = v.length;
		for (int i = 0; i < l - 1; i++) {
			for (int j = i + 1; j < l; j++) {
				if (v[j] < v[i]) {
					int temp = v[i];
					v[i] = v[j];
					v[j] = temp;
				}
			}
		}
	}

	public static void minSort(int[] v) {
		int l = v.length;
		for (int i = 0; i < l; i++) {
			int min = v[i];
			int s = i;
			for (int j = i; j < l; j++) {
				if (v[j] < min) {
					min = v[j];
					s = j;
				}
			}
			int tmp = v[i];
			v[i] = min;
			v[s] = tmp;
		}
	}

	public static void insertSort(int[] v) {
		int l = v.length;
		for (int i = 1; i < l; i++) {
			while (i > 0 && v[i - 1] > v[i]) {
				int temp = v[i];
				v[i] = v[i - 1];
				v[i - 1] = temp;
				i--;
			}
		}
	}

	public static void mergeSort(int[] v) {
		int start = 0;
		int end = v.length - 1;
		Mergesort(v, start, end);
	}

	public static void Mergesort(int[] v, int start, int end) {
		if (start < end) {
			int m = (start + end) / 2;
			Mergesort(v, start, m);
			Mergesort(v, m + 1, end);
			merge(v, start, m, end);
		}
	}

	public static void merge(int[] v, int start, int m, int end) {
		int[] s = new int[end - start + 1];
		int h = start;
		int i = 0;
		int k = m + 1;
		while (h <= m && k <= end) {
			if (v[h] <= v[k]) {
				s[i] = v[h];
				h++;
			} else {
				s[i] = v[k];
				k++;
			}
			i++;
		}

		for (; k <= end; k++) {
			s[i] = v[k];
			i++;
		}
		for (; h <= m; h++) {
			s[i] = v[h];
			i++;
		}
		i = 0;
		for (int j = start; j <= end; j++) {
			v[j] = s[i];
			i++;
		}
	}

	public static void quickSort(int[] v) {
		int start = 0;
		int end = v.length - 1;
		Quicksort(v, start, end);
	}

	public static void Quicksort(int[] v, int start, int end) {
		if (start >= end) {
			return;
		} else {
			int j = partition(v, start, end);
			Quicksort(v, start, j - 1);
			Quicksort(v, j + 1, end);
		}
	}

	public static int partition(int[] v, int start, int end) {
		int pivot = v[start];
		int j = start;
		for (int i = start + 1; i <= end; i++) {
			if (v[i] <= pivot) {
				int k = j + 1;
				// swapv[i] v[k]
				int t1 = v[i];
				v[i] = v[k];
				v[k] = t1;
				// swap v[k] v[j]
				v[j] = v[k];
				v[k] = pivot;
				;
				j = k;
			}
		}
		return j;
	}

	public static void countingSort(int[] v) {
		int l = v.length;
		int max = v[0];
		int min = v[0];
		for (int i = 1; i < l; i++) {
			if (v[i] < min) {
				min = v[i];
			}
			if (v[i] > max) {
				max = v[i];
			}
		}
		int[] cv = new int[max - min + 1];
		for (int i = 0; i < l; i++) {
			int x = v[i];
			cv[x - min]++;
		}
		int j = 0;
		for (int i = 0; i < cv.length; i++) {
			int x = cv[i];
			while (x != 0) {
				v[j] = i + min;
				j++;
				x--;
			}
		}
	}

	public static void radixSort(int[] v) {
		int l = v.length;
		int max = v[0];
		for (int i = 1; i < l; i++) {
			if (max < v[i]) {
				max = v[i];
			}
		}

		for (int j = 1; max / j > 0; j = j * 10) {
			myCountsort(v, j, l);
		}
	}

	public static void myCountsort(int[] v, int exp, int l) {
		int[] sv = new int[l];
		int[] radix = new int[10];

		// put number in his radix element
		for (int i = 0; i < l; i++) {
			radix[(v[i] / exp) % 10]++;
		}
		// set indice for correct position of number
		for (int i = 1; i < 10; i++) {
			radix[i] = radix[i] + radix[i - 1];
		}
		// get array whit number order by exp cifra
		for (int i = l - 1; i >= 0; i--) {
			sv[radix[(v[i] / exp) % 10] - 1] = v[i];
			radix[(v[i] / exp) % 10]--;
		}
		// copi vs in original vector v
		for (int i = 0; i < l; i++) {
			v[i] = sv[i];
		}
	}

	public static void bucketSort(int[] v, int numBucket) {
		int n = v.length;
		// get the max number in the array
		int max = v[0];
		for (int i = 0; i < n; i++) {
			if (max < v[i]) {
				max = v[i];
			}
		}
		int delta = max / numBucket;
		// HashMap to link all buckets
		HashMap<Integer, ArrayList<Integer>> bucketList = new HashMap<Integer, ArrayList<Integer>>();

		// insert element in its bucket
		for (int i = 0; i < n; i++) {
			int x = v[i] / delta;
			if (bucketList.containsKey(x)) {
				ArrayList<Integer> bucket = bucketList.get(x);
				bucket.add(v[i]);
				bucketList.put(x, bucket);
			} else {
				ArrayList<Integer> bucket = new ArrayList<Integer>();
				bucket.add(v[i]);
				bucketList.put(x, bucket);
			}
		}
		// order elements in each bucket
		for (int i = 0; i <= numBucket; i++) {
			ArrayList<Integer> bucket = bucketList.get(i);
			bucket = myCountingSort(bucket, i, delta);
			bucketList.put(i, bucket);
		}
		// copy the order elements in the array
		int j = 0;
		for (int i = 0; i <= numBucket; i++) {
			ArrayList<Integer> bucket = bucketList.get(i);
			if (bucket != null) {
				int k = 0;
				while (k < bucket.size()) {
					int u = bucket.get(k);
					v[j] = u;
					j++;
					k++;
				}
			}
			i++;
		}
	}

	public static ArrayList<Integer> myCountingSort(ArrayList<Integer> l, int i, int delta) {
		if (l != null) {
			int n = delta * (i + 1);
			int dim = l.size();
			ArrayList<Integer> result = new ArrayList<Integer>();
			int[] vs = new int[n];
			for (int j = 0; j < dim; j++) {
				int u = l.get(j);
				vs[u]++;
			}
			int h = 0;
			for (int j = 0; j < n; j++) {
				while (vs[j] != 0) {
					result.add(h, j);
					h++;
					vs[j] = vs[j] - 1;
				}
			}
			return result;
		} else {
			return null;
		}
	}

	public static void main(String[] arg) {
		int[] v = makeVector(20);
		printVector(v);
		bucketSort(v, 6);
		System.out.print("\n" + "order" + "\n");
		printVector(v);
	}

}

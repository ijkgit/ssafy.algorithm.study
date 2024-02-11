import java.util.*;
import java.io.*;

public class Main_sangphil {
	static class Pair {int x; int y; public Pair(int x, int y) {this.x=x;this.y=y;}}

	static final int INF = Integer.MAX_VALUE;

	static int N, M, ans;
	static int[][] arr;
	static int[] open;
	static List<Pair> stores = new ArrayList<Pair>();
	static List<Pair> houses = new ArrayList<Pair>();

	public static void main (String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		open = new int[M];
		ans = INF;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					houses.add(new Pair(i, j));
				} else if (arr[i][j] == 2) {
					stores.add(new Pair(i, j));
				}
			}
		}

		traveling(0, 0);

		System.out.println(ans);
	}
	static void traveling(int index, int depth) {
		if (depth == M) {
			int total = 0;
			for (Pair house : houses) {
				int minimum = INF;
				for (int i : open) {
					minimum = Math.min(minimum, getDistance(house, stores.get(i)));
				}
				total += minimum;
			}
			ans = Math.min(ans, total);
			return;
		}
		if (index == stores.size()) {
			return;
		}

		open[depth] = index;
		traveling(index+1, depth+1);
		traveling(index+1, depth);
	}

	static int getDistance(Pair a, Pair b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
}

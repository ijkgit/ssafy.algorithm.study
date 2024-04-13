import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_semin {
	static int n, maps[][], goal, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		maps = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		goal = 5;
		int[] selected = new int[goal];
		dupCombi(0, 0, selected);

		System.out.println(ans);
	}

	// 0:up, 1:down, 2:left, 3:rignt
	private static void dupCombi(int idx, int k, int[] selected) {
		if (k == selected.length) {
			int[][] copy = makeCopy(maps);
			for (int od : selected) {
				switch (od) {
				case (0):
					copy = up(copy);
					break;
				case (1):
					copy = down(copy);
					break;
				case (2):
					copy = left(copy);
					break;
				case (3):
					copy = right(copy);
					break;
				}
			}
			ans = Math.max(getMax(copy), ans);
			return;
		}
		for (int i = 0; i < 4; i++) {
			selected[k] = i;
			dupCombi(i, k + 1, selected);
		}
	}

	private static int[][] right(int[][] maps) {
		int[][] copy = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = n - 1; j > 0; j--) {
				for (int k = j - 1; k >= 0; k--) {
					if (maps[i][k] == 0) {
						continue;
					}
					if (maps[i][j] == maps[i][k]) {
						maps[i][k] *= 2;
						maps[i][j] = 0;
						j = k;
					}
					break;
				}
			}

			int idx = n - 1;
			for (int j = n - 1; j >= 0; j--) {
				if (maps[i][j] != 0) {
					copy[i][idx] = maps[i][j];
					idx--;
				}
			}
		}
		return copy;
	}

	private static int[][] left(int[][] maps) {
		int[][] copy = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1; j++) {
				int cur = maps[i][j];
				for (int k = j + 1; k < n; k++) {
					if (maps[i][k] == 0) {
						continue;
					}
					if (cur == maps[i][k]) {
						maps[i][k] *= 2;
						maps[i][j] = 0;
						j = k;
					}
					break;
				}
			}
			int idx = 0;
			for (int j = 0; j < n; j++) {
				if (maps[i][j] != 0) {
					copy[i][idx] = maps[i][j];
					idx++;
				}
			}
		}
		return copy;
	}

	private static int[][] down(int[][] maps) {
		int[][] copy = new int[n][n];
		for (int j = 0; j < n; j++) {
			for (int i = n - 1; i > 0; i--) {
				int cur = maps[i][j];
				for (int k = i - 1; k >= 0; k--) {
					if (maps[k][j] == 0) {
						continue;
					}
					if (cur == maps[k][j]) {
						maps[k][j] = maps[k][j] * 2;
						maps[i][j] = 0;
						i = k;
					}
					break;
				}
			}
			int idx = n - 1;
			for (int i = n - 1; i >= 0; i--) {
				if (maps[i][j] != 0) {
					copy[idx][j] = maps[i][j];
					idx--;
				}
			}
		}
		return copy;
	}

	private static int[][] up(int[][] maps) {
		int[][] copy = new int[n][n];
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n - 1; i++) {
				int cur = maps[i][j];
				for (int k = i + 1; k < n; k++) {
					if (maps[k][j] == 0) {
						continue;
					}
					if (cur == maps[k][j]) {
						maps[k][j] = maps[k][j] * 2;
						maps[i][j] = 0;
						i = k;
					}
					break;
				}
			}
			int idx = 0;
			for (int i = 0; i < n; i++) {
				if (maps[i][j] != 0) {
					copy[idx][j] = maps[i][j];
					idx++;
				}
			}
		}
		return copy;
	}

	private static int[][] makeCopy(int[][] maps) {
		int[][] copy = new int[n][n];
		for (int i = 0; i < maps.length; i++) {
			copy[i] = maps[i].clone();
		}
		return copy;
	}

	private static int getMax(int[][] copy) {
		int m = 0;
		for (int i = 0; i < copy.length; i++) {
			for (int j = 0; j < copy[0].length; j++) {
				m = Math.max(copy[i][j], m);
			}
		}
		return m;
	}
}

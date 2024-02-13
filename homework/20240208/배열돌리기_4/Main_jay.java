import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private int n, m, k;
	private int[][] graph;
	private ArrayList<Operation> operationList;
	private int ans;

	public static void main(String[] args) throws IOException {
		new Main().io();
	}

	private void io() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		graph = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		operationList = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			operationList.add(new Operation(r, c, s));
		}
		
		ans = Integer.MAX_VALUE;
//		getResult(graph);
		sol();
		
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void sol() {
		permutation(0, 0, new int[operationList.size()]);
	}

	private void permutation(int k, int v, int[] sel) {
		if (k == sel.length) {
			slide(sel);
			return;
		}

		for (int i = 0; i < sel.length; i++) {
			if ((v & (1 << i)) == 0) {
				sel[k] = i;
				permutation(k + 1, v | 1 << i, sel);
			}
		}
	}

	private void slide(int[] sel) {
		int[][] copy = copy();
		for(int i : sel) {
			Operation o = operationList.get(i);
			for (int s = 1; s <= o.s; s++) {
				int r1 = o.r - s;
				int r2 = o.r + s;
				int c1 = o.c - s;
				int c2 = o.c + s;

				int upTmp = copy[r1][c2];
				for (int y = c2; y > c1; y--) {
					copy[r1][y] = copy[r1][y-1];
				}
				
				int rightTmp = copy[r2][c2];
				for (int x = r2; x > r1; x--) {
					copy[x][c2] = copy[x-1][c2];
				}
				copy[r1+1][c2] = upTmp;
				
				int leftTmp = copy[r2][c1];
				for (int y = c1; y < c2; y++) {
					copy[r2][y] = copy[r2][y+1];
				}
				copy[r2][c2-1] = rightTmp;
				
				for (int x = r1; x < r2; x++) {
					copy[x][c1] = copy[x+1][c1];
				}
				copy[r2-1][c1] = leftTmp;
			}
		}
		getResult(copy);
	}
	
	private void getResult(int[][] copy) {
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = 0; j < m; j++) {
				sum += copy[i][j];
			}
			ans = Math.min(ans, sum);
		}
	}

	private int[][] copy() {
		int[][] copy = new int[n][m];
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[0].length; j++) {
				copy[i][j] = graph[i][j];
			}
		}
		return copy;
	}

	class Operation {
		int r, c, s;

		public Operation(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
}

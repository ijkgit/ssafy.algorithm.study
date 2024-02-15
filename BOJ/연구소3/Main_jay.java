import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private int[][] graph;
	private ArrayList<Virus> virusList;
	private int m, ans = Integer.MAX_VALUE, CNT = 0;
	private static final int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	private int[] sel;
	private boolean[][] visited;

	public static void main(String[] args) throws IOException {
		new Main().sol();
	}

	class Virus {
		public Virus(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

		int x, y, time;
	}

	private void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n][n];
		virusList = new ArrayList<>();
		for (int i = 0; i < graph.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < graph.length; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] == 2) {
					virusList.add(new Virus(i, j, 0));
				} else if (graph[i][j] == 0) {
					CNT++;
				}
			}
		}

		getAns();

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void getAns() {
		if (CNT != 0) {
			sel = new int[m];
			permutation(0, 0);
			ans = ans == Integer.MAX_VALUE ? -1 : ans;
		} else {
			ans = 0;
		}
	}

	private void permutation(int k, int v) {
		if (k == m) {
			int res = bfs();
			res = res == -1 ? Integer.MAX_VALUE : res;
			ans = Math.min(ans, res);
			return;
		}

		for (int i = 0; i < virusList.size(); i++) {
			if ((v & (1 << i)) == 0) {
				sel[k] = i;
				permutation(k + 1, v |= 1 << i);
			}
		}
	}

	private int bfs() {
		Queue<Virus> queue = new ArrayDeque<>();
		visited = new boolean[graph.length][graph.length];
		int cnt = CNT;

		for (int s : sel) {
			queue.offer(virusList.get(s));
			visited[virusList.get(s).x][virusList.get(s).y] = true;
		}

		while (!queue.isEmpty()) {
			Virus v = queue.poll();

			if (v.time+1 >= ans)
				return -1;

			for (int d = 0; d < direction.length; d++) {
				int nx = v.x + direction[d][0];
				int ny = v.y + direction[d][1];

				if (checkStatus(nx, ny)) {
					if (graph[nx][ny] == 0)
						cnt--;
					visited[nx][ny] = true;
					queue.offer(new Virus(nx, ny, v.time + 1));

					if (cnt == 0)
						return v.time + 1;
				}
			}
		}

		return -1;
	}

	private boolean checkStatus(int nx, int ny) {
		return 0 <= nx && nx < graph.length && 0 <= ny && ny < graph.length && graph[nx][ny] != 1 && !visited[nx][ny];
	}
}

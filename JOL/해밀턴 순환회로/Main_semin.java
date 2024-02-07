import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_semin {
	static boolean[] visited;
	static int N, res;
	static int[][] costs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		res = Integer.MAX_VALUE;
		N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());

		visited = new boolean[N];
		costs = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				costs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited[0] = true;
		delivery(0, 0, 0);
		System.out.println(res);
	}

	private static void delivery(int start, int depth, int cost) {
		if (cost > res) {
			return;
		}
		if (depth == N - 1) {
			if (costs[start][0] == 0) {
				return;
			}
			res = Math.min(res, cost + costs[start][0]); // start 에서 시작해서 다시 0까지
			return;
		}
		for (int i = 1; i < N; i++) {
			if (i == start || visited[i]) {
				continue;
			}
			if (costs[start][i] == 0) {
				continue;
			}
			visited[i] = true;
			delivery(i, depth + 1, cost + costs[start][i]);
			visited[i] = false;
		}
	}
}
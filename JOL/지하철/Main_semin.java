import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_semin {
	static int N, M, res;
	static boolean[] visited;
	static int[] selected, answer;
	static int[][] maps;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maps = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		res = Integer.MAX_VALUE;
		selected = new int[N];
		visited = new boolean[N + 1];

		selected[0] = 1; // k=0에 1
		visited[1] = true; // 1은 방문
		permutation(1, 1, 0);
		sb.append(res).append("\n");
		for (int i = 0; i < N; i++) {
			if (answer[i] == 0) {
				break;
			}
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);
	}

	public static void permutation(int idx, int k, int len) {
		if (selected[k - 1] == M) {
			if (res > len) {
				res = len;
				answer = selected.clone();
			}
			return;
		}
		if (len > res) {
			return;
		}
		if (idx == N) {
			return;
		}
		for (int i = 2; i <= N; i++) {
			if (!visited[i] && selected[k - 1] != i) {
				visited[i] = true;
				selected[k] = i;
				permutation(idx + 1, k + 1, len + maps[selected[k - 1]][i]);
				selected[k] = 0;
				visited[i] = false;
			}
		}
	}
}

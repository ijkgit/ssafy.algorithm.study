import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//dfs, bfs도 풀었는데 안올릴게용
public class Solution_semin {
	static int set[], N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		// 입력 받으면서 union 연산 하고,
		// 끝나면 다 돌면서 공통된 루트 개수 세기
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			makeSet(N);
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
				// System.out.println(Arrays.toString(set));
			}

			Set<Integer> dis = new HashSet<>();
			for (int i = 1; i <= N; i++) {
				dis.add(set[i]);
			}
			sb.append(dis.size()).append("\n");
		}
		System.out.println(sb);
	}

	static void makeSet(int n) {
		set = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			set[i] = i;
		}
	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot != bRoot) {
			for (int i = 1; i <= N; i++) {
				if (set[i] == aRoot) {
					set[i] = bRoot;
				}
			}
		}
	}

	static int find(int a) {
		if (a == set[a]) {
			return a;
		}
		return set[a] = find(set[a]);
	}
}

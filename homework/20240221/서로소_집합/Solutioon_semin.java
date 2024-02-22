import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_semin {
	static int n, m, res, set[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			res = 0;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			set = new int[n + 1];
			for (int i = 1; i < set.length; i++) {
				set[i] = i;
			}
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int calc = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (calc == 0) {
					union(a, b);
				} else {
					if (find(a) == find(b)) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int find(int a) {
		if (set[a] == a) {
			return a;
		}
		return set[a] = find(set[a]);
	}

	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot != bRoot) {
			set[aRoot] = bRoot;
		}
	}

}
